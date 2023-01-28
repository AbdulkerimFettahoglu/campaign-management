package com.kerimfettahoglu.campaignmanagement.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CampaignHistoryItemDto {
	
	private Integer log_id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm")
	private Date logDate;
	
	private Integer id;

	private String title;

	private String details;

	private String category;

	private String status;
}
