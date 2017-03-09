/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vinsen.mylibrary.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * 
 * @author cyss
 */
public class TypeUtil {

	public static Integer checkType(Object o) {
		Integer num = -1;
		String type = o.getClass().getName();
		if (type.equals("java.lang.String")) {
			num = 0;
		} else if (type.equals("java.lang.Integer") || type.equals("int")) {
			num = 1;
		} else if (type.equals("java.util.Date")) {
			num = 2;
		}
		return num;
	}

	public static Integer cehckSqlType(Object o) {
		Integer num = -1;
		String type = o.getClass().getName();
		if (type.equals("java.lang.String")) {
			num = 0;
		} else if (type.equals("java.math.BigDecimal")
				|| type.equals("java.lang.Integer") || type.equals("int")) {
			num = 1;
		} else if (type.equals("java.sql.Timestamp")) {
			num = 2;
		}
		return num;
	}

	public static String getTypeInfoToSql(Object o) {
		String s = "";
		Integer num = TypeUtil.checkType(o);
		if (num == 0) {
			s = "'" + (String) o + "'";
		} else if (num == 1) {
			s = "'" + o.toString() + "'";
		} else if (num == 2) {
			s = "to_date('"
					+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(o)
					+ "','yyyy-mm-dd HH24:MI:SS')";
		}
		return s;
	}

	public static void getTypeInfoToBindSql(Object o, PreparedStatement pstmt,
			int i) throws SQLException {
		Integer num = TypeUtil.checkType(o);
		if (num == 0) {
			pstmt.setString(i, (String) o);
		} else if (num == 1) {
			pstmt.setInt(i, (Integer) o);
		} else if (num == 2) {
			java.util.Date dt = (java.util.Date) o;
			pstmt.setTimestamp(i, Timestamp.valueOf(new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(dt)));
		}
	}
}
