package indl.lixn.yimm.common.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author lixn
 * @description
 * @date 2023/01/03 15:15
 **/
public class ObjectUtils {

    private static final Predicate<Object> IS_NULL_PREDICATE = Objects::isNull;

    public static boolean isNull(Object obj) {
        return IS_NULL_PREDICATE.test(obj);
    }

    public static boolean hasNull(Object ... args) {
        if (isNull(args) || args.length == 0) {
            return true;
        }

        return Arrays.stream(args).filter(IS_NULL_PREDICATE).count() == args.length;
    }

    public static boolean hasNull(Collection<?> col) {
        if (IS_NULL_PREDICATE.test(col)) {
            return true;
        }

        return col.stream().filter(IS_NULL_PREDICATE).count() == col.size();
    }

}
