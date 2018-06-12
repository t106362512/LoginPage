package com.example.c402.loginpage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public static final String PREF = "REG_PREF";
    public static final String PREF_USERNAME = "REG_UserName";
    public static final String PREF_PASSWORD = "REG_Password";

    EditText field_userName,field_password;
    Button button;
    TextView tv_RH;
    String userName, password, pref_userName, pref_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
        setOnLinster();
        restorePrefs();
    }

    private void findViews(){
        field_userName = findViewById(R.id.etUserName);
        field_password = findViewById(R.id.etPassword);
        button = findViewById(R.id.btnSignIn);
        tv_RH = findViewById(R.id.tvRegister);
    }

    private void setOnLinster(){
        button.setOnClickListener(singIn);
        tv_RH.setOnClickListener(reg);
    }

    private void restorePrefs(){
        SharedPreferences settings = getSharedPreferences(PREF,0);
        pref_userName = settings.getString(PREF_USERNAME,"");
        pref_password = settings.getString(PREF_PASSWORD,"");
    }
    protected void onRestart() {
        super.onRestart();
        restorePrefs();
    }
    @Override
    protected void onStop() {
        super.onStop();
        restorePrefs();
    }

    View.OnClickListener singIn = v -> {
        userName = field_userName.getText().toString();
        password = field_password.getText().toString();
        if (userName.equals("") || password.equals("") )
            Toast.makeText(this,"欄位不可空白",Toast.LENGTH_LONG).show();
        else if (!userName.equals(pref_userName) || !password.equals(pref_password) )
            Toast.makeText(this,"帳號檢驗失敗",Toast.LENGTH_LONG).show();
        else {
            Bundle bundle = new Bundle();
            bundle.putString("KEY_USERNAME", userName);
            bundle.putString("KEY_PASSWORD", password);
            Toast.makeText(this,"帳號檢驗成功",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, UserHomePage.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };

    View.OnClickListener reg = v -> {
            Intent intent = new Intent(this,RegisterActivity.class);
            startActivity(intent);
    };


}
