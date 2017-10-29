package com.mauwahid.bakingapp.model.database;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Handsome on 8/27/2017.
 */

public class BakingContract {

    public static final String CONTENT_AUTHORITY = "com.mauwahid.bakingapp";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_RECIPE = "recipe";

    public static final String PATH_STEP = "step";

    public static final String PATH_INGREDIENS = "ingrediens";


    public static final class RecipeEntry  implements BaseColumns {

        public static final Uri URI_RECIPE = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_RECIPE)
                .build();



        public static final String TABLE_NAME = "recipe";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_SERVINGS = "servings";

        public static String getSqlSelect() {
            return "select * from "+TABLE_NAME;
        }


    }



    public static final class StepEntry  implements BaseColumns {

        public static final Uri URI_STEP = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_STEP)
                .build();



        public static final String TABLE_NAME = "step";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_SHORT_DESCRIPTION = "short_description";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_VIDEO_URL= "video_url";
        public static final String COLUMN_THUMBNAIL_URL = "thumbnail_url";
        public static final String COLUMN_RECIPE_ID = "recipe_id";

        public static String getSqlSelect() {
            return "select * from "+TABLE_NAME;
        }


    }

    public static final class IngredientEntry  implements BaseColumns {

        public static final Uri URI_INGREDIENS = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_INGREDIENS)
                .build();



        public static final String TABLE_NAME = "step";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_MEASURE = "measure";
        public static final String COLUMN_INGREDIENT = "ingredient";
        public static final String COLUMN_RECIPE_ID = "recipe_id";

        public static String getSqlSelect() {
            return "select * from "+TABLE_NAME;
        }


    }
}
