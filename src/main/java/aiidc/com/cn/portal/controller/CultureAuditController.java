package aiidc.com.cn.portal.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import aiidc.com.cn.portal.entity.Culture;
import aiidc.com.cn.portal.parameter.CultureModel;
import aiidc.com.cn.portal.service.CultureManager;
import aiidc.com.cn.portal.util.Finals;
import aiidc.com.cn.portal.util.JsonTools;
import aiidc.com.cn.portal.util.Pagination;

@Controller
@RequestMapping("/company/culture/audit")
public class CultureAuditController {
	
	@Autowired
	private CultureManager cultureManager;
	@Value("#{configProperties['upload.Image.path']}")
	private String uploadimagepath;

	private String filePath = File.separator + Finals.PIC_TYPE_CULTURE + File.separator;
	private static final String prefix = "audit/culture/";
	/**
	 * 分页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/manage" , method = RequestMethod.GET)
	public String manageGet(@ModelAttribute("pageModel") CultureModel queryModel, Model model ,
			HttpServletRequest request){
		String MSG = request.getParameter("MSG");
		if (StringUtils.hasText(MSG)) {
			JSONObject json = new JSONObject();
			json.put("message","操作成功");
			model.addAttribute("json", json);
		}
		queryModel.setStatus(Finals.STATUS_DEFAULT);
		queryModel.setType(Finals.COMPANY_TYPE_CULTURE);
		Pagination pagn = cultureManager.pageQuery(queryModel );
		model.addAttribute("pagn", pagn);
		return prefix +"manage";
	}
	
	
	/**
	 * 审核
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
		model.addAttribute("pageModel",culture);
		return prefix +"update";
	}
	@RequestMapping(value = "/updStatus/{id}" , method = RequestMethod.POST)
	@ResponseBody
	public void updStatus(@PathVariable("id") String id ,Model model ,
			HttpServletRequest request ,HttpServletResponse response) throws IOException{
		Culture culture = cultureManager.findById(id);
		String status = request.getParameter("status");
		culture.setStatus(status);
		cultureManager.update(culture);
		JsonTools.writeJson(response, "审核成功");
	}
	@RequestMapping(value = "/updStatusAll" , method = RequestMethod.POST)
	@ResponseBody
	public void updStatusAll(@RequestParam("ids") String ids ,Model model ,
			HttpServletRequest request ,HttpServletResponse response) throws IOException{
		String[] split = ids.split(",");
		for (String id : split) {
			Culture culture = cultureManager.findById(id);
			culture.setStatus(Finals.STATUS_YES);
			cultureManager.update(culture);
		}
		JsonTools.writeJson(response, "批量审核成功");
	}
	

}
