package com.dss.account.model;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.dss.account.bean.CashAccountBean;
import com.dss.account.model.PaymentProvider;
import com.dss.account.repository.CashAccountRepository;

@Component("cashAccount")
public class CashAccount implements CashAccountBean {

	private Long cashAccountId;
	private double currentPoint;
	private String paymentAccountId;
	private Member member;
	private PaymentProvider paymentProvider;

	@Resource(name = "cashAccountRepository")
	private CashAccountRepository cashAccountRepository;


	/**
	 * getters & setters
	 */

	public CashAccountRepository getCashAccountRepository() {
		return cashAccountRepository;
	}

	public void setCashAccountRepository(CashAccountRepository cashAccountRepository) {
		this.cashAccountRepository = cashAccountRepository;
	}
	
	public double getCurrentPoint() {
		return currentPoint;
	}
	
	public void getCurrentPoint(Member member){
		this.currentPoint = cashAccountRepository.getCurrentPoint(member);
	}

	public void setCurrentPoint(double currentPoint) {
		this.currentPoint = currentPoint;
	}

	public String getPaymentAccountId() {
		return paymentAccountId;
	}

	public void setPaymentAccountId(String paymentAccountId) {
		this.paymentAccountId = paymentAccountId;
	}

	public PaymentProvider getPaymentProvider() {
		return paymentProvider;
	}

	public void setPaymentProvider(PaymentProvider paymentProvider) {
		this.paymentProvider = paymentProvider;
	}

	public void setCashAccountId(Long cashAccountId) {
		this.cashAccountId = cashAccountId;
	}

	public Long getCashAccountId() {
		return cashAccountId;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Member getMember() {
		return member;
	}

	public boolean isCashEnough(Double point) {
		boolean isCashEnough = false;
		if (point < this.getCurrentPoint()) {
			isCashEnough = true;
		}

		return isCashEnough;
	}

	/**
	 * 充值 金钱与积分比例10：1
	 * @param money
	 * @param member
	 */
	public void fill(Double money, Member member) {
		Double point = money * 10; 
		Double newPoint = this.currentPoint + point;
		this.currentPoint = newPoint;
		this.member = member;
		cashAccountRepository.updateCashAccount(this);
	}

	public boolean isExchangeAtMinimum(Double point) {
		boolean isExchangeAtMinimum = true;

		if (point >= 100.0) {
			isExchangeAtMinimum = false;
		}

		return isExchangeAtMinimum;
	}

	public boolean exchange(Double point, Member member) {
		if (!(isCashEnough(point) && !isExchangeAtMinimum(point))) {
//			return isCashEnough(point);
			return false;
		} else {
			minusCurrentPoint(point, member);
			return true;
		}
//		return isCashEnough(point) && !isExchangeAtMinimum(point);
	}

	public boolean useCash(Double point, Member member) {
		if (!isCashEnough(point) || isExchangeAtMinimum(point)) {
			return isCashEnough(point);
		} else {
			minusCurrentPoint(point, member);
		}
		return isCashEnough(point) && !isExchangeAtMinimum(point);
	}

	public void gainCash(Double point,Member member) {
		Double newPoint = this.currentPoint + point;
		this.currentPoint = newPoint;
		this.member = member;
		cashAccountRepository.updateCashAccount(this);
	}
	
	public void minusCurrentPoint(Double point,Member member){
		Double newPoint = this.currentPoint - point;
		this.currentPoint = newPoint;
		this.member = member;
		cashAccountRepository.updateCashAccount(this);
	}
}
