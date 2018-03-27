
public class Employee {
	private String fName;
	private String lName;
	private String phone;
	private String department;
	private String gender;
	private String title;
	
	public Employee(String fName, String lName, String phone, String department, String gender, String title){
		this.fName = fName;
		this.lName = lName;
		this.phone = phone;
		this.department = department;
		this.gender = gender;
		this.title = title;
	}
	
	public String getFName(){
		return this.fName;
	}
	
	public String getLName(){
		return this.lName;
	}
	
	public String getPhone(){
		return this.phone;
	}
	
	public String getDepartment(){
		return this.department;
	}
	
	public String getGender(){
		return this.gender;
	}
	
	public String getTitle(){
		return this.title;
	}
}
