package com.dss.wanted.bean;

import java.util.Date;

import com.dss.account.bean.MemberBean;

public interface SolutionBean {

	//	private List<Document> proposingDocuments;
	public String getNote();

	public Date getProposeDate();

	public SolutionStatus getStatus();

	public MemberBean getProposer();
	
	public void setProposer(MemberBean member);

	public Long getWantedId();

	public void setWantedId(Long wantedId);

	public Long getDocumentId();
	
//	public Long getWantedId();

}