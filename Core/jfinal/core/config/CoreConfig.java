package jfinal.core.config;

import java.util.ArrayList;
import java.util.List;

import jfinal.core.model.CoreModel;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.plugin.tablebind.AutoTableBindPlugin;
import com.jfinal.ext.plugin.tablebind.SimpleNameStyles;
import com.jfinal.ext.route.AutoBindRoutes;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;

public class CoreConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		// TODO Auto-generated method stub
		loadPropertyFile("db_config.txt");
		me.setDevMode(true);  //设置开发模式
	}

	/**
	 * 配置转发路由
	 */
	@Override
	public void configRoute(Routes me) {
		// TODO Auto-generated method stub
		//me.add(new CoreRoutes());
		//增加自动注册插件
		me.add(new AutoBindRoutes());
	}

	//插件管理设置
	@Override
	public void configPlugin(Plugins me) {
		// TODO 设置插件
		
		//===============读取配置文件，增加连接池插件===========
		C3p0Plugin cp = new C3p0Plugin(getProperty("jdbcUrl"),getProperty("username"), getProperty("password"));
		me.add(cp);
		//================end==========================
		
		
		
		//==============增加Dao插件，用于访问数据库============
		//自动绑定model插件
		AutoTableBindPlugin autoTableBindPlugin = new AutoTableBindPlugin(cp, SimpleNameStyles.UP_UNDERLINE);
		
		//设置无需绑定的model
		List<Class<? extends Model>> listExcClasses = new ArrayList<Class<? extends Model>>();
		listExcClasses.add(CoreModel.class);
		autoTableBindPlugin.addExcludeClasses(listExcClasses);
		
		autoTableBindPlugin.setShowSql(true);   //设置显示sql
		me.add(autoTableBindPlugin);
		//=====================end======================= 
		
		
		//================增加ehcache缓存工具==========
		me.add(new EhCachePlugin()); 
		//===================end=========================
	}

	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub
		//配置全局拦截器
	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub
		
	}


}

