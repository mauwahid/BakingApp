package com.mauwahid.bakingapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mauwahid.bakingapp.activity.IngredientFragment;
import com.mauwahid.bakingapp.activity.StepFragment;

/**
 * Created by Handsome on 10/15/2017.
 */

public class BakingDetailAdapter extends FragmentPagerAdapter {

    private IngredientFragment ingredientFragment;
    private StepFragment stepFragment;

    public BakingDetailAdapter(FragmentManager fm,
                                        IngredientFragment ingredientFragment,
                                        StepFragment stepFragment) {
        super(fm);
        this.ingredientFragment = ingredientFragment;
        this.stepFragment = stepFragment;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ingredientFragment;
            case 1:
                return stepFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Ingredients";
            case 1:
                return "Steps";
        }
        return null;
    }
}
