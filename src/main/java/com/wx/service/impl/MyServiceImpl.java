package com.wx.service.impl;

import com.wx.vo.TemplateMessage;
import com.wx.process.WxApiClient;
import com.wx.process.WxMemoryCacheClient;
import com.wx.process.WxMessageBuilder;
import com.wx.process.MpAccount;
import com.wx.process.MsgXmlUtil;
import com.wx.service.MyService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 业务消息处理
 * 开发者根据自己的业务自行处理消息的接收与回复；
 */

@Service
public class MyServiceImpl implements MyService {

	public String processMsg(Map<String, String> requestMap){

		// 发送方帐号（open_id）
		String fromUserName = requestMap.get("FromUserName");
		// 公众帐号
		String toUserName = requestMap.get("ToUserName");
		// 消息类型
		String msgType = requestMap.get("MsgType");
		// 消息内容
		String content = requestMap.get("Content");

		String respXml = "";
		// 事件推送
		if (msgType.equals("event")) {
			// 自定义菜单点击事件
			String eventKey = requestMap.get("EventKey");// 事件KEY值，与创建自定义菜单时指定的KEY值对应

			String res = requestMap.get("ScanResult");// 扫描到 结果 如需 判断 开发者自行判断并回复消息
			if (null == res){
				MsgXmlUtil.textToXml(WxMessageBuilder.getMsgResponseTextContent(requestMap, "无效的条形码"));
			}
			// 根据 条形码的 ID 自行查询 返回模板消息
			if ("rselfmenu_0_0".equals(eventKey)){// 原料
//				图文消息的调用
//				List<MsgNews> msgNews = Lists.newArrayList();
//				MsgNews news = new MsgNews();
//				news.setPicpath("http://imgsrc.baidu.com/imgad/pic/item/f703738da9773912d40a27abf2198618377ae2c8.jpg");
//				news.setTitle("查看 1");
//				news.setUrl("http://houhousport.com/wx/user/scanGoods.html?goodsId=10020");
//				news.setDescription("描述 1 ");
//				msgNews.add(news);

				// 模板消息调用
				MpAccount mpAccount = WxMemoryCacheClient.getSingleMpAccount();//获取缓存中的唯一账号
				TemplateMessage tplMsg = new TemplateMessage();
				tplMsg.setOpenid(fromUserName);
				//微信公众号号的template id，开发者自行处理参数
				tplMsg.setTemplateId("o7iAhCMXjfVeoEf7oqKEbaixIuoZoi5FRcT2EzUb08U"); // 请开发者 自行增加模板消息ID
				Map<String, String> dataMap = new HashMap<String,String>();
				dataMap.put("place", "原料追溯");
				dataMap.put("msg", "原料追溯 消息");
				tplMsg.setDataMap(dataMap);

				WxApiClient.sendTemplateMessage(tplMsg, mpAccount);

				return "";
			} else if ("rselfmenu_0_1".equals(eventKey)){// 生长


			} else if ("rselfmenu_0_2".equals(eventKey)){// 物流


			} else {
				return "";// 回复空字符串 微信不做处理
			}

		}

		return respXml;
	}
	
}


