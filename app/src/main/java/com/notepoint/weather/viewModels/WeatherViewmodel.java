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

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.notepoint.weather.data.AllWeatherDetails;
import com.notepoint.weather.data.Weather;
import com.notepoint.weather.network.ApiService;
import com.notepoint.weather.network.NetworkService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherViewmodel extends ViewModel {

    private static final String TAG = "WeatherViewmodel";
    private MutableLiveData<AllWeatherDetails> _allWeatherDetails = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public MutableLiveData<Boolean> isError = new MutableLiveData<>();
    public MutableLiveData<Boolean> isUnknownError = new MutableLiveData<>();
    public MutableLiveData<Boolean> showSearchHint = new MutableLiveData<>();
    public MutableLiveData<Boolean> showParentWeather = new MutableLiveData<>();


    public LiveData<AllWeatherDetails> getAllWeatherLiveData() {
        return _allWeatherDetails;
    }
    public LiveData<Boolean> isDataLoading() {
        return isLoading;
    }
    public LiveData<Boolean> shouldShowParentWeather() {
        return showParentWeather;
    }
    public LiveData<Boolean> hasUnknownError() {
        return isUnknownError;
    }
    public LiveData<Boolean> hasNetworkError() {
        return isError;
    }
    public LiveData<Boolean> shouldShowSearchHint() {
        return showSearchHint;
    }


    public void getCurrentWeatherData(String place) {
        showSearchHint.setValue(false);
        isLoading.setValue(true);
        ApiService apiService = NetworkService.create();
        Call<AllWeatherDetails> call = apiService.getCurrentWeather(place, NetworkService.TEMP_UNIT, NetworkService.APIKEY);
        call.enqueue(new Callback<AllWeatherDetails>() {
            @Override
            public void onResponse(Call<AllWeatherDetails> call, Response<AllWeatherDetails> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: " + response.body());
                        _allWeatherDetails.setValue(response.body());
                        isLoading.setValue(false);
                        showParentWeather.setValue(true);
                        showSearchHint.setValue(false);
                        isUnknownError.setValue(false);

                    }
                } else {
                    Log.d(TAG, "onResponse: " + response.message());
                    isUnknownError.setValue(true);
                    isLoading.setValue(false);
                    showParentWeather.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<AllWeatherDetails> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                isError.setValue(true);
                isLoading.setValue(false);
                showSearchHint.setValue(false);
                isUnknownError.setValue(false);
                showParentWeather.setValue(false);
            }
        });
    }
}
