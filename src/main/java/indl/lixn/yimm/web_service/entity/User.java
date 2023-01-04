package indl.lixn.yimm.web_service.entity;

/**
 * @author lixn
 * @description
 * @date 2023/01/03 10:23
 **/
public class User {

    private Integer id;

    private String uId;

    private String password;

    private String infoId;

    /** 是否在线 */
    private boolean online;

    public String getUid() {
        return this.uId;
    }

    public String getInfoId() {
        return this.infoId;
    }

    public Integer getId() {
        return id;
    }

    public String getuId() {
        return uId;
    }

    public String getPassword() {
        return password;
    }

    public boolean isOnline() {
        return online;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public void online() {
        this.online = true;
    }
}
