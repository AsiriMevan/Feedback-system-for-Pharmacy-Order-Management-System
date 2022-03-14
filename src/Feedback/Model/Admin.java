package Feedback.Model;

public class Admin extends Brand {

	private static final long serialVersionUID = -256913911506655244L;
	private String password;
	
	public Admin(int brandID, String name, String password) {
		super(brandID, name);
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
