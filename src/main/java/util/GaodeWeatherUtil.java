package util;

import bean.GaoDeWeather;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;

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
        HttpResponse response = HttpUtil.createGet(String.format(GEO_API, key, province, city))
                .setConnectionTimeout(3000)
                .setReadTimeout(4000)
                .execute();
        return parseObj(parseObj(response.body()).getJSONArray("geocodes").get(0)).getInt("adcode");
    }

    public static GaoDeWeather getNowWeatherInfo(Integer adcCode) {
        HttpResponse response = HttpUtil.createGet(String.format(WEATHER_API, key, adcCode, "base"))
                .setConnectionTimeout(3000)
                .setReadTimeout(4000)
                .execute();
        List<GaoDeWeather> lives =
                parseObj(response.body()).getJSONArray("lives").toList(GaoDeWeather.class);
        return lives.get(0);
    }
}
