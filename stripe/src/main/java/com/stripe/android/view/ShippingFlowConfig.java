package com.stripe.android.view;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.stripe.android.model.Address;
import com.stripe.android.model.ShippingInformation;

import java.util.ArrayList;
import java.util.List;

public class ShippingFlowConfig implements Parcelable {

    @NonNull private List<String> mHiddenAddressFields;
    @NonNull private List<String> mOptionalAddressFields;
    @NonNull private ShippingInformation mShippingInformation;
    private boolean mHideAddressScreen;
    private boolean mHideShippingScreen;

    public ShippingFlowConfig(
            @NonNull List<String> hiddenAddressFields,
            @NonNull List<String> optionalAddressFields,
            @NonNull ShippingInformation shippingInformation,
            boolean hideAddressScreen,
            boolean hideShippingScreen) {
        mHiddenAddressFields = hiddenAddressFields;
        mOptionalAddressFields = optionalAddressFields;
        mShippingInformation = shippingInformation;
        mHideAddressScreen = hideAddressScreen;
        mHideShippingScreen = hideShippingScreen;
    }

    private ShippingFlowConfig(Parcel in) {
        mHiddenAddressFields = new ArrayList<>();
        in.readStringList(mHiddenAddressFields);
        mOptionalAddressFields = new ArrayList<>();
        in.readStringList(mOptionalAddressFields);
        mShippingInformation = in.readParcelable(Address.class.getClassLoader());
        mHideAddressScreen = in.readInt() == 1;
        mHideShippingScreen = in.readInt() == 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShippingFlowConfig that = (ShippingFlowConfig) o;

        if (isHideAddressScreen() != that.isHideAddressScreen()) return false;
        if (isHideShippingScreen() != that.isHideShippingScreen()) return false;
        if (!getHiddenAddressFields().equals(that.getHiddenAddressFields())) return false;
        if (!getOptionalAddressFields().equals(that.getOptionalAddressFields())) return false;
        return getPrepopulatedShippingInfo().equals(that.getPrepopulatedShippingInfo());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeStringList(mHiddenAddressFields);
        parcel.writeStringList(mOptionalAddressFields);
        parcel.writeParcelable(mShippingInformation, flags);
        parcel.writeInt(mHideAddressScreen ? 1 : 0);
        parcel.writeInt(mHideShippingScreen? 1: 0);
    }

    @NonNull List<String> getHiddenAddressFields() {
        return mHiddenAddressFields;
    }

    @NonNull List<String> getOptionalAddressFields() {
        return mOptionalAddressFields;
    }

    @NonNull
    ShippingInformation getPrepopulatedShippingInfo() {
        return mShippingInformation;
    }

    boolean isHideAddressScreen() {
        return mHideAddressScreen;
    }

    boolean isHideShippingScreen() {
        return mHideShippingScreen;
    }

    static final Parcelable.Creator<ShippingFlowConfig> CREATOR
            = new Parcelable.Creator<ShippingFlowConfig>() {

        @Override
        public ShippingFlowConfig createFromParcel(Parcel in) {
            return new ShippingFlowConfig(in);
        }

        @Override
        public ShippingFlowConfig[] newArray(int size) {
            return new ShippingFlowConfig[size];
        }
    };
}
