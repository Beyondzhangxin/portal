package aiidc.com.cn.portal.controller;

import aiidc.com.cn.portal.entity.Overview;
import aiidc.com.cn.portal.entity.Pic;
import aiidc.com.cn.portal.entity.Solution;
import aiidc.com.cn.portal.parameter.OverviewModel;
import aiidc.com.cn.portal.parameter.PicModel;
import aiidc.com.cn.portal.parameter.SolutionModel;
import aiidc.com.cn.portal.service.OverviewManager;
import aiidc.com.cn.portal.service.PicManager;
import aiidc.com.cn.portal.service.SolutionManager;
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
 * Created by Zhangx on 2017/9/5 at 9:34.
 */
@Controller
@RequestMapping("company/solution")
public class SolutionController
{
    @Autowired
    private SolutionManager solutionManager;
    @Value("#{configProperties['upload.Image.path']}")
    private String uploadimagepath;
    @Autowired
    private PicManager picManager;
    @Autowired
    private OverviewManager overviewManager;

    private String filePath = File.separator + Finals.PIC_TYPE_SOLUTION + File.separator;

    private static final String PREFIX = "company/solution/";

    /**
     * 前端分页
     *
     * @param response
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/fr_manage", method = RequestMethod.POST)
    public void fr_managePost(@ModelAttribute SolutionModel queryModel,
                              HttpServletResponse response) throws IOException
    {
        queryModel.setStatus(Finals.STATUS_YES);
        Pagination pagn = solutionManager.pageQuery(queryModel);
        List<Solution> list = (List<Solution>) pagn.getResult();
        for (Solution solution : list)
        {
            PicModel picModel = new PicModel();
            picModel.setItemId(solution.getId());
            List<Pic> picList = picManager.ListQuery(picModel);
            if (picList.size() > 0)
            {
                Pic pic = picList.get(0);
                solution.setSrc(uploadimagepath + filePath + pic.getRealName());
            }
        }
        pagn.setResult(list);
        System.err.println(pagn);
        JsonTools.writeJson(response, pagn);

    }

    /**
     * 前端解决方案图片详情
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
        JsonTools.writeJson(response, picList);
    }

    /**
     * 前端解决方案内容详情
     *
     * @param id
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/fr_detail", method = RequestMethod.POST)
    public void fr_detailPost(@RequestParam String id,
                              HttpServletResponse response) throws IOException
    {
        Solution solution = solutionManager.findById(id);
        JsonTools.writeJson(response, solution);
    }

    @RequestMapping(value = "manage", method = RequestMethod.GET)
    public ModelAndView list(SolutionModel solutionModel)
    {
        if (solutionModel.getType() == null) solutionModel.setType(Finals.SOLUTION_TYPE_GEN);
        ModelAndView modelAndView = new ModelAndView();
        Pagination pagn = solutionManager.pageQuery(solutionModel);
        modelAndView.getModel().put("pagn", pagn);
        modelAndView.getModel().put("type", solutionModel.getType());
        modelAndView.setViewName(PREFIX + "/list");
        return modelAndView;
    }

    @RequestMapping(value = "detail", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ModelAndView detail(String id)
    {
        ModelAndView modelAndView = new ModelAndView();
        if (id != null)
        {
            Solution product = solutionManager.findById(id);
            modelAndView.getModel().put("solution", product);
        }
        SolutionType[] values = SolutionType.values();
        modelAndView.addObject("solutionTypes", values);
        modelAndView.setViewName(PREFIX + "/detail");
        return modelAndView;
    }

    @RequestMapping(value = "add", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String detail(@ModelAttribute("pageModel") Solution product, Model model)
    {
        SolutionType[] values = SolutionType.values();
        model.addAttribute("solutionTypes", values);
        return PREFIX + "/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String save(@Valid Solution product, Errors errors, Model model)
    {
        if (errors.hasErrors())
        {
            JSONObject json = new JSONObject();
            json.put("message", "请检查必填项");
            return json.toString();

        } else
        {
            if (product.getType() == null) product.setType(Finals.SOLUTION_TYPE_GEN);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("content_id", product.getId());
            product.setCreateTime(Calendar.getInstance());
            solutionManager.add(product);
            String str = jsonObject.toString();
            return str;
        }
    }

    @RequestMapping("isTop/{id}")
    public ModelAndView setTop(@PathVariable String id)
    {
        Solution solution = solutionManager.findById(id);
        solution.setIsTop("1");
        solution.setLastModifiedTime(Calendar.getInstance());
        solutionManager.update(solution);
        SolutionModel solutionModel = new SolutionModel();
        solutionModel.setType(solution.getType());
        return list(solutionModel);
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
                    pic.setName(file.getOriginalFilename());
                    pic.setRealName(uploadImgName);
                    pic.setType(Finals.PIC_TYPE_SOLUTION);
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
        Solution solution = solutionManager.findById(id);
        PicModel picModel = new PicModel();
        picModel.setItemId(solution.getId());
        picModel.setType(Finals.PIC_TYPE_SOLUTION);
        List<Pic> picList = picManager.ListQuery(picModel);
        for (Pic pic : picList)
        {
            pic.setRealName(uploadimagepath + filePath + pic.getRealName());
        }
        SolutionType[] values = SolutionType.values();
        model.addAttribute("solutionTypes", values);
        model.addAttribute("picList", picList);
        model.addAttribute("solution", solution);
        model.addAttribute("picList", picList);
        return PREFIX + "/detail";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public String update(Solution solution, Errors errors, Model model)
    {
        if (errors.hasErrors())
        {
            JSONObject json = new JSONObject();
            json.put("message", "请检查必填项");
            model.addAttribute("json", json);
            return PREFIX + "add";

        } else
        {
            solutionManager.update(solution);
            PicModel picModel = new PicModel();
            picModel.setItemId(solution.getId());
            List<Pic> picList = picManager.ListQuery(picModel);
            for (Pic pic : picList)
            {
                picManager.deleteById(pic.getId());
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("content_id", solution.getId());
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
            solutionManager.deleteById(id);
        }
        JsonTools.writeJson(httpServletResponse, "批量删除成功");
    }

    @RequestMapping(value = "overview", method = RequestMethod.GET)
    public String overview(Model model)
    {
        OverviewModel overviewModel = new OverviewModel();
        overviewModel.setType(Finals.OVERVIEW_TYPE_SOLUTION);
        List<Overview> list = overviewManager.ListQuery(overviewModel);
        if (list.size() > 0)
        {
            Overview overview = list.get(0);
            model.addAttribute("pageModel", overview);
        } else
        {
            Overview overview = new Overview();
            model.addAttribute("pageModel", overview);
        }
        return PREFIX + "overview";
    }

    @RequestMapping(value = "overview", method = RequestMethod.POST)
    public String updateOverview(@ModelAttribute("pageModel") @Valid Overview entity, Errors error,
                                 Model model)
    {
        if (error.hasErrors())
        {
            JSONObject json = new JSONObject();
            json.put("message", "请检查必填项");
            model.addAttribute("json", json);
            return PREFIX + "overview";
        } else
        {
            entity.setType(Finals.OVERVIEW_TYPE_SOLUTION);
            if (overviewManager.findById(entity.getId()) != null)
            {
                overviewManager.update(entity);
            } else
            {
                overviewManager.add(entity);
            }
            JSONObject json = new JSONObject();
            json.put("message", "操作成功！");
            model.addAttribute("json", json);
            return PREFIX + "overview";
        }


    }

}
