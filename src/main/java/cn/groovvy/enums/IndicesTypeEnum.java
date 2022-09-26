package cn.groovvy.enums;

/**
 * @author wanghuaan
 * @date 2022/9/26
 */
public enum IndicesTypeEnum {

    SPORT("1"),
    WASH_CAR("2"),
    DRESSING("3"),
    SICK("9"),
    MAKE_UP("13"),
    SPF("16");

    private final String value;

    IndicesTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
