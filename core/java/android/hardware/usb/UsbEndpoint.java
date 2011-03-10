/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.hardware.usb;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * A class representing an endpoint on a {@link android.hardware.usb.UsbInterface}.
 */
public final class UsbEndpoint implements Parcelable {

    private int mAddress;
    private int mAttributes;
    private int mMaxPacketSize;
    private int mInterval;
    private UsbInterface mInterface;

    private UsbEndpoint() {
    }

    /**
     * UsbEndpoint should only be instantiated by UsbService implementation
     * @hide
     */
    public UsbEndpoint(int address, int attributes, int maxPacketSize, int interval) {
        mAddress = address;
        mAttributes = attributes;
        mMaxPacketSize = maxPacketSize;
        mInterval = interval;
    }

    /**
     * Returns the endpoint's address field.
     *
     * @return the endpoint's address
     */
    public int getAddress() {
        return mAddress;
    }

    /**
     * Extracts the endpoint's endpoint number from its address
     *
     * @return the endpoint's endpoint number
     */
    public int getEndpointNumber() {
        return mAddress & UsbConstants.USB_ENDPOINT_NUMBER_MASK;
    }

    /**
     * Returns the endpoint's direction.
     * Returns {@link android.hardware.usb.UsbConstants#USB_DIR_OUT}
     * if the direction is host to device, and
     * {@link android.hardware.usb.UsbConstants#USB_DIR_IN} if the
     * direction is device to host.
     *
     * @return the endpoint's direction
     */
    public int getDirection() {
        return mAddress & UsbConstants.USB_ENDPOINT_DIR_MASK;
    }

    /**
     * Returns the endpoint's attributes field.
     *
     * @return the endpoint's attributes
     */
    public int getAttributes() {
        return mAttributes;
    }

    /**
     * Returns the endpoint's type.
     * Possible results are:
     * <ul>
     * <li>{@link android.hardware.usb.UsbConstants#USB_ENDPOINT_XFER_CONTROL} (endpoint zero)
     * <li>{@link android.hardware.usb.UsbConstants#USB_ENDPOINT_XFER_ISOC} (isochronous endpoint)
     * <li>{@link android.hardware.usb.UsbConstants#USB_ENDPOINT_XFER_BULK} (bulk endpoint)
     * <li>{@link android.hardware.usb.UsbConstants#USB_ENDPOINT_XFER_INT} (interrupt endpoint)
     * </ul>
     *
     * @return the endpoint's type
     */
    public int getType() {
        return mAttributes & UsbConstants.USB_ENDPOINT_XFERTYPE_MASK;
    }

    /**
     * Returns the endpoint's maximum packet size.
     *
     * @return the endpoint's maximum packet size
     */
    public int getMaxPacketSize() {
        return mMaxPacketSize;
    }

    /**
     * Returns the endpoint's interval field.
     *
     * @return the endpoint's interval
     */
    public int getInterval() {
        return mInterval;
    }

    /**
     * Returns the {@link android.hardware.usb.UsbInterface} this endpoint belongs to.
     *
     * @return the endpoint's interface
     */
    public UsbInterface getInterface() {
        return mInterface;
    }

    /**
     * Returns the {@link android.hardware.usb.UsbDevice} this endpoint belongs to.
     *
     * @return the endpoint's device
     */
    public UsbDevice getDevice() {
        return mInterface.getDevice();
    }

    // only used for parcelling
    /* package */ void setInterface(UsbInterface intf) {
        mInterface = intf;
    }

    @Override
    public String toString() {
        return "UsbEndpoint[mAddress=" + mAddress + ",mAttributes=" + mAttributes +
                ",mMaxPacketSize=" + mMaxPacketSize + ",mInterval=" + mInterval +"]";
    }

    public static final Parcelable.Creator<UsbEndpoint> CREATOR =
        new Parcelable.Creator<UsbEndpoint>() {
        public UsbEndpoint createFromParcel(Parcel in) {
            int address = in.readInt();
            int attributes = in.readInt();
            int maxPacketSize = in.readInt();
            int interval = in.readInt();
            return new UsbEndpoint(address, attributes, maxPacketSize, interval);
        }

        public UsbEndpoint[] newArray(int size) {
            return new UsbEndpoint[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(mAddress);
        parcel.writeInt(mAttributes);
        parcel.writeInt(mMaxPacketSize);
        parcel.writeInt(mInterval);
   }
}