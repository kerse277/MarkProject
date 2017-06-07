package com.kerse.markproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kerse.markproject.R;
import com.kerse.markproject.common.Config;
import com.kerse.markproject.fragment.MapFragment;
import com.kerse.markproject.location.GPSTracker;
import com.kerse.markproject.model.Mark;
import com.kerse.markproject.model.MarkAddObject;
import com.kerse.markproject.model.Place;
import com.squareup.picasso.Picasso;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


public class PlaceRecyclerAdapter extends RecyclerView.Adapter<PlaceRecyclerAdapter.PlaceViewHolder> {

    private List<Place> itemList;
    private Context context;
    private String token;
    private GPSTracker gps;


    public PlaceRecyclerAdapter(Context context, List<Place> itemList, String token) {
        this.itemList = itemList;
        this.context = context;
        this.token = token;
        gps = new GPSTracker(context);
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_list_row, null);

        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder holder, final int position) {
        holder.setToken(token);
        holder.setContext(context);
        holder.nameTxt.setText(itemList.get(position).getName());
        holder.addressTxt.setText(itemList.get(position).getPlaceAddress());
        int icon = 0;
        for (String type : itemList.get(position).getPlaceType()) {
            if (Config.getPlaces().contains(type)) {
                icon = Config.getMarkIcons().get(type);
            }
        }
        if (icon != 0)
            Picasso.with(context)
                    .load(icon)
                    .into(holder.iconImage);
        else
            Picasso.with(context)
                    .load(R.drawable.empty_mark)
                    .into(holder.iconImage);


        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gps.canGetLocation()) {
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    Mark mark = new Mark()
                            .setLatitude(String.valueOf(latitude))
                            .setLongitude(String.valueOf(longitude))
                            .setPlaceType(null);

                    MarkAddObject markAddObject = new MarkAddObject()
                            .setPersonToken(token)
                            .setMark(mark);
                    for (String placeType : itemList.get(position).getPlaceType()) {
                        if (Config.getPlaces().contains(placeType)) {
                            mark.setPlaceType(placeType);
                        }
                    }
                    if (mark.getPlaceType() == null) {
                        mark.setPlaceType("empty");
                    }

                    new LeaveMark(markAddObject).execute();


                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return (null != itemList ? itemList.size() : 0);
    }


    public class PlaceViewHolder extends RecyclerView.ViewHolder {

        @Setter
        Context context;

        @Setter
        String token;


        public TextView nameTxt, addressTxt;
        public ImageView iconImage;

        @Getter
        View itemView;

        public PlaceViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            nameTxt = (TextView) itemView.findViewById(R.id.placeName);
            addressTxt = (TextView) itemView.findViewById(R.id.placeAddress);
            iconImage = (ImageView) itemView.findViewById(R.id.iconImage);


        }

    }

    public class LeaveMark extends AsyncTask<Void, Void, Void> {

        private MarkAddObject markAddObject;
        private String response;

        public LeaveMark(MarkAddObject markAddObject) {
            this.markAddObject = markAddObject;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            response = restTemplate.postForObject(Config.ROOT_URL + "/mark/addmark", markAddObject, String.class);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (response.equals("success")) {
                LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(Config.REQUEST_RECEIVED));

            }
        }


    }
}