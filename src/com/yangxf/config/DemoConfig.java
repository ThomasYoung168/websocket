package com.yangxf.config;

import java.util.Set;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;

public class DemoConfig implements ServerApplicationConfig {

	//注解方式启动
	@Override
	public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> arg0) { //服务器扫描注解到本参数
		// TODO Auto-generated method stub
		System.out.println("config.........." + arg0.size());
		return arg0;
	}

	//接口方式启动
	@Override
	public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
