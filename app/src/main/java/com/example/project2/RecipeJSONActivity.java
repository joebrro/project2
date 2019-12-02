package com.example.project2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RecipeJSONActivity {
    private List<myRecipeActivity> newRecipeList;
    private String title;
    private String recipe_url;
    private String img_url;
    private String recipe_id;
    private String f2f_url;
    private String social_rank;
    private double publisher;
    private String publisher_url;



    public List<myRecipeActivity> getJSONdata(String jsonUrl){
        String list = "";
        newRecipeList = new ArrayList<>();
        try{
            URL url = new URL(jsonUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStreams = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStreams, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();

            while((list = reader.readLine())!=null){
                sb.append(list);
            }
            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray jsonArray= jsonObject.getJSONArray("recipes");
            int count = jsonObject.getInt("count");

            for(int i=0; i< count; i++){
                JSONObject aRecipe = jsonArray.getJSONObject(i);
                title = aRecipe.getString("f2f_url");
                title = aRecipe.getString("title");
                recipe_url = aRecipe.getString("source_url");
                title = aRecipe.getString("social_rank");
                img_url = aRecipe.getString("image_url");
                title = aRecipe.getString("publisher");
                recipe_id = aRecipe.getString("recipe_id");
                recipe_id = aRecipe.getString("publisher_url");
                myRecipeActivity recipeActivity = new myRecipeActivity(title, recipe_url, img_url, recipe_id,
                        f2f_url, social_rank, publisher, publisher_url);
                newRecipeList.add(recipeActivity);
//                searchActivity.addData(title, recipe_url,img_url, recipe_id);
                // setPublisher(publisher);
                //        setF2f_url(f2f_url);
                //        setTitle(title);
                //        setSource_url(source_url);
                //        setRecipe_id(recipe_id);
                //        setImage_url(image_url);
                //        setSocial_rank(social_rank);
                //        setPublisher_url(publisher_url);
            }
            connection.disconnect();
            inputStreams.close();

        } catch (JSONException e) {

            return null;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return newRecipeList;
    }
}