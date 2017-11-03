package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class dwonloadAction
 */
@WebServlet("/dwonloadAction")
public class dwonloadAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		사용자가 요청한 파일이름으로 가져오기
		String fileName =request.getParameter("file");
//		서블릿컨텍스트 실제 물리 서버폴더 경로
		String directory = this.getServletContext().getRealPath("/upload/");
		File file = new File(directory +"/"+fileName);
		
//		mine타입 어떤 정보를 가져올지 알려주는것 파일 타입
		String mimeType =  getServletContext().getMimeType(file.toString());
		if ( mimeType == null) {
//			응답정보 파일 정보라는 것을 알려주는 것 이진데이터 octec-stream사용
			response.setContentType("application/octet-stream");
		}
		
		String downloadName = null;
//		브라우저에 따라서 전달 
		if( request.getHeader("user-agent").indexOf("MSIE")== -1) {
//			IE가 아니면 utf-8로 얻어서 깨지지 않도록  8859_1전달 
			downloadName = new String(fileName.getBytes("UTF-8"),"8859_1");
		}else {
			downloadName = new String(fileName.getBytes("EUC-KR"),"8859_1");
		}
//		해더 속성에 이러한  인코딩한 내용을 전달 
		response.setHeader("Content-Disposition", "attachment;filename=\""
				+ downloadName +"\";");
		 
//		파일 담아주기 
		FileInputStream  fileInpuStream = new FileInputStream(file);
		
		ServletOutputStream servletOutputStream = response.getOutputStream();
		
//		실제 전송시 쪼개서 전달 
		byte b[] = new byte[1024];
		int data = 0;
//		반복적으로 1024byte만큼 읽어서 계속 전달 
		while((data = (fileInpuStream.read(b,0,b.length))) != -1) {
			servletOutputStream.write(b,0,data);
		}
		
//		전부 전달 
		servletOutputStream.flush();
//		닫아주기
		servletOutputStream.close();
//		닫아주기 
		fileInpuStream.close();
		
		
	}


}
