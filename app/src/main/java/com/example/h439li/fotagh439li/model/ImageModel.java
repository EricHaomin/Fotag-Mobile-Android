package com.example.h439li.fotagh439li.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.ArrayList;

public class ImageModel{
    public int id;
    public int rating;
    public Bitmap bitmap;
    public Model model;
    public Context context;
    public ArrayList<Observer> observers;

    //import from existing dir
    public ImageModel(Integer id, Context context, Model model){
        this.id = id;
        this.rating = 0;
        this.model = model;
        this.context = context;
        this.bitmap = BitmapFactory.decodeResource(context.getResources(), id);
        this.observers = new ArrayList<>();
    }

    //import by url
    public ImageModel(Bitmap bitmap, Context context, Model model){
        this.bitmap = bitmap;
        this.context = context;
        this.model = model;
    }

    public void setRating(int i){
        this.rating = i;
        this.model.notify(Action.ChangeRating);
    }

    public void addObserver(Observer ob){
        this.observers.add(ob);
    }

    public void notify(Action a){
        for (Observer ob : observers){
            ob.update(a);
        }
    }
}
