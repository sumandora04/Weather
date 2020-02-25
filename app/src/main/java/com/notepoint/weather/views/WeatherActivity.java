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

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.notepoint.weather.R;
import com.notepoint.weather.data.AllWeatherDetails;
import com.notepoint.weather.databinding.ActivityMainBinding;
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

//        weatherViewmodel.getPlace().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//                binding.address.setText(s);
//            }
//        });
//
//        weatherViewmodel.getAllWeatherLiveData().observe(this, new Observer<AllWeatherDetails>() {
//            @Override
//            public void onChanged(AllWeatherDetails allWeatherDetails) {
//                Log.d(TAG, "onChanged: "+allWeatherDetails);
//                binding.address.setText(allWeatherDetails.getCityName());
//            }
//        });
    }
}
