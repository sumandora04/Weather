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

package com.notepoint.weather.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.notepoint.weather.R;
import com.notepoint.weather.data.List;
import com.notepoint.weather.utils.CommonUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import static com.notepoint.weather.data.List.DIFF_CALLBACK;

public class ForecastAdapter extends ListAdapter<List,ForecastAdapter.ForecastViewHolder> {
    public ForecastAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_cell_layout,parent,false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        List list = getItem(position);
        holder.bindData(list);
    }

    public class ForecastViewHolder extends RecyclerView.ViewHolder {
        private TextView dayText,forecastDetailText;
        private ImageView forecastImage;
        public ForecastViewHolder(@NonNull View itemView) {
            super(itemView);
            dayText = itemView.findViewById(R.id.day_date_forecast);
            forecastDetailText = itemView.findViewById(R.id.forecast_details);
            forecastImage = itemView.findViewById(R.id.weather_image);
        }


        void bindData(List list){
            try {
                Date date =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH).parse(list.getDtTxt());
                dayText.setText(CommonUtils.DAY_DATE.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            forecastDetailText.setText(list.getWeather().get(0).getMain());
            Glide.with(forecastImage)
                    .load("http://openweathermap.org/img/w/" + list.getWeather().get(0).getIcon() + ".png")
                    .into(forecastImage);
        }


    }
}
