package com.example.h439li.fotagh439li.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.example.h439li.fotagh439li.R;
import com.example.h439li.fotagh439li.model.Action;
import com.example.h439li.fotagh439li.model.ImageModel;
import com.example.h439li.fotagh439li.model.Model;
import com.example.h439li.fotagh439li.model.Observer;

public class ThumbnailView implements Observer{
    public View view;
    public RatingBar ratingBar;
    public ImageModel imageModel;

    public ThumbnailView(View view, ImageModel im) {
        this.imageModel = im;
        this.view = view;


        this.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(view.getContext(), R.style.FullScreenDialog);
                dialog.setContentView(R.layout.image_view);
                ImageView imageView = dialog.findViewById(R.id.image_view);
                imageView.setImageBitmap(imageModel.bitmap);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                dialog.show();
            }
        });
    }

    @Override
    public void update(Action a) {
        this.ratingBar = this.view.findViewById(R.id.rating_bar);
        this.ratingBar.setRating(this.imageModel.rating);
        this.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean isUser) {
                if(isUser){
                    imageModel.setRating((int)rating);
                }
            }
        });

        ImageView imageView = this.view.findViewById(R.id.image_view);
        imageView.setImageBitmap(this.imageModel.bitmap);
    }

}
