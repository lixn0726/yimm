package indl.lixn.yimm.serialization.impl;

import com.alibaba.fastjson2.JSON;
import indl.lixn.yimm.serialization.Serialization;

/**
 * @author lixn
 * @description
 * @date 2023/01/03 10:09
 **/
public class JsonSerialization implements Serialization {

    private volatile static JsonSerialization instance;

    public static JsonSerialization getInstance() {
        if (instance == null) {
            synchronized (JsonSerialization.class) {
                if (instance == null) {
                    instance = new JsonSerialization();
                }
            }
        }
        return instance;
    }

    private JsonSerialization() {}

    @Override
    public byte[] encode(Object obj) {
        return JSON.toJSONBytes(obj);
    }

    @Override
    public <T> T decode(byte[] source, Class<T> targetClazz) {
        return JSON.parseObject(source, targetClazz);
    }
}
