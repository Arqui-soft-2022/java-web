package web.qrcode.model;

public class QrCodeDto 
{
	private Integer id_code;
	private String url;
	private String url_code;
	private Integer id_type;
	private String date;
	
	
	public QrCodeDto() {}
	
	public QrCodeDto(Integer id_code, String url, String url_code, Integer id_type, String date) {
		super();
		this.id_code = id_code;
		this.url = url;
		this.url_code = url_code;
		this.id_type = id_type;
		this.date = date;
	}
	public Integer getId_code() {
		return id_code;
	}
	public void setId_code(Integer id_code) {
		this.id_code = id_code;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrl_code() {
		return url_code;
	}
	public void setUrl_code(String url_code) {
		this.url_code = url_code;
	}
	public Integer getId_type() {
		return id_type;
	}
	public void setId_type(Integer id_type) {
		this.id_type = id_type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
