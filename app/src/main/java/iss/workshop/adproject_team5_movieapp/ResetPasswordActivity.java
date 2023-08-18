package iss.workshop.adproject_team5_movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import iss.workshop.adproject_team5_movieapp.retrofit.RetrofitService;
import iss.workshop.adproject_team5_movieapp.utils.MovieApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        initializeComponents();
    }
    private void initializeComponents() {

        EditText inputEmail = findViewById(R.id.text_email);


        Button buttonSave = findViewById(R.id.signIn);


        buttonSave.setOnClickListener(view -> {
            String email = String.valueOf(inputEmail.getText());

            if (validateLogin(email)) {
                reset(email);
            }
        });

    }

    public boolean validateLogin(String text) {
        if (text == null || text.trim().length() == 0) {
            Toast.makeText(this, "email is required", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void reset(final String email) {
        RetrofitService retrofitService = new RetrofitService();
        MovieApi userApi = retrofitService.getRetrofit().create(MovieApi.class);

        userApi.reset(email)
                .enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {

                        Intent intent = new Intent(ResetPasswordActivity.this,LoginActivity.class);

                        startActivity(intent);

                        Toast.makeText(ResetPasswordActivity.this, "Send successfully!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                        Toast.makeText(ResetPasswordActivity.this, "no user found  ", Toast.LENGTH_SHORT).show();
                    }




                });

    }

}