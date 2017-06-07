package com.kerse.markproject.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kerse.markproject.R;
import com.kerse.markproject.activity.MainActivity;
import com.kerse.markproject.adapter.CollectedPersonListAdapter;
import com.kerse.markproject.common.Config;
import com.kerse.markproject.common.Prefrences;
import com.kerse.markproject.model.CollectMarkAndPersons;
import com.kerse.markproject.model.CollectedMark;
import com.kerse.markproject.model.CollectedPersonList;
import com.kerse.markproject.model.CustomPerson;
import com.kerse.markproject.model.Mark;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarkFragment extends Fragment {

    RecyclerView recyclerView;
    List<CollectedPersonList> collectedPersonLists = new ArrayList<>();
    List<CollectedMark> collectedMarks = new ArrayList<>();

    List<CollectMarkAndPersons> collectMarkAndPersonsList = new ArrayList<>();
    CollectMarkAndPersons collectMarkAndPersons;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mark, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.collectedPersonList);
        new RecycleTaskToGetCollectedMarks().execute();
        return view;
    }

    public class RecycleTaskToGetCollectedMarks extends AsyncTask<Void,Void,Void>{
        CollectedPersonList collectedPersonList;
        CustomPerson customPerson;



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(gridLayoutManager);
            CollectedPersonListAdapter adapter = new CollectedPersonListAdapter(collectMarkAndPersonsList, ((MainActivity)getActivity()));
            recyclerView.setAdapter(adapter);
        }

        @Override
        protected Void doInBackground(Void... params) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());


            Map<String, String> urlParams = new HashMap<>();
            urlParams.put("mytoken", new Prefrences(getContext()).loadToken());
            collectMarkAndPersonsList = Arrays.asList(restTemplate.getForObject(Config.ROOT_URL + "/person/getmycollectedmarks?authtoken={mytoken}", CollectMarkAndPersons[].class, urlParams));
            return null;
        }
    }


    @Override //alıcı
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mReciever, new IntentFilter(Config.REQUEST_RECEIVED_REFRESHMARKS));
    }


    BroadcastReceiver mReciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case Config.REQUEST_RECEIVED_REFRESHMARKS:
                    new RecycleTaskToGetCollectedMarks().execute();
                    break;

                default:
                    // do nothing
            }
        }
    };

}
