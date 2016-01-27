package cc.cmu.edu.minisite.model;

public class ProfileRecord {
	private String username;
	private String location;
	private String email;
	private String image;
	
	public ProfileRecord() {
		username = "";
		location = "";
		email = "";
		image = "";
	}
	
	public ProfileRecord(String username, String location,
			String email, String image) {
		this.username = username;
		this.location = location;
		this.email = email;
		this.image = image;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	
	
}
