package indl.lixn.yimm.serialization;

/**
 * @author lixn
 * @description
 * @date 2023/01/03 10:15
 **/
public interface Serialization {

    /**
     * 编码：pojo -> byte[]
     * @param obj pojo
     * @return 字节数组
     */
    byte[] encode(Object obj);

    /**
     * 解码：byte[] -> pojo
     * @param source 字节数组
     * @param targetClazz 目标pojo的类对象
     * @param <T> 泛型
     * @return 目标pojo对象
     */
    <T> T decode(byte[] source, Class<T> targetClazz);

}
