package com.fz.premanager;

import android.os.Parcelable;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 安卓序列化
 *
 * @author dingpeihua
 * @version 1.0
 * @date 2020/8/25 17:07
 */
public interface IParcelable {
    /**
     * 读取序列化对象
     *
     * @param key
     * @param clazz
     * @return 保存成功返回true，否则返回false
     * @author dingpeihua
     * @date 2020/7/15 12:03
     * @version 1.0
     */
    default <T extends Parcelable> T readParcelable(String key, Class<T> clazz) {
        throw new UnsupportedOperationException("Not implements.");
    }

    /**
     * 保存序列化对象
     *
     * @param key
     * @param value
     * @return 保存成功返回true，否则返回false
     * @author dingpeihua
     * @date 2020/7/15 12:03
     * @version 1.0
     */
    default boolean saveParcelable(String key, Parcelable value) {
        throw new UnsupportedOperationException("Not implements.");
    }

    /**
     * 存储Parcelable集合数据
     *
     * @param key
     * @param values
     * @author dingpeihua
     * @date 2022/1/13 9:24
     * @version 1.0
     */
    default <T extends Parcelable> boolean saveArrayListParcelable(String key, List<T> values) {
        throw new UnsupportedOperationException("Not implements.");
    }

    /**
     * 读取Parcelable集合数据
     *
     * @param key
     * @param type
     * @return
     * @author dingpeihua
     * @date 2022/1/13 9:32
     * @version 1.0
     */
    default <T extends Parcelable> List<T> readArrayListParcelable(String key, Type type) {
        throw new UnsupportedOperationException("Not implements.");
    }
}
