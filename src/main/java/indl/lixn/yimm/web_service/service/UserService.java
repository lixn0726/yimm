package indl.lixn.yimm.web_service.service;

import indl.lixn.yimm.web_service.entity.User;

/**
 * @author lixn
 * @description
 * @date 2023/01/03 10:41
 **/
public interface UserService {

    void addUser(User user);

    void delUser(String uId);

    default void delUser(User user) {
        delUser(user.getUid());
    }

    User getUser(String uId);
}
