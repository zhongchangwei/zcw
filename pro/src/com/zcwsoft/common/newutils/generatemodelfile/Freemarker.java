package com.zcwsoft.common.newutils.generatemodelfile;
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.OutputStreamWriter;  
import java.io.Writer;  
import java.text.SimpleDateFormat;
import java.util.ArrayList;  
import java.util.Date;
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
  
import freemarker.template.Configuration;  
import freemarker.template.DefaultObjectWrapper;  
import freemarker.template.Template;  
import freemarker.template.TemplateException; 

public class Freemarker
{
    
    public static void main(String[] args)
    {
        String 修改="加类型DATATYPE   int";
        String 增加="加字段 :" +
                    		"isblank(no:不空 yes:可控)" +
                    		"pk_table" +
                    		"" +
                    		"" +
                    		"" +
                    		"" +
                    		"";
        String show = "tableName   pk_Tablename   field   tname   package     outputpath";
        Map tableMap = new HashMap();
        tableMap.put("TABLENAME", "t_t_t");
        tableMap.put("PKTABLENAME", "t_t_t,t_s_t");
        tableMap.put("FIELD", "user");
        tableMap.put("TNAME", "用户管理");
        tableMap.put("PACKAGENAME", "test.user");
        //tableMap.put("OUTPUTPATH", "E:\\zcw");
        tableMap.put("WIDTH", "50");
        tableMap.put("DATATYPE", "date");
        tableMap.put("DATEFORMAT", "yyyy-mm-dd");
        tableMap.put("ISHIDDEN", "1");
        tableMap.put("ISBLANK", "yes");
        
        List<Map> fieldList = new ArrayList<Map>();
        
        Map ma0 = new HashMap();
        ma0.put("TNAME", "字段1");
        ma0.put("FIELD", "ok");
        ma0.put("WIDTH", "50");
        ma0.put("DATATYPE", "date");
        ma0.put("DATEFORMAT", "yyyy-mm-dd");
        ma0.put("ISHIDDEN", "1");
        ma0.put("ISBLANK", "yes");
        fieldList.add(ma0);
        Map ma1 = new HashMap();
        ma1.put("TNAME", "字段2");
        ma1.put("FIELD", "no");
        ma1.put("WIDTH", "30");
        ma1.put("DATATYPE", "optiondata");
        ma1.put("DATEFORMAT", "yyyy-mm-dd");
        ma1.put("ISHIDDEN", "0");
        ma1.put("ISBLANK", "no");
        fieldList.add(ma1);
        Map ma2 = new HashMap();
        ma2.put("TNAME", "字段3");
        ma2.put("FIELD", "no2");
        ma2.put("WIDTH", "30");
        ma2.put("DATATYPE", "optionurl");
        ma2.put("DATEFORMAT", "yyyy-mm-dd");
        ma2.put("ISHIDDEN", "0");
        ma2.put("ISBLANK", "yes");
        fieldList.add(ma2);
        Map ma3 = new HashMap();
        ma3.put("TNAME", "字段4");
        ma3.put("FIELD", "no3");
        ma3.put("WIDTH", "30");
        ma3.put("DATATYPE", "text");
        ma3.put("DATEFORMAT", "yyyy-mm-dd");
        ma3.put("ISHIDDEN", "0");
        ma3.put("ISBLANK", "no");
        fieldList.add(ma3);
        Map ma4 = new HashMap();
        ma4.put("TNAME", "字段5");
        ma4.put("FIELD", "no4");
        ma4.put("WIDTH", "30");
        ma4.put("DATATYPE", "int");
        ma4.put("DATEFORMAT", "yyyy-mm-dd");
        ma4.put("ISHIDDEN", "0");
        ma4.put("ISBLANK", "yes");
        fieldList.add(ma4);
        
        sss(fieldList,tableMap,fieldList);
       
    }
    
    public static Map getTableMap(String tableName){
        return null;
    }
    
    public static List<Map> getFieldList(String tableName){
        return null;
    }
    
