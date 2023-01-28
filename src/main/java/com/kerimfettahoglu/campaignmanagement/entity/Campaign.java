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

import lombok.Data;

@Entity
@Data
public class Campaign {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Length(min= 10, max=50, message = "Başlık uzunluğu 10 ile 50 karakter arasında olmalıdır.")
	@Pattern(regexp = "[a-zA-Z0-9ğüşöçıİĞÜŞÖÇ]*",message = "Başlıktaki ilk karakter alfanümerik olmalıdır.")
	@Column(length = 50)
	private String title;

	@Column
	@Length(min= 20, max=200, message = "İçerik uzunluğu 20 ile 200 karakter arasında olmalıdır.")
	private String details;

	@NotNull(message = "Kategori bilgisi boş bırakılamaz.")
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;

	@ManyToOne
	@JoinColumn(name="status_id")
	private Status status;
}
