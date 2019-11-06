package com.zcwsoft.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.FileCopyUtils;  
import org.springframework.web.multipart.MultipartFile;  
import org.springframework.web.multipart.MultipartHttpServletRequest;  

import com.zcwsoft.common.newutils.CommonManage;
/**
*  <pre>    
* 类名称：FileOperateUtil
* 类描述：   上传下载公共类
* 创建人：cyc
* 创建时间：2016年3月25日10:13:45
* 修改人：
* 修改时间：2016年3月25日10:13:48
* 修改备注： 
* @version V1.0
* </pre>
*/
public class FileOperateUtil {

	private static final String REALNAME = "realName";//文件真实名称
	private static final String STORENAME = "storeName";//你想存储为哪个名称
	private static final String SIZE = "size";//文件大小
	private static final String SUFFIX = "suffix";//文件后缀
	private static final String CONTENTTYPE = "contentType";//文件的mimeType
	private static final String CREATETIME = "createTime";//创建时间
	private static final String UPLOADDIR = "uploadDir/";//上传后放在哪个位置
	
	/** 
	* 将上传的文件进行重命名 
	* @author cyc 
	* @date 2016年3月25日09:49:19
	* @param name 
	* @return 
	*/
	private static String rename(String name) {
		Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));  
		Long random = (long) (Math.random() * now);
		String fileName = now + "" + random;
		if (name.indexOf(".") != -1) {
			fileName += name.substring(name.lastIndexOf("."));
		}
		return fileName;
	}
	
	/** 
	* 压缩后的文件名 
	* @author cyc 
	* @date 2016年3月25日09:50:34
	* @param name 
	* @return 
	*/
	private static String zipName(String name) {  
		String prefix = "";
		if (name.indexOf(".") != -1) {
			prefix = name.substring(0, name.lastIndexOf("."));  
		} else {
			prefix = name;
		}
			return prefix + ".zip";
		}
	
	/** 
	* 上传文件 
	* @author cyc 
	* @date 2016年3月25日09:51:31
	* @param request 
	* @param params 
	* @param values 
	* @return 
	* @throws Exception 
	*/
	public static List<Map<String, Object>> upload(HttpServletRequest request, String[] params, Map<String, Object[]> values, String path) throws Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = mRequest.getFileMap();
//		String uploadDir = request.getSession().getServletContext().getRealPath("/") + FileOperateUtil.UPLOADDIR;
		String uploadDir = request.getSession().getServletContext().getRealPath("/") + path;
		File file = new File(uploadDir);
		if (!file.exists()) {
			file.mkdir();
		}
		String fileName = null;  
		int i = 0;  
		for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext(); i++) {
			Map.Entry<String, MultipartFile> entry = it.next();
			MultipartFile mFile = entry.getValue();
			fileName = mFile.getOriginalFilename();
			String storeName = rename(fileName);
			String noZipName = uploadDir + storeName;
			/*上传的文件统一为zip格式
			String zipName = zipName(noZipName);
			// 上传成为压缩文件  
			ZipOutputStream outputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipName)));
			outputStream.putNextEntry(new ZipEntry(fileName));
//			outputStream.setEncoding("GBK");//解除这句代码的注释会导致乱码
			FileCopyUtils.copy(mFile.getInputStream(), outputStream);
			Map<String, Object> map = new HashMap<String, Object>();
			// 固定参数值对  
			map.put(FileOperateUtil.REALNAME, zipName(fileName));
			map.put(FileOperateUtil.STORENAME, zipName(storeName));
			map.put(FileOperateUtil.SIZE, new File(zipName).length());
			map.put(FileOperateUtil.SUFFIX, "zip");  
			map.put(FileOperateUtil.CONTENTTYPE, "application/octet-stream");
			map.put(FileOperateUtil.CREATETIME, new Date());
			map.put("uploadDir", uploadDir);
			*/
			
			// 上传成为压缩文件  
			FileOutputStream outputStream = new FileOutputStream(noZipName);
			FileCopyUtils.copy(mFile.getInputStream(), outputStream);
			Map<String, Object> map = new HashMap<String, Object>();
			// 固定参数值对  
			map.put(FileOperateUtil.REALNAME, fileName);
			map.put(FileOperateUtil.STORENAME, storeName);
			map.put("uploadDir", uploadDir);
			
			// 自定义参数值对  
//			for (String param : params) {
//				map.put(param, values.get(param)[i]);
//			}
			result.add(map);
		}
		return result;
		}

	/** 
	* 下载 
	* @author geloin 
	* @date 2012-5-5 下午12:25:39 
	* @param request 
	* @param response 
	* @param storeName 
	* @param contentType 
	* @param realName 
	* @throws Exception 
	*/
	public static void download(HttpServletRequest request, HttpServletResponse response, String storeName, String contentType, String realName, String path) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
