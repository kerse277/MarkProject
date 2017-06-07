package com.kerse.markproject.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kerse.markproject.R;
import com.kerse.markproject.common.Config;
import com.kerse.markproject.common.Prefrences;
import com.kerse.markproject.model.CustomPerson;
import com.kerse.markproject.model.Person;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class ProfileSettingsActivity extends AppCompatActivity {

    CustomPerson person;
    EditText etname,etlastname, etemail, etpdesc, etwork,etinstagram;
    Prefrences prefrences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);
        etname=(EditText)findViewById(R.id.etname);
        etlastname=(EditText)findViewById(R.id.etlastname);
        etemail=(EditText)findViewById(R.id.etemail);
        etpdesc=(EditText)findViewById(R.id.etpdesc);
        etwork=(EditText)findViewById(R.id.etwork);
        etinstagram=(EditText)findViewById(R.id.etInsta);
        prefrences =new Prefrences(this);
      new asycTaskToFillProfileSettings().execute();

    }

    public class asycTaskToFillProfileSettings extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            etname.setText(person.getFirstName());
            etlastname.setText(person.getLastName());
            etemail.setText(person.getEmail());
            if(person.getProfileDesc()!=null)
            etpdesc.setText(person.getProfileDesc());
            if(person.getWork()!=null)
            etwork.setText(person.getWork());
            if(person.getInstagram()!=null)
            etinstagram.setText(person.getInstagram());

            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Void... params) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            Map<String, String> urlParams = new HashMap<>();
            urlParams.put("mytoken", new Prefrences(getBaseContext()).loadToken());
            person = restTemplate.getForObject(Config.ROOT_URL + "/person/myinfo?authtoken={mytoken}", CustomPerson.class, urlParams);

            return null;
        }
    }


    public void save (View v){
boolean permission =true;


        if (etname.getText().toString().equals("") || etname.getText().toString() == null) {
            etname.setError("Please enter firstname...");
            permission=false;
        } else if (etlastname.getText().toString().equals("") || etlastname.getText().toString() == null) {
            etlastname.setError("Please enter lastname...");
            permission=false;
        } else if (etemail.getText().toString().equals("") || etemail.getText().toString() == null) {
            etemail.setError("Please enter email...");
            permission=false;
        }
        if(permission==true){

new asycTaskToUpdate(etname.getText().toString(),etlastname.getText().toString(),etpdesc.getText().toString(),etwork.getText().toString(),etinstagram.getText().toString()).execute();
        }

    }

    public class asycTaskToUpdate extends AsyncTask<Void,Void,Void>{
        String etname,etlastname, etpdesc, etwork,etinstagram,response;
        public asycTaskToUpdate(String etname,String etlastname,String etpdeesc,String etwork,String etinstagram) {
            this.etname=etname;
            this.etlastname=etlastname;
            this.etpdesc=etpdeesc;
            this.etwork=etwork;
            this.etinstagram=etinstagram;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(response.equals("success"))
                Toast.makeText(getBaseContext(), "Profile settings were updated", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
            startActivity(intent);
        }

        @Override
        protected Void doInBackground(Void... params) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Person person = new Person()
                    .setAuhtToken(prefrences.loadToken())
                    .setFirstName(etname)
                    .setLastName(etlastname)
                    .setProfileDesc(etpdesc)
                    .setWork(etwork)
                    .setInstagram(etinstagram);

            response = restTemplate.postForObject(Config.ROOT_URL + "/person/updateperson", person, String.class);



            return null;
        }
    }
}
