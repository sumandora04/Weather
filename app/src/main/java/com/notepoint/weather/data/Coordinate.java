
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

package com.notepoint.weather.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coordinate implements Parcelable
{

    @SerializedName("lon")
    @Expose
    private Double lon;
    @SerializedName("lat")
    @Expose
    private Double lat;
    public final static Creator<Coordinate> CREATOR = new Creator<Coordinate>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Coordinate createFromParcel(Parcel in) {
            return new Coordinate(in);
        }

        public Coordinate[] newArray(int size) {
            return (new Coordinate[size]);
        }

    }
    ;

    protected Coordinate(Parcel in) {
        this.lon = ((Double) in.readValue((Double.class.getClassLoader())));
        this.lat = ((Double) in.readValue((Double.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Coordinate() {
    }

    /**
     * 
     * @param lon
     * @param lat
     */
    public Coordinate(Double lon, Double lat) {
        super();
        this.lon = lon;
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(lon);
        dest.writeValue(lat);
    }

    public int describeContents() {
        return  0;
    }


    @Override
    public String toString() {
        return "Coordinate{" +
                "lon=" + lon +
                ", lat=" + lat +
                '}';
    }
}
