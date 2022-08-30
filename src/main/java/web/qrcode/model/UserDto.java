package web.qrcode.model;

public class UserDto 
{
	private Integer user;
	
	public UserDto(){}
	
	public UserDto(Integer user) {
		this.user = user;
	}
	
	public Integer getUser() {
		return user;
	}
	
	public void setUser(Integer user) {
		this.user = user;
	}
}
