package com.dss.wanted.model;

import java.util.Date;
import com.dss.account.bean.MemberBean;
import com.dss.wanted.bean.SolutionBean;
import com.dss.wanted.bean.SolutionStatus;

public class Solution implements SolutionBean{

	private Long id;
	private String note;
	private Date proposeDate;
	private SolutionStatus status;
	private MemberBean proposer;
	private Long wantedId;
	private Long documentId;
	

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getProposeDate() {
		return proposeDate;
	}

	public void setProposeDate(Date proposeDate) {
		this.proposeDate = proposeDate;
	}

	public SolutionStatus getStatus() {
		return status;
	}

	public void setStatus(SolutionStatus status) {
		this.status = status;
	}

	public MemberBean getProposer() {
		return proposer;
	}

	public void setProposer(MemberBean proposer) {
		this.proposer = proposer;
	}

	public Long getWantedId() {
		return wantedId;
	}

	public void setWantedId(Long wantedId) {
		this.wantedId = wantedId;
	}

}
