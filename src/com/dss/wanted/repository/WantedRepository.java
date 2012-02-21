package com.dss.wanted.repository;

import java.util.List;

import com.dss.account.bean.MemberBean;
import com.dss.wanted.bean.SolutionBean;
import com.dss.wanted.bean.WantedBean;
import com.dss.wanted.model.Solution;
import com.dss.wanted.model.Wanted;

public interface WantedRepository {

	public List<Wanted> findOutOfdateWanted();
	public List<Wanted> findPostedWanted(MemberBean poster);
	public List<Wanted> findAvailableWanted();
	public void saveNewWanted(WantedBean aNewWanted);
	public void editWanted(Wanted wanted);
	public WantedBean showWanted(Long wantedId);
	public WantedBean createWanted();
	public SolutionBean createSolution();
	public List<Solution> getWantedSolution(Long wantedId);
	public List<Solution> getProposeSolution(Long proposeId);
	public void saveSolution(SolutionBean solution);
	public void closeWanted(WantedBean wanted);
}