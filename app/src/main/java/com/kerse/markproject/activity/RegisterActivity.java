package com.kerse.markproject.activity;

import android.Manifest;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kerse.markproject.R;
import com.kerse.markproject.common.Config;
import com.kerse.markproject.model.ListableObject;
import com.kerse.markproject.model.Person;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import me.himanshusoni.edittextspinner.EditTextSpinner;

public class RegisterActivity extends AppCompatActivity {

    EditText etFirstName, etLastName, etEmail, etPassword;
    String etGender = null;
    Button btRegister;
    ProgressBar registerProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        tilEmail = (TextInputLayout) findViewById(R.id.tilEmail);
        tilFirstName = (TextInputLayout) findViewById(R.id.tilFirstName);
        tilLastName = (TextInputLayout) findViewById(R.id.tilLastName);
        tilPassword = (TextInputLayout) findViewById(R.id.tilPassword);
        tilGender = (TextInputLayout) findViewById(R.id.tilGender);
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btRegister = (Button) findViewById(R.id.btRegister);
        registerProgress = (ProgressBar) findViewById(R.id.registerProgress);
        etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());


        EditTextSpinner<ListableObject> spinner = (EditTextSpinner<ListableObject>) findViewById(R.id.editText);


        List<ListableObject> listableObjects = Arrays.asList(
                new ListableObject("Male"),
                new ListableObject("Female")
        );

        spinner.setItems(listableObjects);

        spinner.setOnItemSelectedListener(new EditTextSpinner.OnItemSelectedListener<ListableObject>() {
            @Override
            public void onItemSelectedListener(ListableObject item, int selectedIndex) {
                etGender = item.getName();
            }
        });
    }

    TextInputLayout tilFirstName, tilLastName, tilEmail, tilPassword, tilGender;

    public void toLogin(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void visiblePass(View v) {
        if (etPassword.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD))
            etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        else {
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }

    }

    public void register(View v) {
        boolean validation = true;
        tilPassword.setErrorEnabled(false);
        tilEmail.setErrorEnabled(false);
        tilFirstName.setErrorEnabled(false);
        tilLastName.setErrorEnabled(false);
        tilGender.setErrorEnabled(false);
        if (etFirstName.getText().toString().equals("") || etFirstName.getText().toString() == null) {
            tilFirstName.setErrorEnabled(true);
            tilFirstName.setError("Please enter firstname...");
            validation = false;
        } else if (etLastName.getText().toString().equals("") || etLastName.getText().toString() == null) {
            tilLastName.setErrorEnabled(true);
            tilLastName.setError("Please enter lastname...");
            validation = false;
        } else if (etEmail.getText().toString().equals("") || etEmail.getText().toString() == null) {
            tilEmail.setErrorEnabled(true);
            tilEmail.setError("Please enter email...");
            validation = false;
        } else if (etPassword.getText().toString().equals("") || etPassword.getText().toString() == null) {
            tilPassword.setErrorEnabled(true);
            tilPassword.setError("Please enter password...");
            validation = false;
        } else if (etGender == null) {
            tilGender.setErrorEnabled(true);
            tilGender.setError("Please enter gender...");
            validation = false;
        }


        if (validation)
            new RegisterAsync(etFirstName.getText().toString(), etLastName.getText().toString(), etEmail.getText().toString(), etPassword.getText().toString(), etGender).execute();
    }

    public class RegisterAsync extends AsyncTask<Void, Void, Void> {

        String firstName, lastName, email, password, gender, response;

        public RegisterAsync(String firstname, String lastName, String email, String password, String gender) {
            this.firstName = firstname.trim();
            this.lastName = lastName.trim();
            this.email = email.trim();
            this.password = password.trim();
            this.gender = gender.trim();

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            btRegister.setText("");
            registerProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params) {

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Person person = new Person()
                    .setEmail(email)
                    .setPassword(password)
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setGender(gender);

            response = restTemplate.postForObject(Config.ROOT_URL + "/person/addperson", person, String.class);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (response.equals("success")) {
                Toast.makeText(getApplicationContext(), "Kayıt başarılı...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Email zaten kayıtlı", Toast.LENGTH_SHORT).show();
            }

            btRegister.setText("REGISTER");
            registerProgress.setVisibility(View.GONE);
        }
    }

}




