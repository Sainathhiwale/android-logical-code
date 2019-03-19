package com.bcil.samplemvp;

public interface GetQuoteInteractor {
    interface  OnFinishedListener{
        void onFinished(String name);
    }
    void getNextQuote(OnFinishedListener OnFinishedListener);
}
