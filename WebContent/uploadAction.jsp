<%@ page import="file.FileDAO"%>
<%@ page import="java.io.File"%>
<%@ page import="java.util.Enumeration"%>
<%@ page import=" com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@ page import=" com.oreilly.servlet.MultipartRequest"%>
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
		//application 내장객체는 전체 프로젝트에 대한 자원을 관리하는 객체
		//서버의 실제 프로젝트 경로에서 자원을 찾을 때 가장 많이 사용됨

		//저장소 업로드 폴더 찾기
		//String directory =application.getRealPath("/upload/");
		//시큐어코딩으로 root폴더가 아닌 다른 폴더에 지정 저장 
		String directory = "C:/jsp/upload/";

		//max 100mb제한주기
		int maxSize = 1024 * 1024 * 100;
		String encoding = "UTF-8";

		MultipartRequest multipartRequest = new MultipartRequest(request, directory, maxSize, encoding,
				new DefaultFileRenamePolicy());

		Enumeration fileNames = multipartRequest.getFileNames();
		while (fileNames.hasMoreElements()) {
			String parameter = (String) fileNames.nextElement();
			String fileName = multipartRequest.getOriginalFileName(parameter);
			String fileRealName = multipartRequest.getFilesystemName(parameter);

			//파일첨부가 없는 경우
			if(fileName == null) continue;
			//선택된 확장자 파일민 업로드  
			if (!fileName.endsWith(".doc") && !fileName.endsWith(".hwp") && !fileName.endsWith(".pdf")
					&& !fileName.endsWith(".xls")) {
				File file = new File(directory, fileRealName);
				file.delete();
				out.write("업로드를 할 수 없는 확장자입니다.");
			} else {
				new FileDAO().upload(fileName, fileRealName);
				out.write("파일명: " + fileName + "<br>");
				out.write("실제 파일명: " + fileRealName + "<br>");
			}
		}

		/* 	    
		       new FileDAO().upload(fileName, fileRealName);
			     out.write("파일명: "+fileName+"<br>");
			     out.write("실제 파일명: "+fileRealName+"<br>"); */
	%>
</body>
</html>