package registration;
public class Admin extends Person{
	
	
	public Admin() {
		super();
		
	}
	public Admin(String name, String password, String mobile, String address) {
		super(name,password,mobile,address);
		
	}
	
	public Admin(String name, String email, String password, String mobile, String address) {
		super(name,email,password,mobile,address);
	}
	
	
	
	
}
