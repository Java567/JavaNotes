package cn.itcast.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

// 控制器类
@Controller
@SessionScope
@RequestMapping(path="/user")
public class HelloController {

//    @ModelAttribute
//    public ModelAndView getVehicle(){
//        ModelAndView mv=new ModelAndView();
//        mv.setViewName("index");
//        mv.addObject("msg","SpringMVC");
//        return mv;
//    }

    /**
     * 入门案例
     * @return
     */
    @RequestMapping(path="/hello")
    public String sayHello(){
        System.out.println("Hello StringMVC");
        return "success";
    }


    @RequestMapping(value="/index")
    public String testRequestMapping(Model model, String msg){
        System.out.println(msg);
        model.addAttribute("msg",msg);
        return "index";
    }


    /**
     * RequestMapping注解
     * @return
     */
//    @RequestMapping(value="/index",params = {"msg=springmvc"},headers = {"Accept"})
//    public String testRequestMapping(Model model,String msg){
//        msg="springMvc";
//        model.addAttribute("msg","springmvc");
//        return "success";
//    }
//
//    @RequestMapping(value = "index2",method = RequestMethod.GET)
//    public String index(Model model){
//        model.addAttribute("msg","美美");
//        return "index";
//    }

//    @RequestMapping("index")
//    @ResponseBody
//    public ModelAndView index(){
//        ModelAndView mv=new ModelAndView();
//        mv.setViewName("index");
//        mv.addObject("msg","SpringMVC");
//        return mv;
//
//    }

}
