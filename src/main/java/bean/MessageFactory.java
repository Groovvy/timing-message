package bean;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.joda.time.DateTime;
import org.joda.time.Days;
import util.GaodeWeatherUtil;
import util.QhUtil;
import util.WeatherUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghuaan
 * @date 2022/8/25
 */
public class MessageFactory {

    public static WxMpTemplateMessage resolveMessage(Lover lover, String templateId) {
        return WxMpTemplateMessage.builder()
                .toUser(lover.getUserId())
                .templateId(templateId)
                .data(buildData(lover))
                .build();
    }

    /**
     * {@code {{xxxx.DATA}}} xxxx就是一个变量名，消息中设置变量 然后传递时传递变量即可
     * <br/>
     * 色彩取值可以从这里挑选 https://arco.design/palette/list
     *
     * <p>
     * {{qh.DATA}}
     * 📅今天是{{now.DATA}}
     * 🌍现在在{{province.DATA}}{{city.DATA}}
     * 🌥今日天气{{weatherType.DATA}}
     * 🌡️当前气温{{temperature.DATA}}
     * ☀️最高温度{{high.DATA}}
     * 🧊最低温度{{low.DATA}}
     * 💨{{wind.DATA}}{{windLevel.DATA}}
     * 🗒{{remark.DATA}}
     * 💞我们已经恋爱{{total.DATA}}天啦
     * 🎂距离你下一次生日还有{{nextBirthday.DATA}}天
     * 💕具体我们的下一次纪念日还有{{nextMemorialDay.DATA}}天
     * </p>
     */
    private static List<WxMpTemplateData> buildData(Lover lover) {
        int days = Days.daysBetween(new DateTime(lover.getMemorialDay()), DateTime.now()).getDays();
        GaoDeWeather gaodeWeather = GaodeWeatherUtil.getNowWeatherInfo(GaodeWeatherUtil.getAdcCode("江苏", "南京"));
        Weather weather = WeatherUtil.getNowWeatherInfo("江宁区");
        ArrayList<WxMpTemplateData> wxMpTemplateData = Lists.newArrayList(
                TemplateDataBuilder.builder().name("qh").value(QhUtil.getRandomQh()).color("#D91AD9").build(),
                TemplateDataBuilder.builder().name("now").value(DateTime.now().toString("yyyy年MM月dd日")).build(),
                TemplateDataBuilder.builder().name("province").value(lover.getProvince()).build(),
                TemplateDataBuilder.builder().name("weatherType").value(weather.getCurrentDayInfo().getType()).build(),
                TemplateDataBuilder.builder().name("temperature").value(gaodeWeather.getTemperature()).color("#722ED1").build(),
                TemplateDataBuilder.builder().name("high").value(weather.getCurrentDayInfo().getHigh()).color("#F53F3F").build(),
                TemplateDataBuilder.builder().name("low").value(weather.getCurrentDayInfo().getLow()).color("#F5319D").build(),
                TemplateDataBuilder.builder().name("wind").value(gaodeWeather.getWinddirection()).build(),
                TemplateDataBuilder.builder().name("windLevel").value(gaodeWeather.getWindpower()).color("#3491FA").build(),
                TemplateDataBuilder.builder().name("remark").value(weather.getGanmao()).color("#3491FA").build(),
                TemplateDataBuilder.builder().name("total").value(String.valueOf(days)).color("#F53F3F").build(),
                TemplateDataBuilder.builder().name("nextBirthday").value(lover.getNextBirthdayDays()).color("#E865DF").build(),
                TemplateDataBuilder.builder().name("nextMemorialDay").value(lover.getNextMemorialDay()).color("#551DB0").build()
        );

        return wxMpTemplateData;
    }

    static class TemplateDataBuilder {
        private String name;
        private String value;
        private String color;

        public static TemplateDataBuilder builder() {
            return new TemplateDataBuilder();
        }

        public TemplateDataBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TemplateDataBuilder value(String value) {
            this.value = value;
            return this;
        }

        public TemplateDataBuilder color(String color) {
            this.color = color;
            return this;
        }

        public WxMpTemplateData build() {
            if (StrUtil.hasEmpty(name, value)) {
                throw new IllegalArgumentException("参数不正确");
            }
            WxMpTemplateData data = new WxMpTemplateData();
            data.setName(name);
            data.setValue(value);
            data.setColor(color);
            return data;
        }
    }
}
