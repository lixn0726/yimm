package indl.lixn.yimm.web_service.repository;

import indl.lixn.yimm.web_service.entity.FriendShip;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lixn
 * @description
 * @date 2023/01/03 10:36
 **/
@Mapper
public interface FriendShipMapper {

    void addFriendShip(FriendShip friendShip);

    void delFriendShip(String fsId);

    FriendShip getFriendShip(String fsId);

}
