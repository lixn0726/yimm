package indl.lixn.yimm.web_service.service;

import indl.lixn.yimm.web_service.entity.User;
import indl.lixn.yimm.web_service.entity.UserInfo;

import java.util.List;

/**
 * @author lixn
 * @description
 * @date 2023/01/03 10:41
 **/
public interface UserInfoService {

    List<String> getFriends(String uId);

    default List<String> getFriends(User user) {
        return getFriends(user.getUid());
    }

    UserInfo getUserInfo(String infoId);

    default UserInfo getUserInfo(User user) {
        return getUserInfo(user.getInfoId());
    }

    void addUserInfo(UserInfo info);

    void delUserInfo(String infoId);

    default void delUserInfo(UserInfo info) {
        delUserInfo(info.getInfoId());
    }

}
