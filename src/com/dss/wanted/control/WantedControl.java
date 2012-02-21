package com.dss.wanted.control;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.WebApplicationContextUtils;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Include;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.dss.account.bean.MemberBean;
import com.dss.account.control.CookieUtil;
import com.dss.account.service.SecurityService;
import com.dss.wanted.bean.SolutionBean;
import com.dss.wanted.bean.WantedBean;
import com.dss.wanted.model.Wanted;
import com.dss.wanted.service.WantedService;

@SuppressWarnings( { "unused", "serial" })
@Controller("wantedControl")
@Scope("prototype")
public class WantedControl extends GenericForwardComposer {
	
	@Resource(name = "wantedService")
	WantedService wantedService;
	@Resource(name = "SecurityService")
	SecurityService securityService;
	List<WantedBean> wanteds;
	List<WantedBean> myWanteds;
	List<SolutionBean> proposeSolutions;
	List<SolutionBean> wantedSolutions;
	WantedBean wantedContent;

	Listbox wantedItems;
	Window viewWantedWindow;
	Window wantedContentWindow;
	Window authenticate;
	Window createNewWanted;
	private WantedBean selectedWanted;
	Panelchildren wantedsPanel;
	Button btnSearchMyWanted;
	Window myWantedsWindow;
	
	
	
	public List<WantedBean> getMyWanteds() {
		return myWanteds;
	}

	public void setMyWanteds(List<WantedBean> myWanteds) {
		this.myWanteds = myWanteds;
	}

	public List<SolutionBean> getSolutions() {
		return proposeSolutions;
	}

	public void setSolutions(List<SolutionBean> proposeSolutions) {
		this.proposeSolutions = proposeSolutions;
	}

	public List<WantedBean> getWanteds() {
		return wanteds;
	}

	public void setWanteds(List<WantedBean> wanteds) {
		this.wanteds = wanteds;
	}

	public WantedBean getWantedContent() {
		return wantedContent;
	}

	public void setWantedContent(WantedBean wantedContent) {
		this.wantedContent = wantedContent;
	}

	public List<SolutionBean> getWantedSolutions() {
		return wantedSolutions;
	}

	public void setWantedSolutions(List<SolutionBean> wantedSolutions) {
		this.wantedSolutions = wantedSolutions;
	}

	/**
	 * 绑定页面组件事件
	 * 
	 * @throws InterruptedException
	 * @throws InterruptedException
	 */

//	@SuppressWarnings("unchecked")
//	public void onClick$btnSearchMyWanted() throws InterruptedException {
//		boolean isLoginMember = securityService.isLogin(CookieUtil.getToken());
//		if (isLoginMember) {
//			MemberBean poster = securityService.getLoginMember(CookieUtil
//					.getToken());
//			myWanteds = wantedService.findMyWanteds(poster);
//			System.out.println(myWanteds.size());
//			param.put("myWanteds", myWanteds);
//			myWantedsWindow = (Window) Executions.createComponents(
//					"/viewMyWanteds.zul", null, param);
//			myWantedsWindow.doModal();
//		} else {
//			Messagebox.show("请您登录！");
//			authenticate = (Window) Executions.createComponents(
//					"/signinUi.zul", null, null);
//			authenticate.doModal();
//		}
//
//	}

	public void onClick$btnNewWantedWindow() throws InterruptedException {
		boolean isLoginMember = securityService.isLogin();
		if (isLoginMember) {
//			wanted = new EditableWanted();
//			wanted.setPoster(securityService.getLoginMember(CookieUtil
//					.getToken()));
			createNewWanted = (Window) Executions.createComponents(
					"/CreateNewWanted.zul", null, null);

		} else {
//			Messagebox.show("No login");
			authenticate = (Window) Executions.createComponents(
					"/signinUi.zul", null, null);
			authenticate.doModal();
		}
	}

	@SuppressWarnings("unchecked")
	public void onClick$getSelectWantedItem() {
		Listitem selectedItem = wantedItems.getSelectedItem();
		if (wantedItems == null)
			System.out.println("null");
		else
			System.out.println("not null");
		this.selectedWanted = (WantedBean) selectedItem.getValue();
		System.out.println("test in wanted control" + this.selectedWanted);
		this.wantedContent = wantedService.showWanted(selectedWanted.getId());
		this.wantedSolutions = wantedService.getWantedSolution(selectedWanted
				.getId());
		param.put("wanted", selectedWanted);
		param.put("wantedSolutions", this.wantedSolutions);
		wantedContentWindow = (Window) Executions.createComponents(
				"/ViewWantedContent.zul", null, param);

	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		wanteds = wantedService.findAvailableWanted();

	}

}
