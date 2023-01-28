package com.kerimfettahoglu.campaignmanagement.service.exception;

import com.kerimfettahoglu.campaignmanagement.enumaration.StatusEnum;

public class StatusNotApplicableException extends BusinessException {
	
	public StatusNotApplicableException(StatusEnum expected, StatusEnum result) {
		super("bu işlemin yapılabilmesi için kampanyanın \"" + expected.getDescription() + "\" durumunda olması gerekir. Kampanyanın durumu :" + result.getDescription());
	}
	
	public StatusNotApplicableException(StatusEnum expected, StatusEnum expected2, StatusEnum result) {
		super("bu işlemin yapılabilmesi için kampanyanın \"" + expected.getDescription() + "\" veya \"" + expected2.getDescription() + "\" durumunda olması gerekir. Kampanyanın durumu :" + result.getDescription());
	}
}
