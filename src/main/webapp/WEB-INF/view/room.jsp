<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.dms.entity.chatLog"%>
<%@page import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대화방</title>
<script src="/webjars/sockjs-client/sockjs.min.js"></script>
<script>
var roomId = "${roomId}";
var my_id = "${my_id}";
var sock = new SockJS("/ws/multiRoom");

/*onopen 함수는 페이지가 로드되면 자동실행됨*/
sock.onopen = function () {
    sock.send( JSON.stringify({chatRoomId: roomId, type: "JOIN"}) );
}																			
/*onmessage 함수는 메시지가 오면 자동실행됨*/	
    sock.onmessage = function (e) {
    	var chatLog = document.getElementById("chatLog");     
        var content = JSON.parse(e.data);
        var message = content.message;
        var type = content.type;
        var chatId = content.chatId;
        var writer = content.writer;
        var size = content.size;
    	var data = new FormData();
    	data.append("roomId", roomId);
    	
    	if(type == "JOIN"){
    		var chat_ids = document.getElementsByClassName("ids");
    		
    		fetch('getWhoRead', {     
        		method: 'POST',
        	    headers: {},
        	    body: data       
        	}).then(res => res.json())
        	.then(whos => {
        	 	for(var i=0; i<chat_ids.length; i++){      
        	 		console.log("whos -> "+whos);
             		if(!(whos[i].whoRead).includes(my_id)){  
             			if(parseInt(chat_ids[i].innerHTML) == 0)
             				continue;
             			chat_ids[i].innerHTML = parseInt(chat_ids[i].innerHTML) - 1;
             			chat_ids[i].innerHTML = "";
                    	/*방에 접속해있는 모든 사람들(나 포함)에게 내가 이 채팅을 읽지 않았다는것을 보내준다. 그리고 if(type =="READ")에서 나를 제외한 사람들의 읽음 표시를 -1 한다  */
                    	sock.send(JSON.stringify({chatRoomId: roomId, type: "READ", chatId: chat_ids[i].id, writer: my_id}));              	
                    	decreaseUnread(chat_ids[i].id);                  
                    	leaveId(chat_ids[i].id);
                	}
             	}          		
        	})
        	
    	}
    	
        if(type == "SEND"){
        	if(size == 0)
    			chatLog.innerHTML = chatLog.innerHTML + "<p>" +writer +": " + message + " " + "<span class='ids' id='"+chatId+"' style='display: none'>"+size+"</span></p>";
    		else
    			chatLog.innerHTML = chatLog.innerHTML + "<p>" +writer +": " + message + " " + "<span class='ids' id='"+chatId+"' style='color: green'>"+size+"</span></p>";
        }
        
    	if(type == "READ"){
    		if(!(writer == my_id)){
         		var chat_element = document.getElementById(chatId);
           		chat_element.innerHTML = parseInt(chat_element.innerHTML) - 1;
                if(parseInt(chat_element.innerHTML) == 0)
                	chat_element.innerHTML = "";
            }
    	}	
    		
}

function seqCnctingSaveSend(){
	var size;
	var seq;
	var data = new FormData();
	data.append("roomId", roomId);
	
	fetch('getChatSeq', {     
		method: 'POST',
	    headers: {},  
	}).then(res => res.json())
	.then(res => {seq = res;})
	.then(res =>
		fetch('getCncting', {     
			method: 'POST',
			headers: {},  
			body: data
		}).then(res => res.json())
		  .then(res => {if(res == 2){size = 0}else(size = 1)} )
	)
	.then(res => saveMessage(size, seq))
	.then(res => sendMessage(size, seq));
		
}

function sendMessage(size, seq){
	var textarea = document.getElementById("textarea");
	var myMessage = textarea.value;
	sock.send( JSON.stringify({chatRoomId: roomId, type: "SEND", message: myMessage, chatId: seq, writer: my_id, size: size}) );
}

function saveMessage(size, seq){
	var data = new FormData();
	var textarea = document.getElementById("textarea");
	var myMessage = textarea.value;
	
	data.append("message", myMessage);
	data.append("roomId", roomId);
	data.append("chat_id", seq);
	data.append("size", size);

    fetch('saveMessage', {     
		method: 'POST',
        headers: {},
        body: data       
	});
}

function leaveId(chat_id){
	var data = new FormData();
	data.append("chat_id", chat_id);
	    	
	fetch('leaveId', {     
		method: 'POST',
		headers: {},
		body: data       
	});
	    		
}

function decreaseUnread(chat_id){
	var data = new FormData();
	data.append("chat_id", chat_id);

	fetch('decreaseUnread', {     
		method: 'POST',
		headers: {},
		body: data       
	}); 
	
}
</script>    
</head>
<body>
<h1>대화방</h1>
<h2>사용중인 id는 "${my_id}" 입니다.</h2>
<div id="chatLog">
<%
	List<chatLog> list = (List<chatLog>) request.getAttribute("list");
		for(chatLog l : list){
		pageContext.setAttribute("l", l);%>
		<p>
		${l.userId}: ${l.message}
		<c:if test="${l.unread != 0}"><span class="ids" id="${l.chatId}" style="color: green">${l.unread}</span></c:if>
		<c:if test="${l.unread == 0}"><span class="ids" id="${l.chatId}" style="display: none">${l.unread}</span></c:if>
		</p>	
<%} %>
	
</div>
<textarea id="textarea"></textarea>
<input type="button" value="전송" onclick="seqCnctingSaveSend()">
</body>
</html>