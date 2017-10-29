package com.mauwahid.bakingapp.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Handsome on 8/27/2017.
 */

public class BakingDBHelper  extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "baking.db";

    private static final int DATABSE_VERSION = 1;

    public BakingDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    final String SQL_CREATE_RECIPE_TABLE = "CREATE TABLE " + BakingContract.RecipeEntry.TABLE_NAME + " (" +
            BakingContract.RecipeEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BakingContract.RecipeEntry.COLUMN_NAME + " VARCHAR(200) NOT NULL," +
            BakingContract.RecipeEntry.COLUMN_IMAGE + " TEXT NOT NULL," +
            BakingContract.RecipeEntry.COLUMN_SERVINGS + " INTEGER NOT NULL DEFAULT 0," +
            " UNIQUE (" + BakingContract.RecipeEntry.COLUMN_ID + ") ON CONFLICT REPLACE";

    final String SQL_CREATE_STEP_TABLE = "CREATE TABLE " + BakingContract.StepEntry.TABLE_NAME + " (" +
            BakingContract.StepEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BakingContract.StepEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL," +
            BakingContract.StepEntry.COLUMN_SHORT_DESCRIPTION + " TEXT NOT NULL," +
            BakingContract.StepEntry.COLUMN_THUMBNAIL_URL + " TEXT NOT NULL," +
            BakingContract.StepEntry.COLUMN_VIDEO_URL + " TEXT NOT NULL," +
            BakingContract.StepEntry.COLUMN_RECIPE_ID + " INTEGER NOT NULL DEFAULT 0," +
            " UNIQUE (" + BakingContract.RecipeEntry.COLUMN_ID + ") ON CONFLICT REPLACE";

    final String SQL_CREATE_INGREDIENT_TABLE = "CREATE TABLE " + BakingContract.IngredientEntry.TABLE_NAME + " (" +
            BakingContract.IngredientEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            BakingContract.IngredientEntry.COLUMN_INGREDIENT + " TEXT NOT NULL," +
            BakingContract.IngredientEntry.COLUMN_MEASURE + " TEXT NOT NULL," +
            BakingContract.IngredientEntry.COLUMN_QUANTITY + " INTEGER NOT NULL DEFAULT 0," +
            BakingContract.IngredientEntry.COLUMN_RECIPE_ID + " INTEGER NOT NULL DEFAULT 0," +
            " UNIQUE (" + BakingContract.RecipeEntry.COLUMN_ID + ") ON CONFLICT REPLACE";


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(SQL_CREATE_RECIPE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_INGREDIENT_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_STEP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BakingContract.RecipeEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BakingContract.StepEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BakingContract.IngredientEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

}

