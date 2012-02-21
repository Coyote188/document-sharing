package com.dss.wanted.control;

import org.springframework.context.annotation.Scope;
import org.zkoss.zk.ui.Component;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component("viewWantedControl")
@Scope("prototype")
public class ViewWantedContrl extends WantedControl{

	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}
}
