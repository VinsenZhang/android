package com.vinsen.mylibrary.util;



import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetUtil {
	public static boolean checkNet(Context context) {// ��ȡ�ֻ��������ӹ�����󣨰�����wi-fi,net�����ӵĹ���
		try {
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {
				// ��ȡ�������ӹ���Ķ���
				NetworkInfo info = connectivity.getActiveNetworkInfo();
				if (info != null && info.isConnected()) {
					// �жϵ�ǰ�����Ƿ��Ѿ�����
					if (info.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		} catch (Exception e) {
		}
		return false;
	}

	// ��һ��URLȡ��һ��ͼƬ
	public static BitmapDrawable getImageFromUrl(URL url) {
		BitmapDrawable icon = null;
		try {
			HttpURLConnection hc = (HttpURLConnection) url.openConnection();
			icon = new BitmapDrawable(hc.getInputStream());
			hc.disconnect();
		} catch (Exception e) {
		}
		return icon;
	}

	public static String getTimeDiff(Date date) {
		Calendar cal = Calendar.getInstance();
		long diff = 0;
		Date dnow = cal.getTime();
		String str = "";
		diff = dnow.getTime() - date.getTime();

		System.out.println("diff---->" + date);

		if (diff > 24 * 60 * 60 * 1000) {
			// System.out.println("1��ǰ");
			str = "1��ǰ";
		} else if (diff > 5 * 60 * 60 * 1000) {
			// System.out.println("2Сʱǰ");
			str = "2Сʱǰ";
		} else if (diff > 1 * 60 * 60 * 1000) {
			// System.out.println("1Сʱǰ");
			str = "Сʱǰ";
		} else if (diff > 30 * 60 * 1000) {
			// System.out.println("30����ǰ");
			str = "30����ǰ";
		} else if (diff > 15 * 60 * 1000) {
			// System.out.println("15����ǰ");
			str = "15����ǰ";
		} else if (diff > 5 * 60 * 1000) {
			// System.out.println("5����ǰ");
			str = "5����ǰ";
		} else if (diff > 1 * 60 * 1000) {
			// System.out.println("1����ǰ");
			str = "1����ǰ";
		} else {
			str = "�ո�";
		}

		return str;
	}

}
