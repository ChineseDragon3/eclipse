import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CSVOperate {
	/**
	 * 用于连接oracle数据库的方法 只需修改中的参数getConnection("url","用户名","密码");
	 */
	public Connection conn() {
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

	/**
	 * 将读取的一行CSV文件按照逗号分割改为sql串
	 * 
	 * @param readString
	 * @return sql插入语句
	 */
	public String getInsertSql(String readLine) {

		String[] cells = readLine.split(",");
		System.out.println(cells.length);

		String cell_ID = "1";// 申请人工号

		String cell_SQR_XM = cells[1];// 申请人姓名

		String cell_SQR_SJ = cells[2];// 申请人手机

		String cell_SQR_EMAIL = cells[3];// 申请人Email

		String cell_DWMC = cells[4];// 单位名称

		String cell_DWFZR = cells[5];// 单位负责人

		String cell_BMLB = cells[6];// 部门类别

		String cell_BMLB_QT = cells[7];// 其他部门类别说明

		String cell_XZFZR_XM = cells[8];// 行政负责人姓名

		String cell_XZFZR_DH = cells[9];// 行政负责人电话

		String cell_XZFZR_SJ = cells[10];// 行政负责人手机

		String cell_XZFZR_EMAIL = cells[11];//行政负责人Email

		String cell_XTGLY_XM = cells[12];// 系统管理员姓名

		String cell_XTGLY_DH = cells[13];// 系统管理员电话

		String cell_XTGLY_SJ = cells[14];// 系统管理员手机

		String cell_XTGLY_EMAIL = cells[15];// 系统管理员Email

		String cell_EJYM = cells[16];// 二级域名

		String cell_IP = cells[17];// 主机IP

		String cell_isOut = cells[18];// 是否需要校外访问

		String cell_FWZL = cells[19];// 服务种类

		String cell_FWZL_QT = cells[20];// 其他服务种类
		
		String cell_ZYNR = "";// 主要内容
		String cell_GXSH = "";// 最后更新时间
		if (cells.length > 21) {
			cell_ZYNR = cells[21];// 主要内容
			cell_GXSH = cells[22];// 最后更新时间
		}
		String cell_NULL = "";

		// 拼装插入数据库的sql

		String insertSql = "insert into EJYMSQ32 values('" + cell_ID + "','"
				+ cell_BMLB + "','" + cell_BMLB_QT + "','" + cell_DWFZR + "','"
				+ cell_DWMC + "','" + cell_EJYM + "','" + cell_FWZL + "','"
				+ cell_FWZL_QT + "','" + cell_IP + "','" + cell_NULL + "','"
				+ cell_XTGLY_DH + "','" + cell_XTGLY_EMAIL + "','"
				+ cell_XTGLY_SJ + "','" + cell_SQR_XM + "','" + cell_XZFZR_DH
				+ "','" + cell_XZFZR_EMAIL + "','" + cell_XZFZR_SJ + "','"
				+ cell_XZFZR_XM + "','" + cell_ZYNR + "','" + cell_NULL + "','"
				+ cell_NULL + "','" + cell_NULL + "','" + cell_NULL + "','"
				+ cell_NULL + "','" + cell_NULL + "','" + cell_NULL + "','"
				+ cell_NULL + "','" + cell_NULL + "','" + cell_NULL + "','"
				+ cell_NULL + "','" + cell_NULL + "','" + cell_NULL + "','"
				+ cell_NULL + "','" + cell_NULL + "','" + cell_NULL + "')";

		System.out.println("SQL:" + insertSql);

		return insertSql;

	}
	/**
	 * 判断二级域名是否已在数据库中存在
	 * return 0 or 1
	 */
	public int isExistsEJYM(String readLine,Connection conn) {
		int isExist = 0;
		String[] cells = readLine.split(",");
		//System.out.println(cells.length);
		String cell_EJYM = cells[16];// 二级域名
		System.out.println("CSV文件获取的二级域名："+cell_EJYM);
		try {
			String search_EJYM_String = "select * from EJYMSQ32";
			
			// 创建数据库通道
			Statement stmt = conn.createStatement();
			
            ResultSet result = stmt.executeQuery(search_EJYM_String);  
            while(result.next())  
            {  
            	String oralcle_EJYM = result.getString("EJYM");
                if (cell_EJYM.equals(oralcle_EJYM)) {
                	System.out.println("=========================================在数据库中能查询到重复的二级域名："+oralcle_EJYM+"====================================================");  
					isExist = 1;
					break;
				}
            }  
		} catch (Exception e) {
			// TODO: handle exception
		}
		return isExist;//否则改为0，就是在数据库没找到相同的EJYM
	}
	/**
	 * 插入数据 只需插入sql即可 插入sql的样例:insert into t_department values('D004','金融部');
	 * 
	 * @param insert
	 *           插入语句
	 * @return
	 * @throws SQLException
	 */
	public int insert(String insert) throws SQLException {
		Connection conn = this.conn();
		int re = 0;
		try {
			conn.setAutoCommit(false);// 事物开始
			Statement sm = conn.createStatement();
			re = sm.executeUpdate(insert);
			if (re < 0) { // 插入失败
				conn.rollback(); // 回滚
				sm.close();
				conn.close();
				return re;
			}
			conn.commit(); // 插入正常
			sm.close();
			conn.close();
			return re;
		} catch (Exception e) {
			e.printStackTrace();
		}
		conn.close();
		return 0;
	}

}
