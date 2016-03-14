package com.example.alex.bitsandpizzas;

/**
 * Created by Alex on 14.03.2016.
 */
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.drawable.Drawable;

public class CaptionedImageAdapter extends RecyclerView.Adapter<CaptionedImageAdapter.ViewHolder>{

    private String[] captions;
    private int[] imageIds;
    private Listener listener;

    public static interface Listener {
        public void onClick(int position);
    }

    public CaptionedImageAdapter(String[] captions, int[] imageIds){
        this.captions=captions;
        this.imageIds=imageIds;
    }
//Provide a reference to the views used in the recycler view

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
//Our recycler view needs to display CardViews, so we specify that our ViewHolder contains CardViews. If you want to display another type of data in the recycler view, you define it here.
        public ViewHolder(CardView v){
            super(v);
            cardView=v;
        }
    }
    public void setListener(Listener listener){
        this.listener = listener;
    }

    @Override
    public CaptionedImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_captioned_image, parent, false);
        return new ViewHolder(cv);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
//Set the values inside the given view
        
        CardView cardView=holder.cardView;
        cardView.setPreventCornerOverlap(false);
        ImageView imageView = (ImageView) cardView.findViewById(R.id.info_image);
        Drawable drawable=cardView.getResources().getDrawable(imageIds[position]);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(captions[position]);

        TextView textView = (TextView) cardView.findViewById(R.id.info_text);
        textView.setText(captions[position]);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount(){
//Return the number of items in the data set
        return captions.length;
    }
}
