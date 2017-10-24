package aiidc.com.cn.portal.controller;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import aiidc.com.cn.portal.entity.Culture;
import aiidc.com.cn.portal.entity.Pic;
import aiidc.com.cn.portal.parameter.CultureModel;
import aiidc.com.cn.portal.service.CultureManager;
import aiidc.com.cn.portal.service.PicManager;
import aiidc.com.cn.portal.util.FileUtil;
import aiidc.com.cn.portal.util.Finals;
import aiidc.com.cn.portal.util.JsonTools;
import aiidc.com.cn.portal.util.Pagination;

@Controller
@RequestMapping("/company/culture")
public class CultureController {
	
	@Autowired
	private CultureManager cultureManager;
	@Autowired
	private PicManager picManager;
	@Value("#{configProperties['upload.Image.path']}")
	private String uploadimagepath;

	private String filePath = File.separator + Finals.PIC_TYPE_CULTURE + File.separator;
	private static final String prefix = "company/culture/";
	
	
    /**
     * 前端详情
     * @param id
     * @param model
     * @param request
     * @param response
     * @throws IOException
     */
	@RequestMapping(value = "/fr_detail" , method = RequestMethod.POST)
	public void fr_detailPost(@RequestParam String id,
			Model model ,HttpServletRequest request,HttpServletResponse response) throws IOException{
		Culture culture = cultureManager.findById(id);
		JsonTools.writeJson(response, culture);
	}
	/**
	 * 前端分页
	 * @param model
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/fr_manage" , method = RequestMethod.POST)
	public void fr_managePost(@ModelAttribute CultureModel queryModel,
			Model model ,HttpServletRequest request,HttpServletResponse response) throws IOException{
		queryModel.setStatus(Finals.STATUS_YES);
		queryModel.setType(Finals.COMPANY_TYPE_CULTURE);
		Pagination pagn = cultureManager.pageQuery(queryModel );
		List<Culture> list = (List<Culture>) pagn.getResult();
		for (Culture culture : list) {
			culture.setPicName(uploadimagepath+filePath+culture.getPicName());
		}
		pagn.setResult(list);
		System.err.println(pagn);
		JsonTools.writeJson(response, pagn);
	}
	
	/**
	 * 分页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/manage" , method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("pageModel") CultureModel queryModel,Model model ,HttpServletRequest request){
		String MSG = request.getParameter("MSG");
		if (StringUtils.hasText(MSG)) {
			JSONObject json = new JSONObject();
			json.put("message","操作成功");
			model.addAttribute("json", json);
		}
		queryModel.setType(Finals.COMPANY_TYPE_CULTURE);
		Pagination pagn = cultureManager.pageQuery(queryModel );
		model.addAttribute("pagn", pagn);
		return prefix +"manage";
	}
	
	/**
	 * 添加
	 * @param entity
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/add" , method = RequestMethod.GET)
	public String addGet(@ModelAttribute("pageModel") Culture entity,Model model ,HttpServletRequest request){
		return prefix +"add";
	}
	@RequestMapping(value = "/add" , method = RequestMethod.POST)
	public String addPost(@ModelAttribute("pageModel")@Valid Culture entity,Errors error ,
			Model model ,HttpServletRequest request ,HttpServletResponse response) throws Exception{
		if(error.hasErrors() || entity.getFile().isEmpty()){
			JSONObject json = new JSONObject();
			json.put("message","请检查必填项");
			model.addAttribute("json", json);
			return prefix +"add";

		}else{
			MultipartFile file = entity.getFile();
			String saveName = FileUtil.getUploadImgName(file);
			
			FileUtil.uploadImageByTransferTo(file, uploadimagepath + filePath ,saveName);
			
			Pic pic = new Pic();
			pic.setName(file.getOriginalFilename());
			pic.setRealName(saveName);
			pic.setType(Finals.PIC_TYPE_CULTURE);
			picManager.add(pic);
			
			entity.setPicId(pic.getId());
			entity.setPicName(saveName);
			cultureManager.add(entity);
			return "redirect:manage?MSG=1";
		}
	}
	
	/**
	 * 修改
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/update/{id}" , method = RequestMethod.GET)
	public String updateGet(@PathVariable("id") String id,Model model ,HttpServletRequest request){
		Culture culture = cultureManager.findById(id);
		String pathName =uploadimagepath+filePath+culture.getPicName();
		model.addAttribute("pathName", pathName);
		model.addAttribute("pageModel", culture);
		return prefix +"update";
	}
	@RequestMapping(value = "/update" , method = RequestMethod.POST)
	public String updatePost(@ModelAttribute("pageModel")@Valid Culture entity,Errors error ,
			Model model ,HttpServletRequest request ,HttpServletResponse response){
		if (!entity.getFile().isEmpty()) {
			MultipartFile file = entity.getFile();
			String saveName = FileUtil.getUploadImgName(file);
			try {
				//新图上传
				FileUtil.uploadImageByTransferTo(file, uploadimagepath + filePath,saveName);
				//旧图删除
				FileUtil.deleteFile(uploadimagepath+filePath,entity.getPicName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			//更改记录
			Pic pic = picManager.findById(entity.getPicId());
			pic.setName(file.getOriginalFilename());
			pic.setRealName(saveName);
			picManager.update(pic);
			
			entity.setPicName(saveName);
		}
		entity.setCreateTime(Calendar.getInstance());
		entity.setStatus(Finals.STATUS_DEFAULT);
		cultureManager.update(entity);
		return "redirect:manage?MSG=1";
	}

	@RequestMapping(value = "addPic", method = RequestMethod.POST)
	@ResponseBody
	public String upload2(@RequestParam String content_id, HttpServletRequest request) throws Exception
	{
		//创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		//判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request))
		{
			//转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			//取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext())
			{
				//取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null)
				{
					String uploadImgName = FileUtil.getUploadImgName(file);
					FileUtil.uploadImageByTransferTo(file, uploadimagepath + filePath, uploadImgName);

					Pic pic = new Pic();
					pic.setName(file.getOriginalFilename());
					pic.setRealName(uploadImgName);
					pic.setType(Finals.PIC_TYPE_CULTURE);
					pic.setItemId(content_id);
					picManager.add(pic);
				}
			}

		}
		return "success";
	}

	/**
	 * 删除
	 * @param id
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}" , method = RequestMethod.POST)
	public void delete(@PathVariable("id") String id ,Model model ,
			HttpServletRequest request ,HttpServletResponse response){
		//旧图删除
		Culture culture = cultureManager.findById(id);
		FileUtil.deleteFile(uploadimagepath+filePath,culture.getPicName());
		cultureManager.deleteById(id);
	}
	

}
