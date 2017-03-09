package com.vinsen.mylibrary.util;



import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import android.util.Log;

public class StringUtil {

	private static final String TAG = "StringUtil";

	public static boolean isNull(String str) {
		return str == null || str.trim().equals("")
				|| str.trim().equals("null");
	}

	public static String stringNum(String str, int num) {
		if (!isNull(str)) {
			if (str.length() <= num) {
				return str;
			}
			if (str == null || num < 1) {
				return "";
			}
			return str.substring(0, num - 1) + "...";
		} else {
			return "";
		}
	}

	public static String connectArray(Object[] arr, String ch) {
		String str = "";
		int j = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == null || arr[i].equals("")) {
				j++;
			} else {
				if (i != j) {
					str = str + ch;
				}
				str = str + arr[i];
			}
		}
		return str;
	}

	public static String connectArray(Object[] arr, String ch, String s) {
		String str = "";
		int j = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == null || arr[i].equals("")) {
				j++;
			} else {
				if (i != j) {
					str = str + ch;
				}
				str = str + s + arr[i] + s;
			}
		}
		return str;
	}

	public static String connectArray(List list, String ch) {
		String str = "";
		int j = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == null || list.get(i).equals("")) {
				j++;
			} else {
				if (i != j) {
					str = str + ch;
				}
				str = str + list.get(i);
			}
		}
		return str;
	}

	public static String connectArray(List list, String ch, String s) {
		String str = "";
		int j = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == null || list.get(i).equals("")) {
				j++;
			} else {
				if (i != j) {
					str = str + ch;
				}
				str = str + s + list.get(i) + s;
			}
		}
		return str;
	}

	public static String changeNull(String sou, String str) {
		return sou == null || sou.equals("") || sou.equals("null") ? str : sou;
	}

	public static String changeNull(String sou) {
		return sou == null || sou.equals("null") ? "" : sou;
	}

	public static String replaceHtml(String str) {
		try {
			str = str.trim();
			str = str.replaceAll("&", "&amp;");
			str = str.replaceAll("<", "&lt;");
			str = str.replaceAll(">", "&gt;");
			str = str.replaceAll(" ", "&nbps;");
			str = str.replace("'", "&#39;");
			str = str.replaceAll("\"", "&quot;");
			str = str.replace("\n", "<br />");
			return str;
		} catch (NullPointerException e) {
			return null;
		}
	}

	public static String replaceBr(String str) {
		try {
			str = str.replaceAll("<br />", "\n");
			return str;
		} catch (NullPointerException e) {
			return null;
		}
	}

	public static String getMD5Str(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			Log.e(TAG, "NoSuchAlgorithmException caught!", e);
		} catch (UnsupportedEncodingException e) {
			Log.e(TAG, "UnsupportedEncodingException : ", e);
		}

		byte[] byteArray = messageDigest.digest();
		StringBuilder md5StrBuff = new StringBuilder();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			} else {
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}
		}
		return md5StrBuff.toString();
	}


	public static String getExceptionStack(Exception e) {
		if (e == null) {
			return "";
		}
		StackTraceElement[] trace = e.getStackTrace();
		StringBuilder sb = new StringBuilder();
		sb.append(e.toString()).append('\n');
		for (int i = 0; i < trace.length; i++) {
			sb.append("\tat ").append(trace[i]).append('\n');
		}
		if (e.getCause() != null) {
			appStackTraceAsCause(sb, trace, e.getCause());
		}
		return sb.toString();
	}

	private static void appStackTraceAsCause(StringBuilder s,
			StackTraceElement[] causedTrace, Throwable t) {
		StackTraceElement[] trace = t.getStackTrace();
		int m = trace.length - 1, n = causedTrace.length - 1;
		while (m >= 0 && n >= 0 && trace[m].equals(causedTrace[n])) {
			m--;
			n--;
		}
		int framesInCommon = trace.length - 1 - m;

		s.append("Caused by: ").append(t).append('\n');
		for (int i = 0; i <= m; i++) {
			s.append("\tat ").append(trace[i]).append('\n');
		}
		if (framesInCommon != 0) {
			s.append("\t... ").append(framesInCommon).append(" more")
					.append('\n');
		}
		if (t.getCause() != null) {
			appStackTraceAsCause(s, trace, t.getCause());
		}
	}
}
