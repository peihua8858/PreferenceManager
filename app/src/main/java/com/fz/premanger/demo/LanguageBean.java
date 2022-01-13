package com.fz.premanger.demo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Locale;

/**
 * @author linjinyan
 * @des 切换语言bean
 * @date 2019/4/3
 */
public class LanguageBean implements Parcelable {
    /**
     * 语言标识--注意服务端国家站语言码与本地的对应
     */
    public String code="EN";
    /**
     * 语言名
     */
    public String name="English";

    public LanguageBean(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public LanguageBean() {
    }

    @Override
    public String toString() {
        return "LanguageBean{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.code);
        dest.writeString(this.name);
    }

    protected LanguageBean(Parcel in) {
        this.code = in.readString();
        this.name = in.readString();
    }

    public static final Creator<LanguageBean> CREATOR = new Creator<LanguageBean>() {
        @Override
        public LanguageBean createFromParcel(Parcel source) {
            return new LanguageBean(source);
        }

        @Override
        public LanguageBean[] newArray(int size) {
            return new LanguageBean[size];
        }
    };
}
