package iss.workshop.adproject_team5_movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import iss.workshop.adproject_team5_movieapp.Model.User;
import iss.workshop.adproject_team5_movieapp.retrofit.RetrofitService;
import iss.workshop.adproject_team5_movieapp.utils.MovieApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initializeComponents();
    }
    private void initializeComponents() {

        EditText inputEditTextName = findViewById(R.id.form_textFieldName);
        EditText inputEditTextEmail = findViewById(R.id.form_textFieldEmail);
        EditText inputEditTextPassword = findViewById(R.id.form_textFieldPassword);

        Button buttonSave = findViewById(R.id.registerBtn);

        Button btnLogin = findViewById(R.id.loginBtn);

        btnLogin.setOnClickListener(view -> {

                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);

                    startActivity(intent);
                }
        );

        RetrofitService retrofitService = new RetrofitService();
        MovieApi  movieApi= retrofitService.getRetrofit().create(MovieApi.class);

        buttonSave.setOnClickListener(view -> {
            String name = String.valueOf(inputEditTextName.getText());

            String email = String.valueOf(inputEditTextEmail.getText());

            String password = String.valueOf(inputEditTextPassword.getText());

            if(validate(name,email,password))
            {
                User user1 = new User();
                user1.setName(name);
                user1.setEmail(email);
                user1.setPassword(password);


                movieApi.save(user1)
                        .enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {

                                Toast.makeText(RegisterActivity.this, "Save successfully!", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                Toast.makeText(RegisterActivity.this, "Save failed", Toast.LENGTH_SHORT).show();

                                //Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"Error occur");
                            }
                        });



            }


        });


    }
    public boolean validate(String name,String email,String password)
    {
        if(name == null || name.trim().length() == 0)
        {
            Toast.makeText(this,"Name is required",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(email == null || email.trim().length() == 0)
        {
            Toast.makeText(this,"Email is required",Toast.LENGTH_SHORT).show();
            return false;
        }


        if(password == null || password.trim().length() == 0)
        {
            Toast.makeText(this,"Password is required",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}