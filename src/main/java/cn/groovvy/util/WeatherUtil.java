package cn.groovvy.util;

import cn.groovvy.bean.Indices;
import cn.groovvy.bean.Weather;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static cn.hutool.json.JSONUtil.parseObj;

/**
 * @author wanghuaan
 * @date 2022/9/9
 */
public class WeatherUtil {

    private static final String WEATHER_API = "https://devapi.qweather.com/v7/weather/3d?key=2b98d397eaae4d7caf53395efb224529&location=%s";
    private static final String LOCATION_API = "https://geoapi.qweather.com/v2/city/lookup?key=2b98d397eaae4d7caf53395efb224529&location=%s";
    private static final String INDICES_API = "https://devapi.qweather.com/v7/indices/1d?type=0&key=2b98d397eaae4d7caf53395efb224529&location=%s";

    public static String getLocationId(String city){
        HttpResponse response = HttpUtil.createGet(String.format(LOCATION_API, city))
                .setConnectionTimeout(3000)
                .setReadTimeout(4000)
                .execute();
        return parseObj(parseObj(response.body()).getJSONArray("location").get(0)).getStr("id");
    }

    public static Weather getNowWeatherInfo(String city) {
        if (city == null) {
            city = "江宁区";
        }
        String locationId = getLocationId(city);
        HttpResponse response = HttpUtil.createGet(String.format(WEATHER_API, locationId))
                .setConnectionTimeout(3000)
                .setReadTimeout(4000)
                .execute();
        return parseObj(parseObj(response.body()).getJSONArray("daily").get(0)).toBean(Weather.class);
    }

    public static Map<String,Indices> getIndices(String city) {
        if (city == null) {
            city = "江宁区";
        }
        String locationId = getLocationId(city);
        HttpResponse response = HttpUtil.createGet(String.format(INDICES_API, locationId))
                .setConnectionTimeout(3000)
                .setReadTimeout(4000)
                .execute();
        List<Indices> daily = parseObj(response.body()).getJSONArray("daily").toList(Indices.class);
        return daily.stream().collect(Collectors.toMap(Indices::getType, Function.identity()));
    }

    public static void main(String[] args) {
        Map map = getIndices(null);
        System.out.println();
    }
}
