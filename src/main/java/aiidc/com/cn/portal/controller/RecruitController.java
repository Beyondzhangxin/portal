package aiidc.com.cn.portal.controller;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import org.springframework.web.servlet.ModelAndView;

import aiidc.com.cn.portal.entity.AclUser;
import aiidc.com.cn.portal.entity.Recruitment;
import aiidc.com.cn.portal.parameter.RecruitmentModel;
import aiidc.com.cn.portal.service.RecruitmentManager;
import aiidc.com.cn.portal.util.Finals;
import aiidc.com.cn.portal.util.JsonTools;
import aiidc.com.cn.portal.util.LoginHelper;
import aiidc.com.cn.portal.util.Pagination;

/**
 * Created by Zhangx on 2017/9/4 at 10:40.
 */
@Controller
@RequestMapping("company/recruit")
public class RecruitController
{
    @Autowired
    private RecruitmentManager recruitmentManager;
    private static final String PREFIX = "company/recruit";
    
    /**
     * 前端招聘详情
     * @param id
     * @param model
     * @param request
     * @param response
     * @throws IOException
     */
	@RequestMapping(value = "/fr_detail" , method = RequestMethod.POST)
	public void fr_detailPost(@RequestParam String id,
			Model model ,HttpServletRequest request,HttpServletResponse response) throws IOException{
		Recruitment recruitment = recruitmentManager.findById(id);
		System.err.println(recruitment);
		JsonTools.writeJson(response, recruitment);
	}
	/**
	 * 前端分页
	 * @param model
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/fr_manage" , method = RequestMethod.POST)
	public void fr_managePost(@ModelAttribute RecruitmentModel queryModel,
			Model model ,HttpServletRequest request,HttpServletResponse response) throws IOException{
		queryModel.setStatus(Finals.STATUS_YES);
		Pagination pagn = recruitmentManager.pageQuery(queryModel );
		System.err.println(pagn);
		JsonTools.writeJson(response, pagn);
	}
    
    /**
     * 分页
     * @param recruitmentModel
     * @return
     */
    @RequestMapping(value = "manage",method = RequestMethod.GET)
    public ModelAndView list(RecruitmentModel recruitmentModel,HttpServletRequest request)
    {
    	String MSG = request.getParameter("MSG");
        ModelAndView modelAndView = new ModelAndView();
        Pagination pagn = recruitmentManager.pageQuery(recruitmentModel);
        if (StringUtils.hasText(MSG)) {
        	JSONObject json = new JSONObject();
        	json.put("message","操作成功");
        	modelAndView.getModel().put("json", json);
        }
        modelAndView.getModel().put("pagn", pagn);
        modelAndView.setViewName(PREFIX+"/list");
        return modelAndView;
    }
    /**
     * 修改
     * @param id
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "detail/{id}",method =RequestMethod.GET )
    public ModelAndView detail(@PathVariable("id") String id,HttpServletRequest request ,
    		HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        if (id!=null){
            Recruitment recruitment = recruitmentManager.findById(id);
            modelAndView.getModel().put("pageModel", recruitment);
        }
        modelAndView.setViewName(PREFIX+"/detail");
        return modelAndView;
    }
    @RequestMapping(value = "/update" , method = RequestMethod.POST)
	public String updatePost(@ModelAttribute("pageModel") Recruitment entity,Errors error ,
			Model model ,HttpServletRequest request ,HttpServletResponse response){
    	entity.setCreateTime(Calendar.getInstance());
		entity.setStatus(Finals.STATUS_DEFAULT);
		recruitmentManager.update(entity);
		return "redirect:manage?MSG=1";
    }

    @RequestMapping(value = "add",method =RequestMethod.GET , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String detail(@ModelAttribute("pageModel") Recruitment recruitment,Model model)
    {
        return PREFIX + "/add";
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String save(@ModelAttribute("pageModel")@Valid Recruitment recruitment, Errors errors,
    		Model model ,HttpServletRequest request){
        if (errors.hasErrors())
        {
            JSONObject json = new JSONObject();
            json.put("message", "请检查必填项");
            model.addAttribute("json", json);
            return PREFIX + "/add";
        } else
        {
			AclUser loginUser = LoginHelper.getLoginUser(request);
			recruitment.setCreator(loginUser);
            recruitmentManager.add(recruitment);
//            JSONObject json = new JSONObject();
//            json.put("message", "添加成功");
//            model.addAttribute("json", json);
//            return PREFIX+"/manage";
            return "redirect:manage?MSG=1";
        }
    }
    
    /**
     * 批量删除
     * @param ids
     * @param model
     * @param request
     * @param response
     * @throws IOException
     */
	@RequestMapping(value = "/deleteAll" , method = RequestMethod.POST)
	@ResponseBody
	public void deleteAll(@RequestParam("ids") String ids ,Model model ,
			HttpServletRequest request ,HttpServletResponse response) throws IOException{
		String[] split = ids.split(",");
		for (String id : split) {
			recruitmentManager.deleteById(id);
		}
		JsonTools.writeJson(response, "删除成功");
	}
	/**
	 * 置顶
	 * @param id
	 * @param model
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/isTop/{id}" , method = RequestMethod.POST)
	@ResponseBody
	public void isTop(@PathVariable("id") String id ,Model model ,
			HttpServletRequest request ,HttpServletResponse response) throws IOException{
		Recruitment recruitment = recruitmentManager.findById(id);
		String isTop = request.getParameter("isTop");
		recruitment.setIsTop(isTop);
		recruitmentManager.update(recruitment);
		if (isTop.equals("1")) {
			JsonTools.writeJson(response, "置顶成功");
		}else{
			JsonTools.writeJson(response, "取消成功");
		}
	}
    
}
