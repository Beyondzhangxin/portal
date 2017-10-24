package aiidc.com.cn.portal.controller;

import aiidc.com.cn.portal.entity.News;
import aiidc.com.cn.portal.entity.Pic;
import aiidc.com.cn.portal.parameter.NewsModel;
import aiidc.com.cn.portal.parameter.PicModel;
import aiidc.com.cn.portal.service.NewsManager;
import aiidc.com.cn.portal.service.PicManager;
import aiidc.com.cn.portal.util.Finals;
import aiidc.com.cn.portal.util.JsonTools;
import aiidc.com.cn.portal.util.Pagination;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Zhangx on 2017/9/13 at 14:50.
 */
@Controller
@RequestMapping("/company/news/audit")
public class NewsAuditController
{
    @Autowired
    private NewsManager newsManager;
    @Autowired
    private PicManager picManager;
    @Value("#{configProperties['upload.Image.path']}")
    private String uploadimagepath;

    private String filePath = File.separator + Finals.PIC_TYPE_NEWS + File.separator;

    private static final String prefix = "audit/news/";

    /**
     * 分页
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public String manageGet(@ModelAttribute("pageModel") NewsModel queryModel, Model model,
                            HttpServletRequest request)
    {
    	if (queryModel.getType() == null) queryModel.setType("1");
        String MSG = request.getParameter("MSG");
        if (StringUtils.hasText(MSG))
        {
            JSONObject json = new JSONObject();
            json.put("message", "操作成功");
            model.addAttribute("json", json);
        }
        queryModel.setStatus(Finals.STATUS_DEFAULT);
        Pagination pagn = newsManager.pageQuery(queryModel);
        model.addAttribute("pagn", pagn);
        model.addAttribute("type", queryModel.getType());
        return prefix + "manage";
    }


    /**
     * 审核
     *
     * @param id
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateGet(@PathVariable("id") String id, Model model, HttpServletRequest request)
    {
        News news = newsManager.findById(id);
        PicModel picModel = new PicModel();
        picModel.setItemId(news.getId());
        picModel.setType(Finals.PIC_TYPE_NEWS);
        List<Pic> picList = picManager.ListQuery(picModel);
        String rootPath= uploadimagepath+filePath;
        model.addAttribute("pageModel", news);
        model.addAttribute("picList", picList);
        model.addAttribute("rootPath", rootPath);
        return prefix + "update";
    }

    @RequestMapping(value = "/updStatus/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void updStatus(@PathVariable("id") String id, Model model,
                          HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        News news = newsManager.findById(id);
        String status = request.getParameter("status");
        news.setStatus(status);
        newsManager.update(news);
        JsonTools.writeJson(response, "审核成功");
    }

    @RequestMapping(value = "/updStatusAll", method = RequestMethod.POST)
    @ResponseBody
    public void updStatusAll(@RequestParam("ids") String ids, Model model,
                             HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String[] split = ids.split(",");
        for (String id : split)
        {
            News news = newsManager.findById(id);
            news.setStatus(Finals.STATUS_YES);
            newsManager.update(news);
        }
        JsonTools.writeJson(response, "批量审核成功");
    }

}
