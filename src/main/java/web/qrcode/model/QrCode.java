package web.qrcode.model;

import java.util.Date;

public class QrCode {
    private Integer idCode;
    private String url;
    private String urlCode;
    private Date date;
    private Type type;
    private Usuario user;

    public QrCode(){}

    public QrCode(Integer idCode, String url, String urlCode, Date date, Type type, Usuario user) {
        this.idCode = idCode;
        this.url = url;
        this.urlCode = urlCode;
        this.date = date;
        this.type = type;
        this.user = user;
    }

    public Integer getIdCode() {
        return idCode;
    }

    public void setIdCode(Integer idCode) {
        this.idCode = idCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlCode() {
        return urlCode;
    }

    public void setUrlCode(String urlCode) {
        this.urlCode = urlCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
}
