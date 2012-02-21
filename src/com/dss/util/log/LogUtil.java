package com.dss.util.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dss.web.MainToolbarControl;

public class LogUtil {
	private static Log logger ; 
	
	public static void debug(Class clazz, Object message){
		logger = LogFactory.getLog(clazz); 
		logger.debug(message);
	}
	
	public static void error(Class clazz, Object message){
		logger = LogFactory.getLog(clazz); 
		logger.error(message);
	}
}
