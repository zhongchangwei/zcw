package com.zcwsoft.business.enforce.zcw.service.impl;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcwsoft.business.enforce.common.dao.impl.CommonDaoImpl;
import com.zcwsoft.business.enforce.zcw.service.ExcelImpService;

@Service("excelImpService")
public class ExcelImpServiceImpl implements ExcelImpService {
	@Autowired
	private CommonDaoImpl comDao;

	@Override
	public void ExcelImpro(HttpServletRequest request,HttpServletResponse response)throws Exception {
		String xlsFile = "f:/poiSXXFSBigData.xlsx";     //输出文件  
	    //内存中只创建100个对象，写临时文件，当超过100条，就将内存中不用的对象释放。  
	    Workbook wb = new SXSSFWorkbook(100);           //关键语句  
	    Sheet sheet = null;     //工作表对象  
	    Row nRow = null;        //行对象  
	    Cell nCell = null;      //列对象  
	  
	   
	    Connection conn = comDao.getConnection();   
	    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);     
	    String sql = "select * from hpa_normal_tissue limit 1000000";   //100万测试数据  
	    ResultSet rs = stmt.executeQuery(sql);    
	      
	    ResultSetMetaData rsmd = rs.getMetaData();  
	    long  startTime = System.currentTimeMillis();   //开始时间  
	    System.out.println("strat execute time: " + startTime);  
	          
	    int rowNo = 0;      //总行号  
	    int pageRowNo = 0;  //页行号  
	          
	    while(rs.next()) {  
	        //打印300000条后切换到下个工作表，可根据需要自行拓展，2百万，3百万...数据一样操作，只要不超过1048576就可以  
	        if(rowNo%300000==0){  
	            System.out.println("Current Sheet:" + rowNo/300000);  
	            sheet = wb.createSheet("我的第"+(rowNo/300000)+"个工作簿");//建立新的sheet对象  
	            sheet = wb.getSheetAt(rowNo/300000);        //动态指定当前的工作表  
	            pageRowNo = 0;      //每当新建了工作表就将当前工作表的行号重置为0  
	        }     
	        rowNo++;  
	        nRow = sheet.createRow(pageRowNo++);    //新建行对象  
	  
	        // 打印每行，每行有6列数据   rsmd.getColumnCount()==6 --- 列属性的个数  
	        for(int j=0;j<rsmd.getColumnCount();j++){  
	            nCell = nRow.createCell(j);  
	            nCell.setCellValue(rs.getString(j+1));  
	        }  
	              
	        if(rowNo%10000==0){  
	            System.out.println("row no: " + rowNo);  
	        }  
//	      Thread.sleep(1);    //休息一下，防止对CPU占用，其实影响不大  
	    }  
	          
	    long finishedTime = System.currentTimeMillis(); //处理完成时间  
	    System.out.println("finished execute  time: " + (finishedTime - startTime)/1000 + "m");  
	          
	    FileOutputStream fOut = new FileOutputStream(xlsFile);  
	    wb.write(fOut);  
	    fOut.flush();       //刷新缓冲区  
	    fOut.close();  
	          
	    long stopTime = System.currentTimeMillis();     //写文件时间  
	    System.out.println("write xlsx file time: " + (stopTime - startTime)/1000 + "m");  
	          
	    this.close(rs, stmt, conn);  
	}  
   
	//执行关闭流的操作  
	private void close(ResultSet rs, Statement stmt, Connection conn ) throws Exception{  
	    rs.close();     
	    stmt.close();     
	    conn.close();   
	}  
	
	

}
