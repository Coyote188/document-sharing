package com.dss.wanted.control;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.zkoss.zk.ui.Component;

import com.dss.wanted.bean.WantedBean;
import com.dss.wanted.service.WantedService;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component("createNewWantedControl")
@Scope("prototype")
public class CreateNewWantedControl extends WantedControl{
	@Resource
	WantedService wantedService;
	WantedBean wanted;
	
	public WantedBean getWanted() {
		return wanted;
	}

	public void setWanted(WantedBean wanted) {
		this.wanted = wanted;
	}

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		wanted = wantedService.newWantedBean();
	}
	
	public void onClick$btnSubmit(){
		wantedService.save(wanted);
	
	}
}
