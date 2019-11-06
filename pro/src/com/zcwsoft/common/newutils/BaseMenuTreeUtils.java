package com.zcwsoft.common.newutils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.zcwsoft.common.newutils.basebean.MenuTreeBean;
import com.zcwsoft.common.util.Json;

public class BaseMenuTreeUtils {

	/**
	 * 表数据处理成json，菜单层级格式
	 * @param rootMenuBean
	 * @return
	 */
	public static String getMenuBeanList(List<MenuTreeBean> rootMenuBean) {
	    
	    // 查看结果
	    for (MenuTreeBean menu : rootMenuBean) {
	        System.out.println(menu);
	    }
	    // 最后的结果
	    List<MenuTreeBean> menuList = new ArrayList<MenuTreeBean>();
	    // 先找到所有的一级菜单
	    for (int i = 0; i < rootMenuBean.size(); i++) {
	        // 一级菜单没有parentId
	        if (BaseSysUtils.isBlank(rootMenuBean.get(i).getParentid())) {
	            menuList.add(rootMenuBean.get(i));
	        }
	    }
	    // 为一级菜单设置子菜单，getChild是递归调用的
	    for (MenuTreeBean menu : menuList) {
	        menu.setChildren(getChild(menu.getId(), rootMenuBean));
	    }
	    Map<String,Object> jsonMap = new HashMap<String,Object>();
	    jsonMap.put("menu", menuList);
	    //List<Map<String, Object>> listMapByListBean = MapUtils.getListMapByListBean(menuList);
	    //String result = Json.Encode(listMapByListBean);
	   // System.out.println(result);
	    return Json.Encode(menuList);
	}

	/**
	 * 递归查找子菜单
	 * 
	 * @param id
	 *            当前菜单id
	 * @param rootMenuBean
	 *            要查找的列表
	 * @return
	 */
	private static List<MenuTreeBean> getChild(String id, List<MenuTreeBean> rootMenuBean) {
	    // 子菜单
	    List<MenuTreeBean> childList = new ArrayList<MenuTreeBean>();
	    for (MenuTreeBean menu : rootMenuBean) {
	        // 遍历所有节点，将父菜单id与传过来的id比较
	        if (StringUtils.isNotBlank(menu.getParentid())) {
	            if (menu.getParentid().equals(id)) {
	                childList.add(menu);
	            }
	        }
	    }
	    // 把子菜单的子菜单再循环一遍
	    for (MenuTreeBean menu : childList) {// 没有url子菜单还有子菜单
	       // if (BaseSysUtils.isBlank(menu.getUrl())) {
	            // 递归
	            menu.setChildren(getChild(menu.getId(), rootMenuBean));
	       // }
	    } // 递归退出条件
	    if (childList.size() == 0) {
	        return null;
	    }
	    return childList;
	}
	
	
}
