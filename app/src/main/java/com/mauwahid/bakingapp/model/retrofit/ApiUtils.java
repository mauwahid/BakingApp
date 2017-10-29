package com.mauwahid.bakingapp.model.retrofit;

/**
 * Created by Handsome on 8/27/2017.
 */

public class ApiUtils {



        public static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";

        public static BakingInterface getBakingInterface() {
            return RetrofitBuilder.getClient(BASE_URL).create(BakingInterface.class);
        }

}
