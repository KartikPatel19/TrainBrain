package com.deucat.kartik.trainbrain.PNR;

 class PassengerClass {

    private int mIndexNumber;
    private String mBookingStatus;
    private String mCurruntStatus;
    private int mCochePosition;

    PassengerClass() {
    }

    public int getIndexNumber() {
        return mIndexNumber;
    }

     void setIndexNumber(int indexNumber) {
        mIndexNumber = indexNumber;
    }

    public String getBookingStatus() {
        return mBookingStatus;
    }

     void setBookingStatus(String bookingStatus) {
        mBookingStatus = bookingStatus;
    }

    public String getCurruntStatus() {
        return mCurruntStatus;
    }

     void setCurruntStatus(String curruntStatus) {
        mCurruntStatus = curruntStatus;
    }

    public int getCochePosition() {
        return mCochePosition;
    }

     void setCochePosition(int cochePosition) {
        mCochePosition = cochePosition;
    }
}
