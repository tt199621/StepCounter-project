package com.today.step.utils;

import android.os.Build;

public class getDeviceID {
	/**
	 * 根据手机设备信息生成手机唯一标识符
	 * @return deviceID
	 * */
	public static String getDeviceID() {
		String deviceID= "";
		try{
			//一共13位  如果位数不够可以继续添加其他信息
			deviceID= ""+Build.BOARD.length() % 10 + Build.BRAND.length() % 10 +

					Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 +

					Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 +

					Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 +

					Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 +

					Build.TAGS.length() % 10 + Build.TYPE.length() % 10 +

					Build.USER.length() % 10 ;
		}catch (Exception e){
			return "";
		}
		return deviceID;
	}
}
