/*
 * @(#)ResourceUtil.java		       Project:com.sinaapp.msdxblog.androidkit
 * Date:2012-3-27
 *
 * Copyright (c) 2011 CFuture09, Institute of Software, 
 * Guangdong Ocean University, Zhanjiang, GuangDong, China.
 * All rights reserved.
 */
package com.lurencun.cfuture09.androidkit.utils.apk;

import com.lurencun.cfuture09.androidkit.utils.lang.Log4AK;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;

/**
 * 资源工具类。
 * 
 * @author Geek_Soledad (66704238@51uc.com)
 */
public class ResourceUtil {
	private Context mContext;
	private Resources mResources;

	public ResourceUtil(Context context) {
		mContext = context;
		mResources = mContext.getResources();
	}

	/**
	 * 根据资源的名字获取它的ID
	 * 
	 * @param name
	 *            要获取的资源的名字
	 * @param defType
	 *            资源的类型，如drawable, string 。。。
	 * @return 资源的id
	 */
	public int getResId(String name, String defType) {
		String packageName = mContext.getApplicationInfo().packageName;
		return mResources.getIdentifier(name, defType, packageName);
	}

	/**
	 * 获取有关本程序的信息。
	 * 
	 * @return 有关本程序的信息。
	 */
	public ApkInfo getApkInfo() {
		ApkInfo apkInfo = new ApkInfo();
		ApplicationInfo applicationInfo = mContext.getApplicationInfo();
		apkInfo.packageName = applicationInfo.packageName;
		apkInfo.iconId = applicationInfo.icon;
		apkInfo.iconDrawable = mResources.getDrawable(apkInfo.iconId);
		apkInfo.programName = mResources.getText(applicationInfo.labelRes).toString();
		PackageInfo packageInfo = null;
		try {
			packageInfo = mContext.getPackageManager().getPackageInfo(apkInfo.packageName, 0);
			apkInfo.versionCode = packageInfo.versionCode;
			apkInfo.versionName = packageInfo.versionName;
		} catch (NameNotFoundException e) {
			Log4AK.getLog(this.getClass()).w(e);
		}
		return apkInfo;
	}
}
