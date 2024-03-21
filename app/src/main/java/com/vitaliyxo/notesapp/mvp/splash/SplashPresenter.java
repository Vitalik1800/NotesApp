package com.vitaliyxo.notesapp.mvp.splash;

import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashPresenter implements SplashModel.OnProgressListener {

    private final SplashView view;
    private final SplashModel model;
    ProgressBar progressBar;
    TextView textView;

    public SplashPresenter(SplashView view, ProgressBar progressBar, TextView textView) {
        this.view = view;
        this.progressBar = progressBar;
        this.textView = textView;
        this.model = new SplashModel(this, progressBar, textView);
    }

    public void startLoading() {
        model.simulateLoading();
    }

    @Override
    public void onProgressUpdated(int progress) {
        view.showProgress(progress);
    }
}
