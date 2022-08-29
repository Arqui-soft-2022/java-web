package web.qrcode.model;

public class GenerateQR {

    private String url;
    private Integer user;

    public GenerateQR(){

    }

    public GenerateQR(String url, Integer user) {
        this.url = url;
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }
}
