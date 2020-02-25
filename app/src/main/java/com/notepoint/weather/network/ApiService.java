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

package com.notepoint.weather.network;

import com.notepoint.weather.data.AllWeatherDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("weather")
    Call<AllWeatherDetails>getCurrentWeather(
            @Query("q") String place,
            @Query("appid") String apiKey
    );

    @GET("weather")
    Call<AllWeatherDetails>getCurrentWeatherFromLatLang(
            @Query("lat") String latitude,
            @Query("lon") String longitude,
            @Query("appid") String apiKey
    );


    //http://api.openweathermap.org/data/2.5/forecast?q=bangalore&cnt=6&appid=cd33e8e3b9c64bfde031daee6c5cd59b
    @GET("forecast")
    Call<AllWeatherDetails>getWeatherForecast(
            @Query("q") String place,
            @Query("cnt") String numberOfDays,
            @Query("appid") String apiKey
    );


}