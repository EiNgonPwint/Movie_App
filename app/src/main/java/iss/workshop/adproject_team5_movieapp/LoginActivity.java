package iss.workshop.adproject_team5_movieapp;



import android.content.Intent;
import android.content.SharedPreferences;
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

public class LoginActivity extends AppCompatActivity {

    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences pref = getSharedPreferences("user_credentials",MODE_PRIVATE);
        if(pref.contains("username") && pref.contains("password"))
        {
            doLogin(pref.getString("username", ""),
                    pref.getString("password", ""));


        }
        initializeComponents();
    }
    private void initializeComponents() {

        EditText inputEditText = findViewById(R.id.email);

        EditText inputEditTextPassword = findViewById(R.id.password);

        Button buttonSave = findViewById(R.id.signIn);

        Button button_register = findViewById(R.id.register);

        Button button_forgetPassword = findViewById(R.id.forgotPassword);



        buttonSave.setOnClickListener(view -> {
            String name = String.valueOf(inputEditText.getText());

            String password = String.valueOf(inputEditTextPassword.getText());
            if(validateLogin(name,password))
            {
                doLogin(name,password);
            }
        });


        button_register.setOnClickListener(view ->{
            Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);
        });


        button_forgetPassword.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this,ResetPasswordActivity.class);
            startActivity(intent);
        });

    }



    public boolean validateLogin(String name,String password)
    {
        if(name == null || name.trim().length() == 0)
        {
            Toast.makeText(this,"Name is required",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password == null || password.trim().length() == 0)
        {
            Toast.makeText(this,"Password is required",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private  void doLogin(final String name ,final String password)
    {
        RetrofitService retrofitService = new RetrofitService();
        MovieApi movieApi = retrofitService.getRetrofit().create(MovieApi.class);

        movieApi.login(name,password)
                .enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        if(response.isSuccessful())
                        {
                            user = (User) response.body();
                            if(user != null)
                            {
                                SharedPreferences pref = getSharedPreferences("user_credentials",MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("username",user.getName());
                                editor.putString("password",user.getPassword());
                                editor.commit();
                                Toast.makeText(getApplicationContext(),
                                        "Login successful!",Toast.LENGTH_SHORT).show();
                                startProtectedActivity();

                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this, "The username or password is incorrect", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Error! Please try again!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t)
                    {
                        Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
    private void startProtectedActivity() {
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        intent.putExtra("user",user);
        startActivity(intent);
        finish();

    }




}