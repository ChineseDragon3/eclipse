import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class OracleOperate {
	int duplicateCount = 0;
    int newcount = 0;
     int stateCount = 0;
	public List<String> getSqlList() {
		// TODO Auto-generated method stub
		
		List<String> list = new ArrayList<String>();
		try {
			String search_EJYMSQ = "select * from EJYMSQ";
			Connection conn = Oracal_Conn.conn();
			// 创建数据库通道
			Statement stmt = conn.createStatement();
			
            ResultSet resultset = stmt.executeQuery(search_EJYMSQ); 
            
            ExeclOperate excelOperate = new ExeclOperate();
            Ejym e = new Ejym();
            String DWMC_value;
            String DWFZR_value;
            String SJYM_value;
            String EJYM_value;
            String IP_value;
            String XTGLY_XM_value;
            String XTGLY_DH_value;
            String XTGLY_SJ_value;
            String XTGLY_EMAIL_value;
            String XZFZR_XM_value;
            String XZFZR_DH_value;
            String XZFZR_SJ_value;
            String XZFZR_EMAIL_value;
            String ZYNR_value;
            String BMLB_value;
            String BMLB_QT_value;
            String FWZL_value;
            String FWZL_QT_value;
            String OWNER_value;
            String CONTACT_PHONE_value;
            String CONTACT_EMAIL_value;
            String UPDATE_AT_value;
            String STATE_value;
            boolean existsEjym;
            boolean isSuccess;
            while(resultset.next())  
            {  
            	//System.out.println("resultSet........");
            	//设置之前先判断oracle数据库中的null处理
            	DWMC_value = resultset.getString("DWMC");
            	//System.out.println(DWMC_value);
            	//System.out.println("******1");
            	if (resultset.wasNull()) {
					e.setEjym_DWMC("");
				}
            	else {
            		e.setEjym_DWMC(DWMC_value);
				}
                DWFZR_value = resultset.getString("DWFZR");
                //System.out.println(DWFZR_value);
            	if (resultset.wasNull()) {
					e.setEjym_DWFZR("");
				}
            	else {
					e.setEjym_DWFZR(DWFZR_value);
				}
            	SJYM_value = resultset.getString("SJYM");
            	if (resultset.wasNull()) {
					e.setEjym_SJYM("");
				}
            	else {
					e.setEjym_SJYM(SJYM_value);
				}
            	EJYM_value = resultset.getString("EJYM");
            	//System.out.println("******2");
            	if (resultset.wasNull()) {
					e.setEjym_EJYM(e.getEjym_SJYM());
				}
            	else {
            		if (e.getEjym_SJYM().equals(EJYM_value)) {
            			e.setEjym_EJYM(EJYM_value);
					}
            		else {
						e.setEjym_EJYM(e.getEjym_SJYM());
					}
					
				}
            	
            	IP_value = resultset.getString("IP");
            	if (resultset.wasNull()) {
					e.setEjym_IP("");
				}
            	else {
					e.setEjym_IP(IP_value);
				}
            	//System.out.println("******3");
            	XTGLY_XM_value = resultset.getString("XTGLY_XM");
            	if (resultset.wasNull()) {
					e.setEjym_XTGLY_XM("");
				}
            	else {
					e.setEjym_XTGLY_XM(XTGLY_XM_value);
				}
            	XTGLY_DH_value = resultset.getString("XTGLY_DH");
            	if (resultset.wasNull()) {
					e.setEjym_XTGLY_DH("");
				}
            	else {
					e.setEjym_XZFZR_DH(XTGLY_DH_value);
				}
            	XTGLY_SJ_value = resultset.getString("XTGLY_SJ");
            	//System.out.println(XTGLY_SJ_value);
            	if (resultset.wasNull()) {
					e.setEjym_XTGLY_SJ("");
				}
            	else {
					e.setEjym_XTGLY_SJ(XTGLY_SJ_value);
				}
            	
            	XTGLY_EMAIL_value = resultset.getString("XTGLY_EMAIL");
            	if (resultset.wasNull()) {
					e.setEjym_XTGLY_EMAIL("");
				}
            	else {
					e.setEjym_XTGLY_EMAIL(XTGLY_EMAIL_value);
				}
            	XZFZR_XM_value = resultset.getString("XZFZR_XM");
            	if (resultset.wasNull()) {
					e.setEjym_XZFZR_XM("");
				}
            	else {
					e.setEjym_XZFZR_XM(XZFZR_XM_value);
				}
            	XZFZR_DH_value = resultset.getString("XZFZR_DH");
            	if (resultset.wasNull()) {
					e.setEjym_XZFZR_DH("");
				}
            	else {
					e.setEjym_XZFZR_DH(XZFZR_DH_value);
				}
            	XZFZR_SJ_value = resultset.getString("XZFZR_SJ");
            	if (resultset.wasNull()) {
					e.setEjym_XZFZR_SJ("");
				}
            	else {
					e.setEjym_XZFZR_SJ(XZFZR_SJ_value);
				}
            	XZFZR_EMAIL_value = resultset.getString("XZFZR_EMAIL");
            	if (resultset.wasNull()) {
					e.setEjym_XZFZR_EMAIL("");
				}
            	else {
					e.setEjym_XZFZR_EMAIL(XZFZR_EMAIL_value);
				}
            	ZYNR_value = resultset.getString("ZYNR");
            	//System.out.println(ZYNR_value);
            	if (resultset.wasNull()) {
					e.setEjym_ZYNR("");
				}
            	else {
					e.setEjym_ZYNR(ZYNR_value);
				}
            	
            	BMLB_value = resultset.getString("BMLB");
            	if (resultset.wasNull()) {
				     e.setEjym_BMLB("");
				}
            	else {
					e.setEjym_BMLB(BMLB_value);
				}
            	BMLB_QT_value = resultset.getString("BMLB_QT");
            	if (resultset.wasNull()) {
					e.setEjym_BMLB_QT("");
				}
            	else {
					e.setEjym_BMLB_QT(BMLB_QT_value);
				}
            	FWZL_value = resultset.getString("FWZL");
            	
            	if (resultset.wasNull()) {
					e.setEjym_FWZL("");
				}
            	else {
					e.setEjym_FWZL(FWZL_value);
				}
            	FWZL_QT_value = resultset.getString("FWZL_QT");
            	if (resultset.wasNull()) {
					e.setEjym_FWZL_QT("");
				}
            	else {
					e.setEjym_FWZL_QT(FWZL_QT_value);
				}
            	OWNER_value = resultset.getString("SQDWID");
            	if (resultset.wasNull()) {
					e.setEjym_OWNER("");
				}
            	else {
					e.setEjym_OWNER(OWNER_value);
				}
            	CONTACT_PHONE_value = resultset.getString("CONTACT_PHONE");
            	
            	if (resultset.wasNull()) {
					e.setEjym_contact_phone("");
				}
            	else {
					e.setEjym_contact_phone(CONTACT_PHONE_value);
				}
            	CONTACT_EMAIL_value = resultset.getString("CONTACT_EMAIL");
            	if (resultset.wasNull()) {
					e.setEjym_contact_email("");
				}
            	else {
					e.setEjym_contact_email(CONTACT_EMAIL_value);
				}
            	UPDATE_AT_value = "";//数据库中没有update字段
            	e.setUpDate_at(UPDATE_AT_value);

            	STATE_value = resultset.getString("STATE");
            	if (resultset.wasNull()) {
					e.setEjym_state("");
				}
            	else {
					e.setEjym_state(STATE_value);
				}
            	System.out.println("state:"+e.getEjym_state());
            	
            	String insertSql = excelOperate.getSqlString(e);
            	existsEjym = excelOperate.isExistsEjym(e.getEjym_EJYM());
            	isSuccess = isSuccess(STATE_value);
            	System.out.println("existsEjym bool值: "+existsEjym+"!  isSuccess bool值: "+isSuccess+"  !");
            	//二级域名不存在且STATE状态是“success”
        		if (!existsEjym && isSuccess) {//如果不存在此二级域名并且状态是success
        			list.add(insertSql);
        			newcount++;
        			System.out.println("oracle新记录"+newcount+",要插入！");
				}
        		else if(existsEjym && isSuccess){////如果存在此二级域名并且状态是success,标记重复个数
        			duplicateCount++;
        			
        				System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝Oracle 重复  "+duplicateCount+"  ，不插入EJYM数据库！");
        		}
        		else if(!isSuccess){ //标注State不是success的个数
        				stateCount++;
        				System.out.println(" State状态是：  "+STATE_value+"  ，不是success的state个数 = "+stateCount+" , 不插入EJYM数据库！");
					}
        			
				
        		
            }  
		} catch (Exception e) {
			// TODO: handle exception
		}
		 System.out.println("Oracle重复个数："+duplicateCount);
		 System.out.println("state不是success个数："+stateCount);
		return list;
	}

	private boolean isSuccess(String ejym_state) {
		// TODO Auto-generated method stub
		//System.out.println("获取二级域名状态State ："+ejym_state);
		if ("success".equals(ejym_state)) {
			return true;
		}
		else {
			return false;
		}
	}
}
