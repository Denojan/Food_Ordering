package registration;

public class User extends Person{
	
	
	public User() {
		super();
		
	}
	public User(String name, String password, String mobile, String address) {
		super(name,password,mobile,address);
		
	}
	public User(String name, String email, String password, String mobile, String address) {
		super(name,email,password,mobile,address);
	}
	
	
	
	
}
