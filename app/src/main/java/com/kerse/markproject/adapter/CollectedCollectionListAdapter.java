package com.kerse.markproject.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kerse.markproject.R;
import com.kerse.markproject.model.CollectionDefaultMapObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KAPLAN on 14.06.2017.
 */

public class CollectedCollectionListAdapter extends RecyclerView.Adapter<CollectedCollectionListAdapter.ViewHolder> {

    Map<String,CollectionDefaultMapObject> map = new HashMap<>();
    Context context;
    public CollectedCollectionListAdapter(  Map<String,CollectionDefaultMapObject> map, Context context) {
        this.map= map;
        this.context = context;
    }

    @Override
    public CollectedCollectionListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_list_cell, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CollectedCollectionListAdapter.ViewHolder holder, int position) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        final Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getRealMetrics(metrics);
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;

        Picasso.with(context)
                .load( (new ArrayList<CollectionDefaultMapObject>(map.values())).get(position).getMarkpath())
                .resize(width/4,width/4)
                .into(holder.imgMark);
        if((new ArrayList<CollectionDefaultMapObject>(map.values())).get(position).getAlpha()==0) {
            holder.imgMark.setImageAlpha(50);
            holder.tvMarkDesc.setTextColor(Color.argb(50, 255, 100, 0));
        }
        else{
            holder.imgMark.setImageAlpha(255);
            holder.tvMarkDesc.setTextColor(Color.argb(255, 255, 100, 0));
        }
        holder.tvMarkDesc.setText((new ArrayList<CollectionDefaultMapObject>(map.values())).get(position).getMarkDesc());
    }

    @Override
    public int getItemCount() {
        return (map != null ? map.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgMark;
        public TextView tvMarkDesc;
        public RelativeLayout cell;
        public View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            cell = (RelativeLayout) itemView.findViewById(R.id.cell);
            imgMark = (ImageView) itemView.findViewById(R.id.imgMark);
            tvMarkDesc = (TextView) itemView.findViewById(R.id.tvMarkDesc);
        }
    }
}
