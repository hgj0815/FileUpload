<%@page import="java.io.File"%>
<%@page import="file.FileDAO"%>
<%@page import="file.FileDTO"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP파일 업로드</title>
</head>
<body>
	<%
   /* 
        //root 안에  해당 폴더를 사용 할때
   		//String directory = application.getRealPath("/upload/");
   		//secure coding 으로 root이외 장소에 저장하는 방법
        String directory = "C:/jsp/upload/";
   		String files[] = new File(directory).list();
   		
   		for(String file: files){
   			out.write("<a href =\"" + request.getContextPath()+"/dwonloadAction?file="+
   					java.net.URLEncoder.encode(file,"UTF-8")+"\">"+file+"</a><br>"	);
   		}
    */
    ArrayList<FileDTO> fileList= new FileDAO().getList();
    for(FileDTO file : fileList){
    	out.write("<a href =\""+request.getContextPath()+"/dwonloadAction?file="+
    			java.net.URLEncoder.encode(file.getFileRealName(),"UTF-8")+"\">"+
    			file.getfileName()	+"(다운로드 횟수:  "+file.getDownloadCount()+")</a><br>"	
    		
    			);
    }
    
    
    
   %>
</body>
</html>