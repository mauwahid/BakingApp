package com.mauwahid.bakingapp.adapter;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mauwahid.bakingapp.R;
import com.mauwahid.bakingapp.model.domain.Recipe;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Handsome on 10/8/2017.
 */

public class RecipeAdapter  extends RecyclerView.Adapter<RecipeAdapter.RecipeAdapterViewHolder>{

    private Context mContext;
    final private RecipeAdapterOnClickHandler mClickHandler;
    private List<Recipe> recipes;

    String TAG = RecipeAdapter.class.getSimpleName();

    @Override
    public void onBindViewHolder(RecipeAdapterViewHolder holder, int position) {
       // mCursor.moveToPosition(position);

        Recipe recipe = recipes.get(position);

        String imageUrl = recipe.getImage();

        if (imageUrl!="") {
            Uri builtUri = Uri.parse(imageUrl).buildUpon().build();
            Picasso.with(mContext).load(builtUri).into(holder.imgRecipe);
        }

        Log.v(TAG, "Title : "+recipe.getName());


        holder.tvTitle.setText(recipe.getName());
        holder.tvServing.setText(String.valueOf(recipe.getServings()));

    }

    @Override
    public int getItemCount() {
        if (null == recipes) return 0;
        return recipes.size();
    }


    public interface RecipeAdapterOnClickHandler {
        void onClick(Recipe recipe);
    }

    public RecipeAdapter(Context context, RecipeAdapterOnClickHandler clickHandler) {
            mContext = context;
            mClickHandler = clickHandler;
        }

    public RecipeAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){

        Log.v(TAG, "ONCREATE VIEW HOLDER");
        int layoutId = R.layout.item_recipe;

        View view = LayoutInflater.from(mContext).inflate(layoutId,viewGroup,false);
        view.setFocusable(true);
        return new RecipeAdapterViewHolder(view);
    }

    class RecipeAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final ImageView imgRecipe;
        final TextView tvTitle;
        final TextView tvServing;
        //final TextView tvIngrediens;


        RecipeAdapterViewHolder(View view) {
            super(view);

            Log.v(TAG, " VIEW HOLDER");


            imgRecipe = (ImageView) view.findViewById(R.id.iv_img_recipe);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            tvServing = (TextView) view.findViewById(R.id.tv_serving);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
          ///  mCursor.moveToPosition(adapterPosition);
         //   int movieId = mCursor.getInt(MainActivity.INDEX_MOVIE_ID);
         //   mClickHandler.onClick(movieId);
        }
    }

    public void setRecipeData(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
       // mContext=context;
        notifyDataSetChanged();
    }
}
