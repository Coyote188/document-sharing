package com.dss.storage.ui.control;

import org.zkoss.zk.au.http.AuUploader;

public class DssZkUploadHandler extends AuUploader {

	@Override
	protected String handleError(Throwable ex) {
		// TODO Auto-generated method stub
		String msg="�ϴ����ļ��ѳ�������Ĵ�С";
		super.handleError(ex);
		return msg;
	}

}
