package cc.cmu.edu.minisite.model;

public class ShakeEvent {
	private static final double RADIUS = 10;
	private String username;
	private long timestamp;
	private double latitude;
	private double longitude;
	private String match;

	public ShakeEvent(String username, long timestamp, double latitude, double longitude) {
		this.username = username;
		this.timestamp = timestamp;
		this.latitude = latitude;
		this.longitude = longitude;
		match = null;
	}

	public String getUsername() {
		return username;
	}

	public void setUid(String username) {
		this.username = username;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getMatch() {
		return match;
	}

	public void setMatch(String match) {
		this.match = match;
	}

	public boolean withinArea(double longitude, double lantitude) {
		return (this.longitude - longitude) * (this.longitude - longitude)
				+ (this.latitude - latitude) * (this.latitude - latitude) < RADIUS;
	}
}
