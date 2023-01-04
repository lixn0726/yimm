package indl.lixn.yimm.web_service.repository;

import indl.lixn.yimm.web_service.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lixn
 * @description
 * @date 2023/01/03 10:39
 **/
@Mapper
public interface UserInfoMapper {

    UserInfo getInfo(String infoId);

    void addInfo(UserInfo info);

    void delInfo(String infoId);

    default void delInfo(UserInfo info) {
        delInfo(info.getInfoId());
    }
}
