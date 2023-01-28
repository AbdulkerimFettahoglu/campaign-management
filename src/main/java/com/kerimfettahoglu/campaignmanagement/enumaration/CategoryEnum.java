package com.kerimfettahoglu.campaignmanagement.enumaration;

public enum CategoryEnum {
	TSS(1, "TSS"), OSS(2, "ÖSS"), HAYAT(3, "Hayat Sigortası"), DIGER(4, "Diğer");
	Integer id;
	String description;
	
	private CategoryEnum(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return this.id;
	}
	
	public String getDescription() {
		return this.description;
	}

	public static CategoryEnum getCategory(Integer id) {
		switch(id) {
			case 1: {
				return TSS;
			} case 2: {
				return OSS;
			} case 3: {
				return HAYAT;
			} case 4: {
				return DIGER;
			} default:
				return null;
		}
	}
}