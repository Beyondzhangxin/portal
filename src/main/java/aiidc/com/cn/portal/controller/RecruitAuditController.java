package aiidc.com.cn.portal.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import aiidc.com.cn.portal.entity.Recruitment;
import aiidc.com.cn.portal.parameter.RecruitmentModel;
import aiidc.com.cn.portal.service.RecruitmentManager;
import aiidc.com.cn.portal.util.Finals;
import aiidc.com.cn.portal.util.JsonTools;
import aiidc.com.cn.portal.util.Pagination;

/**
 * Created by fancw on 2017/9/11 at 10:40.
 */
@Controller
@RequestMapping("company/recruit/audit")
public class RecruitAuditController
{
    @Autowired
    private RecruitmentManager recruitmentManager;
    private static final String PREFIX = "audit/recruit";
    
    
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
        recruitmentModel.setStatus(Finals.STATUS_DEFAULT);
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
     * 审核
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
    @RequestMapping(value = "/updStatus/{id}" , method = RequestMethod.POST)
	@ResponseBody
	public void updStatus(@PathVariable("id") String id ,Model model ,
			HttpServletRequest request ,HttpServletResponse response) throws IOException{
    	Recruitment recruitment = recruitmentManager.findById(id);
		String status = request.getParameter("status");
		recruitment.setStatus(status);
		recruitmentManager.update(recruitment);
		JsonTools.writeJson(response, "审核成功");
	}

    /**
     * 一键通过
     * @param ids
     * @param model
     * @param request
     * @param response
     * @throws IOException
     */
	@RequestMapping(value = "/updStatusAll" , method = RequestMethod.POST)
	@ResponseBody
	public void updStatusAll(@RequestParam("ids") String ids ,Model model ,
			HttpServletRequest request ,HttpServletResponse response) throws IOException{
		String[] split = ids.split(",");
		for (String id : split) {
			Recruitment recruitment = recruitmentManager.findById(id);
			recruitment.setStatus(Finals.STATUS_YES);
			recruitmentManager.update(recruitment);
		}
		JsonTools.writeJson(response, "批量审核成功");
	}
    
}
