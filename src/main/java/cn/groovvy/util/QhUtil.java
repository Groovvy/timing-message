package cn.groovvy.util;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;


/**
 * @author wanghuaan
 * @date 2022/8/25
 */
public class QhUtil {

    private static final String API = "https://api.lovelive.tools/api/SweetNothings";

    public static String getRandomQh() {
        HttpResponse response = HttpUtil.createGet(API)
                .setConnectionTimeout(3000)
                .setReadTimeout(4000)
                .execute();
        return response.body().replaceAll("<br>","\n");
    }
}
