package HotelBooking.backend.persistant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {
	@Id
	private String username;
	private String password;
	private String role;
	private boolean enable;
	private String avatarPath;
	@Column(columnDefinition = "nvarchar(256)")
	private String fullName;
	public Account() {};
	
	public Account(String username, String password,String fullName, String avatarPath) {
		super();
		this.username = username;
		this.password = password;
		this.role = "ADMIN";
		this.avatarPath = avatarPath;
		this.fullName=fullName;
		enable=true;
	}
	public Account(String username,String fullName, String avatarPath) {
		super();
		this.username = username;
		this.password = "OAUTH2";
		this.role = "USER";
		this.avatarPath = avatarPath;
		this.fullName=fullName;
		enable=false;
	}
	
	
	
	

	public String getAvatarPath() {
		return avatarPath;
	}

	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	
	
	
	
	
	
	
}
