package kutuphaneYonetimSistemi.kutuphaneYonetimSistemi;

public class User {
    private int id;
    private String name;
    private int age;
    private int phoneNumber;
    private String password;
    private String role;

    public User(String name, int age, int phoneNumber, String password, String role) {
        this.id = IdGenerator.generateId(); //benzersiz id 
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


}
