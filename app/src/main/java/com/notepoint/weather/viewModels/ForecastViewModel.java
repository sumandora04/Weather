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
import com.notepoint.weather.network.ApiService;
import com.notepoint.weather.network.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForecastViewModel extends ViewModel {
    private static final String TAG = "ForecastViewModel";
    private MutableLiveData<List<com.notepoint.weather.data.List>> _weatherForecast = new MutableLiveData<>();

    public LiveData<List<com.notepoint.weather.data.List>> getForecastLiveData() {
        return _weatherForecast;
    }


    public ForecastViewModel() {
        getForecast("Bangalore");
    }

    private void getForecast(String place){
        ApiService apiService = NetworkService.create();
        Call<AllWeatherDetails> call = apiService.getWeatherForecast(
                place,
                NetworkService.NUMBER_OF_DAYS_FOR_FORCAST,
                NetworkService.TEMP_UNIT,
                NetworkService.APIKEY
        );

        call.enqueue(new Callback<AllWeatherDetails>() {
            @Override
            public void onResponse(Call<AllWeatherDetails> call, Response<AllWeatherDetails> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: " + response.body());
                        _weatherForecast.setValue(response.body().getList());
                    }
                } else {
                    Log.d(TAG, "onResponse: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<AllWeatherDetails> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
