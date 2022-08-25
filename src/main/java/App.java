import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;

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
        Lover lover = new Lover("o-O-Z6Pz1P9033TloUTSZzLqGy1U", "江苏省", "南京市", "1996-12-05", "2015-10-19");
        WxMpTemplateMessage message = MessageFactory.resolveMessage(lover, "-khCNXbPS6Mf4dTLtESaVP-Dp6217bcdTLt22azh6R0");
        mpService.getTemplateMsgService().sendTemplateMsg(message);
        Lover lover2 = new Lover("o-O-Z6ChkLvecqydcb2UIFR-twnU", "江苏省", "南京市", "1996-12-05", "2015-10-19");
        WxMpTemplateMessage message2 = MessageFactory.resolveMessage(lover2, "-khCNXbPS6Mf4dTLtESaVP-Dp6217bcdTLt22azh6R0");
        mpService.getTemplateMsgService().sendTemplateMsg(message2);

    }
}
