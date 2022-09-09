package bean;

import java.util.List;

/**
 * @author wanghuaan
 * @date 2022/9/9
 */
public class Weather {

    private List<DayInfo> forecast;
    private String ganmao;

    public List<DayInfo> getForecast() {
        return forecast;
    }

    public void setForecast(List<DayInfo> forecast) {
        this.forecast = forecast;
    }

    public DayInfo getCurrentDayInfo(){
        return forecast.get(0);
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    static class DayInfo{
        private String date;
        private String high;
        private String low;
        private String type;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getHigh() {
            return high.split(" ")[1];
        }

        public void setHigh(String high) {
            this.high = high;
        }

        public String getLow() {
            return low.split(" ")[1];
        }

        public void setLow(String low) {
            this.low = low;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

}
