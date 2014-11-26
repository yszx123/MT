package org.fireking.app.kimiralibrary.base;

import java.util.List;

import com.google.inject.Inject;

import roboguice.activity.RoboActionBarActivity;
import roboguice.context.event.OnCreateEvent;
import roboguice.event.Observes;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;

/**
 * activity的基类<br />
 * 完成activity的功能的初始化操作
 * 
 * @author join
 *
 */
public abstract class BaseActivity extends RoboActionBarActivity {

	/**
	 * 路由器<br />
	 * 普通跳转操作
	 * 
	 * @param aty
	 *            元activity
	 * @param clazz
	 *            目标对象
	 */
	protected void router(Activity aty, Class<? extends Activity> clazz) {
		Intent intent = new Intent(aty, clazz);
		startActivity(intent);
	}

	/**
	 * 路由器<br />
	 * 进行普通跳转操作
	 * 
	 * @param aty
	 *            元activity
	 * @param clazz
	 *            目标对象
	 * @param requestCode
	 *            请求编号
	 */
	protected void router(Activity aty, Class<? extends Activity> clazz,
			int requestCode) {
		Intent intent = new Intent(aty, clazz);
		startActivityForResult(intent, requestCode);
	}

	/**
	 * 在oncreate之后执行
	 * 
	 * @param onCreate
	 */
	protected void doSomethingsOnCreate(@Observes OnCreateEvent onCreate) {
	}

	/**
	 * 获取fragment的事务对象
	 * 
	 * @return
	 */
	protected FragmentTransaction getFragmentTransaction() {
		return getSupportFragmentManager().beginTransaction();
	}

	/**
	 * 显示activity中的某个fragment
	 */
	protected void showFragment(int index) {
		FragmentTransaction ftx = getFragmentTransaction();
		List<Fragment> fragments = getSupportFragmentManager().getFragments();
		Fragment f = fragments.get(index);
		ftx.show(f).commit();
	}

	/**
	 * 隐藏activity中的所有fragment
	 */
	protected void hideFragment() {
		FragmentTransaction ftx = getFragmentTransaction();
		List<Fragment> fragments = getSupportFragmentManager().getFragments();
		for (Fragment f : fragments) {
			ftx.hide(f);
		}
		ftx.commit();
	}

	/**
	 * 添加fragmnet到activity中去
	 */
	protected void attachFragments(int resId, Fragment[] fragments) {
		FragmentTransaction ftx = getFragmentTransaction();
		for (Fragment f : fragments) {
			ftx.add(resId, f);
		}
		ftx.commit();
	}
}
