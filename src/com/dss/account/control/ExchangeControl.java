package com.dss.account.control;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Messagebox;

import com.dss.account.bean.CashAccountBean;
import com.dss.account.service.MemberService;
import com.dss.account.service.SecurityService;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component("exchangeControl")
@Scope("request")
public class ExchangeControl extends GenericForwardComposer{
	@Resource
	MemberService memberService;
	@Resource
	SecurityService securityService;
	private double point ;

	/**
	 * getter & setter
	 * @return
	 */
	
	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}


	public void onClick$btnFillSubmit(){
		memberService.fill(point);
	}
	
	public void onClick$btnExchangeSubmit() throws InterruptedException{
		boolean isDone = memberService.exchange(point);
		if(isDone){
			Messagebox.show("您成功兑换了 "+(point/10)+" 元");
		}else {
			Messagebox.show("您的账户余额或者输入少于兑换最小值，兑换失败！");
		}
	}

	@Override
	public void doAfterCompose(org.zkoss.zk.ui.Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
	}




}
