package com.kerimfettahoglu.campaignmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.kerimfettahoglu.campaignmanagement.constant.Constants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Campaign {
	@Id
	@SequenceGenerator(name = "campaign_sequence", sequenceName = "campaign_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "campaign_sequence")
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
