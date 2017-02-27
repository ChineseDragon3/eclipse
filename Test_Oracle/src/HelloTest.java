import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class HelloTest {
public static void main(String[] args) {
//	String cell_EJYM = "www.fdty.fudan.edu.cn";
//	String oralcle_EJYM ="www.cse.fudan.edu.cn";
//	String cellString = "www.cse.fudan.edu.cn";
//	if (cell_EJYM.equals(oralcle_EJYM)) {
//		System.out.println("cell_EJYM与oralcle_EJYM相同");
//	}
//	else {
//		System.out.println("cell_EJYM与oralcle_EJYM不同");
//	}
//	if (oralcle_EJYM.equals(cellString)) {
//		System.out.println("cell_EJYM与cellString相同");
//	}
	try {
		String searchsql = "SELECT nvl(DWMC, 33) from EJYMSQ where EJYM = 'dddd.fudan.edu.cn';";
		System.out.println(searchsql);
		Connection conn = Oracal_Conn.conn();
		// 创建数据库通道
		Statement stmt = conn.prepareStatement(searchsql);
		
		
        ResultSet resultset = stmt.executeQuery(searchsql);
        
        
        
        //System.out.println(resultset.getInt("DWMC"));
        
        if (resultset.next() == false) {
			System.out.println("结果集为空。。。");
		}
        while(resultset.next()) 
        {  
        	String returnString = resultset.getString(1);
            System.out.println("齐凤林获取resultset第一列返回值："+returnString);
        }  
	} catch (Exception e) {
		// TODO: handle exception
	}
}
}
