package com.dss.account.control;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.SuspendNotAllowedException;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Captcha;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;
import org.zkoss.zk.ui.*;


import com.dss.account.bean.MemberBean;
import com.dss.account.service.MemberService;
import com.dss.account.service.SecurityService;
import com.dss.account.ui.EditableMember;

//@Controller("MemberControl")
//@Scope("prototype")
@SuppressWarnings("serial")
public class MemberControl extends GenericForwardComposer implements EventListener {
	
	@Resource(name="SecurityService")
	SecurityService securityService;
//	EditableProfile profile;

	

//	private Captcha captcha;
	private Captcha capAthenticate;
	private Textbox captchaInputBox;
	private Button memberSubmit;
	private Button btnAuthenticate;
	private Textbox password;
	
	private Textbox capInput;
	Toolbarbutton btnLoginWindow,btnLogoff;
	private Window authenticate;
	private Window memberProfileAndCashAccountWindow;

	private Window saveProfileWindow;
	private Label welcomeLoginMemberLabel;
	
	private Toolbar indexToolBar;
	






	
	/**
	 * ���������¼�
	 */
//	public void onLogin$btnLoginWindow(){
//		btnLoginWindow.setVisible(false);
//		btnLogoff.setVisible(true);
//	}
//	public void onLogoff$indexToolBar(){
//		btnLoginWindow.setVisible(true);
//		btnLogoff.setVisible(false);
//	}
	

	/**
	 * 
	 * ��ҳ���¼�
	 * @throws InterruptedException 
	 * @throws SuspendNotAllowedException 
	 * 
	 * @throws InterruptedException
	 */
//	public void onClick$btnLoginWindow() throws SuspendNotAllowedException, InterruptedException{
//		authenticate = (Window) Executions.createComponents("authenticate.zul", null, null);
//		authenticate.doModal();
//		Events.postEvent("onLogin", btnLoginWindow, null);
//	}
	
//	public void onClick$btnAboutMe() throws InterruptedException {
//		MemberBean loginMember = securityService.getLoginMember();
//		if (loginMember == null) {
//			int reply = Messagebox.show("Name cannot be Empty!","Error",Messagebox.YES|Messagebox.NO,Messagebox.QUESTION);
//			System.out.println("messagebox:" + reply);
//			if (reply == Messagebox.YES) {
				
//				onClick$btnLoginWindow();
//			}
			

//		} else {
//			if (memberService.getProfile() == null) {
//				System.out.println("profile is empty"+ (memberService.getProfile()==null));
//				saveProfileWindow = (Window) Executions.createComponents("SaveProfile.zul", null, null);
//				saveProfileWindow.doModal();
//			} else {
//				memberProfileAndCashAccountWindow = (Window) Executions.createComponents("viewprofile.zul", null, null);
//			}
//
//		}
//	}

	/**
	 * �����Աע����Ϣ������ȷ�ϣ���֤��
	 * @throws InterruptedException
	 */
	
	

	public void onChange$captchaInputBox() throws InterruptedException {
		if (!capAthenticate.getValue().equalsIgnoreCase(captchaInputBox.getValue())) {
			memberSubmit.setDisabled(true);
			throw new WrongValueException(captchaInputBox, "��֤��������������룡");
		} else {
			memberSubmit.setDisabled(false);
		}
	}

	public void onClick$captcha() {
		reshow();
	}

    public void reshow()
    {
        capAthenticate.randomValue();
    }
	
	/**
	 * ��Ա��¼��Ϣ����
	 */
	
	public void onClick$capAthenticate() {
		capAthenticate.randomValue();
	}
	
	public void onChange$capInput(){
		if(capAthenticate.getValue().equalsIgnoreCase(this.capInput.getValue())){
			btnAuthenticate.setDisabled(false);
		}else {
			throw new WrongValueException(capInput,"��֤��������������룡");
		}
	}
	

	

	
//	public void onClick$btnLogoff(){
//		securityService.loginOff();
//	}

	/**
	 * ��ʼ��������Ϣ
	 */
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		
	}
}
