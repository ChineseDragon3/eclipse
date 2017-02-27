import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExeclOperate {
	
	List<String> list_repeat_ID = new ArrayList<String>();
    public List<String> getList_repeat_ID() {
		return list_repeat_ID;
	}
	public void setList_repeat_ID(List<String> list_repeat_ID) {
		this.list_repeat_ID = list_repeat_ID;
	}
	public List<String> getSqlList(String filename) throws IOException {
    	 
        Workbook book = null;
        book = getExcelWorkbook(filename);
        Sheet sheet = getSheetByNum(book,0);
        
        int lastRowNum = sheet.getLastRowNum();
        List<String> list = new ArrayList<String>();
        int duplicateCount = 0;
        for(int i = 1 ; i <= lastRowNum ; i++){
        	
//        	if (i==20) {
//				break;
//			}
            Row row = null;
            row = sheet.getRow(i);
            if( row != null ){
                //System.out.println("reading line is " + i);
                int lastCellNum = row.getLastCellNum();
                //System.out.println("lastCellNum is " + lastCellNum );
                Cell cell = null;
              
                Ejym e =  new Ejym();
           
                for( int j = 0 ; j <= lastCellNum ; j++ ){
                    cell = row.getCell(j);
                    
                    if( cell != null ){
                        String cellValue = cell.getStringCellValue();
                        //System.out.print("cell value is:" + cellValue+"\t");
                        switch (j) {
    					case 0:   						
    						e.setEjym_OWNER(cellValue);
    						continue;
    					case 1:
    						//申请人姓名不要插入数据库
    						continue;
    					case 2:
    						e.setEjym_contact_phone(cellValue);
    						continue;
    					case 3:
    						e.setEjym_contact_email(cellValue);
    						continue;
    					case 4:
    						e.setEjym_DWMC(cellValue);
    						continue;
    					case 5:
    						e.setEjym_DWFZR(cellValue);
    						continue;
    					case 6:
    						e.setEjym_BMLB(cellValue);
    						continue;
    					case 7:
    						e.setEjym_BMLB_QT(cellValue);
    						continue;
    					case 8:
    						e.setEjym_XZFZR_XM(cellValue);
    						continue;
    					case 9:
    						e.setEjym_XZFZR_DH(cellValue);
    						continue;
    					case 10:
    						e.setEjym_XZFZR_SJ(cellValue);
    						continue;
    					case 11:
    						e.setEjym_XZFZR_EMAIL(cellValue);
    						continue;
    					case 12:
    						e.setEjym_XTGLY_XM(cellValue);
    						continue;
    					case 13:
    						e.setEjym_XTGLY_DH(cellValue);
    						continue;
    					case 14:
    						e.setEjym_XTGLY_SJ(cellValue);
    						continue;
    					case 15:
    						e.setEjym_XTGLY_EMAIL(cellValue);
    						continue;
    					case 16:
    						e.setEjym_EJYM(cellValue);
    						continue;
    					case 17:
    						e.setEjym_IP(cellValue);
    						continue;
    					case 18:
    						//Excel中"是否需要校外访问"字段在数据库中没有此字段，选择不插入
    						continue;
    					case 19:
    						e.setEjym_FWZL(cellValue);
    						continue;
    					case 20:
    						e.setEjym_FWZL_QT(cellValue);
    						continue;
    					case 21:
    						e.setEjym_ZYNR(cellValue);
    						continue;
    					case 22:
    				        //最后更新日期
    						e.setUpDate_at(cellValue);
    						continue;
    						
    					}
                    }

                }
             // 拼装插入数据库的sql
        		String insertSql = getSqlString(e);
        		
        		//System.out.println("SQL:" + insertSql);
        		//判断二级域名不存在且不为空
        		if (!isExistsEjym(e.getEjym_EJYM())) {
        			list.add(insertSql);
				}
        		else {
        			duplicateCount++;
        			System.out.println("Excel重复   "+duplicateCount+"   ，选择不插入数据库！");
				}
        		
            }
            //System.out.println("Excel的第 "+i+" 行记录读取完毕！！！！！！");
        }
        System.out.println("导入List完成！");  
        return list;
    }
	public String getSqlString(Ejym e)
	{
	
		String sqlString = "insert into EJYM (DWMC,DWFZR,EJYM,IP,XTGLY_XM,XTGLY_DH,XTGLY_SJ,XTGLY_EMAIL,XZFZR_XM,XZFZR_DH,XZFZR_SJ,XZFZR_EMAIL,ZYNR,BMLB,BMLB_QT,FWZL,FWZL_QT,OWNER,CONTACT_PHONE,CONTACT_EMAIL,UPDATE_AT) values('" 
		
        				+e.getEjym_DWMC() + "','"
        				+ e.getEjym_DWFZR() + "','" + e.getEjym_EJYM() + "','" + e.getEjym_IP() + "','"
        				+ e.getEjym_XTGLY_XM() + "','" + e.getEjym_XTGLY_DH() + "','" + e.getEjym_XTGLY_SJ() + "','"
        				+ e.getEjym_XTGLY_EMAIL() + "','" + e.getEjym_XZFZR_XM() + "','" + e.getEjym_XZFZR_DH() + "','"
        				+ e.getEjym_XZFZR_SJ() + "','" + e.getEjym_XZFZR_EMAIL() + "','"+ e.getEjym_ZYNR() + "','"
        				+ e.getEjym_BMLB() + "','" + e.getEjym_BMLB_QT() + "','" + e.getEjym_FWZL() + "','"
        				+ e.getEjym_FWZL_QT() + "','" + e.getEjym_OWNER() + "','" + e.getEjym_contact_phone() + "','"
        				+ e.getEjym_contact_email() + "'," + "to_timestamp('" +e.getUpDate_at() + "',"+"'yyyy-mm-dd hh24:mi:ss'"+ ")" + ")";
		return sqlString;	
	}
    public boolean isNumeric(String str)
    {
	    Pattern pattern = Pattern.compile("[0-9]*");
	    Matcher isNum = pattern.matcher(str);
	    if( !isNum.matches() )
	    {
	    return false;
	    }
	    return true;
    }
    public boolean isExistsEjym(String ejym_EJYM) {
		// TODO Auto-generated method stub
    	boolean result = false;
    	Connection conn = null;
    	Statement stmt = null;
    	try {
			String search_EJYM_String = "select * from EJYM";
			conn = Oracal_Conn.conn();
			// 创建数据库通道
			stmt = conn.createStatement();
			
            ResultSet resultset = stmt.executeQuery(search_EJYM_String);  
            while(resultset.next())  
            {  
            	String oralcle_EJYM = resultset.getString("EJYM");
                if (ejym_EJYM.equals(oralcle_EJYM)) {
                	result = true;
            
					break;
				}
            }  
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	public static Sheet getSheetByNum(Workbook book,int number){  
        Sheet sheet = null;  
        try {  
            sheet = book.getSheetAt(number);  
//          if(sheet == null){  
//              sheet = book.createSheet("Sheet"+number);  
//          }  
        } catch (Exception e) {  
            throw new RuntimeException(e.getMessage());  
        }  
        return sheet;  
    }  
    public static Workbook getExcelWorkbook(String filePath) throws IOException{  
        Workbook book = null;  
        File file  = null;  
        FileInputStream fis = null;   
          
        try {  
            file = new File(filePath);  
            if(!file.exists()){  
                throw new RuntimeException("文件不存在");  
            }else{  
                fis = new FileInputStream(file);  
                book = WorkbookFactory.create(fis); 
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e.getMessage());  
        } finally {  
            if(fis != null){  
                fis.close();  
            }  
        }  
        return book;  
    }  
}
