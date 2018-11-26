package main.java;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

public class WeatherForecaster {


    public static String forecast(City city) throws UnirestException, java.io.IOException{
        String main_city = city.getUrl();
        Coordinates coordinate = new Coordinates(main_city);

        HttpResponse<JsonNode> jsonResponse = Unirest.post("http://api.apixu.com/v1/current.json")
                .queryString("key", "2299d8c403fc4322b22102538181310")
                .queryString("q", coordinate.getLn() + "," + coordinate.getLt()).asJson();
        JSONObject json = new JSONObject(jsonResponse.getBody());
        JSONObject data = json.getJSONArray("array").getJSONObject(0).getJSONObject("current");
        return "\nTemperature: "+ data.getDouble("temp_c") +
                "\nFeels like: " + data.getDouble("feelslike_c") +
                "\nCloud: " + data.getDouble("cloud")+
                "\nWind degree: " + data.getDouble("wind_degree")+
                "\nWind direction: " + data.getString("wind_dir")+
                "\nWind kph: " + data.getDouble("wind_kph")+
                "\nWind mph: " + data.getDouble("wind_mph")+
                "\nPressure in: " + data.getDouble("pressure_in")+
                "\nPressure mb: " + data.getDouble("pressure_mb")+
                "\nDay/night: " + data.getDouble("is_day")+
                "\nPrecip_in: " + data.getDouble("precip_in")+
                "\nPrecip_mm: " + data.getDouble("precip_mm")+
                "\nVis. km: " + data.getDouble("vis_km")+
                "\nHimidity: " + data.getDouble("humidity");


    }

}
