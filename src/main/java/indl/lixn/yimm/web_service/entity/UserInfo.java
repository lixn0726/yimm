package indl.lixn.yimm.web_service.entity;

import java.util.List;

/**
 * @author lixn
 * @description
 * @date 2023/01/03 10:30
 **/
public class UserInfo {

    private Integer id;

    private String infoId;

    private String userName;

    /** 好友Id列表 */
    private List<String> friends;

    /** 上次登录时间戳 */
    private Long lastLoginTime;

    /** 下线时间戳 */
    private Long exitTime;

    public String getInfoId() {
        return infoId;
    }

    public List<String> getFriends() {
        return friends;
    }
}
