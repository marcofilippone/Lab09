package it.polito.tdp.borders.model;

public class Border {
	
	private Country a;
	private Country b;
	public Border(Country a, Country b) {
		super();
		this.a = a;
		this.b = b;
	}
	public Country getA() {
		return a;
	}
	public void setA(Country a) {
		this.a = a;
	}
	public Country getB() {
		return b;
	}
	public void setB(Country b) {
		this.b = b;
	}
	
	

}
