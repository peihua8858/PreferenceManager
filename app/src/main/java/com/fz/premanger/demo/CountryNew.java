package com.fz.premanger.demo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 国家切换
 */
public class CountryNew implements Parcelable, Serializable {

    /**
     * 1为新兴市场 0非新兴市场
     */
    public String is_emerging_country = "1";
    public String region_code = "US";
    public String region_id = "44";
    public String region_name = "United States";
    public List<LanguageBean> support_lang = new ArrayList<>();
    public ExchangeBean exchange = new ExchangeBean();

    public String getRegion_code() {
        return region_code;
    }

    public void setRegion_code(String region_code) {
        this.region_code = region_code;
    }

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public ExchangeBean getExchange() {
        return exchange;
    }

    public void setExchange(ExchangeBean exchange) {
        this.exchange = exchange;
    }

    public CountryNew() {
    }

    @Override
    public String toString() {
        return "CountryNew{" +
                "is_emerging_country='" + is_emerging_country + '\'' +
                ", region_code='" + region_code + '\'' +
                ", region_id='" + region_id + '\'' +
                ", region_name='" + region_name + '\'' +
                ", support_lang=" + support_lang +
                ", exchange=" + exchange +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.is_emerging_country);
        dest.writeString(this.region_code);
        dest.writeString(this.region_id);
        dest.writeString(this.region_name);
        dest.writeParcelable(this.exchange, flags);
        dest.writeTypedList(this.support_lang);
    }

    protected CountryNew(Parcel in) {
        this.is_emerging_country = in.readString();
        this.region_code = in.readString();
        this.region_id = in.readString();
        this.region_name = in.readString();
        this.exchange = in.readParcelable(ExchangeBean.class.getClassLoader());
        this.support_lang = in.createTypedArrayList(LanguageBean.CREATOR);
    }

    public static final Creator<CountryNew> CREATOR = new Creator<CountryNew>() {
        @Override
        public CountryNew createFromParcel(Parcel source) {
            return new CountryNew(source);
        }

        @Override
        public CountryNew[] newArray(int size) {
            return new CountryNew[size];
        }
    };
}