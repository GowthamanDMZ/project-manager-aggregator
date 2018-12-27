package com.tool.pmanager.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tool.pmanager.constants.PMToolConstants;


public class PMToolUtil implements PMToolConstants {

	private final static Logger logger = LoggerFactory.getLogger(PMToolUtil.class);
	
	public static String dateToString(Date date) {
		String dateInStr = EMPTY;
		try {
			if(null != date) {
				SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
				dateInStr = sdf.format(date);
			}
		} catch(Exception e) {
			logger.error("Error - ProjectManagerUtil dateToString : " + e);
		}
		return dateInStr;
	}
	
	public static Date stringToDate(String dateInStr) {
		Date date = null;
		try {
			if(null != dateInStr) {
				SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
				date = sdf.parse(dateInStr);
			}
		} catch(Exception e) {
			logger.error("Error - ProjectManagerUtil stringToDate : " + e);
		}
		return date;
	}
	
}
