package com.example.cineenigma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cineenigma.model.User;

public class MainActivity extends AppCompatActivity {

    private EditText mUsername;
    private EditText mPassword;
    private TextView mLoginErrorText;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = new User("toto", "titi");

        mUsername = (EditText)findViewById(R.id.username_login);
        mPassword = (EditText)findViewById(R.id.password_login);
        mLoginErrorText = (TextView)findViewById(R.id.error_login);
    }


    /**
     * Called when the button login is clicked
     * If password and username is correct it open list of rating
     */
    public void login(View view){
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();
        if (user.getUsername().equals(username) && user.getPassword().equals(password)){
            //User login good
            hideLoginError();

            //Start rating list page now
            Context context = MainActivity.this;
            Class raitingListClass = RaitingListActivity.class;

            Intent intent = new Intent(context, raitingListClass);
            intent.putExtra("username", username);
            startActivity(intent);
        }
        else {
            //Login error
            showLoginError();
        }

    }

    /**
     * set the mLoginError visible
     */
    private void showLoginError(){
        mLoginErrorText.setVisibility(View.VISIBLE);
    }

    /**
     * set the mLoginError invisible
     */
    private void hideLoginError(){
        mLoginErrorText.setVisibility(View.INVISIBLE);
    }

}
