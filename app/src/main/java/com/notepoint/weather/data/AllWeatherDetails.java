
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

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllWeatherDetails implements Parcelable
{

    @SerializedName("coord")
    @Expose
    private Coordinate coordinate;
    @SerializedName("weather")
    @Expose
    private List<Weather> weather = null;
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("main")
    @Expose
    private TemperatureDetails temperatureDetails;
    @SerializedName("visibility")
    @Expose
    private Integer visibility;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("sys")
    @Expose
    private Sys sys;
    @SerializedName("timezone")
    @Expose
    private Integer timezone;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String cityName;
    @SerializedName("cod")
    @Expose
    private Integer cod;

    @SerializedName("message")
    @Expose
    private Integer message;
    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("list")
    @Expose
    private java.util.List<com.notepoint.weather.data.List> list = null;
    @SerializedName("city")
    @Expose
    private City city;

    public final static Creator<AllWeatherDetails> CREATOR = new Creator<AllWeatherDetails>() {


        @SuppressWarnings({
            "unchecked"
        })
        public AllWeatherDetails createFromParcel(Parcel in) {
            return new AllWeatherDetails(in);
        }

        public AllWeatherDetails[] newArray(int size) {
            return (new AllWeatherDetails[size]);
        }

    }
    ;

    protected AllWeatherDetails(Parcel in) {
        this.coordinate = ((Coordinate) in.readValue((Coordinate.class.getClassLoader())));
        in.readList(this.weather, (Weather.class.getClassLoader()));
        this.base = ((String) in.readValue((String.class.getClassLoader())));
        this.temperatureDetails = ((TemperatureDetails) in.readValue((TemperatureDetails.class.getClassLoader())));
        this.visibility = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.wind = ((Wind) in.readValue((Wind.class.getClassLoader())));
        this.clouds = ((Clouds) in.readValue((Clouds.class.getClassLoader())));
        this.dt = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.sys = ((Sys) in.readValue((Sys.class.getClassLoader())));
        this.timezone = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.cityName = ((String) in.readValue((String.class.getClassLoader())));
        this.cod = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.cnt = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.list, (com.notepoint.weather.data.List.class.getClassLoader()));
        this.city = ((City) in.readValue((City.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public AllWeatherDetails() {
    }

    /**
     * 
     * @param visibility
     * @param timezone
     * @param temperatureDetails
     * @param clouds
     * @param sys
     * @param dt
     * @param coordinate
     * @param weather
     * @param cityName
     * @param cod
     * @param id
     * @param base
     * @param wind
     */
    public AllWeatherDetails(Coordinate coordinate, List<Weather> weather, String base, TemperatureDetails temperatureDetails, Integer visibility, Wind wind, Clouds clouds, Integer dt, Sys sys, Integer timezone, Integer id, String cityName, Integer cod) {
        super();
        this.coordinate = coordinate;
        this.weather = weather;
        this.base = base;
        this.temperatureDetails = temperatureDetails;
        this.visibility = visibility;
        this.wind = wind;
        this.clouds = clouds;
        this.dt = dt;
        this.sys = sys;
        this.timezone = timezone;
        this.id = id;
        this.cityName = cityName;
        this.cod = cod;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public TemperatureDetails getTemperatureDetails() {
        return temperatureDetails;
    }

    public void setTemperatureDetails(TemperatureDetails temperatureDetails) {
        this.temperatureDetails = temperatureDetails;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public Integer getMessage() {
        return message;
    }

    public void setMessage(Integer message) {
        this.message = message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public java.util.List<com.notepoint.weather.data.List> getList() {
        return list;
    }

    public void setList(java.util.List<com.notepoint.weather.data.List> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(coordinate);
        dest.writeList(weather);
        dest.writeValue(base);
        dest.writeValue(temperatureDetails);
        dest.writeValue(visibility);
        dest.writeValue(wind);
        dest.writeValue(clouds);
        dest.writeValue(dt);
        dest.writeValue(sys);
        dest.writeValue(timezone);
        dest.writeValue(id);
        dest.writeValue(cityName);
        dest.writeValue(cod);
        dest.writeValue(message);
        dest.writeValue(cnt);
        dest.writeList(list);
        dest.writeValue(city);
    }

    public int describeContents() {
        return  0;
    }

    @Override
    public String toString() {
        return "AllWeatherDetails{" +
                "coordinate=" + coordinate +
                ", weather=" + weather +
                ", base='" + base + '\'' +
                ", temperatureDetails=" + temperatureDetails +
                ", visibility=" + visibility +
                ", wind=" + wind +
                ", clouds=" + clouds +
                ", dt=" + dt +
                ", sys=" + sys +
                ", timezone=" + timezone +
                ", id=" + id +
                ", cityName='" + cityName + '\'' +
                ", cod=" + cod +
                ", message=" + message +
                ", cnt=" + cnt +
                ", list=" + list +
                ", city=" + city +
                '}';
    }
}
