package com.dss.storage.ui.control;

import org.zkoss.zk.au.http.AuUploader;

public class DssZkUploadHandler extends AuUploader {

	@Override
	protected String handleError(Throwable ex) {
		// TODO Auto-generated method stub
		String msg="上传的文件已超过允许的大小";
		super.handleError(ex);
		return msg;
	}

}
