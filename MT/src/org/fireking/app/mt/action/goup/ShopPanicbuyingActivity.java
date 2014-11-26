package org.fireking.app.mt.action.goup;

import java.util.Calendar;
import java.util.List;

import org.fireking.app.kimiralibrary.base.BaseActivity;
import org.fireking.app.kimiralibrary.utils.ViewHolder;
import org.fireking.app.mt.R;
import org.fireking.app.mt.model.ShopPanicListEntity;
import org.fireking.app.mt.service.grab_webpage.GroupGrab;

import com.squareup.picasso.Picasso;

import roboguice.context.event.OnCreateEvent;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 名店抢购页面
 * 
 * @author join
 *
 */
@ContentView(R.layout.group_panic)
public class ShopPanicbuyingActivity extends BaseActivity implements
		OnClickListener {
	/***************************************/
	// 名店抢购倒计时（这里直接显示本地时间了，相当于时钟功能）
	/***************************************/
	@InjectView(R.id.hour)
	private TextView hour;// 小时
	@InjectView(R.id.minues)
	private TextView minues;// 分钟
	@InjectView(R.id.secound)
	private TextView secound;// 秒
	@InjectView(R.id.panic_list)
	private ListView panic_list;
	PanicBuyingAdapter adapter;
	@InjectView(R.id.loading_layout)
	RelativeLayout loading_layout;// 加载内容页面

	// 获取数据
	static final int GET_PANIC_LIST_SUC = 0x1001;
	static final int GET_PANIC_LIST_ERR = 0x2001;

	// 抢购提醒
	@InjectView(R.id.panic_tip)
	private TextView panic_tip;

	@Override
	protected void doSomethingsOnCreate(OnCreateEvent onCreate) {
		super.doSomethingsOnCreate(onCreate);
		adapter = new PanicBuyingAdapter(this);
		panic_list.setAdapter(adapter);
		loading_layout.setVisibility(View.VISIBLE);
		// 显示名前抢购倒计时
		showDowntimes();
		// 抓取名店抢购列表内容
		getPanicList();
		panic_tip.setOnClickListener(this);
	}

	/**
	 * 获取名店抢购列表
	 */
	private void getPanicList() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					List<ShopPanicListEntity> entitys = GroupGrab
							.getPanicList();
					Message msg = handler.obtainMessage();
					if (entitys == null || entitys.size() == 0) {
						msg.what = GET_PANIC_LIST_ERR;
					} else {
						msg.what = GET_PANIC_LIST_SUC;
						msg.obj = entitys;
					}
					msg.sendToTarget();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case GET_PANIC_LIST_SUC:
				loading_layout.setVisibility(View.GONE);
				List<ShopPanicListEntity> entitys = (List<ShopPanicListEntity>) msg.obj;
				if (entitys == null || entitys.size() == 0) {
					return;
				}
				adapter.setData(entitys);
				break;
			case GET_PANIC_LIST_ERR:
				loading_layout.setVisibility(View.GONE);
				break;
			default:
				break;
			}
		};
	};

	/**
	 * 名前抢购倒计时模拟
	 */
	private void showDowntimes() {
		CountDownTimer timer = new CountDownTimer(24 * 60 * 1000 * 60, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.SECOND, 1);// 时间增加1秒
				hour.setText(calendar.get(Calendar.HOUR_OF_DAY) <= 9 ? "0"
						+ calendar.get(Calendar.HOUR_OF_DAY) : calendar
						.get(Calendar.HOUR_OF_DAY) + "");// 设置小时
				minues.setText(calendar.get(Calendar.MINUTE) <= 9 ? "0"
						+ calendar.get(Calendar.MINUTE) : calendar
						.get(Calendar.MINUTE) + "");// 设置分钟
				secound.setText(calendar.get(Calendar.SECOND) <= 9 ? "0"
						+ calendar.get(Calendar.SECOND) : calendar
						.get(Calendar.SECOND) + "");// 设置秒
			}

			@Override
			public void onFinish() {
				this.start();
			}
		};
		timer.start();
	}

	/**
	 * 名店抢购列表适配器
	 * 
	 * @author join
	 *
	 */
	class PanicBuyingAdapter extends BaseAdapter {
		LayoutInflater inflater;
		List<ShopPanicListEntity> entitys;
		Context context;

		public PanicBuyingAdapter(Context context) {
			this.context = context;
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		public void setData(List<ShopPanicListEntity> entitys) {
			this.entitys = entitys;
		}

		@Override
		public int getCount() {
			return entitys == null || entitys.size() == 0 ? 0 : entitys.size();
		}

		@Override
		public Object getItem(int position) {
			return entitys == null || entitys.size() == 0 ? null : entitys
					.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = inflater
						.inflate(R.layout.item_panic_buying, null);
			}

			ShopPanicListEntity entity = (ShopPanicListEntity) getItem(position);
			TextView origin_price = ViewHolder.get(convertView,
					R.id.origin_price);
			origin_price.setText(entity.getOrigin_price());
			TextView panic_price = ViewHolder
					.get(convertView, R.id.panic_price);
			panic_price.setText(entity.getPanic_price());
			TextView title = ViewHolder.get(convertView, R.id.title);
			title.setText(entity.getTitle());
			TextView description = ViewHolder
					.get(convertView, R.id.description);
			description.setText(entity.getDescription());
			ImageView panic_photo = ViewHolder.get(convertView,
					R.id.panic_photo);
			Picasso.with(context).load(entity.getImage()).into(panic_photo);
			return convertView;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.panic_tip:
			
			break;

		default:
			break;
		}
	}
}
