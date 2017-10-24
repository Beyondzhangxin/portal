package aiidc.com.cn.portal.controller;

import aiidc.com.cn.portal.entity.AclResource;
import aiidc.com.cn.portal.entity.AclUser;
import aiidc.com.cn.portal.entity.Company;
import aiidc.com.cn.portal.parameter.AclUserModel;
import aiidc.com.cn.portal.parameter.LeaderModel;
import aiidc.com.cn.portal.service.AclResourceManager;
import aiidc.com.cn.portal.service.AclUserManager;
import aiidc.com.cn.portal.service.CompanyManager;
import aiidc.com.cn.portal.util.Finals;
import aiidc.com.cn.portal.util.JsonTools;
import aiidc.com.cn.portal.util.LoginHelper;
import aiidc.com.cn.portal.util.MD5Util;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/common")
public class LoginController
{

    @Autowired
    private AclUserManager aclUserManager;
    @Autowired
    private AclResourceManager AclResourceManager;
    @Autowired
    private CompanyManager companyManager;
    @Value("#{configProperties['upload.Image.path']}")
    private String uploadimagepath;

    private String filePath = File.separator + Finals.PIC_TYPE_PERSON + File.separator;

    /**
     * 前端公司简介
     *
     * @param model
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/fr_manage", method = RequestMethod.POST)
    public void fr_managePost(@ModelAttribute LeaderModel queryModel,
                              Model model, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        List<Company> all = companyManager.getAll();
        if (all != null && all.size() > 0)
        {
            System.err.println(all.get(0));
            JsonTools.writeJson(response, all.get(0));
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet(@ModelAttribute("pageModel") AclUser entity, Model model,
                           HttpServletRequest request)
    {
        WebUtils.setSessionAttribute(request, "loginUser", null);
        return "login";
    }

    @RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String LoginPost(@RequestParam("loginName") String loginName,
                            @RequestParam("passwd") String password, HttpServletRequest request)
    {
        JSONObject json = new JSONObject();
        if (StringUtils.hasText(loginName) && StringUtils.hasText(password))
        {
            AclUserModel queryModel = new AclUserModel();
            queryModel.setLoginName(loginName);
            queryModel.setPassword(MD5Util.md5(password, false));
            List<AclUser> list = aclUserManager.ListQuery(queryModel);

            if (list.isEmpty() || list.size() != 1)
            {
                json.put("success", false);
                json.put("message", "用户名或密码输入不正确。");
            } else
            {
                LoginHelper.setSession(request, list.get(0));
                json.put("success", true);
            }
            return json.toString();
        } else
        {
            json.put("success", false);
            json.put("message", "必填项不能为空");
            return json.toString();
        }
    }


    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(Model model, HttpServletRequest request, HttpServletResponse response)
    {
        return "main";
    }

    @RequestMapping(value = "/company", method = RequestMethod.GET)
    public String companyGet(@ModelAttribute("pageModel") Company entity, Model model,
                             HttpServletRequest request, HttpServletResponse response)
    {
        List<Company> all = companyManager.getAll();
        if (all != null && all.size() > 0)
        {
            model.addAttribute("pageModel", all.get(0));
        }
        return "company";
    }

    @RequestMapping(value = "/company", method = RequestMethod.POST)
    public String companyPost(@ModelAttribute("pageModel") @Valid Company entity, Errors error, Model model,
                              HttpServletRequest request, HttpServletResponse response)
    {
        if (error.hasErrors())
        {
            JSONObject json = new JSONObject();
            json.put("message", "请检查必填项");
            model.addAttribute("json", json);
            return "company";
        } else
        {
            JSONObject json = new JSONObject();
            json.put("message", "提交成功");
            model.addAttribute("json", json);
            List<Company> all = companyManager.getAll();
            if (all == null || all.size() < 1)
            {
                companyManager.add(entity);
            } else
            {
                companyManager.update(entity);
            }
            return "company";
        }
    }


    @RequestMapping(value = "/menuResource", method = RequestMethod.GET)
    public String menuResource(Model model, HttpServletRequest request, HttpServletResponse response)
    {
        AclUser loginUser = LoginHelper.getLoginUser(request);
        if (loginUser != null)
        {
            List<AclResource> AclResource = AclResourceManager.findAllResource(loginUser);
            model.addAttribute("AclResource", AclResource);
            model.addAttribute("loginUser", loginUser);
            model.addAttribute("picUrl", uploadimagepath + filePath + loginUser.getPicName());
        }
        return "menuResource";
    }

}
