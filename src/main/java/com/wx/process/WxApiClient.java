package com.wx.process;

import com.wx.vo.TemplateMessage;
import net.sf.json.JSONObject;

/**
 * 微信 客户端，统一处理微信相关接口
 */

public class WxApiClient {
	
	//获取accessToken
	public static String getAccessToken(MpAccount mpAccount){
		//获取唯一的accessToken，如果是多账号，请自行处理
		AccessToken token = WxMemoryCacheClient.getSingleAccessToken();
		if(token != null && !token.isExpires()){//不为空，并且没有过期
			return token.getAccessToken();
		}else{
			token = WxApi.getAccessToken(mpAccount.getAppid(),mpAccount.getAppsecret());
			if(token != null){
				if(token.getErrcode() != null){//获取失败
					System.out.println("## getAccessToken Error = " + token.getErrmsg());
				}else{
					WxMemoryCacheClient.addAccessToken(mpAccount.getAccount(), token);
					return token.getAccessToken();
				}
			}
			return null;
		}
	}

	/**
	 * 发送模板消息
	 * @param tplMsg
	 * @param mpAccount
	 * @return
	 */
	public static JSONObject sendTemplateMessage(TemplateMessage tplMsg, MpAccount mpAccount){
		if(tplMsg != null){
			String accessToken = getAccessToken(mpAccount);
			JSONObject jsonObject = WxApi.httpsRequest(WxApi.getSendTemplateMessageUrl(accessToken), "POST", tplMsg.toString());
			System.out.println("模板消息发送的返回：" + jsonObject);
			return jsonObject;
		}
		return null;
	}
}



