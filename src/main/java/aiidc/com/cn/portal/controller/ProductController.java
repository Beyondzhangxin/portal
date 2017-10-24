package aiidc.com.cn.portal.controller;

import aiidc.com.cn.portal.entity.AclUser;
import aiidc.com.cn.portal.entity.Overview;
import aiidc.com.cn.portal.entity.Pic;
import aiidc.com.cn.portal.entity.Product;
import aiidc.com.cn.portal.parameter.OverviewModel;
import aiidc.com.cn.portal.parameter.ProductModel;
import aiidc.com.cn.portal.service.OverviewManager;
import aiidc.com.cn.portal.service.PicManager;
import aiidc.com.cn.portal.service.ProductManager;
import aiidc.com.cn.portal.util.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/company/product")
public class ProductController
{

    @Autowired
    private ProductManager productManager;
    @Autowired
    private PicManager picManager;
    @Autowired
    private OverviewManager overviewManager;
    @Value("#{configProperties['upload.Image.path']}")
    private String uploadimagepath;

    private String filePath = File.separator + Finals.PIC_TYPE_PRODUCTS + File.separator;
    private static final String prefix = "company/product/";


    /**
     * 前端详情
     *
     * @param id
     * @param model
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/fr_detail", method = RequestMethod.POST)
    public void fr_detailPost(@RequestParam String id,
                              Model model, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Product product = productManager.findById(id);
        JsonTools.writeJson(response, product);
    }

    /**
     * 前端分页
     *
     * @param model
     * @param request
     * @param response
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/fr_manage", method = RequestMethod.POST)
    public void fr_managePost(@ModelAttribute ProductModel queryModel,
                              Model model, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        if (queryModel.getSubTypeStr().length() > 0)
        {
            ProductSubType d_dsj = ProductSubType.valueOf(queryModel.getSubTypeStr());
            queryModel.setSubType(d_dsj);
        }
        Pagination pagn = productManager.pageQuery(queryModel);
        List<Product> list = (List<Product>) pagn.getResult();
        for (Product product : list)
        {
            product.setSubTypeString(product.getSubType().getValue());
            product.setPicName(uploadimagepath + filePath + product.getPicName());
        }
        pagn.setResult(list);
        JsonTools.writeJson(response, pagn);
    }

    @RequestMapping(value = "fr_overview", method = RequestMethod.GET)
    public void fr_overview(HttpServletResponse httpServletResponse) throws IOException
    {
        OverviewModel overviewModel = new OverviewModel();
        overviewModel.setType(Finals.OVERVIEW_TYPE_PRODUCT);
        List<Overview> overviews = overviewManager.ListQuery(overviewModel);
        JsonTools.writeJson(httpServletResponse, overviews.get(0));
    }

    /**
     * 分页
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public String manageGet(@ModelAttribute("pageModel") ProductModel queryModel, Model model, HttpServletRequest request)
    {
        String MSG = request.getParameter("MSG");
        if (StringUtils.hasText(MSG))
        {
            JSONObject json = new JSONObject();
            json.put("message", "操作成功");
            model.addAttribute("json", json);
        }
        Pagination pagn = productManager.pageQuery(queryModel);
        model.addAttribute("pagn", pagn);
        return prefix + "manage";
    }

    /**
     * 添加
     *
     * @param entity
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addGet(@ModelAttribute("pageModel") Product entity, Model model, HttpServletRequest request)
    {
        model.addAttribute("types", Finals.PRODUCT_TYPE);
        model.addAttribute("subTypes", ProductSubType.values());
        return prefix + "add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPost(@ModelAttribute("pageModel") @Valid Product entity, Errors error,
                          Model model, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        if (error.hasErrors() || entity.getFile().isEmpty())
        {
            JSONObject json = new JSONObject();
            json.put("message", "请检查必填项");
            model.addAttribute("json", json);
            model.addAttribute("types", Finals.PRODUCT_TYPE);
            model.addAttribute("subTypes", ProductSubType.values());
            return prefix + "add";

        } else
        {
            MultipartFile file = entity.getFile();
            String saveName = FileUtil.getUploadImgName(file);

            FileUtil.uploadImageByTransferTo(file, uploadimagepath + filePath, saveName);

            Pic pic = new Pic();
            pic.setName(file.getOriginalFilename());
            pic.setRealName(saveName);
            pic.setType(Finals.PIC_TYPE_PRODUCTS);
            picManager.add(pic);

            entity.setPicId(pic.getId());
            entity.setPicName(saveName);
            AclUser loginUser = LoginHelper.getLoginUser(request);
            entity.setCreator(loginUser.getId());
            productManager.add(entity);
            return "redirect:manage?MSG=1";
        }
    }

    /**
     * 修改
     *
     * @param id
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateGet(@PathVariable("id") String id, Model model, HttpServletRequest request)
    {
        Product Product = productManager.findById(id);
        String pathName = uploadimagepath + filePath + Product.getPicName();
        model.addAttribute("pathName", pathName);
        model.addAttribute("pageModel", Product);
        model.addAttribute("types", Finals.PRODUCT_TYPE);
        model.addAttribute("subTypes", ProductSubType.values());
        return prefix + "update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updatePost(@ModelAttribute("pageModel") @Valid Product entity, Errors error,
                             Model model, HttpServletRequest request, HttpServletResponse response)
    {
        if (!entity.getFile().isEmpty())
        {
            MultipartFile file = entity.getFile();
            String saveName = FileUtil.getUploadImgName(file);
            try
            {
                //新图上传
                FileUtil.uploadImageByTransferTo(file, uploadimagepath + filePath, saveName);
                //旧图删除
                FileUtil.deleteFile(uploadimagepath + filePath, entity.getPicName());
            } catch (Exception e)
            {
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
        productManager.update(entity);
        return "redirect:manage?MSG=1";
    }


    /**
     * 删除
     *
     * @param id
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public void delete(@PathVariable("id") String id, Model model,
                       HttpServletRequest request, HttpServletResponse response)
    {
        //旧图删除
        Product Product = productManager.findById(id);
        FileUtil.deleteFile(uploadimagepath + filePath, Product.getPicName());
        picManager.deleteById(Product.getPicId());
        productManager.deleteById(id);
    }

    @RequestMapping(value = "overview", method = RequestMethod.GET)
    public String overview(Model model)
    {
        OverviewModel overviewModel = new OverviewModel();
        overviewModel.setType(Finals.OVERVIEW_TYPE_PRODUCT);
        List<Overview> list = overviewManager.ListQuery(overviewModel);
        if (list.size() > 0)
        {
            Overview overview = list.get(0);
            model.addAttribute("pageModel", overview);
        }else {
            Overview overview = new Overview();
            model.addAttribute("pageModel", overview);
        }
        return prefix + "overview";
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
            return prefix + "overview";
        } else
        {
            entity.setType(Finals.OVERVIEW_TYPE_PRODUCT);
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
            return prefix + "overview";
        }


    }

}
