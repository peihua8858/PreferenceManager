package com.fz.premanager;

import android.content.SharedPreferences;
import android.os.Parcelable;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.socks.library.KLog;

import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 私有数据管理实现
 *
 * @author dingpeihua
 * @version 1.0
 * @date 2020/6/23 14:58
 */
public class PreferenceManagerImpl implements IPreferenceManager, IParcelable {
    /**
     * SharedPreferences实例对象
     */
    @NonNull
    private final SharedPreferences sharedPreferences;
    private Gson mGson;

    public PreferenceManagerImpl(@NonNull SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @NonNull
    @Override
    public SharedPreferences getPreferences() {
        return sharedPreferences;
    }

    /**
     * 保存double类型数据到配置文件中
     *
     * @param key   键名称
     * @param value double类型数据
     * @return 成功返回true，失败返回false
     */
    @Override
    public synchronized boolean save(@NonNull String key, double value) {
        return putString(key, String.valueOf(value)).commit();
    }

    /**
     * 将map集合数据保存到sharedPreference
     *
     * @param data 需要保存的数据集合
     * @return 保存成功返回true ，反之false
     * @author dingpeihua
     * @date 2017/8/22 17:43
     * @version 1.0
     */
    @Override
    public synchronized boolean saveMap(@NonNull Map<String, Object> data) {
        if (data == null || data.size() == 0) {
            return false;
        }
        Iterator<String> keys = data.keySet().iterator();
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        if (editor == null) {
            throw new NullPointerException("editor == null");
        }
        while (keys.hasNext()) {
            final String key = keys.next();
            savePrimitiveKeyValue(editor, key, data.get(key));
        }
        editor.apply();
        return true;
    }

    /**
     * 保存JSONObject类型数据到配置文件中，该方法会遍历JSONObject中的数据
     *
     * @param jsonObject JSONObject类型数据
     * @return 成功返回true，失败返回false
     */
    @Override
    public synchronized boolean save(@NonNull JSONObject jsonObject) {
        if (jsonObject != null) {
            Iterator<String> iterator = jsonObject.keys();
            while (iterator.hasNext()) {
                final String key = iterator.next();
                final Object value = jsonObject.opt(key);
                if (value != null) {
                    save(key, value);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    @Nullable
    public <T> T read(String key, Class<T> clazz) {
        Object returnValue;
        if (Boolean.class.isAssignableFrom(clazz)) {
            returnValue = getBoolean(key, false);
        } else if (String.class.isAssignableFrom(clazz)) {
            returnValue = getString(key, "");
        } else if (Float.class.isAssignableFrom(clazz)) {
            returnValue = getFloat(key, 0.0F);
        } else if (Integer.class.isAssignableFrom(clazz)) {
            returnValue = getInt(key, 0);
        } else if (Long.class.isAssignableFrom(clazz)) {
            returnValue = getLong(key, 0L);
        } else if (Short.class.isAssignableFrom(clazz)) {
            returnValue = getShort(key, (short) 0);
        } else if (Double.class.isAssignableFrom(clazz)) {
            returnValue = getDouble(key, 0.0);
        } else if (Parcelable.class.isAssignableFrom(clazz)) {
            returnValue = readParcelable(key, clazz.asSubclass(Parcelable.class));
        } else {
            String content = getString(key);
            return getGson().fromJson(content, clazz);
        }
        try {
            return clazz.cast(returnValue);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public <T> boolean save(@NonNull String key, @NonNull T value) {
        savePrimitiveKeyValue(edit(), key, value);
        return true;
    }

    protected final void savePrimitiveKeyValue(SharedPreferences.Editor editor, String key, Object value) {
        if (value == null) {
            KLog.e("value is null.");
            return;
        }
        if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof Short) {
            editor.putString(key, String.valueOf(value));
        } else if (value instanceof Double) {
            editor.putString(key, String.valueOf(value));
        } else if (value instanceof Parcelable) {
            saveParcelable(key, (Parcelable) value);
        } else {
            String content = getGson().toJson(value);
            editor.putString(key, content);
        }
    }

    @Override
    public short getShort(@NonNull String key) {
        return getShort(key, (short) 0);
    }

    @Override
    public short getShort(@NonNull String key, short defaultValue) {
        try {
            return Short.parseShort(sharedPreferences.getString(key, String.valueOf(defaultValue)));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return defaultValue;
    }


    @Override
    public double getDouble(@NonNull String key) {
        return getDouble(key, 0.0);
    }

    @Override
    public double getDouble(@NonNull String key, double defaultValue) {
        try {
            return Double.parseDouble(sharedPreferences.getString(key, String.valueOf(defaultValue)));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    @Override
    public <T extends Parcelable> T readParcelable(String key, Class<T> clazz) {
        String content = read(key, "");
        return getGson().fromJson(content, clazz);
    }

    @Override
    public boolean saveParcelable(String key, Parcelable value) {
        String content = getGson().toJson(value);
        return save(key, content);
    }

    @Override
    public <T extends Parcelable> boolean saveArrayListParcelable(String key, List<T> values) {
        String content = getGson().toJson(values);
        return save(key, content);
    }

    @Override
    public <T extends Parcelable> List<T> readArrayListParcelable(String key, Class<List<T>> type) {
        String content = read(key, "");
        if (!TextUtils.isEmpty(content)) {
            List<T> result = getGson().fromJson(content, type);
            return result != null ? result : new ArrayList<>();
        }
        return new ArrayList<>();
    }

    @Override
    public <T extends Parcelable> List<T> readArrayListParcelable(String key, Type type) {
        String content = read(key, "");
        if (!TextUtils.isEmpty(content)) {
            List<T> result = getGson().fromJson(content, type);
            return result != null ? result : new ArrayList<>();
        }
        return new ArrayList<>();
    }

    @Override
    public <T> boolean saveArrayList(String key, List<T> values) {
        String content = getGson().toJson(values);
        return save(key, content);
    }

    @Override
    public <T> List<T> readArrayList(String key, Type type) {
        String content = read(key, "");
        if (!TextUtils.isEmpty(content)) {
            List<T> result = getGson().fromJson(content, type);
            return result != null ? result : new ArrayList<>();
        }
        return new ArrayList<>();
    }

    private Gson getGson() {
        Gson gson = mGson;
        if (gson == null) {
            gson = new Gson();
            mGson = gson;
        }
        return gson;
    }
}
