package com.dms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dms.entity.chatLog;
import com.dms.entity.whoRead;
import com.dms.service.userDao;


public class service {

	userDao dao = new userDao();
	Connection conn;
	
	public void saveMessage(String userid, String message, String roomId, int chatId, int size) {
		String sql = "insert into chatLog(user_id, message, room_id, unread, who_read, chat_id, regdate) values(?, ?, ?, ?, ?, ?, sysdate)";
		
		try {
			conn = dao.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, userid);
			st.setString(2, message);
			st.setString(3, roomId);
			st.setInt(4, size);
			st.setString(5, userid);
			st.setInt(6, chatId);
			st.executeUpdate();
		
			st.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<chatLog> getChatlog(String roomId){
		List<chatLog> list = new ArrayList<>();

		String sql = "select * from chatlog where room_id = ? order by regdate";
	
	try {
		conn = dao.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);		
		st.setString(1, roomId);
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			String userId = rs.getString("user_id");
			String message = rs.getString("message");
			int unread = rs.getInt("unread");
			int chatId = rs.getInt("chat_id");
			chatLog entity = new chatLog(userId, message, unread, chatId);
			list.add(entity);
		}
		
		rs.close();
		st.close();
		conn.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return list;
} 
	
	public List<whoRead> getWhoRead(String roomId){
		List<whoRead> list = new ArrayList<>();

		String sql = "select who_read from chatlog where room_id = ? order by regdate";
	
	try {
		conn = dao.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);		
		st.setString(1, roomId);
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			String who_read = rs.getString("who_read");
			whoRead entity = new whoRead(who_read);
			list.add(entity);
		}
		
		rs.close();
		st.close();
		conn.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return list;
} 
	
	public void decreaseUnread(int chat_id) {
		String sql = "update chatlog set unread = unread - 1 where chat_id = ?";
	
			try {
				conn = dao.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, chat_id);
				st.executeUpdate();
				st.close();
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
	
	public void leaveId(int chat_id,String user_id) {
		String sql = "update chatlog set who_read = concat(who_read,concat(' ',?)) where chat_id = ?";
		int result = 0;
				
		try {
			conn = dao.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, user_id);
			st.setInt(2, chat_id);
			st.executeUpdate();
			st.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	public int getChatSeq() {
		int	chatSeq = 0;

		String sql = "select chat_id.nextval chatSeq from dual";
						
			try {
				conn = dao.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet rs = st.executeQuery();
				rs.next();
				chatSeq = rs.getInt("chatSeq");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			return chatSeq;
	}
	
}
