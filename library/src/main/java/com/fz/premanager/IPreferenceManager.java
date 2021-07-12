package com.fz.premanager;

import android.content.SharedPreferences.*;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.socks.library.KLog;

import org.json.JSONObject;

import java.util.Map;
import java.util.Set;

/**
 * 程序私有数据存储管理
 *
 * @author dingpeihua
 * @version 1.0
 * @date 2020/6/23 14:20
 */
public interface IPreferenceManager extends SharedPreferences, Editor {
    /**
     * 存储key -value
     *
     * @param key
     * @param value
     * @return {@link Editor}
     * @author dingpeihua
     * @date 2020/6/23 15:11
     * @version 1.0
     * @see Editor#putString(String, String)
     */
    @Override
    default Editor putString(String key, @Nullable String value) {
        return edit().putString(key, value);
    }

    /**
     * 存储key -value
     *
     * @param key
     * @param values
     * @return {@link Editor}
     * @author dingpeihua
     * @date 2020/6/23 15:11
     * @version 1.0
     * @see Editor#putStringSet(String, Set<String>)
     */
    @Override
    default Editor putStringSet(String key, @Nullable Set<String> values) {
        return edit().putStringSet(key, values);
    }

    /**
     * 存储key -value
     *
     * @param key
     * @param value
     * @return {@link Editor}
     * @author dingpeihua
     * @date 2020/6/23 15:11
     * @version 1.0
     * @see Editor#putInt(String, int)
     */
    @Override
    default Editor putInt(String key, int value) {
        return edit().putInt(key, value);
    }

    /**
     * 存储key -value
     *
     * @param key
     * @param value
     * @return {@link Editor}
     * @author dingpeihua
     * @date 2020/6/23 15:11
     * @version 1.0
     * @see Editor#putLong(String, long)
     */
    @Override
    default Editor putLong(String key, long value) {
        return edit().putLong(key, value);
    }

    /**
     * 存储key -value
     *
     * @param key
     * @param value
     * @return {@link Editor}
     * @author dingpeihua
     * @date 2020/6/23 15:11
     * @version 1.0
     * @see Editor#putFloat(String, float)
     */
    @Override
    default Editor putFloat(String key, float value) {
        return edit().putFloat(key, value);
    }

    /**
     * 存储key -value
     *
     * @param key
     * @param value
     * @return {@link Editor}
     * @author dingpeihua
     * @date 2020/6/23 15:11
     * @version 1.0
     * @see Editor#putBoolean(String, boolean)
     */
    @Override
    default Editor putBoolean(String key, boolean value) {
        return edit().putBoolean(key, value);
    }

    /**
     * 删除key -value
     *
     * @param key
     * @return {@link Editor}
     * @author dingpeihua
     * @date 2020/6/23 15:11
     * @version 1.0
     * @see Editor#remove(String)
     */
    @Override
    default Editor remove(String key) {
        boolean result = edit().remove(key).commit();
        KLog.d("remove result:" + result);
        return this;
    }

    /**
     * 清除数据
     *
     * @return {@link Editor}
     * @author dingpeihua
     * @date 2020/6/23 15:11
     * @version 1.0
     * @see Editor#clear()
     */
    @Override
    default Editor clear() {
        boolean result = edit().clear().commit();
        KLog.d("clear result:" + result);
        return this;
    }

    /**
     * 同步提交操作
     *
     * @return {@link Editor}
     * @author dingpeihua
     * @date 2020/6/23 15:11
     * @version 1.0
     * @see Editor#clear()
     */
    @Override
    default boolean commit() {
        return edit().commit();
    }

    /**
     * 异步提交操作
     *
     * @return {@link Editor}
     * @author dingpeihua
     * @date 2020/6/23 15:11
     * @version 1.0
     * @see Editor#clear()
     */
    @Override
    default void apply() {
        edit().apply();
    }

    /**
     * 获取存储对象
     *
     * @return SharedPreferences
     * @author dingpeihua
     * @date 2020/6/23 15:39
     * @version 1.0
     */
    @NonNull
    SharedPreferences getPreferences();

