package com.example.c402.loginpage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    public static final String PREF = "REG_PREF";
    public static final String PREF_EMAIL = "REG_Email";
    public static final String PREF_USERNAME = "REG_UserName";
    public static final String PREF_PASSWORD = "REG_Password";

    EditText emailF,userNameF,passwordF,ageF;
    String email,userName,password,age,pref_Email,pref_userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViews();
        restorePrefs();
    }

    private void findViews(){
        emailF = findViewById(R.id.etEmail);
        userNameF = findViewById(R.id.etUserName);
        passwordF = findViewById(R.id.etPassword);
        ageF = findViewById(R.id.etAge);
    }

    private void getInf(){
        email = emailF.getText().toString();
        userName = userNameF.getText().toString();
        password = passwordF.getText().toString();
        age = ageF.getText().toString();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences settings = getSharedPreferences(PREF,0);
        settings.edit().putString(PREF_EMAIL,email).commit();
        settings.edit().putString(PREF_USERNAME,userName).commit();
        settings.edit().putString(PREF_PASSWORD,password).commit();
    }

    private void restorePrefs(){
        SharedPreferences settings = getSharedPreferences(PREF,0);
        pref_Email = settings.getString(PREF_EMAIL,"");
        pref_userName = settings.getString(PREF_USERNAME,"");
    }
    protected void onRestart() {
        super.onRestart();
        restorePrefs();
    }

    public void onClick_R(View view) {
        getInf();
        if (email.equals("") || userName.equals("") || password.equals("") || age.equals(""))
            Toast.makeText(this,"欄位不可空白",Toast.LENGTH_SHORT).show();
        else if(Patterns.EMAIL_ADDRESS.matcher(email).matches() == false)
            Toast.makeText(this,"信箱格式錯誤",Toast.LENGTH_SHORT).show();
        else if(userName.equals(pref_userName) || email.equals(pref_Email) )
            Toast.makeText(this,"帳號己存在，請重新輸入",Toast.LENGTH_SHORT).show();
        else {
            Toast.makeText(this,"註冊成功",Toast.LENGTH_SHORT).show();
            Bundle bundle = new Bundle();
            bundle.putString("KEY_USERNAME", userName);
            bundle.putString("KEY_PASSWORD", password);
            Intent intent = new Intent(this, UserHomePage.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }

}
