package file;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class FileDAO {
	private Connection conn;
	//private final Logger logger = Logger.getLogger(FileDAO.class);	
	public FileDAO() {
		try {
			String dbURL="jdbc:mysql://localhost:3306/studydb";
			String dbID="study";
			String dbPassword="study";

			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(dbURL,dbID,dbPassword);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();	
		}

	}
	public int upload(String fileName, String fileRealName) {

		String SQL = "insert into file  values(?,?,0)	";
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

	public int hit(String fileRealName) {
		String SQL = "update file set downloadCount = downloadCount + 1 "
				+" where fileRealName=?	" ;
		try {
			PreparedStatement psmt = conn.prepareStatement(SQL);
			psmt.setString(1, fileRealName);
			return psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return -1;
	}

	public ArrayList<FileDTO> getList(){
		String SQL = "select * from file";
		ArrayList<FileDTO> list = new ArrayList<FileDTO>();
		try {
			PreparedStatement psmt = conn.prepareStatement(SQL);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				FileDTO file = new FileDTO(rs.getString(1),rs.getString(2),rs.getInt(3));
				list.add(file);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return list;
	}
}
