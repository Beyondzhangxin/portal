package aiidc.com.cn.portal.controller;

import aiidc.com.cn.portal.entity.Leader;
import aiidc.com.cn.portal.entity.Pic;
import aiidc.com.cn.portal.parameter.LeaderModel;
import aiidc.com.cn.portal.service.LeaderManager;
import aiidc.com.cn.portal.service.PicManager;
import aiidc.com.cn.portal.util.FileUtil;
import aiidc.com.cn.portal.util.Finals;
import aiidc.com.cn.portal.util.JsonTools;
import aiidc.com.cn.portal.util.Pagination;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/company/leader")
public class LeaderController {
	
	@Autowired
	private LeaderManager leaderManager;
	@Autowired
	private PicManager picManager;
	@Value("#{configProperties['upload.Image.path']}")
	private String uploadimagepath;
	
	private String filePath = File.separator+Finals.PIC_TYPE_LEADER+File.separator;
	
	private static final String prefix = "company/leader/";
	
	/**
	 * 前端分页
	 * @param model
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/fr_manage" , method = RequestMethod.POST)
	public void fr_managePost(@ModelAttribute LeaderModel queryModel,
			Model model ,HttpServletRequest request,HttpServletResponse response) throws IOException{
		queryModel.setStatus(Finals.STATUS_YES);
		Pagination pagn = leaderManager.pageQuery(queryModel );
		List<Leader> list = (List<Leader>) pagn.getResult();
		for (Leader leader : list) {
			leader.setPicName(uploadimagepath+filePath+leader.getPicName());
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
	public String manageGet(@ModelAttribute("pageModel") LeaderModel queryModel, Model model ,
			HttpServletRequest request){
		String MSG = request.getParameter("MSG");
		if (StringUtils.hasText(MSG)) {
			JSONObject json = new JSONObject();
			json.put("message","操作成功");
			model.addAttribute("json", json);
		}
		Pagination pagn = leaderManager.pageQuery(queryModel );
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
	public String addGet(@ModelAttribute("pageModel") Leader entity,Model model ,HttpServletRequest request){
		return prefix +"add";
	}
	@RequestMapping(value = "/add" , method = RequestMethod.POST)
	public String addPost(@ModelAttribute("pageModel")@Valid Leader entity,Errors error ,
			Model model ,HttpServletRequest request ,HttpServletResponse response) throws Exception{
		if(error.hasErrors() || entity.getFile().isEmpty()){
			JSONObject json = new JSONObject();
			json.put("message","请检查必填项");
			model.addAttribute("json", json);
			return prefix +"add";

		}else{
			CommonsMultipartFile file = entity.getFile();
			String saveName = FileUtil.getUploadImgName(file);
			
			FileUtil.uploadImageByTransferTo(file, uploadimagepath + filePath,saveName);
			
			Pic pic = new Pic();
			pic.setName(file.getOriginalFilename());
			pic.setRealName(saveName);
			pic.setType(Finals.PIC_TYPE_LEADER);
			picManager.add(pic);
			
			entity.setPicId(pic.getId());
			entity.setPicName(saveName);
			leaderManager.add(entity);
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
		Leader leader = leaderManager.findById(id);
		String pathName =uploadimagepath+filePath+leader.getPicName();
		model.addAttribute("pathName", pathName);
		model.addAttribute("pageModel", leader);
		return prefix +"update";
	}
	@RequestMapping(value = "/update" , method = RequestMethod.POST)
	public String updatePost(@ModelAttribute("pageModel")@Valid Leader entity,Errors error ,
			Model model ,HttpServletRequest request ,HttpServletResponse response){
		if (!entity.getFile().isEmpty()) {
			CommonsMultipartFile file = entity.getFile();
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
		leaderManager.update(entity);
		return "redirect:manage?MSG=1";
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
		leaderManager.deleteById(id);
	}
	
	

}
