package com.moving.kotlin.frame.utils;

import android.os.Handler;
import android.os.Looper;

/**
 * 模块：监听网络状态的变化
 * 功能：用于监听和注销监听网络状态的变化
 */
public class ThreadUtils {

	/**
	 * 将代码运行在子线程
	 * @param r Runnable接口的实现，里面是运行在子线程的代码
	 */
	public static void runBackThread(Runnable r) {
		new Thread(r).start();
	}

	/**
	 * 将代码运行在UI线程
	 * @param r Runnable接口的实现，里面是运行在UI线程的代码
	 */
	public static void runMainUIThread(Runnable r) {
		handler.post(r);
	}
	//handler  Message -->sendMesasge-->handleMessage-->r.run
	private static Handler handler=new Handler(Looper.getMainLooper());
}
