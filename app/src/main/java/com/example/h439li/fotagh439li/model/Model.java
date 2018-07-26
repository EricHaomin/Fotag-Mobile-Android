package com.example.h439li.fotagh439li.model;

import android.content.Context;

import com.example.h439li.fotagh439li.R;

import java.io.Serializable;
import java.util.ArrayList;

public class Model {
    private transient ArrayList<Observer> observers;
    public ArrayList<ImageModel> loadedImages;
    public int curFilter;
    public Context context;

    public Model(Context context) {
        this.observers = new ArrayList<>();
        this.curFilter = 0;
        this.context = context;
        this.loadedImages = new ArrayList<>();
    }

    public void defaultLoadImages() {
        this.loadedImages.add(new ImageModel(R.drawable.pic1, this.context, this));
        this.loadedImages.add(new ImageModel(R.drawable.pic2, this.context, this));
        this.loadedImages.add(new ImageModel(R.drawable.pic3, this.context, this));
        this.loadedImages.add(new ImageModel(R.drawable.pic4, this.context, this));
        this.loadedImages.add(new ImageModel(R.drawable.pic5, this.context, this));
        this.loadedImages.add(new ImageModel(R.drawable.pic6, this.context, this));
        this.loadedImages.add(new ImageModel(R.drawable.pic7, this.context, this));
        this.loadedImages.add(new ImageModel(R.drawable.pic8, this.context, this));
        this.loadedImages.add(new ImageModel(R.drawable.pic9, this.context, this));
        this.loadedImages.add(new ImageModel(R.drawable.pic10, this.context, this));

        this.notify(Action.AddImage);
    }


    public void setCurFilter(int i) {
        this.curFilter = i;
        this.notify(Action.SetFilter);
    }

    public void addObserver(Observer ob) {
        this.observers.add(ob);
    }

    public void notify(Action a) {
        for (Observer ob : this.observers) {
            ob.update(a);
        }
    }

    public void clearImage() {
        this.loadedImages = new ArrayList<>();
        this.notify(Action.RemoveImage);
    }



}
