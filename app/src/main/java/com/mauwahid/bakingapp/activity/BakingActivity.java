package com.mauwahid.bakingapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mauwahid.bakingapp.R;
import com.mauwahid.bakingapp.adapter.RecipeAdapter;
import com.mauwahid.bakingapp.model.domain.Recipe;
import com.mauwahid.bakingapp.model.retrofit.ApiUtils;
import com.mauwahid.bakingapp.model.retrofit.BakingInterface;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BakingActivity extends AppCompatActivity implements RecipeAdapter.RecipeAdapterOnClickHandler {


    private final String TAG = BakingActivity.class.getSimpleName();

    @BindView(R.id.rv_recipes) RecyclerView mRecycleRecipe;
    @BindView(R.id.pb_loading_indicator) ProgressBar mLoadingIndicator;
    @BindView(R.id.tv_connection_info) TextView tvConnection;

    String baseUri = "";

    private RecipeAdapter mRecipeAdapter;
    private Call<ArrayList<Recipe>> recipe;

    static String ALL_RECIPES="All_Recipes";
    static String SELECTED_RECIPES="Selected_Recipes";
    static String SELECTED_STEPS="Selected_Steps";
    static String SELECTED_INDEX="Selected_Index";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baking);
       // getSupportActionBar().setElevation(0f);

       // mRecycleRecipe = (RecyclerView) findViewById(R.id.rv_recipes);

        ButterKnife.bind(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);

        mRecipeAdapter = new RecipeAdapter(this, this);


        mRecycleRecipe.setLayoutManager(layoutManager);
        mRecycleRecipe.setAdapter(mRecipeAdapter);

       // showLoading();
        loadData();
    }



    private void showLoading() {
        mRecycleRecipe.setVisibility(View.INVISIBLE);
        mLoadingIndicator.setVisibility(View.VISIBLE);
        tvConnection.setVisibility(View.INVISIBLE);
    }

    private void hideLoading() {
        mRecycleRecipe.setVisibility(View.VISIBLE);
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        tvConnection.setVisibility(View.INVISIBLE);
    }

    private void loadData(){
        BakingInterface iBaking = ApiUtils.getBakingInterface();
        recipe = iBaking.getRecipe();

        recipe.enqueue(new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
                Integer statusCode = response.code();
                Log.v("status code: ", statusCode.toString());

                ArrayList<Recipe> recipes = response.body();

                Bundle recipesBundle = new Bundle();
                recipesBundle.putParcelableArrayList(ALL_RECIPES, recipes);

                mRecipeAdapter.setRecipeData(recipes);

                hideLoading();

              //  if (idlingResource != null) {
               //     idlingResource.setIdleState(true);
               // }

            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
                Log.v("http fail: ", t.getMessage());
            }
        });

    }

    @Override
    public void onClick(Recipe recipe) {

        Bundle selectedRecipeBundle = new Bundle();
      //  ArrayList<Recipe> selectedRecipe = new ArrayList<>();
      //  selectedRecipe.add(recipe);
      //  selectedRecipeBundle.putParcelableArrayList(SELECTED_RECIPES,selectedRecipe);

        selectedRecipeBundle.putParcelable("recipe",recipe);

        final Intent intent = new Intent(this, BakingDetailActivity.class);
        intent.putExtras(selectedRecipeBundle);
        startActivity(intent);

    }
}
