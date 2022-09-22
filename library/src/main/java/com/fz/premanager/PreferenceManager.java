package com.fz.premanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * 私有数据存储管理
 *
 * @author dingpeihua
 * @version 1.0
 * @date 2020/6/23 14:57
 */
public class PreferenceManager implements IPreferenceManager {
    private static PreferenceManager mManager;
    @NonNull
    private final IPreferenceManager mRealForeverManager;
    protected Context mContext;

    protected PreferenceManager(@NonNull Context context, @NonNull SharedPreferences sharedPreferences) {
        this.mContext = context.getApplicationContext();
        mRealForeverManager = new PreferenceManagerImpl(sharedPreferences);
    }

    private PreferenceManager(@NonNull Context context) {
        this(context, context.getSharedPreferences(context.getPackageName() + "_profiles",
                Context.MODE_PRIVATE));
    }

    public static PreferenceManager initManager(@NonNull Context context) {
        if (mManager == null) {
            synchronized (PreferenceManager.class) {
                if (mManager == null) {
                    mManager = new PreferenceManager(context);
                }
            }
        }
        return mManager;
    }

    public static PreferenceManager initManager(@NonNull Context context,
                                                @NonNull SharedPreferences sharedPreferences) {
        if (mManager == null) {
            synchronized (PreferenceManager.class) {
                if (mManager == null) {
                    mManager = new PreferenceManager(context, sharedPreferences);
                }
            }
        }
        return mManager;
    }

    public static PreferenceManager getInstance() {
        if (mManager == null) {
            throw new NullPointerException("PreferenceManager is not initialized.");
        }
        return mManager;
    }

    @NonNull
    @Override
    public SharedPreferences getPreferences() {
        return mRealForeverManager.getPreferences();
    }

    @Override
    public boolean save(@NonNull String key, double value) {
        return mRealForeverManager.save(key, value);
    }

    @Override
    public boolean saveMap(@NonNull Map<String, Object> data) {
        return mRealForeverManager.saveMap(data);
    }

    @Override
    public boolean save(@NonNull JSONObject data) {
        return mRealForeverManager.save(data);
    }

    @Override
    public <T> boolean save(@NonNull String key, @NonNull T value) {
        return mRealForeverManager.save(key, value);
    }

    @Override
    public short getShort(@NonNull String key) {
        return mRealForeverManager.getShort(key);
    }

    @Override
    public short getShort(@NonNull String key, short defaultValue) {
        return mRealForeverManager.getShort(key, defaultValue);
    }

    @Override
    public double getDouble(@NonNull String key) {
        return mRealForeverManager.getDouble(key);
    }

    @Override
    public double getDouble(@NonNull String key, double defaultValue) {
        return mRealForeverManager.getDouble(key, defaultValue);
    }

    @Nullable
    @Override
    public <T> T read(String key, Class<T> clazz) {
        return mRealForeverManager.read(key, clazz);
    }

    @Override
    public <T extends Parcelable> T readParcelable(String key, Class<T> clazz) {
        return mRealForeverManager.readParcelable(key, clazz);
    }

    @Override
    public boolean saveParcelable(String key, Parcelable value) {
        return mRealForeverManager.saveParcelable(key, value);
    }

    @Override
    public <T extends Parcelable> boolean saveArrayListParcelable(String key, List<T> values) {
        return mRealForeverManager.saveArrayListParcelable(key, values);
    }

    @Override
    public <T extends Parcelable> List<T> readArrayListParcelable(String key, Type type) {
        return mRealForeverManager.readArrayListParcelable(key, type);
    }

    @Override
    public <T extends Serializable> boolean saveArrayList(String key, List<T> values) {
        return mRealForeverManager.saveArrayList(key, values);
    }

    @Override
    public <T extends Serializable> List<T> readArrayList(String key, Type type) {
        return mRealForeverManager.readArrayList(key, type);
    }

    public static <T> boolean saveValue(String key, T value) {
        return getInstance().save(key, value);
    }

    public static <T> T readValue(String key, T value) {
        return getInstance().read(key, value);
    }

    public static void removeKey(String key) {
        getInstance().remove(key);
    }

    public static void clearAll() {
        getInstance().clear();
    }
}

