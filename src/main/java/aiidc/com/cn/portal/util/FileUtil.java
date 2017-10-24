package aiidc.com.cn.portal.util;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FileUtil {
	/**
	 * 删除单个文件
	 * @param path 
	 * 		目标文件的路径:xxx/xxx/xxx/xxx.html
	 * @return
	 */
	public static boolean deleteFile(String path){
		boolean ok =false;
		File deleteFile = new File(path);
		if(deleteFile.exists()){
			ok = deleteFile.delete();
		}
		return ok;
	}
	/**
	 * 
	 * @param path 相对路径
	 * @param fileName 文件名字
	 * @return
	 */
	public static boolean deleteFile(String path,String fileName){
		boolean ok =false;
		String filePath = PathUtil.getRootPath()+File.separator+path+fileName;
		File deleteFile = new File(filePath);
		if(deleteFile.exists()){
			ok = deleteFile.delete();
		}
		return ok;
	}
	
	/**
	 * 通过transferTo()方法进行图片上传
	 * @param file	上传文件
	 * @param filePath 相对于工程根目录的路径
	 * @return
	 * @throws Exception
	 */
	public static JSONObject uploadImageByTransferTo(MultipartFile file,String filePath ,String saveFileName)
	throws Exception
	{
		JSONObject json = new JSONObject();
		//判断上传的文件是否为图片
		String imagename = file.getOriginalFilename();
		if(!checkImage(imagename)){
			json.put("success", false);
			json.put("message", "图片格式不正确");
			return json;
		}
		
		String pathName = PathUtil.getRootPath()+File.separator+filePath;
		System.err.println("存储路径======="+pathName);
		File newFile = new File(pathName , saveFileName);
		if(!newFile.exists()) newFile.mkdirs();
		file.transferTo(newFile);
		
		json.put("success", true);
		json.put("message", "图片上传成功");
		return json;
	}
	
	/**
	 * 获得一个当前时间的图片名
	 * @return
	 */
	public static String getUploadImgName(MultipartFile file){
		String originalFilename = file.getOriginalFilename();
		String lastName = originalFilename.substring(originalFilename.lastIndexOf("."));// 获取后缀	
		Calendar instance = Calendar.getInstance();
		Date date = instance.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String timeStr = sdf.format(date);
		long millis = instance.getTimeInMillis();
		String saveName = timeStr+millis+lastName;
		return saveName;
	}
	
	
	/**
	 *  判断上传的文件是否为图片
	 * @param imageName
	 * @return
	 */
	public static boolean checkImage(String imageName){
		// 声明图片后缀名数组
		String imgeArray [][] = { 
			{"bmp", "0"}, {"dib", "1"}, {"gif", "2"},{"jfif", "3"}, {"jpe", "4"}, {"jpeg", "5"}, 
			{"jpg", "6"}, {"png", "7"} ,{"tif", "8"}, {"tiff", "9"}, {"ico", "10"}};
		String postfix =imageName.substring(imageName.indexOf(".")+1);
		for(int i = 0; i<imgeArray.length;i++){
			if(imgeArray [i][0].equals(postfix.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
}
