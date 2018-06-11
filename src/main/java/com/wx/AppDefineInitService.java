package com.wx;

import com.wx.domain.Account;
import com.wx.process.WxMemoryCacheClient;
import com.wx.spring.SpringBeanDefineService;

/**
 * 系统启动时自动加载，把公众号信息加入到缓存中
 */
public class AppDefineInitService implements SpringBeanDefineService {
	
	public void initApplicationCacheData() {
		Account account = new Account();

		account.setAccount("gh_a64dee2c2d28"); 						// 微信号
		account.setAppid("wx05e3cd5c456d01b0");						// AppID
		account.setAppsecret("91d7e1c87847320ab41be7b885b5b199");	// secret
		account.setToken("WE_CHAT_ACCESS_TOKEN");					// 微信接入测试 token
		// TODO 各种 微信 配置
		WxMemoryCacheClient.addMpAccount(account);
	}
	
}
