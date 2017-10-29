package com.mauwahid.bakingapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mauwahid.bakingapp.R;
import com.mauwahid.bakingapp.adapter.IngredientAdapter;
import com.mauwahid.bakingapp.model.domain.Ingredient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Handsome on 10/15/2017.
 */

public class IngredientFragment extends Fragment {


    @BindView(R.id.rv_ingredient) RecyclerView rvIngredient;

    private IngredientAdapter adapter;

    public static IngredientFragment newInstance(ArrayList<Ingredient> ingredients) {
        IngredientFragment ingredientFragment = new IngredientFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("ingredients", ingredients);
        ingredientFragment.setArguments(bundle);

        return ingredientFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredient, container, false);
        ButterKnife.bind(this, view);


        initRecycleView();

        return view;
    }

    private void initRecycleView() {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvIngredient.setLayoutManager(layoutManager);

        ArrayList<Ingredient> ingredients = getArguments().getParcelableArrayList("ingredients");
        adapter = new IngredientAdapter(ingredients);
        rvIngredient.setAdapter(adapter);

    }



}
