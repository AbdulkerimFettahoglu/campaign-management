package com.kerimfettahoglu.campaignmanagement.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.kerimfettahoglu.campaignmanagement.constant.Constants;

import lombok.Builder;
import lombok.Data;

@Builder
@Entity
@Data
public class Campaign {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Length(min= 10, max=50, message = Constants.CAMPAIGN_TITLE_LENGTH)
	@Pattern(regexp = Constants.CAMPAIGN_TITLE_REGEX, message = Constants.CAMPAIGN_TITLE_CHARACTER_TYPE)
	@Column(length = 50)
	private String title;

	@Column
	@Length(min= 20, max=200, message = Constants.CAMPAIGN_DESCRIPTION_LENGTH)
	private String details;

	@NotNull(message = Constants.CAMPAIGN_CATEGORY_NULL)
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;

	@ManyToOne
	@JoinColumn(name="status_id")
	private Status status;
}