    /**
     * 存储key -value
     *
     * @param key   要存储的键名
     * @param value 要存储的值
     * @return 保存成功返回true，否则返回false
     * @author dingpeihua
     * @date 2020/6/23 14:20
     * @version 1.0
     */
    default boolean save(@NonNull String key, boolean value) {
        return putBoolean(key, value).commit();
    }

    /**
     * 存储key -value
     *
     * @param key   要存储的键名
     * @param value 要存储的值
     * @return 保存成功返回true，否则返回false
     * @author dingpeihua
     * @date 2020/6/23 14:20
     * @version 1.0
     */
    default boolean save(@NonNull String key, int value) {
        return putInt(key, value).commit();
    }

    /**
     * 存储key -value
     *
     * @param key   要存储的键名
     * @param value 要存储的值
     * @return 保存成功返回true，否则返回false
     * @author dingpeihua
     * @date 2020/6/23 14:20
     * @version 1.0
     */
    default boolean save(@NonNull String key, long value) {
        return putLong(key, value).commit();
    }

    /**
     * 存储key -value
     *
     * @param key   要存储的键名
     * @param value 要存储的值
     * @return 保存成功返回true，否则返回false
     * @author dingpeihua
     * @date 2020/6/23 14:20
     * @version 1.0
     */
    default boolean save(@NonNull String key, float value) {
        return putFloat(key, value).commit();
    }

    /**
     * 存储key -value
     *
     * @param key   要存储的键名
     * @param value 要存储的值
     * @return 保存成功返回true，否则返回false
     * @author dingpeihua
     * @date 2020/6/23 14:20
     * @version 1.0
     */
    boolean save(@NonNull String key, double value);

    /**
     * 存储key -value
     *
     * @param key   要存储的键名
     * @param value 要存储的值
     * @return 保存成功返回true，否则返回false
     * @author dingpeihua
     * @date 2020/6/23 14:20
     * @version 1.0
     */
    default boolean save(@NonNull String key, String value) {
        return putString(key, value).commit();
    }

    /**
     * 存储key -value
     *
     * @param data 要存储的map 集合，不能为null
     * @return 保存成功返回true，否则返回false
     * @author dingpeihua
     * @date 2020/6/23 14:20
     * @version 1.0
     */
    boolean saveMap(@NonNull Map<String, Object> data);

    /**
     * 存储key -value
     *
     * @param data 要存储的JSONObject对象
     * @return 保存成功返回true，否则返回false
     * @author dingpeihua
     * @date 2020/6/23 14:20
     * @version 1.0
     */
    boolean save(@NonNull JSONObject data);

    /**
     * 存储key -value
     * <T>值读取{@link Boolean}、{@link String}、{@link Float}、
     * {@link Integer}、{@link Long}
     * 其他存储默认按{@link String}返回，如果转换异常则返回默认值
     *
     * @param key   要存储的键名
     * @param value 要存储的任意类型值
     * @return 保存成功返回true，否则返回false
     * @author dingpeihua
     * @date 2020/6/23 14:20
     * @version 1.0
     */
    <T> boolean save(@NonNull String key, @NonNull T value);

    /**
     * 读取{@link Boolean} 类型数据
     *
     * @param key 要存储的键名
     * @return 读取成功返回读取到的值，否则返回false
     * @author dingpeihua
     * @date 2020/6/23 14:20
     * @version 1.0
     */
    default boolean getBoolean(@NonNull String key) {
        return getPreferences().getBoolean(key, false);
    }

    /**
     * 读取{@link Boolean} 类型数据
     *
     * @param key          要存储的键名
     * @param defaultValue 如果未读取到对应数据，则默认返回该值
     * @return 读取成功返回读取到的值，否则返回defaultValue
     * @author dingpeihua
     * @date 2020/6/23 14:20
     * @version 1.0
     */
    @Override
    default boolean getBoolean(@NonNull String key, boolean defaultValue) {
        return getPreferences().getBoolean(key, defaultValue);
    }

    /**
     * 读取{@link Integer} 类型数据
     *
     * @param key 要存储的键名
     * @return 读取成功返回读取到的值，否则返回0
     * @author dingpeihua
     * @date 2020/6/23 14:20
     * @version 1.0
     */
    default int getInt(@NonNull String key) {
        return getPreferences().getInt(key, 0);
    }

