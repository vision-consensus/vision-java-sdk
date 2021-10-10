package org.vision.visionjsdk.client.Transaction;

public class TransactionSignDTO {
    private String rawData;
    private boolean rawDataIsHex = true;
    private String signature;
    private String address;
    private boolean addressIsHex = true;

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public boolean isRawDataIsHex() {
        return rawDataIsHex;
    }

    public void setRawDataIsHex(boolean rawDataIsHex) {
        this.rawDataIsHex = rawDataIsHex;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isAddressIsHex() {
        return addressIsHex;
    }

    public void setAddressIsHex(boolean addressIsHex) {
        this.addressIsHex = addressIsHex;
    }
}
