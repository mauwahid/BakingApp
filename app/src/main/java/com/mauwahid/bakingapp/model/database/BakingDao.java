package com.mauwahid.bakingapp.model.database;

import android.content.Context;

import com.mauwahid.bakingapp.model.domain.Ingredient;
import com.mauwahid.bakingapp.model.domain.Recipe;
import com.mauwahid.bakingapp.model.domain.Step;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Handsome on 8/27/2017.
 */

public class BakingDao {

    private Context context;
    private BakingDBHelper mOpenHelper;



    public BakingDao(Context context){
        this.context = context;
    }

    //Get All Recipe Data
    public ArrayList<Recipe> getAllRecipe(){
        return null;
    }

    public ArrayList<Ingredient> getAllIngredients(String recipeId){
        return null;
    }

    public ArrayList<Step> getAllStep(String recipeId){
        return null;
    }

    public Recipe getRecipe(String recipeId){
        return null;
    }

    public Ingredient getIngredient(String ingredientId){
        return null;
    }

    public Step getStep(String stepId){
        return null;
    }

    public int insertBulkRecipes(List recipes){
        return 0;
    }

}
