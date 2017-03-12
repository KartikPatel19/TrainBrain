package com.deucat.kartik.trainbrain.PNR;

class PassengerClass {

    private int mIndexNumber;
    private String mBookingStatus;
    private String mCurruntStatus;
    private int mCochePosition;


    PassengerClass() {
    }

    int getIndexNumber() {
        return mIndexNumber;
    }

    void setIndexNumber(int indexNumber) {
        mIndexNumber = indexNumber;
    }

    String getBookingStatus() {
        return mBookingStatus;
    }

    void setBookingStatus(String bookingStatus) {
        mBookingStatus = bookingStatus;
    }

    String getCurruntStatus() {
        return mCurruntStatus;
    }

    void setCurruntStatus(String curruntStatus) {
        mCurruntStatus = curruntStatus;
    }

    int getCochePosition() {
        return mCochePosition;
    }

    void setCochePosition(int cochePosition) {
        mCochePosition = cochePosition;
    }
}
