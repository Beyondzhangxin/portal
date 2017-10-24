package aiidc.com.cn.portal.controller;

import aiidc.com.cn.portal.entity.Company;
import aiidc.com.cn.portal.entity.Pic;
import aiidc.com.cn.portal.service.CompanyManager;
import aiidc.com.cn.portal.service.PicManager;
import aiidc.com.cn.portal.util.FileUtil;
import aiidc.com.cn.portal.util.Finals;
import aiidc.com.cn.portal.util.JsonTools;
import aiidc.com.cn.portal.util.PathUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/company/organize")
public class CompanyController
{

    @Autowired
    private PicManager picManager;
    @Autowired
    private CompanyManager companyManager;

    @Value("#{configProperties['upload.Image.path']}")
    private String uploadimagepath;

    private static final String prefix = "company/organize/";
    private String filePath = File.separator + Finals.PIC_TYPE_ORGANIZE + File.separator;

    /**
     * 前端展示
     *
     * @param model
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/fr_manage", method = RequestMethod.POST)
    public void fr_managePost(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Pic pic = picManager.findByProperty("type", Finals.PIC_TYPE_ORGANIZE);
        if (pic != null)
        {
            String pathname = uploadimagepath + filePath + pic.getRealName();
            JsonTools.writeJson(response, pathname);
        }

    }

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public String organizeGet(Model model, HttpServletRequest request)
    {
        Pic pic = picManager.findByProperty("type", Finals.PIC_TYPE_ORGANIZE);
//		System.err.println(PathUtil.getRootPath());
        if (pic != null)
        {
//			System.err.println(filePath);
            String pathname = uploadimagepath + filePath + pic.getRealName();
            model.addAttribute("pathname", pathname);
        }
        return prefix + "manage";
    }

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public String uploadImage(@RequestParam(name = "file") CommonsMultipartFile file, Model model)
            throws Exception
    {
        JSONObject json = new JSONObject();
        Pic pic = picManager.findByProperty("type", Finals.PIC_TYPE_ORGANIZE);
        if (file.isEmpty())
        {
            json.put("message", "上传文件为空。");
            model.addAttribute("msgJson", json);
            if (pic != null)
            {
                String pathname = uploadimagepath + filePath + pic.getRealName();
                model.addAttribute("pathname", pathname);
            }
        } else
        {
            String saveName = FileUtil.getUploadImgName(file);
            FileUtil.uploadImageByTransferTo(file, uploadimagepath + filePath, saveName);

            if (pic == null)
            {
                pic = new Pic();
                pic.setName(file.getOriginalFilename());
                pic.setRealName(saveName);
                pic.setType(Finals.PIC_TYPE_ORGANIZE);
                picManager.add(pic);
            } else
            {
                String oldName = pic.getRealName();
                pic.setName(file.getOriginalFilename());
                pic.setRealName(saveName);
                picManager.update(pic);
                json.put("message", "上传成功");
                model.addAttribute("msgJson", json);
                //删除旧图片
                String path = PathUtil.getRootPath() + File.separator + uploadimagepath + filePath + oldName;
                FileUtil.deleteFile(path);
                String pathname = uploadimagepath + filePath + pic.getRealName();
                model.addAttribute("pathname", pathname);
            }
        }
        return prefix + "manage";
    }

    @RequestMapping(value = "/companyInfo",method = RequestMethod.GET)
    public void companyInfo(Model model,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException
    {
        List<Company> all = companyManager.getAll();
        JsonTools.writeJson(httpServletResponse,all.get(0));
    }

}
