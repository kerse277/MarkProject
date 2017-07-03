package com.kerse.markproject.fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kerse.markproject.R;
import com.kerse.markproject.activity.MainActivity;
import com.kerse.markproject.activity.ProfileActivity;
import com.kerse.markproject.adapter.PlaceRecyclerAdapter;
import com.kerse.markproject.common.CircleTransform;
import com.kerse.markproject.common.Config;
import com.kerse.markproject.common.Prefrences;
import com.kerse.markproject.location.GPSTracker;
import com.kerse.markproject.model.CustomPerson;
import com.kerse.markproject.model.Mark;
import com.kerse.markproject.model.MarkAddObject;
import com.kerse.markproject.model.MarkCollectObject;
import com.kerse.markproject.model.Place;
import com.squareup.picasso.Picasso;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MapFragment extends Fragment implements OnMapReadyCallback {

    MapView mMapView;
    GoogleMap gMap = null;
    GPSTracker gps;
    ImageButton leaveMark;
    EditText etAutoComplate;
    ImageView searchMark;
    String longitude = null, latitude = null;
    RecyclerView leaveMarkRecycler;
    Prefrences prefrences;
    ProgressBar dialogProgress,markInfoProgress;
    Dialog dialog, collectDialog;
    ImageView personImg;
    TextView personName;
    ImageView imgProfile;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        gps = new GPSTracker(getActivity());
        MapsInitializer.initialize(this.getActivity());
        prefrences = new Prefrences(getActivity());
        mMapView = (MapView) rootView.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(MapFragment.this);
        searchMark = (ImageView) rootView.findViewById(R.id.searchMark);
        etAutoComplate = (EditText) rootView.findViewById(R.id.autoCompleteTextView);
        markInfoProgress = (ProgressBar) rootView.findViewById(R.id.markInfoProgress);
        imgProfile=(ImageView)rootView.findViewById(R.id.imgProfile);

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProfileActivity.class);
                getContext().startActivity(intent);
            }
        });

        searchMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (longitude != null || latitude != null) {
                    new NearMarkers(String.valueOf(longitude), String.valueOf(latitude)).execute();
                }
            }
        });

        etAutoComplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent =
                            new PlaceAutocomplete
                                    .IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                                    .build(getActivity());
                    startActivityForResult(intent, 1);
                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                } catch (GooglePlayServicesNotAvailableException e) {
                    // TODO: Handle the error.
                }
            }
        });

        leaveMark = (ImageButton) rootView.findViewById(R.id.leaveMark);
        leaveMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leaveMark();
            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == getActivity().RESULT_OK) {
                com.google.android.gms.location.places.Place place = PlaceAutocomplete.getPlace(getActivity(), data);

                etAutoComplate
                        .setText(place.getName());
                longitude = String.valueOf(place.getLatLng().longitude);
                latitude = String.valueOf(place.getLatLng().latitude);
                if (longitude != null || latitude != null) {
                    new NearMarkers(String.valueOf(longitude), String.valueOf(latitude)).execute();
                }

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {

            } else if (resultCode == getActivity().RESULT_CANCELED) {

            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mReciever);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        gMap.setMyLocationEnabled(true);
        gMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                if (gps.canGetLocation()) {
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    LatLng mylocation = new LatLng(latitude, longitude);

                    new NearMarkers(String.valueOf(longitude), String.valueOf(latitude)).execute();

                    gMap.moveCamera(CameraUpdateFactory.newLatLng(mylocation));
                    CameraUpdate center = CameraUpdateFactory.newLatLng(mylocation);
                    CameraUpdate zoom = CameraUpdateFactory.zoomTo(17);

                    gMap.moveCamera(center);
                    gMap.animateCamera(zoom);

                }

                return false;
            }
        });
        gMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                collectMark(marker);
                //  Toast.makeText(getActivity(), String.valueOf(marker.getPosition().longitude), Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        if (gps.canGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            LatLng mylocation = new LatLng(latitude, longitude);

            new NearMarkers(String.valueOf(longitude), String.valueOf(latitude)).execute();

            gMap.moveCamera(CameraUpdateFactory.newLatLng(mylocation));
            CameraUpdate center = CameraUpdateFactory.newLatLng(mylocation);
            CameraUpdate zoom = CameraUpdateFactory.zoomTo(17);

            gMap.moveCamera(center);
            gMap.animateCamera(zoom);
        }

    }
    Button collectMark;
    public void collectMark(Marker marker) {
        collectDialog = new Dialog(getActivity());
        collectDialog.setContentView(R.layout.collect_mark_dialog);
        collectDialog.setTitle("Title...");
        RelativeLayout underLayout = (RelativeLayout) collectDialog.findViewById(R.id.underLayout);
        personImg = (ImageView) collectDialog.findViewById(R.id.personImg);
        personName = (TextView) collectDialog.findViewById(R.id.personName);
        collectMark = (Button) collectDialog.findViewById(R.id.collectMark);
        final String markid = marker.getSnippet();

        WindowManager wm = (WindowManager) (getContext().getSystemService(Context.WINDOW_SERVICE));
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int height = metrics.heightPixels;
        RelativeLayout.LayoutParams layoutParamsImg = new RelativeLayout.LayoutParams(height / 5, height / 5);
        layoutParamsImg.setMargins(0, height / 20, 0, 0);
        layoutParamsImg.addRule(RelativeLayout.CENTER_HORIZONTAL);
        personImg.setLayoutParams(layoutParamsImg);


        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, height / 10 + height / 20, 0, 0);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.collectMark);
        underLayout.setLayoutParams(layoutParams);
        if (gps.canGetLocation()) {
            final double latitude = gps.getLatitude();
            final double longitude = gps.getLongitude();
            double markerLat1 = marker.getPosition().latitude - 0.00250;
            double markerLat2 = marker.getPosition().latitude + 0.00250;
            double markerLong1 = marker.getPosition().longitude - 0.00250;
            double markerLong2 = marker.getPosition().longitude + 0.00250;

            if (latitude >= markerLat1 && latitude <= markerLat2 && longitude >= markerLong1 && longitude <= markerLong2) {
                collectMark.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MarkCollectObject markCollectObject = new MarkCollectObject()
                                .setMarkID(markid)
                                .setPersonToken(prefrences.loadToken())
                                .setLatitude(String.valueOf(latitude))
                                .setLongitude(String.valueOf(longitude));
                        new CollectMark(markCollectObject).execute();
                    }
                });
            } else {
                collectMark.setText("CLOSE");
                collectMark.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        collectDialog.dismiss();
                    }
                });
            }


        }


        new MarkOwner(markid).execute();
    }

    public void leaveMark() {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_leave_mark);
        dialog.setTitle("Title...");
        leaveMarkRecycler = (RecyclerView) dialog.findViewById(R.id.leaveMarkRecycler);
        Button quickLeaveMark = (Button) dialog.findViewById(R.id.quickLeaveMark);
        dialogProgress = (ProgressBar) dialog.findViewById(R.id.dialogProgress);
        if (gps.canGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            new LeaveMarkPlaces(String.valueOf(longitude), String.valueOf(latitude)).execute();
        }

        quickLeaveMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gps.canGetLocation()) {
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    Mark mark = new Mark()
                            .setLatitude(String.valueOf(latitude))
                            .setLongitude(String.valueOf(longitude))
                            .setPlaceType("empty");

                    MarkAddObject markAddObject = new MarkAddObject()
                            .setPersonToken(prefrences.loadToken())
                            .setMark(mark);

                    new LeaveMark(markAddObject).execute();
                    dialog.dismiss();

                }


            }
        });

        dialog.show();
    }

    public class MarkOwner extends AsyncTask<Void, Void, Void> {
        CustomPerson customPerson;
        String markID;

        public MarkOwner(String markID) {

            this.markID = markID;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            markInfoProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params) {

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());


            Map<String, String> urlParams = new HashMap<>();
            urlParams.put("markid", markID);


            customPerson = restTemplate.getForObject(Config.ROOT_URL + "/mark/markowner?markid={markid}", CustomPerson.class, urlParams);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Picasso.with(getActivity())
                    .load(Config.SERVER_URL + customPerson.getProfilePic())
                    .transform(new CircleTransform())
                    .resize(400,400)
                    .into(personImg);
            personName.setText(customPerson.getFirstName() + " " + customPerson.getLastName());
            String aa = prefrences.loadID();
            if(customPerson.getUniqueID().equals(prefrences.loadID())){
                collectMark.setText("CLOSE");
                collectMark.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        collectDialog.dismiss();
                    }
                });

            }

            collectDialog.show();
            markInfoProgress.setVisibility(View.GONE);

        }
    }

    public class NearMarkers extends AsyncTask<Void, Void, Void> {

        private String longitude, latitude;
        private List<Mark> marks = new ArrayList<>();

        public NearMarkers(String longitude, String latitude) {
            this.longitude = longitude;
            this.latitude = latitude;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            gMap.clear();
            gMap.setMyLocationEnabled(true);
        }

        @Override
        protected Void doInBackground(Void... params) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Map<String, String> urlParams = new HashMap<>();
            urlParams.put("long", longitude);
            urlParams.put("lat", latitude);

            Mark[] marks1 = restTemplate.getForObject(Config.ROOT_URL + "/mark/marksmyloc?long={long}&lat={lat}", Mark[].class, urlParams);
            if (marks1 != null) {
                marks = Arrays.asList(marks1);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            for (Mark mark : marks) {
                LatLng markLocation = new LatLng(Double.parseDouble(mark.getLatitude()), Double.parseDouble(mark.getLongitude()));
                gMap.addMarker(new MarkerOptions().position(markLocation).icon(BitmapDescriptorFactory.fromResource(Config.getMarkIcons().get(mark.getPlaceType()).getMarkpath())).snippet(mark.getUniqueID()));


            }
            LatLng mylocation = new LatLng(Double.parseDouble(this.latitude), Double.parseDouble(this.longitude));
            gMap.moveCamera(CameraUpdateFactory.newLatLng(mylocation));
            CameraUpdate center = CameraUpdateFactory.newLatLng(mylocation);
            CameraUpdate zoom = CameraUpdateFactory.zoomTo(17);

            gMap.moveCamera(center);
            gMap.animateCamera(zoom);
        }


    }

    public class LeaveMarkPlaces extends AsyncTask<Void, Void, Void> {

        private String longitude, latitude;
        private List<Place> places = new ArrayList<>();

        public LeaveMarkPlaces(String longitude, String latitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialogProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params) {

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Map<String, String> urlParams = new HashMap<>();
            urlParams.put("long", latitude);
            urlParams.put("lat", longitude);

            Place[] places1 = restTemplate.getForObject(Config.ROOT_URL + "/mark/nearbyplace?long={long}&lat={lat}", Place[].class, urlParams);
            if (places1 != null) {
                places = Arrays.asList(places1);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            LinearLayoutManager lLayout = new LinearLayoutManager(getContext());

            leaveMarkRecycler.setHasFixedSize(true);

            leaveMarkRecycler.setLayoutManager(lLayout);

            PlaceRecyclerAdapter mAdapter = new PlaceRecyclerAdapter((MainActivity) getActivity(), places, prefrences.loadToken());

            leaveMarkRecycler.setAdapter(mAdapter);
            dialogProgress.setVisibility(View.GONE);

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
                if (gps.canGetLocation()) {
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    new NearMarkers(String.valueOf(longitude), String.valueOf(latitude)).execute();
                    Toast.makeText(getActivity(), "İz başarı ile bırakıldı", Toast.LENGTH_SHORT).show();
                }
            }
        }


    }


    public class CollectMark extends AsyncTask<Void, Void, Void> {

        MarkCollectObject markCollectObject;
        String response;

        public CollectMark(MarkCollectObject markCollectObject) {
            this.markCollectObject = markCollectObject;
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

            response = restTemplate.postForObject(Config.ROOT_URL + "/mark/collectMark", markCollectObject, String.class);
            

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (response.equals("success"))
                if (gps.canGetLocation()) {
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    new NearMarkers(String.valueOf(longitude), String.valueOf(latitude)).execute();
                }
            collectDialog.dismiss();
            LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(new Intent(Config.REQUEST_RECEIVED_REFRESHMARKS));

        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mReciever, new IntentFilter(Config.REQUEST_RECEIVED));
    }


    BroadcastReceiver mReciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case Config.REQUEST_RECEIVED:
                    if (gps.canGetLocation()) {
                        double latitude = gps.getLatitude();
                        double longitude = gps.getLongitude();
                        new NearMarkers(String.valueOf(longitude), String.valueOf(latitude)).execute();
                        Toast.makeText(getActivity(), "İz başarı ile bırakıldı", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                    break;

                default:
                    // do nothing
            }
        }
    };
}
