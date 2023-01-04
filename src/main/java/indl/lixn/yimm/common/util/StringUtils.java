package indl.lixn.yimm.common.util;

/**
 * @author lixn
 * @description
 * @date 2023/01/03 15:10
 **/
public class StringUtils {

    public static boolean isBlank(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean hasBlank(String ... strs) {
        if (ObjectUtils.isNull(strs)) {
            return true;
        }
        for (String str : strs) {
            if (isBlank(str)) {
                return true;
            }
        }
        return false;
    }

}
