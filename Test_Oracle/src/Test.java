import java.sql.Connection;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;


public class Test {
	public static void main(String[] args) throws Exception {
		
		//读取文件名
		String filename = "/Users/huangyue/QFL/Eclipse/Test_Oracle/src/EJYM.xlsx";
		//创建连接
		
		Connection conn = Oracal_Conn.conn();
		// 创建数据库通道
		Statement stmt = conn.createStatement();
		int while_count = 0;
		
		//读取excel文件插入EJYM表
		ExeclOperate excelOperate = new ExeclOperate();
		List<String> list_excel = excelOperate.getSqlList(filename);
	    //方法excel
	    Iterator<String> it1 = list_excel.iterator();
	    while(it1.hasNext()){
	        String insertString = it1.next();
	        while_count++;
			System.out.println("这里执行  excel   插入-----"+while_count+"--------语句");		
	        System.out.println(insertString);
		    stmt.executeUpdate(insertString);	
	    }
	    
	    System.out.println("excel读取插入OK，以下是EJYMSQ表读取插入");
	   
	    //读取数据库中EJYMSQ表，插入表
	    OracleOperate oracleOperate = new OracleOperate();
	        
	    List<String> list_oracel = oracleOperate.getSqlList();
	    //System.out.println("获取list_oracleOperate..list_oracel.size = "+list_oracel.size());
	  //方法oralce
	    Iterator<String> it2 = list_oracel.iterator();
	    System.out.println(it2.hasNext());
	    while(it2.hasNext()){
	        String insertString = it2.next();
	        while_count++;
			System.out.println("这里执行  数据库  插入-----"+while_count+"--------语句");		
	        System.out.println(insertString);
		    stmt.executeUpdate(insertString);	
	    }
		System.out.println("Hello End!");
	}
}
