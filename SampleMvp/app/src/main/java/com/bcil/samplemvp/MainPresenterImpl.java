package com.bcil.samplemvp;

public class MainPresenterImpl implements MainPresenter,GetQuoteInteractor.OnFinishedListener {
    private MainView mainView;
    private GetQuoteInteractor getQuoteInteractor;

    public MainPresenterImpl(MainView mainView, GetQuoteInteractor getQuoteInteractor) {
        this.mainView = mainView;
        this.getQuoteInteractor = getQuoteInteractor;
    }

    @Override
    public void onFinished(String name) {
   if (mainView!=null){
       mainView.setQuote(name);
       mainView.hideProgress();
   }
    }

    @Override
    public void onClickButton() {
        if (mainView!=null){
            mainView.showProgress();
        }
        getQuoteInteractor.getNextQuote(this);
    }

    @Override
    public void onDestroy() {
      mainView = null;
    }
}
