package com.kerimfettahoglu.campaignmanagement.service.exception;

public class CampaignNotFoundException extends BusinessException {
	
	public CampaignNotFoundException(Integer id) {
		super("kampanya bulunamadı :" + id);
	}
}
