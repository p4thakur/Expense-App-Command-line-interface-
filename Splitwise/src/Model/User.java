package Model;

public class User {
	
	private String id;
	private String name;
	private String gmail;
    private String phone;
    
    public User(String id, String name, String gmail, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.gmail = gmail;
		this.phone = phone;
	}
   
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGmail() {
		return gmail;
	}
	
	public void setGmail(String gmail) {
		this.gmail = gmail;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
    

}
