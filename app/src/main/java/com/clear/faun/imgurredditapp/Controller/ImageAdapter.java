package com.clear.faun.imgurredditapp.Controller;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.clear.faun.imgurredditapp.Model.ImgurContainer;
import com.clear.faun.imgurredditapp.Model.ImgurResponse;
import static android.widget.ImageView.ScaleType.CENTER_CROP;
import java.util.ArrayList;
import java.util.zip.Inflater;


/**
 * Created by spencer on 9/2/2015.
 */
public class ImageAdapter extends BaseAdapter {


    private Context mContext;
    ImgurContainer imgurContainers;
    private ImageView myImageView;

    // Constructor
    public ImageAdapter(Context mContext, ImgurContainer imgurContainers) {
        Log.i("MyImageAdapter", "ImageAdapter Constructor");

        this.mContext = mContext;
        this.imgurContainers = imgurContainers;


    }

    public int getCount() {
        return imgurContainers.getImgurData().size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    Inflater inflater;
    int width = 0;
    int height = 0;
    private String currentSubName = "nycstreetart";

    @Override
    public View getView(int position, View recycled, ViewGroup container) {
        Log.d("MyImageAdapter", "getView() pos " + position);



       /* //clear if running a new sub.
        if(!imgurContainers.getSubRedditName().equals(currentSubName)
                &&position == 0 ){
            Glide.clear(recycled);
            currentSubName = imgurContainers.getSubRedditName();
        }*/


        if (recycled == null) {

            Log.d("MyImageAdapter", "recycled == null" );
            myImageView = new ImageView(mContext);
            myImageView.setScaleType(CENTER_CROP);
        }else {
            Log.d("MyImageAdapter", "else");

            myImageView = (ImageView) recycled;

        }

        if (width == 0) {
            width = getScreenWidth() / 2;
            height = width;
        }


        String url = imgurContainers.getImgurData().get(position).getLink();

  /*  Picasso.with(mContext)
            .load(url)
            .resize(width, height)
            .centerCrop()
            //.centerInside()
            .into(myImageView);*/


       // img.setImageBitmap(decodeSampleBitmapFromResource(R.drawable.dog_animation, width, height));
//dont forge

        Glide.with(mContext)
                .load(url)
                .override(width, height)
                .centerCrop()
                .crossFade()
                .into(myImageView);



        return myImageView;
    }

    private int getScreenWidth(){
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }


}