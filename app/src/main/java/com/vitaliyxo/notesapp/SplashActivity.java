package com.vitaliyxo.notesapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.vitaliyxo.notesapp.mvp.splash.SplashPresenter;
import com.vitaliyxo.notesapp.mvp.splash.SplashView;
import com.vitaliyxo.notesapp.databinding.ActivitySplashBinding;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity implements SplashView {

    ActivitySplashBinding binding;
    SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        presenter = new SplashPresenter(this, binding.progressBar, binding.textProgress);
        presenter.startLoading();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showProgress(int progress) {
        binding.progressBar.setProgress(progress);
        binding.textProgress.setText(progress + "%");
    }

}
