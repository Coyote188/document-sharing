package com.dss.storage.bean;

import java.util.List;

import com.dss.account.model.Member;

public interface DocumentDirectoryBean  {
	List<DocumentBean> getDocuments();
	String getName();
	List<DocumentDirectoryBean> getChildren();
	public void setMember(Member member);
	public Member getMember();
	DocumentDirectoryBean getParent();
}
