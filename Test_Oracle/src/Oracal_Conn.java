import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Oracal_Conn {
	/**
	 * 用于连接oracle数据库的方法 只需修改中的参数getConnection("url","用户名","密码");
	 */
	public static Connection conn() {
		try {
			// 第一步：加载JDBC驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 第二步：创建数据库连接
			String url = "jdbc:oracle:thin:@10.4.0.16:1251/zhdb";// Server name的加载方式
			// String url = "jdbc:oracle:thin:@10.4.0.16:1251:zhdb";//SID的加载方式
			String usr = "USR_YBT_TEST";
			String pwd = "xx123!@#ABC";
			Connection con = DriverManager.getConnection(url, usr, pwd);
			return con;
		} catch (ClassNotFoundException cnf) {
			System.out.println("driver not find:" + cnf);
			return null;
		} catch (SQLException sqle) {
			System.out.println("can't connection db:" + sqle);
			return null;
		} catch (Exception e) {
			System.out.println("Failed to load JDBC/ODBC driver.");
			return null;
		}
	}

}
