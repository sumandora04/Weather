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

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.notepoint.weather.adapter.ForecastAdapter;
import com.notepoint.weather.R;
import com.notepoint.weather.data.List;
import com.notepoint.weather.databinding.ForecastBottomSheetBinding;
import com.notepoint.weather.viewModels.ForecastViewModel;

public class ForecastBottomsheetDialog extends BottomSheetDialogFragment {

    private static final String TAG = "ForecastBottomsheetDial";

    private ForecastBottomSheetBinding binding;
    private ForecastViewModel forecastViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ForecastBottomSheetBinding.inflate(getLayoutInflater());
        forecastViewModel = ViewModelProviders.of(this).get(ForecastViewModel.class);
        binding.setViewModel(forecastViewModel);
        binding.setLifecycleOwner(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        binding.forecastRecycler.setLayoutManager(layoutManager);

        ForecastAdapter adapter = new ForecastAdapter();
        binding.forecastRecycler.setAdapter(adapter);


        forecastViewModel.getForecastLiveData().observe(getViewLifecycleOwner(), new Observer<java.util.List<List>>() {
            @Override
            public void onChanged(java.util.List<List> lists) {
                Log.d(TAG, "onChanged: "+lists);
                adapter.submitList(lists);
            }
        });


        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
}
