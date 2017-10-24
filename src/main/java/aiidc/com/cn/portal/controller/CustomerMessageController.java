package aiidc.com.cn.portal.controller;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import aiidc.com.cn.portal.entity.CustomerMessage;
import aiidc.com.cn.portal.parameter.CustomerMessageModel;
import aiidc.com.cn.portal.service.CustomerMessageManager;
import aiidc.com.cn.portal.util.JsonTools;
import aiidc.com.cn.portal.util.Pagination;

@Controller
@RequestMapping("/company/message")
public class CustomerMessageController {
	
	@Autowired
	private CustomerMessageManager customerMessageManager;
	
	
	private static final String prefix = "company/message/";
	
	@RequestMapping(value = "/fr_add" , method = RequestMethod.POST)
	public void fr_addPost(CustomerMessage entity,Errors error ,
			Model model ,HttpServletRequest request ,HttpServletResponse response) throws IOException{
		customerMessageManager.add(entity);
		JsonTools.writeJson(response, "感谢你的留言");
	}
	/**
	 * 前端分页
	 * @param model
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/fr_manage" , method = RequestMethod.POST)
//	@ResponseBody
	public void fr_managePost(@ModelAttribute CustomerMessageModel queryModel,
			Model model ,HttpServletRequest request,HttpServletResponse response) throws IOException{
		queryModel.setIsVisible("1");
		Pagination pagn = customerMessageManager.pageQuery(queryModel );
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
	public String manageGet(@ModelAttribute("pageModel") CustomerMessageModel queryModel,
			Model model ,HttpServletRequest request){
		String MSG = request.getParameter("MSG");
		if (StringUtils.hasText(MSG)) {
			JSONObject json = new JSONObject();
			json.put("message","操作成功");
			model.addAttribute("json", json);
		}
		queryModel.setStatus("0");
		Pagination pagn = customerMessageManager.pageQuery(queryModel );
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
	public String addGet(@ModelAttribute("pageModel") CustomerMessage entity,Model model ,HttpServletRequest request){
		return prefix +"add";
	}
	@RequestMapping(value = "/add" , method = RequestMethod.POST)
	public String addPost(@ModelAttribute("pageModel")@Valid CustomerMessage entity,Errors error ,
			Model model ,HttpServletRequest request ,HttpServletResponse response){
		if(error.hasErrors()){
			JSONObject json = new JSONObject();
			json.put("message","请检查必填项");
			model.addAttribute("json", json);
			return prefix +"add";

		}else{
			customerMessageManager.add(entity);
			return "redirect:manage?MSG=1";
		}
	}
	
	/**
	 * 回复
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/update/{id}" , method = RequestMethod.GET)
	public String updateGet(@PathVariable("id") String id,Model model ,HttpServletRequest request){
		CustomerMessage CustomerMessage = customerMessageManager.findById(id);
		model.addAttribute("pageModel", CustomerMessage);
		return prefix +"update";
	}
	@RequestMapping(value = "/update" , method = RequestMethod.POST)
	public String updatePost(@ModelAttribute("pageModel")@Valid CustomerMessage entity,Errors error ,
			Model model ,HttpServletRequest request ,HttpServletResponse response){
		if(error.hasErrors()){
			JSONObject json = new JSONObject();
			json.put("message","请检查必填项");
			model.addAttribute("json", json);
			return prefix +"update";
		}else{
			//回复留言，默认修改为可见/显示
			CustomerMessage message = customerMessageManager.findById(entity.getId());
			message.setReply(entity.getReply());
			message.setReplyTime(Calendar.getInstance());
			message.setIsVisible("1");
			message.setStatus("1");
			customerMessageManager.update(message);
			return "redirect:manage?MSG=1";
		}
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
		customerMessageManager.deleteById(id);
	}
	
	/**
	 * 显示/隐藏
	 * @param id
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/updStatus/{id}" , method = RequestMethod.POST)
	public void updStatus(@PathVariable("id") String id ,Model model ,
			HttpServletRequest request ,HttpServletResponse response){
		CustomerMessage message = customerMessageManager.findById(id);
		String isVisible = request.getParameter("isVisible");
		if (isVisible.equals("0")) {
			message.setIsVisible("1");
		}else{
			message.setIsVisible("0");
		}
		customerMessageManager.update(message);
	}
	

}
