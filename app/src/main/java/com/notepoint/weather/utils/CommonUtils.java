/*
 * Copyright 2020 SumanShekhar. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.notepoint.weather.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CommonUtils {

    public static final SimpleDateFormat DATE_TIME = new SimpleDateFormat("d MMMM, yyyy h:mm,a", Locale.ENGLISH);
    public static final SimpleDateFormat TIME = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);
    public static final SimpleDateFormat DAY_DATE = new SimpleDateFormat("E\nMMM d", Locale.ENGLISH);

    public static String epochToDateTimeConverter(long epoch, SimpleDateFormat formatter) {
        Date date = new Date(epoch*1000);
        return formatter.format(date);
    }




    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
}
