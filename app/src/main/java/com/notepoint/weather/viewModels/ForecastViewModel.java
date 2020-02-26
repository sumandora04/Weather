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

package com.notepoint.weather.viewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.notepoint.weather.data.AllWeatherDetails;
import com.notepoint.weather.data.Weather;
import com.notepoint.weather.network.ApiService;
import com.notepoint.weather.network.NetworkService;
import com.notepoint.weather.utils.CommonUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForecastViewModel extends ViewModel {
    private static final String TAG = "ForecastViewModel";
    private MutableLiveData<List<com.notepoint.weather.data.List>> _weatherForecast = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public MutableLiveData<Boolean> isError = new MutableLiveData<>();

    public LiveData<Boolean> isDataLoading() {
        return isLoading;
    }
    public LiveData<Boolean> hasNetworkError() {
        return isError;
    }

    public LiveData<List<com.notepoint.weather.data.List>> getForecastLiveData() {
        return _weatherForecast;
    }


    public ForecastViewModel() {
        getForecast(NetworkService.SEARCHED_CITY);
    }

    private void getForecast(String place){
        isLoading.setValue(true);
        ApiService apiService = NetworkService.create();
        Call<AllWeatherDetails> call = apiService.getWeatherForecast(
                place,
                NetworkService.TEMP_UNIT,
                NetworkService.APIKEY
        );

        call.enqueue(new Callback<AllWeatherDetails>() {
            @Override
            public void onResponse(Call<AllWeatherDetails> call, Response<AllWeatherDetails> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: " + response.body());

                        List<com.notepoint.weather.data.List> responseList = response.body().getList();
                        List<com.notepoint.weather.data.List> tempList = new ArrayList<>();

                        /*
                        * Modify the date format.
                        * */
                        for (com.notepoint.weather.data.List item : responseList) {
                            try {
                                Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH).parse(item.getDtTxt());
                                String strDate =CommonUtils.DATE.format(date);
                                item.setDtTxt(strDate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }

                        /*
                        * Removing duplicate date forecast
                        * */
                        HashMap<String, List<Weather>> hashMap = new LinkedHashMap<>();
                        for (com.notepoint.weather.data.List item : responseList) {
                            hashMap.put(item.getDtTxt(),item.getWeather());
                        }

                        /*
                        * Create a list of type com.notepoint.weather.data.List
                        * */
                        Iterator hmIterator = hashMap.entrySet().iterator();
                        while (hmIterator.hasNext()) {
                            Map.Entry mapElement = (Map.Entry)hmIterator.next();
                            com.notepoint.weather.data.List list = new com.notepoint.weather.data.List((List<Weather>) mapElement.getValue(), (String) mapElement.getKey());
                            tempList.add(list);
                        }

                        _weatherForecast.setValue(tempList);
                        isLoading.setValue(false);
                    }
                } else {
                    Log.d(TAG, "onResponse: " + response.message());
                    isLoading.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<AllWeatherDetails> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                isLoading.setValue(false);
                isError.setValue(true);
            }
        });
    }
}
