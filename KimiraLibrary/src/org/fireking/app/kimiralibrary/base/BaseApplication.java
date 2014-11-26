package org.fireking.app.kimiralibrary.base;

import roboguice.RoboGuice;
import android.app.Application;

/**
 * application基类，进行初始化操作
 * 
 * @author join
 *
 */
public class BaseApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		RoboGuice.setUseAnnotationDatabases(false);
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
	}
}
