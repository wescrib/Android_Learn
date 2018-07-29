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
import android.widget.Switch;

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


public class MainActivity extends AppCompatActivity {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
/*************SAVE STUFF ****************/
//      ParseObject score = new ParseObject("Score");
//      score.put("username", "nick");
//      score.put("score", 45);
//      score.saveInBackground(new SaveCallback() {
//          @Override
//          public void done(ParseException e) {
//              if(e == null){
//                  Log.i("Success", "saved test score");
//              }else{
//                  e.printStackTrace();
//              }
//          }
//      });

      /*******************GET STUFF *******************************/
      ParseQuery<ParseObject> query = ParseQuery.getQuery("Tweets");

      query.getInBackground("FRtpXtw4nF", new GetCallback<ParseObject>() {
          @Override
          public void done(ParseObject object, ParseException e) {
              if( e == null && object != null){
                  object.getString("username");
                  Log.i("USERNAME", object.getString("username"));
                  Log.i("TWEET", object.getString("content"));

                  object.put("content","SECOND tweet");
                  object.saveInBackground();

                  Log.i("USERNAME", object.getString("username"));
                  Log.i("TWEET", object.getString("content"));
              }
          }
      });

//      ParseObject tweets = new ParseObject("Tweets");
//      tweets.put("username", "coolguy91");
//      tweets.put("content", "i am twittering");
//      tweets.saveInBackground(new SaveCallback() {
//          @Override
//          public void done(ParseException e) {
//              if(e == null){
//                  Log.i("Success", "posted!");
//              }else{
//                  Log.i("Failure", "not posted");
//              }
//          }
//      });
    
    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

}