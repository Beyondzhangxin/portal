package aiidc.com.cn.portal.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUpload;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.aiidc.base.ControllerInfo;

import aiidc.com.cn.portal.util.FileUtil;
import aiidc.com.cn.portal.util.Finals;

@Controller
@RequestMapping("pub")
@ControllerInfo(order=10,value="公共方法")
public class PublicController {
	
	@Value("#{configProperties['upload.Image.path']}")
	private String uploadimagepath;
	
	private String filePath = File.separator+Finals.PIC_TYPE_NEW_CONTENT+File.separator;
	
	@RequestMapping(value="uploadImage",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody String uploadImage(@RequestParam("upfile")  CommonsMultipartFile upfile
			,HttpServletRequest request) 
	throws Exception
	{
		JSONObject json = new JSONObject();
		String saveName = FileUtil.getUploadImgName(upfile);
		String savepath=uploadimagepath+filePath;
		FileUtil.uploadImageByTransferTo(upfile, savepath, saveName);
		
		//是否上传成功
		json.put("state", "SUCCESS");
        //现在文件名称
		json.put("title", upfile.getOriginalFilename());
        //文件原名称 
		json.put("original", upfile.getOriginalFilename());
		//文件路径
		json.put("url", request.getContextPath()+File.separator+savepath+saveName);
		return json.toString();
	}
}
