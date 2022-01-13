package com.fz.premanger.demo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 汇率对象
 *
 * @author yeshunda
 * @version 1.1
 * @date 2016/8/18
 * @since 1.0
 */
public class ExchangeBean  implements Parcelable {
    public String name="USB";
    public double rate=1.0;
    public String sign="$";
    /**
     * 精度
     */
    public int exponet = 2;
    /**
     * 货币符号位置，1左边，2右边
     */
    public String position="1";
    /**
     * 千分位符号
     */
    public String thousandSign="";
    /**
     * 小数点符号
     */
    public String decimalSign=".";
    /**
     * 是否要在取整的基础上添加点00, 1添加0不添加
     */
    public String isZero="0";

    /**
     * 商品价格精度：
     * 走默认精度配置-其他金额（含结算金额）：所有优惠折扣金额（包括大学生折扣，coupon折扣，营销活动折扣）、运费、保险费、税费、积分抵扣金额、结算金额等所有除商品价格以外的金额
     */
    public int goodsExponent =0;

    public int getExponet() {
        return exponet;
    }

    public void setExponet(int exponet) {
        this.exponet = exponet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getThousandSign() {
        return thousandSign;
    }

    public void setThousandSign(String thousandSign) {
        this.thousandSign = thousandSign;
    }

    public String getDecimalSign() {
        return decimalSign;
    }

    public void setDecimalSign(String decimalSign) {
        this.decimalSign = decimalSign;
    }

    public String getIsZero() {
        return isZero;
    }

    public void setIsZero(String isZero) {
        this.isZero = isZero;
    }

    public ExchangeBean(String name, double rate, String sign, int exponet, String position, String thousandSign, String decimalSign, String isZero, int goodsExponent) {
        this.name = name;
        this.rate = rate;
        this.sign = sign;
        this.exponet = exponet;
        this.position = position;
        this.thousandSign = thousandSign;
        this.decimalSign = decimalSign;
        this.isZero = isZero;
        this.goodsExponent = goodsExponent;
    }

    public ExchangeBean() {
    }

    @Override
    public String toString() {
        return "ExchangeBean{" +
                "name='" + name + '\'' +
                ", rate=" + rate +
                ", sign='" + sign + '\'' +
                ", exponet=" + exponet +
                ", position='" + position + '\'' +
                ", thousandSign='" + thousandSign + '\'' +
                ", decimalSign='" + decimalSign + '\'' +
                ", isZero='" + isZero + '\'' +
                ", goodsExponent=" + goodsExponent +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeDouble(this.rate);
        dest.writeString(this.sign);
        dest.writeInt(this.exponet);
        dest.writeString(this.position);
        dest.writeString(this.thousandSign);
        dest.writeString(this.decimalSign);
        dest.writeString(this.isZero);
        dest.writeInt(this.goodsExponent);
    }

    protected ExchangeBean(Parcel in) {
        this.name = in.readString();
        this.rate = in.readDouble();
        this.sign = in.readString();
        this.exponet = in.readInt();
        this.position = in.readString();
        this.thousandSign = in.readString();
        this.decimalSign = in.readString();
        this.isZero = in.readString();
        this.goodsExponent = in.readInt();
    }

    public static final Creator<ExchangeBean> CREATOR = new Creator<ExchangeBean>() {
        @Override
        public ExchangeBean createFromParcel(Parcel source) {
            return new ExchangeBean(source);
        }

        @Override
        public ExchangeBean[] newArray(int size) {
            return new ExchangeBean[size];
        }
    };
}