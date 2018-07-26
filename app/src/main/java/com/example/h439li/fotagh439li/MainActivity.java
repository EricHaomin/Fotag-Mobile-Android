package com.example.h439li.fotagh439li;

import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.example.h439li.fotagh439li.model.Action;
import com.example.h439li.fotagh439li.model.Model;
import com.example.h439li.fotagh439li.model.Observer;
import com.example.h439li.fotagh439li.model.ThumbnailAdapter;
import com.example.h439li.fotagh439li.ui.ActionBar;

public class MainActivity extends AppCompatActivity implements Observer{
    public Model model;
    public GridView galleryView;
    public ActionBar actionBar;



    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            this.actionBar.allowTitleDisplay(false);
            galleryView.setNumColumns(1);
        }
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            this.actionBar.allowTitleDisplay(true);
            galleryView.setNumColumns(2);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.model = new Model(this);
        this.model.addObserver(this);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        this.galleryView = findViewById(R.id.gridview);
        galleryView.setAdapter(new ThumbnailAdapter(this, this.model));
        this.actionBar = new ActionBar(this, this.model);
        this.actionBar.allowTitleDisplay(false);
    }

    @Override
    public void update(Action a) {
        ((ThumbnailAdapter)this.galleryView.getAdapter()).refreshImages();
        ((ThumbnailAdapter)this.galleryView.getAdapter()).notifyDataSetChanged();
    }
}