//		String ctxPath = request.getSession().getServletContext().getRealPath("/") + FileOperateUtil.UPLOADDIR;
		String ctxPath = request.getSession().getServletContext().getRealPath("/") + path;
		String downLoadPath = ctxPath + storeName;
		long fileLength = new File(downLoadPath).length();
		response.setContentType(contentType);
		response.setHeader("Content-disposition", "attachment; filename="+new String( realName.getBytes(),"iso-8859-1"));
		response.setHeader("Content-Length", String.valueOf(fileLength));
		bis = new BufferedInputStream(new FileInputStream(downLoadPath));  
		bos = new BufferedOutputStream(response.getOutputStream());
		byte[] buff = new byte[2048];  
		int bytesRead;  
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);  
		}
		bis.close();  
		bos.close();  
	}
	
	
	/**
	 * 功能：附件上传
	 * 作者：xsh
	 * 开发时间：2016-7-30
	 */
	public static List<Map<String, Object>> uploadAttachment(HttpServletRequest request, String path) throws Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = mRequest.getFileMap();
		String uploadDir = request.getSession().getServletContext().getRealPath("/") + path;
		String realName = null;//原来的文件名
		String storeName = null; //重新命名后的文件名
		String hzName = null;//后缀名
		for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext(); ) {
			Map.Entry<String, MultipartFile> entry = it.next();
			MultipartFile mFile = entry.getValue();
			realName = mFile.getOriginalFilename();
			hzName = realName.substring(realName.lastIndexOf("."));
			storeName = CommonManage.getUuid() + hzName;//获取唯一文件名
			File file = new File(uploadDir, storeName);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream outputStream = new FileOutputStream(file);
			FileCopyUtils.copy(mFile.getInputStream(), outputStream);
			Map<String, Object> map = new HashMap<String, Object>(); 
			map.put("storeName", storeName);
			map.put("realName", realName);
			map.put("uploadDir", uploadDir);
			result.add(map);
		}
		return result;
	}
	
	/**
	 * 功能：附件下载
	 * 作者：徐盛
	 * 开发时间：2016-7-30
	 */
	public static boolean downloadAttachment(HttpServletRequest request, HttpServletResponse response, 
			String contentType, String storeName, String realName, String dirPath) throws Exception {
		boolean flag = false;
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		dirPath = request.getSession().getServletContext().getRealPath("/") + dirPath;
		String downLoadFilePath = dirPath + storeName;
		File downLoadFile = new File(downLoadFilePath);
		if(downLoadFile.exists() && downLoadFile.isFile()){
			long fileLength = downLoadFile.length();
			response.setContentType(contentType);
			response.setHeader("Content-disposition", "attachment; filename=" 
				+ new String(realName.getBytes("gb2312"),"iso-8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(downLoadFile));// 获取输入流 
			bos = new BufferedOutputStream(response.getOutputStream());// 获取输出流
			byte[] buff = new byte[2048];  
			int bytesRead;  
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);// 写出到浏览器
			}
			flag = true;
			bis.close();  
			bos.close();  
		} else {
			flag = false;
		}
		return flag;
	}
	
	//导出excel，弹出下载框
	public static boolean exportExcel(HttpServletRequest request, HttpServletResponse response, String contentType, String fileName, InputStream inputStream) throws Exception {
		boolean flag = false;
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		response.setContentType(contentType);
		if(inputStream!=null){
			String name = new String(fileName.getBytes("utf-8"),"iso-8859-1");
			response.setHeader("Content-disposition", "attachment; filename=" + name);
			bis = new BufferedInputStream(inputStream);// 获取输入流 
			bos = new BufferedOutputStream(response.getOutputStream());// 获取输出流
			byte[] buff = new byte[2048];  
			int bytesRead;  
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);// 写出到浏览器
			}
			flag = true;
			bis.close();  
			bos.close();  
		}else {
			flag = false;
		}
		return flag;
	}
}
