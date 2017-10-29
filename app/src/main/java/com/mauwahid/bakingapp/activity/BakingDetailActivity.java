package com.mauwahid.bakingapp.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.mauwahid.bakingapp.R;
import com.mauwahid.bakingapp.adapter.BakingDetailAdapter;
import com.mauwahid.bakingapp.model.domain.Recipe;
import com.mauwahid.bakingapp.model.domain.Step;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Handsome on 10/15/2017.
 */

public class BakingDetailActivity extends AppCompatActivity  implements StepFragment.StepFragmentListener {


    @BindView(R.id.img_expanded)
    ImageView imgExpanded;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;



    ViewPager viewPager;
    FrameLayout flDetailStepContainer;

    private BakingDetailAdapter mSectionsPagerAdapter;
    private IngredientFragment ingredientFragment;
    private StepFragment stepFragment;
    private boolean isTablet;
    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_baking_detail);

        ButterKnife.bind(this);

        if (getIntent() != null && getIntent().hasExtra("recipe")) {
            recipe = getIntent().getParcelableExtra("recipe");
        }

        setupToolbar();

    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(recipe.getName());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void setupFragment(Bundle savedInstanceState) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            ingredientFragment = IngredientFragment.newInstance(recipe.getIngredients());
            stepFragment = StepFragment.newInstance(recipe.getSteps(), isTablet);
        } else {
            ingredientFragment = (IngredientFragment) fragmentManager
                    .getFragment(savedInstanceState, "ingredientFragment");
            stepFragment = (StepFragment) fragmentManager.getFragment(savedInstanceState,
                    "stepFragment");
        }

        mSectionsPagerAdapter = new BakingDetailAdapter(
                fragmentManager,
                ingredientFragment,
                stepFragment);
        viewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    private void showDetailStepFragment(Step step) {
      //  getSupportFragmentManager().beginTransaction()
      //          .replace(R.id.fl_detail_step_container, StepDetailFragment.newInstance(step, isTablet))
       //         .commit();
    }

    @Override
    public void onStepClicked(Step step) {
        showDetailStepFragment(step);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
