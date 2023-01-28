package com.kerimfettahoglu.campaignmanagement.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.kerimfettahoglu.campaignmanagement.constant.Constants;
import com.kerimfettahoglu.campaignmanagement.entity.Campaign;

import lombok.Data;

@Data
public class CreateCampaignDto {
	@Length(min= 10, max=50, message = Constants.CAMPAIGN_TITLE_LENGTH)
	@Pattern(regexp = Constants.CAMPAIGN_TITLE_REGEX, message = Constants.CAMPAIGN_TITLE_CHARACTER_TYPE)
	private String title;
	@Length(min= 20, max=200, message = Constants.CAMPAIGN_DESCRIPTION_LENGTH)
	private String details;
	@NotNull(message = Constants.CAMPAIGN_CATEGORY_NULL)
	private Integer category;
	
	public static Campaign mapDtoToObj(CreateCampaignDto createCampaignDto) {
		return Campaign.builder().title(createCampaignDto.getTitle())
				.details(createCampaignDto.getDetails())
				.build();
	}
}
