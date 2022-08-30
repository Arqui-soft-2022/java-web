package web.qrcode.model;

import java.util.List;

public class Usuario {

    private int idUsuario;
    private String username;
    private String password;
    private String email;
    private String name;
    List<QrCode> qrCodeList;

    public Usuario(){}

    public Usuario(int idUsuario, String username, String password, String email, String name, List<QrCode> qrCodeList) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.qrCodeList = qrCodeList;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
    }

    public List<QrCode> getQrCodeList() {
        return qrCodeList;
    }

    public void setQrCodeList(List<QrCode> qrCodeList) {
        this.qrCodeList = qrCodeList;
    }
}
