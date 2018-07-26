package com.example.h439li.fotagh439li.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.h439li.fotagh439li.R;
import com.example.h439li.fotagh439li.ui.ThumbnailView;

import java.util.ArrayList;

public class ThumbnailAdapter extends BaseAdapter {
    private Context context;
    public Model model;
    public ArrayList<ImageModel> imageModels;
    public ArrayList<ThumbnailView> thumbnailViews;


    public ThumbnailAdapter(Context c, Model m) {
        this.context = c;
        this.model = m;
        this.thumbnailViews = new ArrayList<>();
        refreshImages();
    }

    public void refreshImages() {
        this.imageModels = new ArrayList<>();
        if (model.curFilter > 0) {
            for (ImageModel imageModel : this.model.loadedImages) {
                if (imageModel.rating >= model.curFilter) {
                    this.imageModels.add(imageModel);
                }
            }
        }
        else {
            this.imageModels = this.model.loadedImages;
        }
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            View thumbnail = LayoutInflater.from(this.context).inflate(R.layout.image_block, parent, false);

            ThumbnailView thumbnailView = new ThumbnailView(thumbnail, this.imageModels.get(position));
            thumbnailView.update(Action.RefreshPage);

            this.thumbnailViews.add(thumbnailView);

            return thumbnailView.view;
        }
        else {
            ThumbnailView thumbnailView = getThumbnailViewForBackingView(convertView);
            thumbnailView.imageModel = imageModels.get(position);
            thumbnailView.update(Action.RefreshPage);

            return convertView;
        }
    }

    private ThumbnailView getThumbnailViewForBackingView(View v) {
        for (ThumbnailView thumbnailView : this.thumbnailViews) {
            if (thumbnailView.view == v) {
                return thumbnailView;
            }
        }

        return null;
    }

    public int getCount() {
        return this.imageModels.size();
    }

    public Object getItem(int i) {
        return this.imageModels.get(i);
    }

    public long getItemId(int i) {
        return i;
    }

}