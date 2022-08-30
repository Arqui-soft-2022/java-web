package web.qrcode.model.dto;

/*
 * Clase para realizar la peticion de historial de codigos
 */

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
