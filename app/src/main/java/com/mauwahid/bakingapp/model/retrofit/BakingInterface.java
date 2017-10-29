package com.mauwahid.bakingapp.model.retrofit;

import com.mauwahid.bakingapp.model.domain.Recipe;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Handsome on 8/27/2017.
 */

public interface BakingInterface {

    @GET("baking.json")
    Call<ArrayList<Recipe>> getRecipe();
}
