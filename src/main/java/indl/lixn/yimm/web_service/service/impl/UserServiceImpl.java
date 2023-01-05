package indl.lixn.yimm.web_service.service.impl;

import indl.lixn.yimm.common.util.ObjectUtils;
import indl.lixn.yimm.common.util.StringUtils;
import indl.lixn.yimm.web_service.entity.User;
import indl.lixn.yimm.web_service.entity.UserInfo;
import indl.lixn.yimm.web_service.repository.UserMapper;
import indl.lixn.yimm.web_service.service.UserInfoService;
import indl.lixn.yimm.web_service.service.UserService;
import indl.lixn.yimm.web_service.util.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lixn
 * @description
 * @date 2023/01/03 11:12
 **/
@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;

//    @Resource
//    private UserInfoService userInfoService;

    @Override
    public void addUser(User user) {
        if (ObjectUtils.isNull(user) || StringUtils.hasBlank(user.getPassword())) {
            // TODO
            throw new IllegalArgumentException();
        }
        // bcrypt加密
        user.setPassword(UserUtils.saltedPwd(user.getPassword()));
//        userInfoService.addUserInfo(buildInfo());
        userMapper.addUser(user);
    }

    @Override
    public void delUser(String uId) {
        userMapper.delUser(uId);
    }

    @Override
    public User getUser(String uId) {
        return userMapper.getUser(uId);
    }

    private UserInfo buildInfo() {
        return new UserInfo();
    }
}
