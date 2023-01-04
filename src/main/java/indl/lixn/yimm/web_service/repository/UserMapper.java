package indl.lixn.yimm.web_service.repository;

import indl.lixn.yimm.web_service.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lixn
 * @description
 * @date 2023/01/03 10:39
 **/
@Mapper
public interface UserMapper {

    User getUser(String uId);

    void addUser(User user);

    String getPassword(String uId);

    void delUser(String uId);

}
