package cn.groovvy.bean;

/**
 * @author wanghuaan
 * @date 2022/8/25
 */
public class GaoDeWeather {
    private String province;
    private String city;
    private String weather;
    private String temperature;
    private String winddirection;
    private String windpower;
    private String humidity;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWinddirection() {
        return winddirection + "风";
    }

    public void setWinddirection(String winddirection) {
        this.winddirection = winddirection;
    }

    public String getWindpower() {
        return windpower + "级";
    }

    public void setWindpower(String windpower) {
        this.windpower = windpower;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}
