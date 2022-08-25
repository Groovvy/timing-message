import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;

import static cn.hutool.json.JSONUtil.parseObj;

/**
 * @author wanghuaan
 * @date 2022/8/25
 */
public class QhUtil {

    private static final String API = "https://api.1314.cool/words/api.php?return=json";

    public static String getRandomQh() {
        HttpResponse response = HttpUtil.createGet(API)
                .setConnectionTimeout(3000)
                .setReadTimeout(4000)
                .execute();
        return parseObj(response.body()).get("word").toString();
    }
}
