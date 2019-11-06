package com.zcwsoft.common.newutils.listenter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.zcwsoft.business.enforce.common.dao.CommonDao;
import com.zcwsoft.common.newutils.redisutils.core.RedisCore;
import com.zcwsoft.common.newutils.redisutils.core.RedisEache;

public class ListenterCreateServletContext implements ServletContextListener{
	public CommonDao dao = null;
	
	@Override
	public void contextDestroyed(ServletContextEvent context) {
		
	}
	

	@SuppressWarnings("static-access")
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		RedisEache redisEache =RedisEache.getInstance();
		System.out.print("Redis_Eache_Delete Begin ********* ");
		if(RedisCore.isNull()){
    		System.out.println(" failed *********：java.net.ConnectException: Connection refused: connect\n");
    		return;
    		}
		int delN = redisEache.deleteAllEacheKey();
		System.out.println("success :num=【"+delN+"】");
	}

}
