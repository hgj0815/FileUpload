package file;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;




public class fileDAO {
	private Connection conn;
    private final Logger logger = Logger.getLogger(fileDAO.class);	
	public fileDAO() {
		try {
			String dbURL="jdbc:mysql://localhost:3306/studydb";
			String dbID="study";
			String dbPassword="study";
			
			Class.forName("com.mysql.Driver");

			conn = DriverManager.getConnection(dbURL,dbID,dbPassword);
		} catch (Exception e) {
			// TODO: handle exception
		   e.printStackTrace();	
		}
		
	}
   public int upload(String fileName, String fileRealName) {
	   
	   String SQL = "insert into file  valuse(?,?)	";
	   try {
		   PreparedStatement psmt = conn.prepareStatement(SQL);
		   psmt.setString(1, fileName);  
		   psmt.setString(2, fileRealName);  
		
		   return psmt.executeUpdate();
		   //  파일 저장을 위해서 해당 index.jsp저장 폴더로 이동하여 upload폴더 생성
		   //   C:XXXXX\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\File Upload
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	   
	   return -1;
   }
}
