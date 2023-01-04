package indl.lixn.yimm.web_service.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * @author lixn
 * @description
 * @date 2023/01/03 15:57
 **/
public class UserUtils {

    /**
     * 密码加盐存储
     * @param originPwd 原始密码
     * @return 加盐密码串
     */
    public static String saltedPwd(String originPwd) {
        return BCrypt.hashpw(originPwd, BCrypt.gensalt());
    }

    public static boolean isSamePwd(String originPwd, String dbPwd) {
        return BCrypt.checkpw(originPwd, dbPwd);
    }

}
