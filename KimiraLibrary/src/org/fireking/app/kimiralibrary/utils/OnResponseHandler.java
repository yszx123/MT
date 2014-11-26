package org.fireking.app.kimiralibrary.utils;

public interface OnResponseHandler {

	void getResult(String content);

	void error(String errors);
}
