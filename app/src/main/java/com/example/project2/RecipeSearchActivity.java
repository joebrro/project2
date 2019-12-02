package com.example.project2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import android.content.Context;
import java.util.List;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecipeSearchActivity extends AppCompatActivity {
    private static final int RESULT_SAVE = 3;
    Toolbar testToolbar;
    String choiceNewsStr = "news";
    String choiceElecStr = "electric";
    String choiceCurrencyStr = "currency";
    String choiceMealStr = "meal";
    String choiceRecipeAlert = "alert for menu ";

    public static final String ITEM_SELECTED = "ITEM";
    public static final String ITEM_POSITION = "POSITION";
    public static final String ITEM_ID = "Id";
    public static final String ITEM_URL = "url";
    public static final String ITEM_IMAGE_URL = "image_Url";
    public static final String ITEM_RECIPE_ID = "recipeID";
    public static final String ITEM_ACTIVITY_CALLING = "Activity";
    public static final int EMPTY_ACTIVITY = 345;

    private EditText searchRecipeText;
    private Button bottonnSearchnSearch;
    private ProgressBar progressBar;
    private ListView listView;

    public static final String SHARED_PREFS = "sPrefs";
    public static final String TEXT = "text";
    private String meals;
    private String text2;
    public SharedPreferences prefs;
    private List<myRecipeActivity> myRecipeActivity = new ArrayList<>();

    private String overflowMessage = "This is the overflow message";
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recipe);
        testToolbar = findViewById(R.id.testToolbar);
        testToolbar.setTitle("Recipe Menu");
        setSupportActionBar(testToolbar);

        listView = (ListView) findViewById(R.id.list_result);
        searchRecipe = (EditText) findViewById(R.id.recipe_search);
        bottonSearch = (Button) findViewById(R.id.recipe_searchButton);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String search = prefs.getString("search menu(comp)", "");
        searchRecipeText.setText(search);

    }

    Button bottonSearch = (Button) findViewById(R.id.insert);
    bottonSearch.setOnClickListener(new View.OnClickListener()

    {
        public void onClick (View v){
        meals = searchRecipeText.getText().toString();

        if (meals != null && !meals.isEmpty()) {
            new RecipeAsync().execute(queryUrl);
        } else {
            toastMessage(null);
        }
    }
    });


//    Button alertDialogButton = (Button)findViewById(R.id.insert);
//        alertDialogButton.setOnClickListener( clik ->  );
//
//    //Show the toast immediately:
//        Toast.makeText(this, "Welcome to Menu Example", Toast.LENGTH_LONG).show();
//}

    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.recipe_menu, menu);
        this.menu = menu;
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EMPTY_ACTIVITY) {
            if (resultCode == RESULT_SAVE) //if you hit the delete button instead of back button
            {

//                long listID = data.getLongExtra(LIST_ITEM_ID, 0);
//                deleteListMessageId((int) listID);
//                long dbID = data.getLongExtra(DB_ITEM_ID, 0);
//                deleteDbMessageID((int) dbID);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
//        prefs = getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("userSearch", searchRecipeText.getText().toString());
//            editor.putBoolean("logged_in", true);
//        editor.apply();
        editor.commit();

    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public List<myRecipeActivity> getData(){
        return myRecipeActivity;
    }

//    private void runQuery(String query, String responseType)
//    {
//        RecipeAsync theQuery = new RecipeAsync(responseType);
//        theQuery.execute();//new String [] {"A", "B", "C", "D"} );
//    }

    public class RecipeAsync extends AsyncTask<String,  Integer, List<myRecipeActivity>>{
        String title;
        String foodUrl;
        String imageUrl;
        myRecipeActivity recipe;
        String imageID;
        String result = null;
        Context context;
        String queryURL = "http://torunski.ca/FinalProjectLasagna.json";
        public RecipeJSONActivity queryData = new RecipeJSONActivity();

        @Override
        protected List<myRecipeActivity> doInBackground(String... strings) {
            myRecipeActivity = queryData.getJSONdata(queryURL);
            return myRecipeActivity;
//             result = null;
//            String queryURL = "http://torunski.ca/FinalProjectLasagna.json";
//
//            try {       // Connect to the server:
//                URL url = new URL(queryURL);
//                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                InputStream inStream = urlConnection.getInputStream();
//
//                //Set up the JSON object parser:
//                // json is UTF-8 by default
//                BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "UTF-8"), 8);
//                StringBuilder sb = new StringBuilder();
//
//                String line = null;
//                while ((line = reader.readLine()) != null) {
//                    sb.append(line + "\n");
//                }
//
//                urlConnection.disconnect();
//                inStream.close();
//
//                return result;
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//                return result;
//            }

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
            progressBar.setVisibility(View.VISIBLE);
        }


        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List<myRecipeActivity> result) {
            super.onPostExecute(result);
        }

    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.news:
                Toast.makeText(this, choiceNewsStr , Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, RecipeSearchActivity.class));

                break;
            case R.id.dest:
                Toast.makeText(this, choiceElecStr , Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, RecipeSearchActivity.class));
                break;
            case R.id.payment:
                Toast.makeText(this, choiceCurrencyStr , Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, RecipeSearchActivity.class));
                break;
            case R.id.meal:
                Toast.makeText(this, choiceMealStr , Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, RecipeSearchActivity.class));
                break;
            case R.id.recipe_choice:
                Toast.makeText(this, choiceRecipeAlert, Toast.LENGTH_SHORT).show();
                alertExample();
                break;
        }

        return true;

    }

    public void alertExample(){
        View alertMiddle = this.getLayoutInflater().inflate(R.layout.activity_main_dialog, null);
         final EditText alertET = alertMiddle.findViewById(R.id.alertET);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Menu for recipe search ");
        builder.setMessage("The Message")
                .setPositiveButton("Positive", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        choiceRecipeAlert = alertET.getText().toString();
                    }
                })
                .setNegativeButton("Negative", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // What to do on Cancel
                        dialog.cancel();
                    }
                }).setView(alertMiddle);

        builder.create().show();
    }
}
