package com.dss.wanted.service;

import java.util.ArrayList;
import java.util.List;

import com.dss.account.bean.MemberBean;
import com.dss.wanted.bean.SolutionBean;
import com.dss.wanted.bean.WantedBean;
import com.dss.wanted.model.Solution;
import com.dss.wanted.model.Wanted;

public interface WantedService {

	public List<WantedBean> findAvailableWanted();
	public void save(WantedBean wanted);
	public void closeWanted(WantedBean wanted);
	public WantedBean showWanted(Long wantedId);
	public WantedBean newWantedBean();
	public List<SolutionBean> getWantedSolution(Long wantedId);
	public List<SolutionBean> getProposeSolution(Long proposeId);
	public void saveNewSolution(SolutionBean solution);
	public List<WantedBean> findMyWanteds(MemberBean poster);
	public SolutionBean newSolution();
}