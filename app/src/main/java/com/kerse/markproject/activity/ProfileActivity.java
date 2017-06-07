package com.kerse.markproject.activity;

import android.animation.Animator;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kerse.markproject.R;
import com.kerse.markproject.common.Config;
import com.kerse.markproject.common.Prefrences;
import com.kerse.markproject.model.CollectedMark;
import com.kerse.markproject.model.CustomPerson;
import com.kerse.markproject.model.Person;
import com.squareup.picasso.Picasso;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
    RelativeLayout rl;
    TextView tvName, tvPdesc, tvWork;
    RelativeLayout RLPop;
    int pop;
    CustomPerson person;
    ImageView imgSettings;
    Button sendMessage;
    String Uid;
    boolean msgPer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        tvName = (TextView) findViewById(R.id.tvname);
        tvPdesc = (TextView) findViewById(R.id.tvpdesc);
        tvWork = (TextView) findViewById(R.id.tvwork);
        RLPop = (RelativeLayout) findViewById(R.id.RL);
        imgSettings = (ImageView) findViewById(R.id.imgSettings);
        sendMessage=(Button)findViewById(R.id.sendmessage);
        sendMessage.setVisibility(View.INVISIBLE);
        if (getIntent().getStringExtra("Uid") != null) {
            Uid = getIntent().getStringExtra("Uid");
            msgPer = getIntent().getBooleanExtra("msgPer", false);
            imgSettings.setVisibility(View.INVISIBLE);
            if(msgPer==true)
                sendMessage.setVisibility(View.VISIBLE);
        }


        new asycTaskToLoadProfileActivity().execute();


        imgSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ProfileSettingsActivity.class);
                startActivity(intent);
            }
        });
    }


    public class asycTaskToLoadProfileActivity extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());


            Map<String, String> urlParams = new HashMap<>();
            if (Uid != null) {
                urlParams.put("uid", Uid);
                person = restTemplate.getForObject(Config.ROOT_URL + "/person/showprofile?uid={uid}", CustomPerson.class, urlParams);


            } else {
                urlParams.put("mytoken", new Prefrences(getBaseContext()).loadToken());
                person = restTemplate.getForObject(Config.ROOT_URL + "/person/myinfo?authtoken={mytoken}", CustomPerson.class, urlParams);
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvName.setText("Loading..");
            tvWork.setText("Loading..");
            tvPdesc.setText("Loading..");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            tvPdesc.setText(person.getProfileDesc());
            tvName.setText(person.getFirstName() + " " + person.getLastName());
            tvWork.setText(person.getWork());
            calculatePop(person.getPopularPoint());
            super.onPostExecute(aVoid);
        }
    }

    public void calculatePop(int p) {
        if (p < 10) {
            RLPop.setBackgroundResource(R.drawable.pop1);
            return;
        } else if (p < 20) {
            RLPop.setBackgroundResource(R.drawable.pop2);
            return;
        } else if (p < 30) {
            RLPop.setBackgroundResource(R.drawable.pop3);
            return;
        } else if (p < 40) {
            RLPop.setBackgroundResource(R.drawable.pop4);
            return;
        } else if (p < 50) {
            RLPop.setBackgroundResource(R.drawable.pop5);
            return;
        } else if (p < 60) {
            RLPop.setBackgroundResource(R.drawable.pop6);
            return;
        } else if (p < 70) {
            RLPop.setBackgroundResource(R.drawable.pop7);
            return;
        } else if (p < 80) {
            RLPop.setBackgroundResource(R.drawable.pop8);
            return;
        } else if (p < 90) {
            RLPop.setBackgroundResource(R.drawable.pop9);
            return;
        } else if (p < 100) {
            RLPop.setBackgroundResource(R.drawable.pop10);
            return;
        }
    }
}
