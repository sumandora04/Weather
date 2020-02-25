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
    private MutableLiveData<String> place = new MutableLiveData<>();
    private MutableLiveData<String> date = new MutableLiveData<>();
    private MutableLiveData<String> country = new MutableLiveData<>();
    private MutableLiveData<Integer> sunrise = new MutableLiveData<>();
    private MutableLiveData<Integer> sunset = new MutableLiveData<>();
    private MutableLiveData<Double> temperature = new MutableLiveData<>();
    private MutableLiveData<Double> tempMin = new MutableLiveData<>();
    private MutableLiveData<Double> tempMax = new MutableLiveData<>();
    private MutableLiveData<Double> tempFeelsLike = new MutableLiveData<>();
    private MutableLiveData<Integer> pressure = new MutableLiveData<>();
    private MutableLiveData<Integer> humidity = new MutableLiveData<>();
    private MutableLiveData<Double> windSpeed = new MutableLiveData<>();
    private MutableLiveData<String> currentWeather = new MutableLiveData<>();
    private MutableLiveData<String> weatherDescription = new MutableLiveData<>();
    private MutableLiveData<String> weatherIcon = new MutableLiveData<>();

    public LiveData<AllWeatherDetails> getAllWeatherLiveData() {
        return _allWeatherDetails;
    }


    public WeatherViewmodel() {
        getCurrentWeatherData();

    }

    public LiveData<String> getPlace() {
        return place;
    }

    public LiveData<String> getDate() {
        return date;
    }

    public LiveData<String> getCountry() {
        return country;
    }

    public LiveData<Integer> getSunrise() {
        return sunrise;
    }

    public LiveData<Integer> getSunset() {
        return sunset;
    }

    public LiveData<Double> getTemperature() {
        return temperature;
    }

    public LiveData<Double> getTempMin() {
        return tempMin;
    }

    public LiveData<Double> getTempMax() {
        return tempMax;
    }

    public LiveData<Double> getTempFeelsLike() {
        return tempFeelsLike;
    }

    public LiveData<Integer> getPressure() {
        return pressure;
    }

    public LiveData<Integer> getHumidity() {
        return humidity;
    }

    public LiveData<Double> getWindSpeed() {
        return windSpeed;
    }

    public LiveData<String> getCurrentWeather() {
        return currentWeather;
    }

    public LiveData<String> getWeatherDescription() {
        return weatherDescription;
    }

    public LiveData<String> getWeatherIcon() {
        return weatherIcon;
    }

    private void getCurrentWeatherData() {
        ApiService apiService = NetworkService.create();

        Call<AllWeatherDetails> call = apiService.getCurrentWeather("Bangalore", NetworkService.TEMP_UNIT, NetworkService.APIKEY);
        call.enqueue(new Callback<AllWeatherDetails>() {
            @Override
            public void onResponse(Call<AllWeatherDetails> call, Response<AllWeatherDetails> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: " + response.body());
                        _allWeatherDetails.setValue(response.body());
                        AllWeatherDetails allWeatherDetails = response.body();

                        date.setValue(allWeatherDetails.getDt().toString());
                        country.setValue(allWeatherDetails.getSys().getCountry());
                        sunrise.setValue(allWeatherDetails.getSys().getSunrise());
                        sunset.setValue(allWeatherDetails.getSys().getSunset());
                        temperature.setValue(allWeatherDetails.getTemperatureDetails().getTemp());
                        tempMin.setValue(allWeatherDetails.getTemperatureDetails().getTempMin());
                        tempMax.setValue(allWeatherDetails.getTemperatureDetails().getTempMax());
                        tempFeelsLike.setValue(allWeatherDetails.getTemperatureDetails().getFeelsLike());
                        pressure.setValue(allWeatherDetails.getTemperatureDetails().getPressure());
                        humidity.setValue(allWeatherDetails.getTemperatureDetails().getHumidity());
                        windSpeed.setValue(allWeatherDetails.getWind().getSpeed());

                        List<Weather> currentWeatherDetail = allWeatherDetails.getWeather();
                        for (Weather weather : currentWeatherDetail) {
                            currentWeather.setValue(weather.getMain());
                            weatherDescription.setValue(weather.getDescription());
                            weatherIcon.setValue(weather.getIcon());
                        }


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
