package indl.lixn.yimm.web_service.service.impl;

import indl.lixn.yimm.web_service.entity.UserInfo;
import indl.lixn.yimm.web_service.repository.UserInfoMapper;
import indl.lixn.yimm.web_service.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lixn
 * @description
 * @date 2023/01/03 11:13
 **/
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private static final Logger log = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private UserServiceImpl userService;

    @Override
    public List<String> getFriends(String uId) {
        return userInfoMapper.getInfo(userService.getUser(uId).getInfoId()).getFriends();
    }

    @Override
    public UserInfo getUserInfo(String infoId) {
        return userInfoMapper.getInfo(infoId);
    }

    @Override
    public void addUserInfo(UserInfo info) {
        userInfoMapper.addInfo(info);
    }

    @Override
    public void delUserInfo(String infoId) {
        userInfoMapper.delInfo(infoId);
    }

}
