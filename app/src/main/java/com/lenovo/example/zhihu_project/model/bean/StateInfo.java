package com.lenovo.example.zhihu_project.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lenovo on 2019/9/10.
 */

public class StateInfo implements Parcelable {

    private String type;
    private boolean state;

    public StateInfo(String type, boolean state) {
        this.type = type;
        this.state = state;
    }

    protected StateInfo(Parcel in) {
        type = in.readString();
        state = in.readByte() != 0;

    }





    public static final Creator<StateInfo> CREATOR = new Creator<StateInfo>() {
        @Override
        public StateInfo createFromParcel(Parcel in) {
            return new StateInfo(in);
        }

        @Override
        public StateInfo[] newArray(int size) {
            return new StateInfo[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeByte((byte) (state ? 1 : 0));
    }
}
