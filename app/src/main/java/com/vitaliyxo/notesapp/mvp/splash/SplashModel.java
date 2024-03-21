package com.vitaliyxo.notesapp.mvp.splash;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.vitaliyxo.notesapp.ProgressBarAnimation;

public class SplashModel {

    public interface OnProgressListener{
        void onProgressUpdated(int progress);
    }

    private final OnProgressListener listener;
    private final ProgressBar progressBar;
    private final TextView textView;

    public SplashModel(OnProgressListener listener, ProgressBar progressBar, TextView textView){
        this.listener = listener;
        this.progressBar = progressBar;
        this.textView = textView;
    }

    public void simulateLoading(){
        for(int progress = 0; progress <= 100; progress++){
            try{
                listener.onProgressUpdated(progress);
                updateAnimation(progress);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void updateAnimation(int progress) {
        ProgressBarAnimation animation = new ProgressBarAnimation(progressBar.getContext(), progressBar, textView, 0f, progress);
        animation.setDuration(8000); // Налаштуйте тривалість анімації відповідно до потреб
        progressBar.setAnimation(animation);
    }
}
