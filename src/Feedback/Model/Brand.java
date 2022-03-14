package Feedback.Model;

import java.io.Serializable;

public class Brand implements Serializable {

	private static final long serialVersionUID = -6970460026835978519L;
	
	int brandID;
	String name;
	
	public Brand(int brandID, String name) {
		super();
		this.brandID = brandID;
		this.name = name;
	}

	public int getBrandID() {
		return brandID;
	}

	public void setBrandID(int brandID) {
		this.brandID = brandID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