    /**
     * 读取{@link Integer} 类型数据
     *
     * @param key          要存储的键名
     * @param defaultValue 如果未读取到对应数据，则默认返回该值
     * @return 读取成功返回读取到的值，否则返回defaultValue
     * @author dingpeihua
     * @date 2020/6/23 14:20
     * @version 1.0
     */
    @Override
    default int getInt(@NonNull String key, int defaultValue) {
        return getPreferences().getInt(key, defaultValue);
    }

    /**
     * 读取{@link Integer} 类型数据
     *
     * @param key 要存储的键名
     * @return 读取成功返回读取到的值，否则返回0
     * @author dingpeihua
     * @date 2020/6/23 14:20
     * @version 1.0
     */
    short getShort(@NonNull String key);

    /**
     * 读取{@link Short} 类型数据
     *
     * @param key          要存储的键名
     * @param defaultValue 如果未读取到对应数据，则默认返回该值
     * @return 读取成功返回读取到的值，否则返回defaultValue
     * @author dingpeihua
     * @date 2020/6/23 14:20
     * @version 1.0
     */
    short getShort(@NonNull String key, short defaultValue);

    /**
     * 读取{@link Long} 类型数据
     *
     * @param key 要存储的键名
     * @return 读取成功返回读取到的值，否则返回0
     * @author dingpeihua
     * @date 2020/6/23 14:20
     * @version 1.0
     */
    default long getLong(@NonNull String key) {
        return getPreferences().getLong(key, 0L);
    }

    /**
     * 读取{@link Long} 类型数据
     *
     * @param key          要存储的键名
     * @param defaultValue 如果未读取到对应数据，则默认返回该值
     * @return 读取成功返回读取到的值，否则返回defaultValue
     * @author dingpeihua
     * @date 2020/6/23 14:20
     * @version 1.0
     */
    @Override
    default long getLong(@NonNull String key, long defaultValue) {
        return getPreferences().getLong(key, defaultValue);
    }

    /**
     * 读取{@link Float} 类型数据
     *
     * @param key 要存储的键名
     * @return 读取成功返回读取到的值，否则返回0
     * @author dingpeihua
     * @date 2020/6/23 14:20
     * @version 1.0
     */
    default float getFloat(@NonNull String key) {
        return getPreferences().getFloat(key, 0.0f);
    }

    /**
     * 读取{@link Float} 类型数据
     *
     * @param key          要存储的键名
     * @param defaultValue 如果未读取到对应数据，则默认返回该值
     * @return 读取成功返回读取到的值，否则返回defaultValue
     * @author dingpeihua
     * @date 2020/6/23 14:20
     * @version 1.0
     */
    @Override
    default float getFloat(@NonNull String key, float defaultValue) {
        return getPreferences().getFloat(key, defaultValue);
    }

    /**
     * 读取{@link Double} 类型数据
     *
     * @param key 要存储的键名
     * @return 读取成功返回读取到的值，否则返回0.0
     * @author dingpeihua
     * @date 2020/6/23 14:20
     * @version 1.0
     */
    double getDouble(@NonNull String key);

    /**
     * 读取{@link Double} 类型数据
     *
     * @param key          要存储的键名
     * @param defaultValue 如果未读取到对应数据，则默认返回该值
     * @return 读取成功返回读取到的值，否则返回defaultValue
     * @author dingpeihua
     * @date 2020/6/23 14:20
     * @version 1.0
     */
    double getDouble(@NonNull String key, double defaultValue);

    /**
     * 读取{@link String} 类型数据
     *
     * @param key 要存储的键名
     * @return 读取成功返回读取到的值，否则返回空字符串
     * @author dingpeihua
     * @date 2020/6/23 14:20
     * @version 1.0
     */
    @NonNull
    default String getString(@NonNull String key) {
        return getPreferences().getString(key, "");
    }

