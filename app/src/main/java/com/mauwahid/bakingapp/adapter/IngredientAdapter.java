package com.mauwahid.bakingapp.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mauwahid.bakingapp.R;
import com.mauwahid.bakingapp.model.domain.Ingredient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Handsome on 10/15/2017.
 */

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    private ArrayList<Ingredient> ingredients = new ArrayList<>();
    private Context context;

    public IngredientAdapter(ArrayList<Ingredient> ingredients) {
            this.ingredients = ingredients;
            }



    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_ingredient, parent, false);

            if (context == null) {
            context = itemView.getContext();
            }

            return new IngredientAdapter.IngredientViewHolder(itemView);
            }

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {

            Ingredient item = ingredients.get(position);
           // holder.tvIngredient.setText(TextUtils.capitalizeEachWords(item.getIngredient()));
           // String quantity = .removeTrailingZero(String.valueOf(item.getQuantity())) + " " +item.getMeasure();

            holder.tvIngredient.setText(item.getIngredient());
            holder.tvQuantity.setText(item.getQuantity()+ " "+item.getMeasure());
            }

    @Override
    public int getItemCount() {
            return ingredients.size();
            }

    class IngredientViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_ingredient)
        LinearLayout llIngredient;
        @BindView(R.id.tv_ingredient)
        TextView tvIngredient;
        @BindView(R.id.tv_quantity)
        TextView tvQuantity;

        IngredientViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
