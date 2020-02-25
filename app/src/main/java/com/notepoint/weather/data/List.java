
package com.notepoint.weather.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List implements Parcelable
{

    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("temperatureDetails")
    @Expose
    private TemperatureDetails temperatureDetails;
    @SerializedName("weather")
    @Expose
    private java.util.List<Weather> weather = null;
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("sys")
    @Expose
    private Sys sys;
    @SerializedName("dt_txt")
    @Expose
    private String dtTxt;
    public final static Creator<List> CREATOR = new Creator<List>() {


        @SuppressWarnings({
            "unchecked"
        })
        public List createFromParcel(Parcel in) {
            return new List(in);
        }

        public List[] newArray(int size) {
            return (new List[size]);
        }

    }
    ;

    protected List(Parcel in) {
        this.dt = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.temperatureDetails = ((TemperatureDetails) in.readValue((TemperatureDetails.class.getClassLoader())));
        in.readList(this.weather, (com.notepoint.weather.data.Weather.class.getClassLoader()));
        this.clouds = ((Clouds) in.readValue((Clouds.class.getClassLoader())));
        this.wind = ((Wind) in.readValue((Wind.class.getClassLoader())));
        this.sys = ((Sys) in.readValue((Sys.class.getClassLoader())));
        this.dtTxt = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public List() {
    }

    /**
     * 
     * @param dt
     * @param dtTxt
     * @param weather
     * @param temperatureDetails
     * @param clouds
     * @param sys
     * @param wind
     */
    public List(Integer dt, TemperatureDetails temperatureDetails, java.util.List<Weather> weather, Clouds clouds, Wind wind, Sys sys, String dtTxt) {
        super();
        this.dt = dt;
        this.temperatureDetails = temperatureDetails;
        this.weather = weather;
        this.clouds = clouds;
        this.wind = wind;
        this.sys = sys;
        this.dtTxt = dtTxt;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public TemperatureDetails getTemperatureDetails() {
        return temperatureDetails;
    }

    public void setTemperatureDetails(TemperatureDetails temperatureDetails) {
        this.temperatureDetails = temperatureDetails;
    }

    public java.util.List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(java.util.List<Weather> weather) {
        this.weather = weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(dt);
        dest.writeValue(temperatureDetails);
        dest.writeList(weather);
        dest.writeValue(clouds);
        dest.writeValue(wind);
        dest.writeValue(sys);
        dest.writeValue(dtTxt);
    }

    public int describeContents() {
        return  0;
    }


    @Override
    public String toString() {
        return "List{" +
                "dt=" + dt +
                ", temperatureDetails=" + temperatureDetails +
                ", weather=" + weather +
                ", clouds=" + clouds +
                ", wind=" + wind +
                ", sys=" + sys +
                ", dtTxt='" + dtTxt + '\'' +
                '}';
    }
}
