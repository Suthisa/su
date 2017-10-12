package app.kesorn.suthisa.su.utility;

/**
 * Created by suthisa on 10/12/2017.
 */

public class MyConstant {
    private String hostString = "ftp.swiftcodingthai.com";
    private String userString = "ino@swiftcodingthai.com";
    private String passwordString = "Abc12345";
    private int PortAnInt = 21;
    private String urPostUser = "http://swiftcodingthai.com/ino/adduser.php";
    private String urlGetdata = "http://swiftcodingthai.com/ino/getAllDataSuthisa.php";

    public String getUrlGetdata() {
        return urlGetdata;
    }

    public String getUrPostUser() {
        return urPostUser;
    }

    //Alt+Fn+insert >>Getter
    public String getHostString() {
        return hostString;
    }

    public String getUserString() {
        return userString;
    }

    public String getPasswordString() {
        return passwordString;
    }

    public int getPortAnInt() {
        return PortAnInt;
    }
}
