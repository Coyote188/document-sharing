package com.dss.wanted.bean;

import java.util.Date;
import java.util.List;

import com.dss.account.bean.MemberBean;

public interface WantedBean {
	public Long getId();
	public Date getCloseDate();
	public String getDescription();
	public Date getOpenDate();
	public String getSuject();
	public WantedStatus getStatus();
//	public DocumentTypeBean getDocumentType();
	public MemberBean getPoster();
	public List<SolutionBean> getProposedSolutions();
	public void setPoster(MemberBean poster);
}