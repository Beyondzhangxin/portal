package aiidc.com.cn.portal.controller;

import aiidc.com.cn.portal.entity.AclRole;
import aiidc.com.cn.portal.entity.AclUser;
import aiidc.com.cn.portal.parameter.AclUserModel;
import aiidc.com.cn.portal.service.AclRoleManagerImpl;
import aiidc.com.cn.portal.service.AclUserManager;
import aiidc.com.cn.portal.util.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("company/usermanage")
public class UserManageController {
	
	@Autowired
	private AclUserManager aclUserManager;
	
	@Autowired
	private AclRoleManagerImpl aclRoleManagerImpl;
	
	@Value("#{configProperties['upload.Image.path']}")
	private String uploadimagepath;
	
	private String filePath = File.separator+Finals.PIC_TYPE_PERSON+File.separator;

	@RequestMapping("list")
	public ModelAndView list(@ModelAttribute("pageModel") AclUserModel userQuery)
	{
		ModelAndView m = new ModelAndView();
		Pagination pageQuery = aclUserManager.pageQuery(userQuery);
		m.getModel().put("pagn", pageQuery);
		m.setViewName("company/user/list");      	
		return m;
	}
	
	@RequestMapping("addUserPage")
	public ModelAndView addUserPage(@RequestParam(name="id",required=false) String id)
	{
		ModelAndView  m = new ModelAndView();
		Map<String, Object> model = m.getModel();
		if(StringUtils.hasText(id)){
			AclUser user = aclUserManager.findById(id);
			model.put("personImagePath", uploadimagepath + filePath+user.getPicName());
			model.put("editUserInfo", user);
		}
		
		List<AclRole> roleList = aclRoleManagerImpl.findAllRole();
		model.put("roleList",roleList);
		m.setViewName("company/user/add");
		return m;
	}
	
	@RequestMapping(value = "deleteUser", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String  delete(String id){
		aclUserManager.deleteById(id);
		JSONObject json = new JSONObject();
		json.put("success", true);
		json.put("message", "删除成功");
		return json.toString();
	}
	

	@RequestMapping(value="addUser",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String addUser(@ModelAttribute @Valid AclUser user,Errors errors,HttpServletRequest request,
			@RequestParam(name = "personImage") CommonsMultipartFile personImage) throws Exception
	{
		JSONObject json = new JSONObject();
		if(errors.hasErrors()){
			json.put("success", false);
			json.put("message", "请检查必填项");
			return json.toString();
		}
		if (!request.getParameter("passwdR").equals(user.getPasswd())){
			json.put("success", false);
			json.put("message", "请确认密码");
			return json.toString();
		}
		String saveName="";
		String imageSavePath=uploadimagepath + filePath;
		//判断是否有头像上传
		if(!personImage.isEmpty()){
			saveName = FileUtil.getUploadImgName(personImage);
			FileUtil.uploadImageByTransferTo(personImage,imageSavePath, saveName);
		}

		AclUser saveUser = new AclUser();
		if(!StringUtils.hasText(user.getId())){
			user.setId(saveUser.getId());
			saveUser=user;
			String md5 = MD5Util.md5("admin", false);
			saveUser.setPasswd(md5);
			saveUser.setGatePass("admin");
			saveUser.setRole(aclRoleManagerImpl.findById(user.getRoleId()));
			if(StringUtils.hasText(saveName)) saveUser.setPicName(saveName);
			
			aclUserManager.add(saveUser);
			//添加成功后，如果需要停留在添加页，需要将id返回给页面
			json.put("message","添加成功");
		}else{
			saveUser = aclUserManager.findById(user.getId());
			saveUser.setLoginName(user.getLoginName());
			saveUser.setName(user.getName());
			saveUser.setMale(user.getMale());
			if (!user.getPasswd().equals(saveUser.getPasswd())){
				String md5 = MD5Util.md5(user.getPasswd(), false);
				saveUser.setPasswd(md5);
			}
			saveUser.setRole(aclRoleManagerImpl.findById(user.getRoleId()));
			saveUser.setMobile(user.getMobile());
			saveUser.setEmail(user.getEmail());
		
			if(StringUtils.hasText(saveName)) {
				FileUtil.deleteFile(imageSavePath, saveUser.getPicName());
				saveUser.setPicName(saveName);
			}
			
			aclUserManager.update(saveUser);
			LoginHelper.setSession(request, saveUser);
			//添加成功后，如果需要停留在添加页，需要将id返回给页面
			json.put("message","修改成功");
		}
		json.put("success", true);
		return json.toString();
	}

	@RequestMapping(value = "loginUser")
	public String user(HttpServletRequest servletRequest,Model model){
		AclUser loginUser = LoginHelper.getLoginUser(servletRequest);
		List<AclRole> roleList = aclRoleManagerImpl.findAllRole();
		model.addAttribute("roleList", roleList);
		model.addAttribute("editUserInfo", loginUser);
		return "company/user/loginUser";
	}

}
