package com.kerimfettahoglu.campaignmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Status {
	@Id
	private Integer id;

	@Column
	private String name;
}
