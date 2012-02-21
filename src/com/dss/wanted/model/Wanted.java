package com.dss.wanted.model;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dss.account.bean.MemberBean;
import com.dss.account.model.Member;
import com.dss.wanted.bean.SolutionStatus;
import com.dss.wanted.bean.WantedBean;
import com.dss.wanted.bean.WantedStatus;
import com.dss.wanted.repository.WantedRepository;

@SuppressWarnings("unchecked")
@Component("wanted")
public class Wanted implements WantedBean{
	private WantedRepository wantedRepository;
	private Long id;
	private Date closeDate;
	private String description;
	private Date openDate;
	private String suject;
	private WantedStatus status;
	@Resource
	public MemberBean poster;
	private List proposedSolutions;
	
	public WantedRepository getWantedRepository() {
		return wantedRepository;
	}
	
	public void setWantedRepository(WantedRepository wantedRepository) {
		this.wantedRepository = wantedRepository;
	}
	
	public void isAnySolutionProposed() {
		
	}
	public boolean isAnySolutionTaken() {
		return false;
	}
	public boolean outofdate() {
		return false;
	}
	public void close(WantedRepository wantedRepository){
		wantedRepository.editWanted(this);
	}
	public void save(WantedBean wanted,Member poster){
		setPoster(poster);
		wantedRepository.saveNewWanted(this);
	}
	public void propose(){
		
	}
	public void accept(Solution solution){
		solution.setStatus(SolutionStatus.Accepted);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public String getSuject() {
		return suject;
	}

	public void setSuject(String suject) {
		this.suject = suject;
	}

	public WantedStatus getStatus() {
		return status;
	}

	public void setStatus(WantedStatus status) {
		this.status = status;
	}

	public MemberBean getPoster() {
		return poster;
	}

	public void setPoster(MemberBean poster) {
		this.poster = poster;
	}

	public List getProposedSolutions() {
		return proposedSolutions;
	}

	public void setProposedSolutions(List proposedSolutions) {
		this.proposedSolutions = proposedSolutions;
	}
}
