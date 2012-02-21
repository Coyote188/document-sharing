package com.dss.wanted.control;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.dss.account.bean.MemberBean;
import com.dss.account.control.CookieUtil;
import com.dss.account.model.Member;
import com.dss.wanted.bean.SolutionBean;
import com.dss.wanted.bean.WantedBean;
import com.dss.wanted.service.WantedService;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component("viewWantedContent")
@Scope("session")
public class ViewWantedContent extends WantedControl{
	@Resource
	WantedService wantedService;
//	WantedBean wantedContent;

	Button btnCloseTheWanted,btnNewSolutionWindow;
	
	/**
	 * initialize the page
	 */
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
//		isTheWanted();
	}
	
	/**
	 * getter & setter 
	 */
	public WantedBean getWantedContent() {
		return wantedContent;
	}


	public void setWantedContent(WantedBean wantedContent) {
		this.wantedContent = wantedContent;
	}
	
	/**
	 * binding the page's button event
	 * @throws InterruptedException
	 */
	@SuppressWarnings("unchecked")
	public void onClick$btnNewSolutionWindow() throws InterruptedException {
		MemberBean member = null;
		boolean isLoginMember = securityService.isLogin();
		if (isLoginMember) {
			member = securityService.getLoginMember();
			SolutionBean solution = wantedService.newSolution();
			WantedBean selectedWanted = (WantedBean) arg.get("wanted");
			solution.setWantedId(selectedWanted.getId());
			solution.setProposer(member);
			param.put("solution", solution);
			wantedContentWindow = (Window) Executions.createComponents(
					"/newsolution.zul", null, param);
		} else {
			Messagebox.show("No login");
			authenticate = (Window) Executions.createComponents(
					"/signinUi.zul", null, null);
			authenticate.doModal();
		}

	}
	
	public void btnCloseTheWanted(){
		wantedService.closeWanted(wantedContent);
		btnCloseTheWanted.setDisabled(false);
		btnNewSolutionWindow.setDisabled(false);
	}
	
	private void isTheWanted(){
		MemberBean theMember = securityService.getLoginMember();
		if (theMember != null) {
			System.out.println("test in view wanted content :" + wantedContent.getPoster() + theMember.getId());
//			if(wantedContent.getPoster().getId() == theMember.getId()){
//				btnCloseTheWanted.setVisible(true);
//			}
		}
		
	}
}
