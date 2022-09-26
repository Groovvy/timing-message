package cn.groovvy.util;

import cn.groovvy.bean.GaoDeWeather;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.squareup.okhttp.*;

import java.io.IOException;
import java.util.List;

import static cn.hutool.json.JSONUtil.parseObj;

/**
 * @author wanghuaan
 * @date 2022/8/25
 */
public class GaodeWeatherUtil {

    private static final String key = "5f69460257bc787951c5da939769fb21";

    private static final String GEO_API = "https://restapi.amap.com/v3/geocode/geo?key=%s&address=%s&city=%s";
    private static final String WEATHER_API = "https://restapi.amap.com/v3/weather/weatherInfo?key=%s&city=%d&extensions=%s";

    public static Integer getAdcCode(String province, String city) {
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request httpRequest = new Request.Builder()
                .url(String.format(GEO_API, key, province, city))
                .get()
                .build();
        Response response = null;
        try {
            Call call = okHttpClient.newCall(httpRequest);
            response = call.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parseObj(parseObj(extractBody(response)).getJSONArray("geocodes").get(0)).getInt("adcode");
    }

    public static GaoDeWeather getNowWeatherInfo(Integer adcCode) {
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request httpRequest = new Request.Builder()
                .url(String.format(WEATHER_API, key, adcCode, "base"))
                .get()
                .build();
        Response response = null;
        try {
            Call call = okHttpClient.newCall(httpRequest);
            response = call.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<GaoDeWeather> lives =
                parseObj(extractBody(response)).getJSONArray("lives").toList(GaoDeWeather.class);
        return lives.get(0);
    }

    public static String extractBody(Response response) {
        String body = null;
        ResponseBody responseBody = response.body();
        try {
            if (responseBody != null) {
                body = responseBody.string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body;
    }
}
