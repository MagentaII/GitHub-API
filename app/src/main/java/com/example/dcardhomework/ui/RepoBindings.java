package com.example.dcardhomework.ui;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class RepoBindings {
    //progressBar的顯示或隱藏
    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show){
        view.setVisibility(show? View.VISIBLE : View.GONE);
    }

    // 圖片+ databinding無法成功
    @BindingAdapter("imageUrl")
    public static void bindImage(ImageView ImageView, String url){
        Context context = ImageView.getContext();
        Glide.with(context)
                .load(url)
                .into(ImageView);
    }
}
