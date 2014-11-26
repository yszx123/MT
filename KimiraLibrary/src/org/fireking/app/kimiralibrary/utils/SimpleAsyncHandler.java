package org.fireking.app.kimiralibrary.utils;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * 网络请求工具类<br />
 * 附带加载dialog效果
 * 
 * @author join
 *
 */
public class SimpleAsyncHandler extends AsyncHttpResponseHandler {
	OnResponseHandler handler;

	public SimpleAsyncHandler(OnResponseHandler handler) {
		this.handler = handler;
	}

	@Override
	public void onFinish() {
		super.onFinish();
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
		handler.error(new String(arg2));
	}

	@Override
	public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
		handler.getResult(new String(arg2));
	}

}
