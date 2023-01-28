package com.kerimfettahoglu.campaignmanagement.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignLog {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer log_id;

	@Column
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm")
	private Date logDate;

	@Column
	private Integer id;

	@Column
	private String title;

	@Column
	private String details;

	@Column
	private Integer category;

	@Column
	private Integer status;
}