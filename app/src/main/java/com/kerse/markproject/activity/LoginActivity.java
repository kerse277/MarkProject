package com.kerse.markproject.activity;

import android.Manifest;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kerse.markproject.R;
import com.kerse.markproject.common.Config;
import com.kerse.markproject.common.Prefrences;
import com.kerse.markproject.model.LoginObject;
import com.kerse.markproject.model.Token;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    ProgressBar loginProgress;
    Button btLogin;
    TextInputLayout tilEmail, tilPassword;
    Prefrences prefrences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        prefrences = new Prefrences(this);

        if(prefrences.loadLogin()){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }


        tilEmail = (TextInputLayout) findViewById(R.id.tilEmail);
        tilPassword = (TextInputLayout) findViewById(R.id.tilPassword);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        loginProgress = (ProgressBar) findViewById(R.id.loginProgress);
        btLogin = (Button) findViewById(R.id.btLogin);

    }


    public void toRegister(View v) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    public void login(View v) {
        boolean validation = true;
        tilPassword.setErrorEnabled(false);
        tilEmail.setErrorEnabled(false);
        if (etEmail.getText().toString().equals("") || etEmail.getText().toString() == null) {
            tilEmail.setErrorEnabled(true);
            tilEmail.setError("Please enter email...");
            validation = false;
        } else if (etPassword.getText().toString().equals("") || etPassword.getText().toString() == null) {
            tilPassword.setErrorEnabled(true);
            tilPassword.setError("Please enter password...");
            validation = false;
        }


        if (validation)
            new LoginAsync(etEmail.getText().toString(), etPassword.getText().toString()).execute();

    }

    public class LoginAsync extends AsyncTask<Void, Void, Void> {

        private String email, password;
        private Token token;

        public LoginAsync(String email, String password) {
            this.email = email;
            this.password = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            btLogin.setText("");
            loginProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            LoginObject loginObject = new LoginObject()
                    .setEmail(email)
                    .setPassword(password);

            token = restTemplate.postForObject(Config.ROOT_URL + "/person/login", loginObject, Token.class);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (token != null) {
                prefrences.saveLogin(true);
                prefrences.saveToken(token.getAuthToken());
                prefrences.saveID(token.getUniqueID());
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            } else
                Toast.makeText(getApplicationContext(), "Wrong password or email", Toast.LENGTH_SHORT).show();

            btLogin.setText("LOGIN");
            loginProgress.setVisibility(View.GONE);
        }
    }
}
