package org.fireking.app.mt.action.host;

import org.fireking.app.kimiralibrary.base.BaseActivity;
import org.fireking.app.mt.R;
import org.fireking.app.mt.action.host.fragment.BusFragment;
import org.fireking.app.mt.action.host.fragment.GroupFragment;
import org.fireking.app.mt.action.host.fragment.MoreFragment;
import org.fireking.app.mt.action.host.fragment.OwnerFragment;

import roboguice.context.event.OnCreateEvent;
import roboguice.event.Observes;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.support.v4.app.Fragment;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

/**
 * 主框架activity界面<br />
 * 提供底部切换按钮
 * 
 * @author join
 *
 */
@ContentView(R.layout.activity_host)
public class HostActivity extends BaseActivity {

	@InjectView(R.id.host_radiogroup)
	RadioGroup hostRadioGroup;// 底部按钮组
	private Fragment[] fragments = new Fragment[] { new GroupFragment(),
			new BusFragment(), new OwnerFragment(), new MoreFragment() };

	@Override
	protected void doSomethingsOnCreate(@Observes OnCreateEvent onCreate) {
		// 添加所有的fragment到activity中
		attachFragments(R.id.content, fragments);
		// 监听radiobutton的变化，以实现fragment的动态切换
		hostRadioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
	}

	/**
	 * 切换fragment<br />
	 * 
	 */
	OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			int count = group.getChildCount();
			hideFragment();
			for (int i = 0; i < count; i++) {
				RadioButton button = (RadioButton) group.getChildAt(i);
				if (button.isChecked()) {
					showFragment(i);
				}
			}
		}
	};

}