    public static String sss(List<Map> fieldList,Map tableMap,List<Map> pkMaps){
        

        List<Map> fieldListGrid1 =null;
        List<Map> fieldListGrid2 =null;
        if(tableMap.get("FIELD")==null)return null;
        //标识符号，是否有外键（is：有外键  no：没有外键）
        Object pkTB = tableMap.get("PKTABLENAME");
        String flag = "no";
        String flag2 = "no";
        if(pkTB!=null){
            String[] pkTbn = pkTB.toString().split(",");//如果关联有多张从表，获取存放到数组中
            for (int i = 0; i < pkTbn.length; i++)
            {
                flag = "is";
                fieldListGrid1 = getFieldList(pkTbn[i]);
                if(i==1){//只支持2个从表生成
                    flag2 = "is";
                    fieldListGrid2 = getFieldList(pkTbn[i]);
                    break;
                }
            }
        }
        String firPath = "E:/zcw/EclipseWorkspace/";
        String proName = "pro/";//项目名
        String src = "src/";
        String packagePath ="com/zcwsoft/business/enforce/"; //系统写死（src层）--后期放配置文件
        String pagePath ="WebRoot/WEB-INF/jsp/enforce/"; //系统写死（WebRoot层）--后期放配置文件
        String field = tableMap.get("FIELD").toString();//文件名称（类名）
        String packageName =tableMap.get("PACKAGENAME").toString(); //包名
        String tName = tableMap.get("TNAME").toString();//标题（页面使用）
        //设置首字母大写
        field = field.substring(0,1).toUpperCase()+field.substring(1,field.length());
        //设置首字母小写（主要是页面文件名称，IOC容器BeanID）
        String lowerField = field.substring(0,1).toLowerCase()+field.substring(1,field.length());
        
        //controller使用： 返回页面路径 
        String resultPagePath ="/enforce/"+(packageName+"/"+lowerField).replace(".", "/"); 
        //页面文件存放完整路径(文件名前的路径)
        String pageFullPath =(pagePath+packageName).replace(".", "/"); 
        //java中包完整路径（文件前的路径com开始）
        String javaFullPath =(packagePath+packageName).replace("/", "."); 
        //注释设置时间
        String nowDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        
        
        
      /*  String sourcePath = "E:\\zcw\\EclipseWorkspace\\pro\\WebRoot\\freemarker";
        String resultPath = "E:\\zcw\\EclipseWorkspace\\pro\\src\\com\\zcw\\";
        String resultPathPage = "E:\\zcw\\EclipseWorkspace\\pro\\WebRoot\\result\\";
        */
        /*String sourcePath = "E:/zcw/EclipseWorkspace/pro/WebRoot/freemarker";
        String resultPath = "E:/zcw/EclipseWorkspace/pro/src/com/zcw/"+packageName.replace(".", "/")+"/";
        String resultPathPage = "E:/zcw/EclipseWorkspace/pro/WebRoot/result/"+packageName.replace(".", "/")+"/";
        */
        String sourcePath = "E:/zcw/EclipseWorkspace/pro/WebRoot/freemarker";
        String resultPath = firPath+proName+src+packagePath;//例如：E:/zcw/EclipseWorkspace/pro/src/
        String resultPathPage = firPath+proName+pagePath;//例如：E:/zcw/EclipseWorkspace/pro/WebRoot/WEB-INF/jsp/enforce/
        if(tableMap.get("OUTPUTPATH")!=null){
            resultPath=tableMap.get("OUTPUTPATH").toString()+"/code/"+proName+src+ packagePath+packageName.replace(".", "/")+"/";
            resultPathPage=tableMap.get("OUTPUTPATH").toString()+"/code/"+proName+pagePath+packageName.replace(".", "/")+"/";
        }else{
            resultPath =resultPath+ packageName.replace(".", "/")+"/";
            resultPathPage = resultPathPage+packageName.replace(".", "/")+"/";
        }
     //  查询list
      //  String path = "E:\\zcw\\EclipseWorkspace\\pro\\WebRoot";
        Map<String, Object> weaponMap = new HashMap<String, Object>();  
        weaponMap.put("'$'", "$");    
        weaponMap.put("titleName", tName);    
        weaponMap.put("mvcPath", lowerField+"Controller");    
        weaponMap.put("gridListQuery", generateList(fieldList)); 
        sitterConfig("listQueryModel.model",lowerField+"List.jsp",sourcePath,resultPathPage,weaponMap);
        
       // 表单+ｇｒｉｄ编辑
        Map<String, Object> formMap = new HashMap<String, Object>();  
        formMap.put("'$'", "$");    
        formMap.put("flag", flag);    
        formMap.put("flag2", flag2);//有2个从表标识符,如果这个等于is就有2个grid
        formMap.put("titleName", tName);    
        formMap.put("mvcPath", lowerField+"Controller");    
        formMap.put("formLabel", generateForm(fieldList)); 
        formMap.put("gridEdit", generateListEdit(fieldListGrid1));//有从表。Detail页面增加grid1，主要看flag字段
        formMap.put("gridEdit2", generateListEdit(fieldListGrid2));//有从表2。Detail页面增加grid2,主要看flag2字段
        sitterConfig("formDetailModel.model",lowerField+"Detail.jsp",sourcePath,resultPathPage,formMap);
        
       //controller
        Map<String, Object> controllerMap = new HashMap<String, Object>();  
        controllerMap.put("flag", flag);    
        controllerMap.put("field", field);    
        controllerMap.put("mvcPath", lowerField);    
        controllerMap.put("pagePath", resultPagePath);    
        controllerMap.put("lowerField", lowerField);    
        controllerMap.put("javaFullPath", javaFullPath); 
        controllerMap.put("nowDate", nowDate); 
        sitterConfig("controller.model",field+"Controller.java",sourcePath,resultPath+"controller/",controllerMap);
   
        //service
        Map<String, Object> serviceMap = new HashMap<String, Object>();  
        serviceMap.put("flag", flag);    
        serviceMap.put("field", field);    
        serviceMap.put("lowerField", lowerField);    
        serviceMap.put("javaFullPath", javaFullPath); 
        serviceMap.put("nowDate", nowDate); 
        sitterConfig("serviceModel.model",field+"Service.java",sourcePath,resultPath+"service/",serviceMap);
        
        //serviceImpl
        Map<String, Object> serviceImplMap = new HashMap<String, Object>();  
        serviceImplMap.put("flag", flag);    
        serviceImplMap.put("field", field);            
        serviceImplMap.put("flag2", flag2);//有2个从表标识符,如果这个等于is就有2个grid
        serviceImplMap.put("lowerField", lowerField);    
        serviceImplMap.put("javaFullPath", javaFullPath); 
        serviceImplMap.put("nowDate", nowDate); 
        sitterConfig("serviceImplModel.model",field+"ServiceImpl.java",sourcePath,resultPath+"service/impl/",serviceImplMap);
       
        return null;
    }
    
    
    
    
    
    
    /**
     * freemarker,准备设置
     * @param lsm
     */
    public static void sitterConfig(String sourceName,String resultName,String sourcePath,String resultPath,Map m){
        try{
            //AddFolder(resultPath);
            //创建文件夹
            File file = new File(resultPath);
            if(!file.exists()){
                file.mkdirs();
            }
          //创建一个合适的Configration对象  
            Configuration configuration = new Configuration();  
            configuration.setDirectoryForTemplateLoading(new File(sourcePath));  
            configuration.setObjectWrapper(new DefaultObjectWrapper());  
            configuration.setDefaultEncoding("UTF-8");   //这个一定要设置，不然在生成的页面中 会乱码  
            //获取或创建一个模版。  
            Template template = configuration.getTemplate(sourceName);  
             
              
            Writer writer  = new OutputStreamWriter(new FileOutputStream(resultPath+resultName),"UTF-8");  
            template.process(m, writer);  
              
            System.out.println(resultName+"文件！生成成功~~");  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (TemplateException e) {  
            e.printStackTrace();  
        } 
    }
    
    
    private static void AddFolder(String resultPath)
    {
        
        File file = new File(resultPath);
        if(!file.exists()){
            file.mkdirs();
        }
        /*String hear = resultPath.substring(0,3);
        String content = resultPath.substring(3,resultPath.length());
        String[] path = content.split("/");
        for (int i = 0; i < path.length; i++)
        {
            hear+=path[i];
            System.out.println(hear);
            File f = new File(hear);
            if(!f.exists()){
                try
                {
                    f.mkdir()
                    //f.createNewFile();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            hear+="/";
        }*/
    }

    /**
     * List查看列表生成
     * @param lsm
     */
    public static String generateList(List<Map> lsm){
        if(lsm==null)return "什么都没有！呵呵";
        String splic = "";
        for (int j = 0; j < lsm.size(); j++) {
            Map<String,String> m =lsm.get(j);
            if(m.get("FIELD")!=null){//字段
                splic+="<div field=\""+m.get("FIELD")+"\" headerAlign=\"center\" ";
            }
            if(m.get("WIDTH")!=null){//宽度
                splic+="width=\""+m.get("WIDTH")+"\" ";
            }
            if(m.get("DATATYPE")!=null&&"date".equals(m.get("DATATYPE"))){//时间 1=是时间字段
                if(m.get("DATEFORMAT")!=null){//显示的时间格式
                    splic+=" allowSort=\"true\"";
                    splic+=" dateFormat=\""+m.get("DATEFORMAT")+"\" ";
                }
            }
            if(m.get("ISHIDDEN")!=null&&"0".equals(m.get("ISHIDDEN"))){//是否隐藏0隐藏
                splic+=" visible=\"false\" ";
            }else{
                splic+=" visible=\"true\" ";
            }
            if(m.get("TNAME")!=null){//标题名称
                splic+=">"+m.get("TNAME")+"</div>";
            }else{
                splic+=">unKnow</div>";
            }
             splic+="\n        ";
        }
        System.out.println(splic);
        return splic;
    }
    /**
     * 详细表单生成
     * @param lsm
     */
    public static String generateForm(List<Map> lsm){
        if(lsm==null)return "什么都没有！呵呵";
        final int row = 4;//每行多少个就换行
        String splic = "";
        String splic2 = "";
        String blank4 = "                ";
        String blank3 = "           ";
        int nm=0;
        for (int i = 0; i < lsm.size(); i++) {
            Map<String,String> m = lsm.get(i);
         if(m.get("FIELD")!=null&&m.get("TNAME")!=null&&m.get("DATATYPE")!=null){//字段
             if(nm%row==0){
                 splic+=" ";
                 if(i!=0){
                     splic+=blank3+"<tr>\n";
                 }else{
                     splic+="<tr>\n";
                 }
             }
                if("text".equals(m.get("DATATYPE"))){//文本
                    nm++;
                    splic+=blank4+"<td class=\"name\"><label>##_ISBLANK_##"+m.get("TNAME")+"：</label></td>\n"+
                            blank4+"<td><input id=\""+m.get("FIELD").toLowerCase()+"\" name=\""+m.get("FIELD")+"\"  vtype=\"maxLength:30\"  required=\"true\"   class=\"mini-textbox\"/></td>";
                }else if("optiondata".equals(m.get("DATATYPE"))){//下拉选择
                    nm++;
                    splic+=blank4+"<td class=\"name\"><label>##_ISBLANK_##"+m.get("TNAME")+"：</label></td>\n"+
                            blank4+"<td><input id=\""+m.get("FIELD").toLowerCase()+"\" name=\""+m.get("FIELD")+"\"  valueField=\"pk\" textField=\"name\" " +
                                    "   data=\""+"[{pk:'1',name:'男'},{pk:'0',name:'女'}]"+"\" allowInput=\"false\" " +
                                    "   required=\"true\"   class=\"mini-combobox\" showNullItem=\"false\"/></td>";
                }else if("optionurl".equals(m.get("DATATYPE"))){//下拉选择
                    nm++;
                    splic+=blank4+"<td class=\"name\"><label>##_ISBLANK_##"+m.get("TNAME")+"：</label></td>\n"+
                            blank4+"<td><input id=\""+m.get("FIELD").toLowerCase()+"\" name=\""+m.get("FIELD")+"\"  valueField=\"pk\" textField=\"name\" " +
                                    "   url=\"<%=path%>/common/getCityKV.do\" allowInput=\"false\" " +
                                    "   required=\"true\"   class=\"mini-combobox\" showNullItem=\"false\"/></td>";
                }else if("date".equals(m.get("DATATYPE"))){//日期
                    nm++;
                    splic+=blank4+"<td class=\"name\"><label>##_ISBLANK_##"+m.get("TNAME")+"：</label></td>\n"+
                            blank4+"<td><input id=\""+m.get("FIELD").toLowerCase()+"\" name=\""+m.get("FIELD")+"\" allowinput=\"false\" required=\"true\" class=\"mini-datepicker\" " +
                                    "   format=\"yyyy-MM-dd\" showTime=\"true\"  showClearButton=\"true\"/></td>";
                }else if("int".equals(m.get("DATATYPE"))){//数字
                    nm++;
                    splic+=blank4+"<td class=\"name\"><label>##_ISBLANK_##"+m.get("TNAME")+"：</label></td>\n"+
                            blank4+"<td><input id=\""+m.get("FIELD").toLowerCase()+"\" name=\""+m.get("FIELD")+"\"    required=\"true\"  class=\"mini-spinner\"  minValue=\"0\" maxValue=\"999\"/></td>";
                }
                
                if("no".equals(m.get("ISBLANK"))){
                    splic=splic.replace("##_ISBLANK_##", "<span style=\"color:red;\">*</span>");
                }else{
                    splic=splic.replace("##_ISBLANK_##", "");
                }
                
                splic+="\n";
                if(nm%row==0||i==lsm.size()-1){
                    splic+=blank3+"</tr>\n";
                }
            }
         
         splic2+=splic+"\n";
        }

        System.out.println(splic);      
        return splic;

    }
    
     
    public static String generateListEdit(List<Map> lsm){
        if(lsm==null)return "什么都没有！呵呵";
        String splic = "";
        String blank4 = "                                   ";
        String blank3 = "            ";
        for (int j = 0; j < lsm.size(); j++) {
            Map<String,String> m =lsm.get(j);
            if(m.get("FIELD")!=null){//字段
                splic+=" \n";
                splic+=blank3+"<div field=\""+m.get("FIELD")+"\" headerAlign=\"center\"  align=\"center\" ";
            }
            if(m.get("WIDTH")!=null){//宽度
                splic+="width=\""+m.get("WIDTH")+"\" ";
            }else{
                splic+="width=\"50px\" ";//默认宽度
            }
            
            if("no".equals(m.get("ISBLANK"))){//是否可空no:不能空 yes:可以空
                splic+="vtype=\"required\" ";
            }
            
            if(m.get("ISHIDDEN")!=null&&"0".equals(m.get("ISHIDDEN"))){//是否隐藏0隐藏
                splic+="visible=\"false\" ";
            }else{
                splic+="visible=\"true\" ";
            }
            
            if(m.get("DATATYPE")!=null&&"date".equals(m.get("DATATYPE"))){//时间 1=是时间字段
                if(m.get("DATEFORMAT")!=null){//显示的时间格式
                    splic+="allowSort=\"true\" ";
                    splic+="dateFormat=\""+m.get("DATEFORMAT")+"\" ";
                }
                splic+=">\n" +
                        "##_TNAME_##<input property=\"editor\" class=\"mini-datepicker\" style=\"width:100%;\"/>" +
                        "\n"+blank3+"</div>";
            }else if(m.get("DATATYPE")!=null&&"optiondata".equals(m.get("DATATYPE"))){//下拉选项data
                splic+=" type=\"comboboxcolumn\" ";
                splic+=">\n" +
                        "##_TNAME_##<input property=\"editor\" class=\"mini-combobox\" valueField=\"pk\" textField=\"name\" data=\"[{pk:'1',name:'男'},{pk:'0',name:'女'}]\" " +
                        "allowInput=\"false\" showNullItem=\"false\" />" +
                        "\n"+blank3+"</div>";
            }else if(m.get("DATATYPE")!=null&&"optionurl".equals(m.get("DATATYPE"))){//下拉选项url
                splic+=" type=\"comboboxcolumn\" ";
                splic+=">\n" +
                        "##_TNAME_##<input property=\"editor\" class=\"mini-combobox\" valueField=\"PK\" textField=\"NAME\" url=\"<%=path%>/common/getCityKV.do\" " +
                        "allowInput=\"false\" showNullItem=\"false\" />" +
                        "\n"+blank3+"</div>";
            }else if(m.get("DATATYPE")!=null&&"int".equals(m.get("DATATYPE"))){//数字
                splic+=">\n " +
                        "##_TNAME_##<input property=\"editor\" class=\"mini-spinner\"  vtype=\"float\" minValue=\"0\" maxValue=\"999\"  />" +
                        "\n"+blank3+"</div>";
            }else if(m.get("DATATYPE")!=null&&"text".equals(m.get("DATATYPE"))){//text文本
                splic+=">\n" +
                        "##_TNAME_##<input property=\"editor\" class=\"mini-textbox\"  maxlength=\"30\"/>" +
                		"\n"+blank3+"</div>";
            }
            
            if(m.get("TNAME")!=null){//标题名称
               splic= splic.replace("##_TNAME_##", blank4+m.get("TNAME"));
            }else{
                splic= splic.replace("##_TNAME_##", blank4+"unKnow");
            }
            
             //splic+="\n        ";
        }
        System.out.println(splic);
        return splic;
        
        
    }
    
}
