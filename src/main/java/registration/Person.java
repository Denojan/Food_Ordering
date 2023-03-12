package registration;

public class Person {
	protected String name;
	protected String email;
	protected String password;
	protected String mobile;
	protected String address;
	
	
	
	public Person() {
		super();
		this.name=" ";
		this.email=" ";
		this.mobile=" ";
		this.password= " ";
		this.address=" ";
	}
	public Person(String name, String password, String mobile, String address) {
		super();
		this.name = name;
		this.password = password;
		this.mobile = mobile;
		this.address = address;
	}
	
	public Person(String name, String email, String password, String mobile, String address) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.address = address;
	}
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	public String getemail() {
		return email;
	}
	public void setemail(String email) {
		this.email = email;
	}
	public String getpassword() {
		return password;
	}
	public void setpassword(String password) {
		this.password = password;
	}
	public String getmobile() {
		return mobile;
	}
	public void setmobile(String mobile) {
		this.mobile = mobile;
	}
	public String getaddress() {
		return address;
	}
	public void setaddress(String address) {
		this.address = address;
	}
	
	
	
	
}
