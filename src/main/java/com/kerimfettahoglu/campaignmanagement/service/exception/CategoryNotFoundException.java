package com.kerimfettahoglu.campaignmanagement.service.exception;

public class CategoryNotFoundException extends BusinessException {
	
	public CategoryNotFoundException(Integer id) {
		super("kategori bulunamadı :" + id);
	}
}
