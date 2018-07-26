package com.example.h439li.fotagh439li.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.h439li.fotagh439li.MainActivity;
import com.example.h439li.fotagh439li.R;
import com.example.h439li.fotagh439li.model.Action;
import com.example.h439li.fotagh439li.model.DownloadImage;
import com.example.h439li.fotagh439li.model.ImageModel;
import com.example.h439li.fotagh439li.model.Model;
import com.example.h439li.fotagh439li.model.Observer;

import java.util.ArrayList;

public class ActionBar implements Observer {
    public Context context;
    public View view;
    private Model model;
    public ArrayList<ImageButton> starBtns;
    public Drawable unfilledStar, filledStar;

    private class StarBtnListener implements View.OnClickListener {
        int index;
        public StarBtnListener(int pos){
            index = pos;
        }

        @Override
        public void onClick(View view) {
            if (index+1 == model.curFilter){
                model.setCurFilter(0);
            }
            else {
                model.setCurFilter(index + 1);
            }
        }
    }
    public ActionBar(Context c, Model m) {
        this.context = c;
        this.model = m;
        this.model.addObserver(this);

        this.unfilledStar = ContextCompat.getDrawable(context, R.drawable.unfilled_star);
        this.filledStar = ContextCompat.getDrawable(context, R.drawable.filled_star);

        this.view = ((Activity) c).findViewById(R.id.action_bar);


        this.starBtns = new ArrayList<>();

        this.starBtns.add((ImageButton) this.view.findViewById(R.id.star0));
        this.starBtns.add((ImageButton) this.view.findViewById(R.id.star1));
        this.starBtns.add((ImageButton) this.view.findViewById(R.id.star2));
        this.starBtns.add((ImageButton) this.view.findViewById(R.id.star3));
        this.starBtns.add((ImageButton) this.view.findViewById(R.id.star4));

        for(int i=0; i < 5; ++i) {
            ImageButton starBtn = this.starBtns.get(i);
            starBtn.setOnClickListener(new StarBtnListener(i));
            starBtn.setTag(i);
        }


        ImageButton loadBtn = this.view.findViewById(R.id.upload);
        ImageButton clearBtn = this.view.findViewById(R.id.clear);
        ImageButton searchBtn = this.view.findViewById(R.id.search);

        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.defaultLoadImages();
                Toast.makeText(context,"Load images", Toast.LENGTH_LONG).show();
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.clearImage();
                Toast.makeText(context,"Clear images", Toast.LENGTH_LONG).show();
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActionBar.this.context);
                builder.setTitle("Enter a image url");

                final EditText input = new EditText(ActionBar.this.context);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.setPositiveButton("Import", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String url = input.getText().toString();
                        new DownloadImage(context, model, url).execute();
                    }
                });

                builder.show();
            }
        });
    }

    public void allowTitleDisplay(boolean b){
        TextView title = this.view.findViewById(R.id.title);
        if(b){
            title.setVisibility(View.VISIBLE);
        }
        else{
            title.setVisibility(View.GONE);
        }
    }

    @Override
    public void update(Action a) {
        switch (a) {

            case SetFilter:
                for (int i=0; i < 5; i++){
                    if(i < model.curFilter) {
                        this.starBtns.get(i).setImageDrawable(filledStar);
                    }
                    else {
                        this.starBtns.get(i).setImageDrawable(unfilledStar);
                    }
                }
                break;
        }
    }
}

