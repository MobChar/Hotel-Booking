package HotelBooking.backend.bussiness.DTO;

import java.io.Serializable;

public class CommentDTO implements Serializable{
	private String username;
	private String avatarPath;
	private String fullName;
	private String comment;
	
	
	
	
	public CommentDTO(String username, String avatarPath, String fullName, String comment) {
		super();
		this.username = username;
		this.avatarPath = avatarPath;
		this.fullName = fullName;
		this.comment = comment;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAvatarPath() {
		return avatarPath;
	}
	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
