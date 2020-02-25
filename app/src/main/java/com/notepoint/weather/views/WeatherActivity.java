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

package com.notepoint.weather.views;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.notepoint.weather.R;
import com.notepoint.weather.data.AllWeatherDetails;
import com.notepoint.weather.data.Weather;
import com.notepoint.weather.databinding.ActivityMainBinding;
import com.notepoint.weather.utils.CommonUtils;
import com.notepoint.weather.viewModels.WeatherViewmodel;

public class WeatherActivity extends AppCompatActivity {
    private static final String TAG = "WeatherActivity";
    WeatherViewmodel weatherViewmodel;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        weatherViewmodel = ViewModelProviders.of(this).get(WeatherViewmodel.class);

        binding.forecastLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForecastBottomsheetDialog bottomSheet = new ForecastBottomsheetDialog();
                bottomSheet.show(getSupportFragmentManager(), "forecastBottomSheet");
            }
        });

        weatherViewmodel.getAllWeatherLiveData().observe(this, new Observer<AllWeatherDetails>() {
            @Override
            public void onChanged(AllWeatherDetails allWeatherDetails) {
                Log.d(TAG, "onChanged: "+allWeatherDetails);
                Weather currentWeatherDetail = allWeatherDetails.getWeather().get(0);
                binding.status.setText(currentWeatherDetail.getDescription());
                binding.address.setText(String.format("%s, %s", allWeatherDetails.getCityName(), allWeatherDetails.getSys().getCountry()));
                binding.updatedAt.setText(CommonUtils.epochToDateTimeConverter(allWeatherDetails.getDt(), CommonUtils.DATE_TIME));
                binding.temp.setText(String.format("%s°C", allWeatherDetails.getTemperatureDetails().getTemp()));
                binding.tempMin.setText(String.format("%s°C",allWeatherDetails.getTemperatureDetails().getTempMin()));
                binding.tempMax.setText(String.format("%s°C",allWeatherDetails.getTemperatureDetails().getTempMax()));
                binding.sunrise.setText(CommonUtils.epochToDateTimeConverter(allWeatherDetails.getSys().getSunrise(), CommonUtils.TIME));
                binding.sunset.setText(CommonUtils.epochToDateTimeConverter(allWeatherDetails.getSys().getSunset(), CommonUtils.TIME));
                binding.wind.setText(String.valueOf(allWeatherDetails.getWind().getSpeed()));
                binding.pressure.setText(String.valueOf(allWeatherDetails.getTemperatureDetails().getPressure()));
                binding.humidity.setText(String.valueOf(allWeatherDetails.getTemperatureDetails().getHumidity()));


                Glide.with(WeatherActivity.this)
                        .load("http://openweathermap.org/img/w/" + currentWeatherDetail.getIcon() + ".png")
                        .into(binding.weatherImage);
            }
        });


        weatherViewmodel.hasNetworkError().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean!=null){
                    if (aBoolean) {
                        binding.networkErrorLayout.setVisibility(View.VISIBLE);
                    }else {
                        binding.networkErrorLayout.setVisibility(View.GONE);
                    }
                }
            }
        });

        weatherViewmodel.isDataLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    binding.progressView.setVisibility(View.VISIBLE);
                    binding.weatherParentLayout.setVisibility(View.GONE);
                }else {
                    binding.progressView.setVisibility(View.GONE);
                    binding.weatherParentLayout.setVisibility(View.VISIBLE);
                }

            }
        });

        weatherViewmodel.shouldShowSearchHint().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
               if (aBoolean!=null){
                   if (aBoolean) {
                       binding.searchForCityHintText.setVisibility(View.VISIBLE);
                   }else {
                       binding.searchForCityHintText.setVisibility(View.GONE);
                   }
               }
            }
        });


        binding.tryAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CommonUtils.isNetworkAvailable(WeatherActivity.this)){
                    weatherViewmodel.isError.setValue(false);
                }else {
                    weatherViewmodel.isError.setValue(true);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem mSearch = menu.findItem(R.id.weather_search);
        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setQueryHint("Search");
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (CommonUtils.isNetworkAvailable(WeatherActivity.this)) {
                    weatherViewmodel.getCurrentWeatherData(query);
                }else {
                    weatherViewmodel.isError.setValue(true);
                }
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
               // adapter.getFilter().filter(newText);
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==R.id.weather_search){
            Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
