package it.polito.tdp.borders.model;

public class Country {
	
	private int code;
	private String stateAbb;
	private String name;
	
	public Country(int code, String stateAbb, String name) {
		super();
		this.code = code;
		this.stateAbb = stateAbb;
		this.name = name;
	}
	public String getStateAbb() {
		return stateAbb;
	}
	public void setStateAbb(String stateAbb) {
		this.stateAbb = stateAbb;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (code != other.code)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return this.getName();
	}
	
	

}
