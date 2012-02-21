package com.dss.account.control;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

@SuppressWarnings("serial")
@Component("viewProfileControl")
@Scope("prototype")
public class ViewProfileControl extends ProfileControl{

	Window memberProfileAndCashAccountWindow;
	
	public void onClick$btnFill(){
		memberProfileAndCashAccountWindow = (Window) Executions.createComponents("/fill.zul", null, null);
	}
	
	public void doAfterCompose(org.zkoss.zk.ui.Component comp) throws Exception {
		super.doAfterCompose(comp);
	}
}