    /**
     * 读取{@link String} 类型数据
     *
     * @param key          要存储的键名
     * @param defaultValue 如果未读取到对应数据，则默认返回该值
     * @return 读取成功返回读取到的值，否则返回defaultValue
     * @author dingpeihua
     * @date 2020/6/23 14:20
     * @version 1.0
     */
    @Override
    @NonNull
    default String getString(@NonNull String key, @NonNull String defaultValue) {
        return getPreferences().getString(key, defaultValue);
    }

    @Nullable
    <T> T read(String key, Class<T> clazz);

    /**
     * 读取{@link String} 类型数据
     * <T>值读取{@link Boolean}、{@link String}、{@link Float}、
     * {@link Integer}、{@link Long}
     * 其他返回默认按{@link String}返回，如果转换异常则返回默认值
     *
     * @param key          要存储的键名
     * @param defaultValue 如果未读取到对应数据，则默认返回该值
     * @return 读取成功返回读取到的值，否则返回defaultValue
     * @author dingpeihua
     * @date 2020/6/23 14:20
     * @version 1.0
     */
    /**
     * 读取{@link String} 类型数据
     * <T>值读取{@link Boolean}、{@link String}、{@link Float}、
     * {@link Integer}、{@link Long}
     * 其他返回默认按{@link String}返回，如果转换异常则返回默认值
     *
     * @param key          要存储的键名
     * @param defaultValue 如果未读取到对应数据，则默认返回该值
     * @return 读取成功返回读取到的值，否则返回defaultValue
     * @author dingpeihua
     * @date 2020/6/23 14:20
     * @version 1.0
     */
    default <T> T read(String key, @NonNull T defaultValue) {
        Object returnValue;
        if (defaultValue instanceof Boolean) {
            returnValue = getBoolean(key, (Boolean) defaultValue);
        } else if (defaultValue instanceof String) {
            returnValue = getString(key, (String) defaultValue);
        } else if (defaultValue instanceof Float) {
            returnValue = getFloat(key, (Float) defaultValue);
        } else if (defaultValue instanceof Integer) {
            returnValue = getInt(key, (Integer) defaultValue);
        } else if (defaultValue instanceof Long) {
            returnValue = getLong(key, (Long) defaultValue);
        } else {
            returnValue = getString(key);
        }
        try {
            return (T) returnValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 检索偏好所有值。
     *
     * @return
     * @author dingpeihua
     * @date 2020/6/23 17:45
     * @version 1.0
     */
    @Override
    default Map<String, ?> getAll() {
        return getPreferences().getAll();
    }

    /**
     * 获取set 数据集
     *
     * @param key
     * @param defValues
     * @return
     * @author dingpeihua
     * @date 2020/6/23 15:26
     * @version 1.0
     * @see SharedPreferences#getStringSet(String, Set)
     */
    @Nullable
    @Override
    default Set<String> getStringSet(String key, @Nullable Set<String> defValues) {
        return getPreferences().getStringSet(key, defValues);
    }

    /**
     * 检查偏好是否包含偏好
     *
     * @param key
     * @return
     * @author dingpeihua
     * @date 2020/6/23 15:27
     * @version 1.0
     * @see SharedPreferences#contains(String)
     */
    @Override
    default boolean contains(String key) {
        return getPreferences().contains(key);
    }

    /**
     * 创建这些首选项，通过它可以进行修改，数据的喜好和原子提交这些更改回共享偏好对象的新编辑器
     *
     * @return
     * @author dingpeihua
     * @date 2020/6/23 15:27
     * @version 1.0
     * @see SharedPreferences#contains(String)
     */
    @Override
    default Editor edit() {
        return getPreferences().edit();
    }

    /**
     * 注册一个在变化发生的偏好时要调用的回调
     *
     * @param listener
     * @author dingpeihua
     * @date 2020/6/23 15:28
     * @version 1.0
     */
    @Override
    default void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
        getPreferences().registerOnSharedPreferenceChangeListener(listener);
    }

    /**
     * 注销以前的回调
     *
     * @param listener
     * @author dingpeihua
     * @date 2020/6/23 15:29
     * @version 1.0
     */
    @Override
    default void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
        getPreferences().unregisterOnSharedPreferenceChangeListener(listener);
    }
}
