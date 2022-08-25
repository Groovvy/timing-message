import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghuaan
 * @date 2022/8/25
 */
public class App {

    private static final String appId = "wx10aea90e260e8ae3";

    private static final String appSecret = "db96c6ed25059b014931ab83dd7bffda";

    public static void main(String[] args) throws WxErrorException {
        WxMpDefaultConfigImpl storage = new WxMpDefaultConfigImpl();
        storage.setAppId(appId);
        storage.setSecret(appSecret);
        WxMpServiceImpl mpService = new WxMpServiceImpl();
        mpService.setWxMpConfigStorage(storage);
        List<WxMpTemplateData> data = new ArrayList<WxMpTemplateData>();
        data.add(new WxMpTemplateData("friendName", "groovvy"));
        data.add(new WxMpTemplateData("age", "11"));
        WxMpTemplateMessage message = WxMpTemplateMessage.builder()
                .toUser("o-O-Z6Pz1P9033TloUTSZzLqGy1U")
                .templateId("kJB3QnqiDGTCELJo1vJqvFOjmufviWzI3MHjjW1lWog").data(data).build();
        mpService.getTemplateMsgService().sendTemplateMsg(message);

    }
}
