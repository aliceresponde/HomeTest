package com.aliceresponde.hometest;

/**
 * Created by alice on 10/12/15.
     UK Pounds (GBP)
     EU Euro (EUR)
     Japan Yen ­ (JPY)
     Brazil Reais ­ (BRL)
 */
public class MyCurrency {

    private float  value_GBP=0f, value_EUR=0f, value_JPY=0f, value_BRL=0f;


    public MyCurrency() {
    }

    public float getValue_GBP() {
        return value_GBP;
    }

    public void setValue_GBP(float value_GBP) {
        this.value_GBP = value_GBP;
    }

    public float getValue_EUR() {
        return value_EUR;
    }

    public void setValue_EUR(float value_EUR) {
        this.value_EUR = value_EUR;
    }

    public float getValue_JPY() {
        return value_JPY;
    }

    public void setValue_JPY(float value_JPY) {
        this.value_JPY = value_JPY;
    }

    public float getValue_BRL() {
        return value_BRL;
    }

    public void setValue_BRL(float value_BRL) {
        this.value_BRL = value_BRL;
    }

    @Override
    public String toString() {
        return "MyCurrency{" +
                "value_GBP=" + value_GBP +
                ", value_EUR=" + value_EUR +
                ", value_JPY=" + value_JPY +
                ", value_BRL=" + value_BRL +
                '}';
    }
}

