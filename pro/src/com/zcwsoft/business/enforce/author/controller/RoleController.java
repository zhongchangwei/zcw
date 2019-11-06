package com.zcwsoft.business.enforce.author.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zcwsoft.business.enforce.author.service.RoleService;
import com.zcwsoft.common.newutils.basebean.ReceParam;

@Controller
@RequestMapping("roleController")
public class RoleController {
    @Autowired
    private RoleService ser;

    public Logger log  =  LoggerFactory.getLogger(RoleController.class);

    /**
     * 作者：--
     * 功能：加载列表
     * 时间：2018-02-28
     * @return 
     */
    @RequestMapping("treeList")
    public ModelAndView treeList(ReceParam rcep){
        ModelAndView mv=new ModelAndView("/enforce/author/role/roleTree");
        try {
            String treeData = ser.treeList(rcep);
            mv.addObject("treeData", treeData);
            mv.addObject("title", "角色管理");
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return mv;
    }
    /**
     * 作者：--
     * 功能：加载列表
     * 时间：2018-02-28
     * @return 
     */
    @RequestMapping("list")
    public ModelAndView list(ReceParam rcep){
        ModelAndView mv=new ModelAndView("/enforce/author/role/roleList");
        try {
            String data = ser.list(rcep);
            mv.addObject("data", data);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return mv;
    }

    /**
     * 作者：--
     * 功能：新增  修改（回显）弹出页面
     * 时间：2018-02-28
     * @return 
     */
    @RequestMapping("openDetail.do")
    public ModelAndView openDetail(ReceParam rcep){
        ModelAndView mv = new ModelAndView("/enforce/author/role/roleDetail");
        String id = rcep.getId();
        String dataShow=null;
        try{
            if(rcep.isNotEmpty(id)){//跳转修改页面操作
                dataShow = ser.formShowData(rcep);//表单回显
                mv.addObject("dataShow",rcep.getURLEncoder(dataShow));//表单回显
            }
        }catch(Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return mv;
    }
    /**
     * 作者：--
     * 功能：保存
     * 时间：2018-02-28
     * @return 
     */
    @RequestMapping("commitSave.do")
    @ResponseBody
    public Object commitSave(ReceParam rcep){
        String data="";
        try{
                data = ser.save(rcep);
        }catch(Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return data;
    }
    /**
     * 作者：--
     * 功能：删除
     * 时间：2018-02-28
     * @return 
     */
    @RequestMapping("remove.do")
    @ResponseBody
    public String remove(ReceParam rcep){
        String flag="";
        try{
            flag = ser.remove(rcep);
        }catch(Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }
}