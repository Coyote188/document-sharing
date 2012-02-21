package com.dss.wanted.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;


import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import com.dss.account.bean.MemberBean;
import com.dss.account.service.SecurityService;
import com.dss.wanted.bean.SolutionBean;
import com.dss.wanted.bean.WantedBean;
import com.dss.wanted.model.Solution;
import com.dss.wanted.model.Wanted;
import com.dss.wanted.repository.WantedRepository;
import com.dss.wanted.service.WantedService;
@Test
@Transactional
public class WantedServiceImpl implements WantedService {
	
	public WantedRepository wantedRepository; 
	@Resource
	private SecurityService securityService;
	private WantedBean wanted;
	public WantedRepository getWantedRepository() {
		return wantedRepository;
	}

	public void setWantedRepository(WantedRepository wantedRepository) {
		this.wantedRepository = wantedRepository;
	}
	
	@Override
	public WantedBean newWantedBean() {
		// TODO Auto-generated method stub
		return wantedRepository.createWanted();
	}
	
	@Override
	public SolutionBean newSolution(){
		return wantedRepository.createSolution();
	}

	@Override
	public void closeWanted(WantedBean wanted) {
		wantedRepository.closeWanted(wanted);
		
	}

	public List<WantedBean> findAvailableWanted(){
		List<WantedBean>  result= new ArrayList<WantedBean>();
		List<Wanted> tmpList= wantedRepository.findAvailableWanted();
		for(WantedBean itr: tmpList){
			result.add(itr);
		}
		return result;
	}
	
	public List<WantedBean> findMyWanteds(MemberBean poster){
		List<WantedBean>  result= new ArrayList<WantedBean>();
		List<Wanted> tmpList= wantedRepository.findPostedWanted(poster);
		for(WantedBean itr: tmpList){
			result.add(itr);
		}
		return result;
	}
	
	public void save(WantedBean wanted){
		wanted.setPoster(securityService.getLoginMember());
		wantedRepository.saveNewWanted(wanted);
	}
	public void closeWanted(Wanted wanted){
		wanted.close(wantedRepository);
	}

	@Override
	public List getWantedSolution(Long wantedId){
		return wantedRepository.getWantedSolution(wantedId);
	}
	
	@Override
	public List<SolutionBean> getProposeSolution(Long proposeId){
		List<SolutionBean> sList = new ArrayList<SolutionBean>();
		List<Solution> temList = wantedRepository.getProposeSolution(proposeId);
		for(SolutionBean solution : temList){
			sList.add(solution);
		}
		return sList;
	}
	
	@Override
	public void saveNewSolution(SolutionBean solution){
		solution.setProposer(securityService.getLoginMember());
		wantedRepository.saveSolution(solution);		
	}
	@Override
	public WantedBean showWanted(Long wantedId) {
		return wantedRepository.showWanted(wantedId);
	}
	
}
