package ma.altenshop.dto;

public class TokenResponse {

	
	private String accessToken;
	private long dateExpiration;
	
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public long getDateExpiration() {
		return dateExpiration;
	}
	public void setDateExpiration(long dateExpiration) {
		this.dateExpiration = dateExpiration;
	}
	
	
	
	
}
