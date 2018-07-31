/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    EditText emailEditText;
    EditText passwordEditText;
    EditText usernameEditText;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);



    ParseUser user = new ParseUser();

    
    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

  public void signUp(View view) {
      ParseUser user = new ParseUser();

      usernameEditText = findViewById(R.id.usernameEditText);
      emailEditText = findViewById(R.id.emailForm);
      passwordEditText = findViewById(R.id.passwordForm);

      String errMsg = "";

      if (emailEditText.getText().toString().matches("") && passwordEditText.getText().toString().matches("") && usernameEditText.getText().toString().matches("")) {
          errMsg += "Username, Email, Password is required";
          Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show();
      }else if(usernameEditText.getText().toString().matches("")) {
          errMsg += "Username is required";
          Toast.makeText(this, errMsg + " required", Toast.LENGTH_SHORT).show();
      } else if (emailEditText.getText().toString().matches("")) {
          errMsg += "Email is required";
          Toast.makeText(this, errMsg + " required", Toast.LENGTH_SHORT).show();
      } else if(passwordEditText.getText().toString().matches("")){
              errMsg += "Password is required";
              Toast.makeText(this, errMsg + " required", Toast.LENGTH_SHORT).show();
      }else{
          user.setUsername(usernameEditText.getText().toString());
          user.setEmail(emailEditText.getText().toString());
          user.setPassword(passwordEditText.getText().toString());

          user.signUpInBackground(new SignUpCallback() {
              @Override
              public void done(ParseException e) {
                  if(e == null){
                      Log.i("SignUp", "Success!");
                  }else {
                      Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                  }
              }
          });
      }
  }

}