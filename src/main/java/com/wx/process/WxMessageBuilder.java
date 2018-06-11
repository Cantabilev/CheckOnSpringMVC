package com.wx.process;

import com.wx.domain.MsgNews;
import com.wx.process.MsgType;
import com.wx.vo.Article;
import com.wx.vo.MsgResponseNews;
import com.wx.vo.MsgResponseText;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 消息builder工具类
 */
public class WxMessageBuilder {
	
	//客服文本消息
	public static String prepareCustomText(String openid,String content){
		JSONObject jsObj = new JSONObject();
		jsObj.put("touser", openid);
		jsObj.put("msgtype", MsgType.Text.name());
		JSONObject textObj = new JSONObject();
		textObj.put("content", content);
		jsObj.put("text", textObj);
		return jsObj.toString();
	}

	public static MsgResponseText getMsgResponseTextContent(Map<String, String> msgRequest, String content){
		if(StringUtils.isNotBlank(content)){
			MsgResponseText reponseText = new MsgResponseText();
			reponseText.setToUserName(msgRequest.get("FromUserName"));
			reponseText.setFromUserName(msgRequest.get("ToUserName"));
			reponseText.setMsgType(MsgType.Text.toString());
			reponseText.setCreateTime(new Date().getTime());
			reponseText.setContent(content);
			return reponseText;
		}else{
			return null;
		}
	}

	//获取 MsgResponseNews 对象
	public static MsgResponseNews getMsgResponseNews(Map<String, String> msgRequest, List<MsgNews> msgNews){
		if(msgNews != null && msgNews.size() > 0){
			MsgResponseNews responseNews = new MsgResponseNews();
			responseNews.setToUserName(msgRequest.get("FromUserName"));
			responseNews.setFromUserName(msgRequest.get("ToUserName"));
			responseNews.setMsgType(MsgType.News.toString());
			responseNews.setCreateTime(new Date().getTime());
			responseNews.setArticleCount(msgNews.size());
			List<Article> articles = new ArrayList<Article>(msgNews.size());
			for(MsgNews n : msgNews){
				Article a = new Article();
				a.setTitle(n.getTitle());
				a.setPicUrl(n.getPicpath());
				if(StringUtils.isEmpty(n.getFromurl())){
					a.setUrl(n.getUrl());
				}else{
					a.setUrl(n.getFromurl());
				}
				a.setDescription(n.getBrief());
				articles.add(a);
			}
			responseNews.setArticles(articles);
			return responseNews;
		}else{
			return null;
		}
	}

}
