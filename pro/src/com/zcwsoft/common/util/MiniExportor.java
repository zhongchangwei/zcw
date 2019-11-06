package com.zcwsoft.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import com.zcwsoft.common.newutils.CommonManage;

import jxl.Workbook;
import jxl.biff.DisplayFormat;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 需求功能概述:导出Excel
 * 作者：黄晓洁
 * 开发时间：2014年11月4日
 */
public class MiniExportor {
	
	
	/**
	 * 自适应长度
	 * 作者：黄晓洁
	 * 开发时间：2014年11月4日
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	private Integer[] getLength(String[] titles,List rtList){
		Integer[] len = new Integer[titles.length];
		//默认为表头的长度
		for (int i = 0; i < len.length; i++) {
			len[i] = length(titles[i]); 
		}
		
		//找数据的最大长度
		for(int row = 0 ;row<rtList.size();row++){
			Object[] values = (Object[]) rtList.get(row);
			for(int i = 0 ;i<values.length;i++){
				if (values[i] != null) {
					Integer valuelen = length(values[i].toString().trim());
					if(len[i] < valuelen){
						len[i] = valuelen;
					}
				}
			}
		}
		//设置最小最大值
		for (int i = 0; i < len.length; i++) {
			if(len[i]<16){
				len[i]= 17; 
			}
			if (len[i] >100) {
				len[i]= 100; 
			}
		}
		return len;
	}
	/**
	 * 计算字符串长度
	 * 作者：黄晓洁
	 * 开发时间：2014年11月4日
	 * @throws Exception
	 */
	public  int length(String value) {
		int valueLength = 0;
		String chinese = "[\u0391-\uFFE5]";
		/* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
		for (int i = 0; i < value.length(); i++) {
			/* 获取一个字符 */
			String temp = value.substring(i, i + 1);
			/* 判断是否为中文字符 */
			if (temp.matches(chinese)) {
				/* 中文字符长度为2 */
				valueLength += 2;
			} else {
				/* 其他字符长度为1 */
				valueLength += 1;
			}
		}
		return valueLength;
	}
	/**
	 * 功能：公积金统计分析表头、已使用、请勿修改
	 * 作者：zcw
	 * 开发时间：2017年1月17日08:52:44
	 * @throws Exception
	 */
	private void setHead( String[] titles ,WritableSheet sheet) throws Exception {
		if(titles==null)return;
		
		//是否显示网格
		sheet.getSettings().setShowGridLines(true); 
		//设置行高
		sheet.getSettings().setDefaultRowHeight(350); 
		//设置字体
		WritableFont font = new  WritableFont(WritableFont.ARIAL,11,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
		//设置背景
		WritableCellFormat wcf = new WritableCellFormat(font);
		wcf.setBackground(Colour.WHITE);//冰蓝

		wcf.setAlignment(jxl.format.Alignment.CENTRE);
		 wcf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE); // 设置垂直对齐方
		//边框黑色
		wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
		for(int i = 0 ;i<titles.length;i++){
			String title = titles[i];
			Label label = new Label(i,0,title);
			label.setCellFormat(wcf);
			try {
				sheet.addCell(label);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	/**
	 * 功能：公积金统计分析导出，已使用、请勿修改
	 * 作者：zcw
	 * 开发时间：2017年1月17日08:50:38
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public InputStream exportExcel_noTrim(String[] titles,List rtList,String type){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		WritableWorkbook workbook;
		try {
			workbook = Workbook.createWorkbook(baos);
			WritableSheet sheet = workbook.createSheet("Sheet1", 0);

			//一、写表头
			if("t".equals(type)){
				setHeadcase(titles,sheet);
			}else{
				setHead(titles,sheet);
			}
			//二、写数据
			if(rtList!=null && rtList.size()>0){
				//边框黑色
				//WritableCellFormat wcf = new WritableCellFormat();
				//wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
				WritableFont wf = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false);
				DisplayFormat displayFormat = NumberFormats.TEXT;
				WritableCellFormat wcf = new WritableCellFormat(wf,displayFormat);
				wcf.setAlignment(jxl.format.Alignment.LEFT);
				wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
				int row =1;
				if("t".equals(type)){
				row= 3;
				}
				//Label labelN;
				Integer[] len = getLength(titles,rtList);
				for(int i = 0 ;i<rtList.size();i++){
					Object[] values = (Object[]) rtList.get(i);
					for(int j = 0;j<values.length;j++){ 
						String value = CommonManage.toNotNullString(values[j]);
						if(NumCheck.isNumber(value)){//判断是否为数字
							jxl.write.Number labelN;
							labelN = new jxl.write.Number(j, row, Double.parseDouble(value),wcf);
							labelN.setCellFormat(wcf);
							sheet.addCell(labelN); 
							//sheet.setColumnView(j,len[j]);
						}else{
							Label labelN;
							if(value.equals("")){
								labelN = new Label(j, row,value);
							}else{
								labelN = new Label(j, row,value);
							}
							labelN.setCellFormat(wcf);
							sheet.addCell(labelN); 
							//sheet.setColumnView(j,len[j]);
						}
						//labelN = new Label(j, row,value.trim());
						//labelN.setCellFormat(wcf);
						//sheet.addCell(labelN);
						if (titles[j].indexOf("隐藏")>0) {
							sheet.setColumnView(j,0);//原来把宽度设置为0
						} else {
							//否则自适应
							sheet.setColumnView(j,len[j]);
							//如果等于这几列，宽度就设置为10
							if(j<8||j>9){
								if(type.equals("t")){//如果等于投诉职工人数统计导出
									sheet.setColumnView(j,13);
								}
							}
							if(j<values.length-2&&j>2){
								if(type.equals("l")&&j!=4){//如果等于投诉职工人数统计导出
									sheet.setColumnView(j,11);
								}
							}
						}
					}
					row++;
				}
				
			}
			workbook.write();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		InputStream inputStream = new ByteArrayInputStream(baos.toByteArray());
		return inputStream;
	}

	
	
	
	/**
	 * 功能：表头单元格合并，已使用、请勿修改
	 * 作者：zcw
	 * 开发时间：2017年1月16日15:32:45
	 * @throws Exception
	 */
	private void setHeadcase( String[] titles ,WritableSheet sheet) throws Exception {
		if(titles==null)return;
		
		//是否显示网格
		sheet.getSettings().setShowGridLines(true); 
		//设置行高
		sheet.getSettings().setDefaultRowHeight(350); 
		//设置字体
		WritableFont font = new  WritableFont

(WritableFont.ARIAL,10,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
		//设置背景
		WritableCellFormat wcf = new WritableCellFormat(font);
		
		wcf.setAlignment(jxl.format.Alignment.CENTRE);
		 wcf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE); // 设置垂直对齐方

		//wcf.setBackground(Colour.ICE_BLUE);//冰蓝
		//边框黑色
		wcf.setBorder

(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
		for(int i = 0 ;i<titles.length;i++){
			String title = titles[i];
			Label label=null;
			if(title.equals("办结合计")){
				sheet.mergeCells(i, 0, titles.length-1, 0);//合并单元格
				label = new Label(i,0,"办结");
				label.setCellFormat(wcf);
				sheet.addCell(label);
				sheet.mergeCells(i, 1, i, 2);//合并单元格
				label = new Label(i,1,title);
				label.setCellFormat(wcf);
				sheet.addCell(label);
			}if(title.equals("未发行处书补缴")){
				sheet.mergeCells(i, 1, i+1, 1);//合并单元格
				label = new Label(i,1,"单位补缴");
				label.setCellFormat(wcf);
				sheet.addCell(label);
				label = new Label(i,2,title);
				label.setCellFormat(wcf);
				sheet.addCell(label);
			}
			if(title.equals("已发行处书后补缴")){
				label = new Label(i,2,title);
				label.setCellFormat(wcf);
			}
				if(i<7){

					label = new Label(i,0,title);
					label.setCellFormat(wcf); 
					sheet.mergeCells(i, 0, i, 2);//合并单元格
				}else if(i>9){
					sheet.mergeCells(i, 1, i, 2);//合并单元格
					label = new Label(i,1,title);
					label.setCellFormat(wcf);
				}
			
			try {
				sheet.addCell(label);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		
	}
}
