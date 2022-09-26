package cn.groovvy.bean;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @author wanghuaan
 * @date 2022/8/25
 */
public class Lover {
    private String userId;
    private String province;
    private String city;
    private String birthday;
    private String memorialDay;

    public Lover() {
    }

    public Lover(String userId, String province, String city, String birthday, String memorialDay) {
        this.userId = userId;
        this.province = province;
        this.city = city;
        this.birthday = birthday;
        this.memorialDay = memorialDay;
    }

    public String getNextBirthdayDays() {
        return getNextDay(DateUtil.parse(birthday));
    }

    public String getNextMemorialDay() {
        return getNextDay(DateUtil.parse(memorialDay));
    }

    public static String getNextDay(DateTime dateTime) {
        dateTime = DateUtil.beginOfDay(dateTime);
        DateTime now = DateUtil.beginOfDay(new Date());
        dateTime.offset(DateField.YEAR, now.year() - dateTime.year());
        if (now.isAfter(dateTime)) {
            return String.valueOf(dateTime.offset(DateField.YEAR, 1).between(now, DateUnit.DAY));
        }
        return String.valueOf(dateTime.between(now, DateUnit.DAY));
    }

    public String getUserId() {
        return userId;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getMemorialDay() {
        return memorialDay;
    }
}
