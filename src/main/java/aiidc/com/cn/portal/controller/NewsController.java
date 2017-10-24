package aiidc.com.cn.portal.controller;

import aiidc.com.cn.portal.entity.News;
import aiidc.com.cn.portal.entity.Pic;
import aiidc.com.cn.portal.parameter.NewsModel;
import aiidc.com.cn.portal.parameter.PicModel;
import aiidc.com.cn.portal.service.NewsManager;
import aiidc.com.cn.portal.service.PicManager;
import aiidc.com.cn.portal.util.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Zhangx on 2017/8/29 at 15:29.
 */
@Controller
@RequestMapping("company/news")
public class NewsController
{
    @Autowired
    private NewsManager newsManager;
    @Value("#{configProperties['upload.Image.path']}")
    private String uploadimagepath;
    @Autowired
    private PicManager picManager;

    private String filePath = File.separator + Finals.PIC_TYPE_NEWS + File.separator;
    private static final String PREFIX = "company/news";

    /**
     * 前端分页
     *
     * @param response
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/fr_manage", method = RequestMethod.POST)
    public void fr_managePost(@ModelAttribute NewsModel queryModel, HttpServletResponse response) throws IOException
    {
        queryModel.setStatus(Finals.STATUS_YES);
        Pagination pagn = newsManager.pageQuery(queryModel);
        List<News> list = (List<News>) pagn.getResult();
        for (News news : list)
        {
            PicModel picModel = new PicModel();
            picModel.setItemId(news.getId());
            List<Pic> picList = picManager.ListQuery(picModel);
            if (picList.size() > 0)
            {
                Pic pic = picList.get(0);
                news.setSrc(uploadimagepath + filePath + pic.getRealName());
            }
        }
        pagn.setResult(list);
        JsonTools.writeJson(response, pagn);

    }

    /**
     * 前端新闻图片详情
     *
     * @param id
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/fr_pic", method = RequestMethod.POST)
    public void fr_picPost(@RequestParam String id,
                           HttpServletResponse response) throws IOException
    {
        PicModel picModel = new PicModel();
        picModel.setItemId(id);
        List<Pic> picList = picManager.ListQuery(picModel);
        Pic pic = picList.get(0);
        System.out.println(pic.getList());
        String src = uploadimagepath + filePath + pic.getRealName();
        pic.setRealName(src);
        System.out.println(pic);
        JsonTools.writeJson(response, pic);
    }

    /**
     * 前端新闻内容详情
     *
     * @param id
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/fr_detail", method = RequestMethod.POST)
    public void fr_detailPost(@RequestParam String id,
                              HttpServletResponse response) throws IOException
    {
        News news = newsManager.findById(id);
        JsonTools.writeJson(response, news);
    }


    @RequestMapping(value = "manage", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ModelAndView list(NewsModel newsModel)
    {

        if (newsModel.getType() == null) newsModel.setType("1");
        ModelAndView modelAndView = new ModelAndView();
        Pagination pagn = newsManager.pageQuery(newsModel);
        modelAndView.getModel().put("pagn", pagn);
        modelAndView.getModel().put("type", newsModel.getType());
        modelAndView.setViewName(PREFIX + "/list");
        return modelAndView;
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ModelAndView detail(NewsModel newsModel)
    {

        NewsType[] values = NewsType.values();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModel().put("newsTypes", values);

        modelAndView.setViewName(PREFIX + "/detail");
        return modelAndView;
    }

    @RequestMapping(value = "add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String add(@Valid News news, Errors errors, Model model)
    {

        if (errors.hasErrors())
        {
            JSONObject json = new JSONObject();
            json.put("message", "请检查必填项");
            return json.toString();

        } else
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("content_id", news.getId());
            news.setCreateTime(Calendar.getInstance());
            news.setLastModifiedTime(Calendar.getInstance());
            newsManager.add(news);
            String str = jsonObject.toString();
            return str;
        }
    }


    @RequestMapping(value = "addPic", method = RequestMethod.POST)
    @ResponseBody
    public String upload2(@RequestParam String content_id, HttpServletRequest request, HttpServletResponse response) throws Exception
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
                    String id = multiRequest.getParameter("id");
                    pic.setList(id);
                    pic.setName(file.getOriginalFilename());
                    pic.setRealName(uploadImgName);
                    pic.setType(Finals.PIC_TYPE_NEWS);
                    pic.setItemId(content_id);
                    picManager.add(pic);
                }
            }

        }
        return "success";
    }


    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable String id, Model model)
    {
        News news = newsManager.findById(id);
        PicModel picModel = new PicModel();
        picModel.setItemId(news.getId());
        picModel.setType(Finals.PIC_TYPE_NEWS);
        List<Pic> picList = picManager.ListQuery(picModel);
        for (Pic pic : picList)
        {
            pic.setRealName(uploadimagepath + filePath + pic.getRealName());
        }
        model.addAttribute("picList", picList);
        NewsType[] values = NewsType.values();
        model.addAttribute("newsTypes", values);
        model.addAttribute("news", news);
        return PREFIX + "/update";
    }

    @RequestMapping("isTop/{id}")
    public ModelAndView setTop(@PathVariable String id)
    {
        News news = newsManager.findById(id);
        news.setIsTop("1");
        news.setLastModifiedTime(Calendar.getInstance());
        newsManager.update(news);
        NewsModel newsModel = new NewsModel();
        newsModel.setType(news.getType());
        return list(newsModel);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public String update(News news, Errors errors, Model model)
    {
        if (errors.hasErrors())
        {
            JSONObject json = new JSONObject();
            json.put("message", "请检查必填项");
            model.addAttribute("json", json);
            return PREFIX + "add";

        } else
        {
            news.setLastModifiedTime(Calendar.getInstance());
            newsManager.update(news);
            PicModel picModel = new PicModel();
            picModel.setItemId(news.getId());
            List<Pic> picList = picManager.ListQuery(picModel);
            for (Pic pic : picList)
            {
                picManager.deleteById(pic.getId());
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("content_id", news.getId());
            String str = jsonObject.toString();
            return str;
        }
    }

    @RequestMapping(value = "deleteAll", method = RequestMethod.POST)
    public void deleteAll(@RequestParam("ids") String ids, HttpServletResponse httpServletResponse) throws IOException
    {
        String[] split = ids.split(",");
        for (String id : split)
        {
            newsManager.deleteById(id);
        }
        JsonTools.writeJson(httpServletResponse, "批量删除成功");
    }

}
