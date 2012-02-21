package com.dss.account.control;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import com.dss.account.bean.CashAccountBean;
import com.dss.account.bean.MemberBean;
import com.dss.account.bean.PaymentProviderBean;
import com.dss.account.bean.ProfileBean;
import com.dss.account.service.MemberService;
import com.dss.account.service.SecurityService;
import com.dss.account.ui.EditableProfile;

@SuppressWarnings("serial")
@Component("profileControl")
@Scope("prototype")
public class ProfileControl extends GenericForwardComposer{
	
	@Resource(name="memberService")
	MemberService memberService;
	@Resource(name="SecurityService")
	SecurityService securityService;
	MemberBean member;
	private ProfileBean profile;
	@Resource(name="cashAccount")
	private CashAccountBean cashAccount;
	@Resource(name="paymentProvider")
	private PaymentProviderBean paymentProvider;

	private ProfileBean newProfile;
//	EditableProfile newProfile;
	
	/**
	 * 页面组件Textbox
	 */
	Textbox txtCalledName,txtRealName,txtLocation,txtContactMethods,txtHobbie,txtPaymentAccountId,txtHomePage;
	Combobox comGender;
	Datebox dateBirthDate;
	Button btnModify,btnSaveModify;
	
	boolean isLogin;
	
	/**
	 * getters & setters
	 */

	public CashAccountBean getCashAccount() {
		return cashAccount;
	}

	public void setCashAccount(CashAccountBean cashAccount) {
		this.cashAccount = cashAccount;
	}
	
	public ProfileBean getProfile() {
		return profile;
	}

	public void setProfile(ProfileBean profile) {
		this.profile = profile;
	}

	public ProfileBean getNewProfile() {
		return newProfile;
	}

	public void setNewProfile(ProfileBean newProfile) {
		this.newProfile = newProfile;
	}

	public void setPaymentProvider(PaymentProviderBean paymentProvider) {
		this.paymentProvider = paymentProvider;
	}

	public PaymentProviderBean getPaymentProvider() {
		return paymentProvider;
	}
	/**
	 * 页面绑定信息
	 * @throws InterruptedException 
	 */
	public void onClick$btnNewProfile() throws InterruptedException{
		System.out.println("btn clicked");
		isLogin = securityService.isLogin();
		member = securityService.getLoginMember();
		
		newProfile.setMember(member);
		System.out.println("test if member = null" +(member == null)+ newProfile.getMember());
		memberService.saveProfile(newProfile);
		Messagebox.show("会员信息保存成功！");
	}
	
//	public void onClick$btnModify(){
//		txtCalledName.setDisabled(false);
//		txtRealName.setDisabled(false);
//		txtLocation.setDisabled(false);
//		txtContactMethods.setDisabled(false);
//		txtHobbie.setDisabled(false);
//		txtPaymentAccountId.setDisabled(false);
////		txtHomePage.setDisabled(false);
//		btnModify.setVisible(false);
//		btnSaveModify.setVisible(true);
//	}
	
	public void onClick$btnSaveModify() throws InterruptedException{
		int replay =  Messagebox.show("确认修改这些信息？", "警告",Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION); 
		if (replay == 1) {
			memberService.updateProfile(profile);
		}		
	}
	
	@Override
	public void doAfterCompose(org.zkoss.zk.ui.Component comp) throws Exception {
		super.doAfterCompose(comp);
//		newProfile=new EditableProfile();
		this.profile = memberService.getProfile();
		this.cashAccount = memberService.getCashAccount();
		newProfile = memberService.newProfile();
//		this.paymentProvider = cashAccount.getPaymentProvider();
	}

}
