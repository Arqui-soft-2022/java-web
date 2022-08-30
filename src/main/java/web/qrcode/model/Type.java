package web.qrcode.model;

import java.util.List;

public class Type {
    private Integer idType;
    private String urlImg;
    private String descripcion;
    private List<QrCode> qrCodeList;

    public Type(){}

    public Type(Integer idType, String urlImg, String descripcion, List<QrCode> qrCodeList) {
        this.idType = idType;
        this.urlImg = urlImg;
        this.descripcion = descripcion;
        this.qrCodeList = qrCodeList;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<QrCode> getQrCodeList() {
        return qrCodeList;
    }

    public void setQrCodeList(List<QrCode> qrCodeList) {
        this.qrCodeList = qrCodeList;
    }
}
