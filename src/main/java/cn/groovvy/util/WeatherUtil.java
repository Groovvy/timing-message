package cn.groovvy.util;

import cn.groovvy.bean.Weather;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;

import static cn.hutool.json.JSONUtil.parseObj;

/**
 * @author wanghuaan
 * @date 2022/9/9
 */
public class WeatherUtil {

    private static final String WEATHER_API = "http://wthrcdn.etouch.cn/weather_mini?city=%s";

    public static Weather getNowWeatherInfo(String city) {
        if (city == null) {
            city = "江宁区";
        }
        HttpResponse response = HttpUtil.createGet(String.format(WEATHER_API, city))
                .setConnectionTimeout(3000)
                .setReadTimeout(4000)
                .execute();
        return parseObj(response.body()).getJSONObject("data").toBean(Weather.class);
    }
}
