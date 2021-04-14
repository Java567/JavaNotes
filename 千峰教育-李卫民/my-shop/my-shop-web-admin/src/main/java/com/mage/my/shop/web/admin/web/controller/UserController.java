package com.mage.my.shop.web.admin.web.controller;


import com.mage.my.shop.commons.dto.BaseResult;
import com.mage.my.shop.domain.TbUser;
import com.mage.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


/**
 * 用户管理
 */
@Controller
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private TbUserService tbUserService;


    @ModelAttribute
    public TbUser getTbUser(Long id){
        TbUser tbUser=null;

        //id 不为空则从数据库获取
        if(id!=null){
            tbUser=tbUserService.getById(id);
        }

        else {
            tbUser=new TbUser();
        }

        return tbUser;
    }


    /**
     * 跳转到用户列表页
     * @return
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model){
        List<TbUser> tbUsers=tbUserService.selectAll();
        model.addAttribute("tbUsers",tbUsers);
        return "user_list";
    }


    /**
     * 跳转用户表单页
     * @return
     */
    @RequestMapping(value = "from",method = RequestMethod.GET)
    public String from(){
        return "user_form";
    }


    /**
     * 保存用户的信息
     * @param tbUser
     * @return
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(TbUser tbUser,Model model, RedirectAttributes redirectAttributes){
        BaseResult baseResult=tbUserService.save(tbUser);

        //保存成功
        if(baseResult.getStatus()==BaseResult.STATUS_SUCCESS){
            //重定向用redirectAttributes,放在session
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/user/list";
        }

        //保存失败
        else {
            //跳转用model,放在 request
            model.addAttribute("baseResult",baseResult);
            return "user_form";
        }
    }


    /**
     * 搜索
     * @param tbUser
     * @param model
     * @return
     */
    @RequestMapping(value = "search",method = RequestMethod.POST)
    public String search(TbUser tbUser,Model model){
       List<TbUser> tbUsers= tbUserService.search(tbUser);
       model.addAttribute("tbUsers",tbUsers);
       return "user_list";
    }


    /**
     * 删除用户信息
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delete" ,method = RequestMethod.POST)
    public BaseResult delete(String ids){
        BaseResult baseResult=BaseResult.success();
        System.out.println(ids);
        return baseResult;
    }
}
