package com.example.h439li.fotagh439li.model;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.InputStream;

public class DownloadImage extends AsyncTask<Void, Void, Bitmap> {
    public Model model;
    private String url;
    public Context context;

    public DownloadImage(Context context, Model model, String url){
        this.context = context;
        this.model = model;
        this.url = url;
    }

    @Override
    protected Bitmap doInBackground(Void... strings) {
        Bitmap bitmap = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return bitmap;
    }


    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(bitmap == null){
            return;
        }
        super.onPostExecute(bitmap);
        this.model.loadedImages.add(new ImageModel(bitmap, this.context, this.model));
        this.model.notify(Action.AddImage);
    }
}
