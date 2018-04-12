package com.yangxf.socket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.yangxf.vo.Message;

@ServerEndpoint("/chatSocket")
public class ChatSocket {

	private String username;
	private static List<Session> sessions = new ArrayList<Session>();
	private static List<String> names = new ArrayList<String>();
	
	@OnOpen
	public void open(Session session){
		// 这个session不是servlet session
		String queryString = session.getQueryString();
		System.out.println(queryString);
		username = queryString.split("=")[1];
		
		this.names.add(username);
		this.sessions.add(session);
		
		String msg = "欢迎" + this.username + "进入聊天室！";
		
		Message msg2 = new Message();
		msg2.setWelcome(msg);
		msg2.setUsernames(this.names);
		
		this.broadcast(this.sessions, msg2.toJson());
	}
	
	public void broadcast(List<Session> ss,String msg) {
		for(Iterator it=ss.iterator();it.hasNext();) {
			Session session = (Session) it.next();
			
			try {
				session.getBasicRemote().sendText(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@OnClose
	public void close(Session session) {
		this.sessions.remove(session);
		this.names.remove(this.username);
		
		String msg = "拜拜" + this.username + "再见！";
		Message msg2 = new Message();
		msg2.setWelcome(msg);
		msg2.setUsernames(this.names);
		
		broadcast(this.sessions, msg2.toJson());
	}
}
