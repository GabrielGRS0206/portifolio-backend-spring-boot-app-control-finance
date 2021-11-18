package io.spring.github.domain.utils;

public enum UtilsEmun {

	ONE(1,"1"),
	TWO(2,"2"),
	THREE(3,"3"),
	FOUR(4,"4"),
	FIVE(5,"5"),
	VALUE_EMPTY(0,"");
	
	
	private String value;
	private int intValue;
	
	UtilsEmun(int intValue,String value) {
		this.value = value;
		this.intValue = intValue;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	
	
}
