package com.kerse.markproject.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kerse.markproject.R;
import com.kerse.markproject.activity.MainActivity;
import com.kerse.markproject.adapter.CollectedCollectionListAdapter;
import com.kerse.markproject.common.Config;
import com.kerse.markproject.common.Prefrences;
import com.kerse.markproject.model.CollectedMark;
import com.kerse.markproject.model.CollectionDefaultMapObject;
import com.kerse.markproject.model.CustomPerson;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CollectedCollectionFragment extends Fragment {

    RecyclerView recyclerView;
    CustomPerson customPerson;
    ObjectMapper objectMapper = new ObjectMapper();
    Map<String, CollectionDefaultMapObject> tempmap;
    CollectedCollectionListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_collected_collection, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.collectedCollection);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        tempmap = Config.getMarkIcons();
        adapter = new CollectedCollectionListAdapter(tempmap, getActivity());
        recyclerView.setAdapter(adapter);
        task();

        return rootView;
    }

    public void task() {
        new AsyncTask<Void, Void, Void>() {


            @Override
            protected Void doInBackground(Void... params) {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());


                Map<String, String> urlParams = new HashMap<>();
                urlParams.put("mytoken", new Prefrences(getContext()).loadToken());
                customPerson = restTemplate.getForObject(Config.ROOT_URL + "/person/myinfo?authtoken={mytoken}", CustomPerson.class, urlParams);

                for (int i = 0; i < customPerson.getCollectedCollection().size(); i++) {
                    //    CollectedMark collectedMark = objectMapper.readValue(customPerson.getCollectedMarks().get(i), CollectedMark.class);
                        String type=customPerson.getCollectedCollection().get(i);
                        if (tempmap.containsKey(type)) {
                            tempmap.get(type).setAlpha(1);
                        }
                }

                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                adapter.notifyDataSetChanged();
            }
        }.execute();

    }


}