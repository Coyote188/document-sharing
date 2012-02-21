package com.dss.wanted.control;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.dss.wanted.bean.SolutionBean;
import com.dss.wanted.service.WantedService;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component("createNewSolutionControl")
@Scope("request")
public class CreateNewSolutionControl extends WantedControl{
	SolutionBean solution;
	@Resource
	WantedService wantedService;
	
	Window newSolutionWindow;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		solution = (SolutionBean) arg.get("solution");
	}
	
	/**
	 * getter setter 
	 * @return
	 */
	public SolutionBean getSolution() {
		return solution;
	}
	public void setSolution(SolutionBean solution) {
		this.solution = solution;
	}
	
	public void onClick$btnNewSolution() throws InterruptedException{
		wantedService.saveNewSolution(solution);
		Messagebox.show("±£´æ³É¹¦£¡");
		newSolutionWindow.detach();
	}
}
