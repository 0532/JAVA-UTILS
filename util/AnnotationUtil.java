package com.haier.neusoft.o2o.common.util;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.haier.neusoft.o2o.common.model.*;
import com.haier.neusoft.o2o.common.annotation.Excel;
import com.haier.neusoft.o2o.common.annotation.ExcelTitle;
import com.haier.neusoft.o2o.common.annotation.LevelExcelTitle;
import com.haier.neusoft.o2o.common.annotation.LevelExcelTitles;
import com.haier.neusoft.o2o.common.annotation.Sheet;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




/**
 * Created by pc on 14-6-12.
 *
 */
@Slf4j
public class AnnotationUtil{

    public static ExcelInfo getExcelInfo(List list){
        ExcelInfo e = new ExcelInfo();

        if(list== null || list.isEmpty()){
           return null;
        }

        //文件名称
        String execelFileName =  "" ;
//        String execelextension = "" ;
        //标题list
        List<ExcelTitleInfo> titlesList = Lists.newArrayList();
        List<String> fieldNameList =   Lists.newArrayList();

        Object object = list.get(0);
//        System.out.println(object.getClass());
//        System.out.println(Reflections.getUserClass(object.getClass()));
        Class clazz = Reflections.getUserClass(object.getClass());


        List<SheetInfo> sheetInfos = null;
        List<LevelTitle> levelTitles = null;
        /**
         * 获取类上的Annotation
         */
        for(Annotation annotation :clazz.getDeclaredAnnotations()){
            //判断是否包含Execl标签
            if(annotation.annotationType().equals(Excel.class)){
                Excel excel = (Excel) annotation;
                execelFileName = excel.filename();
//                execelextension = excel.fileextension();

            }else if(annotation.annotationType().equals(Sheet.class)){
                if(sheetInfos == null){
                    sheetInfos = Lists.newArrayList();
                }
                Sheet sheet = (Sheet)annotation;
                int[] numOfSheet = sheet.numOfSheet();
                int numPerSheet = sheet.numPersheet();
                String[] sheetnames = sheet.sheetnames();

                if (sheetnames.length == numOfSheet.length && numOfSheet.length!=0){
                    for(int i=0;i<numOfSheet.length;i++){
                      if(numOfSheet[i] <=0){
                          numOfSheet[i] = numPerSheet;
                      }
                    }
                }else if(sheetnames.length >numOfSheet.length && numOfSheet.length!=0){
                    int[] nums = new int[sheetnames.length];
                    System.arraycopy(numOfSheet,0,nums,0,numOfSheet.length);
                    for(int i=0;i<nums.length;i++){
                        if(nums[i] <=0){
                            nums[i] = numPerSheet;
                        }
                    }
                    numOfSheet = nums;
                }else if(numOfSheet.length==0&&sheetnames.length >1){
                    int[] nums = new int[sheetnames.length];
                    for(int i=0;i<nums.length;i++){
                            nums[i] = numPerSheet;
                    }
                    numOfSheet = nums;
                }else if (sheetnames.length ==1){
                    //如果只设置了一个sheet名字, 代表根据 总数据条数\每页个数  个sheet
                    int sheetSize=list.size()/numPerSheet+1;
                    String sheetName=sheetnames[0];
                    String[] sheetnametemp=new String[sheetSize];
                    for(int i=0;i<sheetSize;i++){
                        if (sheetSize==1){
                            sheetnametemp[i]=sheetName;
                        }else{
                            sheetnametemp[i]=sheetName+i;
                        }

                    }
                    sheetnames=sheetnametemp;
                    int[] nums = new int[sheetnames.length];
                    for(int i=0;i<nums.length;i++){
                        nums[i] = numPerSheet;
                    }
                    numOfSheet = nums;
                }

                for(int i = 0;i<sheetnames.length;i++){
                    String sheetName = sheetnames[i];
                    int num = numOfSheet[i];
                    SheetInfo sheetInfo = new SheetInfo();
                    sheetInfo.setNum(num);
                    sheetInfo.setSheetName(sheetName);
                    sheetInfos.add(sheetInfo);
                }
            }else if(annotation.annotationType().equals(LevelExcelTitles.class)){
                if(levelTitles == null){
                    levelTitles = Lists.newArrayList();
                }
                LevelExcelTitles titles = (LevelExcelTitles)annotation;
                LevelExcelTitle[] array = titles.value();
                for(int i=0;i<array.length;i++){
                    int[] colspans = array[i].colspans();
                    int titleLevel= array[i].titleLevel();
                    String[] titlesnames = array[i].titlenames();
                    LevelTitle levelTitle = new LevelTitle();
                    levelTitle.setColspans(colspans);
                    levelTitle.setTitleLevel(titleLevel);
                    levelTitle.setTitleNames(titlesnames);
                    levelTitle.setAligns(array[i].aligns());
                    levelTitles.add(levelTitle);
                }
            }

        }


        e.setSheetInfos(sheetInfos);

        if(levelTitles != null){
            Collections.sort(levelTitles);
        }
        e.setLevelTitles(levelTitles);

        if(Strings.isNullOrEmpty(execelFileName)){
             //不能为空这里
            return null;
        }
        //最终文件名称
//        String execelFullName = Files.getNameWithoutExtension(execelFileName)+"."+ execelextension;
        e.setFileName(execelFileName);


        /**
         * 获取属性上面的Annotation
         */
        for(Field field :clazz.getDeclaredFields()){
           String fieldName = field.getName();
//           System.out.println("fieldName:"+fieldName);
           for(Annotation annotation : field.getDeclaredAnnotations()){
               //判断是否包含ExcelTitle标签
               if(annotation.annotationType().equals(ExcelTitle.class)){
                   ExcelTitle excelTitle = (ExcelTitle) annotation;
                   ExcelTitleInfo info = new ExcelTitleInfo();
                   String titlename = excelTitle.titlename();
                   int width = excelTitle.width();
                   int index = excelTitle.index();
                   String type=excelTitle.type();
                   info.setIndex(index);
                   info.setWidth(width);
                   info.setType(type);
                   //校验titile的名称，如果有插入list，如果没有用field名称
                   if(Strings.isNullOrEmpty(titlename)){
                       info.setTitle(fieldName);
                       titlesList.add(info);
                   }
                   else{
                       info.setTitle(titlename);
                       titlesList.add(info);
                   }
                   info.setFieldName(fieldName);
                   fieldNameList.add(fieldName);

               }
           }
        }

//        System.out.println(titlesList.toString());
        if( (!titlesList.isEmpty()) && titlesList.size()!=1){
            //Lists.
            Collections.sort(titlesList);
        }
        e.setTitleInfo(titlesList);
//        System.out.println(titlesList.toString());

       //getAccessibleField Reflections.getFieldValue()
        List<List<ExcelBodyInfo>> result = Lists.newArrayList();
        for(Object o :list){
             List<ExcelBodyInfo> row = Lists.newArrayList();
             for(ExcelTitleInfo excelTitleInfo : titlesList){
                  Object value = Reflections.getFieldValue(o,excelTitleInfo.getFieldName()) ;
                  ExcelBodyInfo excelBodyInfo=new ExcelBodyInfo();
                  if(value == null ){
                      excelBodyInfo.setValue("");
                      excelBodyInfo.setType("String");
                      row.add(excelBodyInfo);
                  }
                  else if(value instanceof Date){
                      //时间类型的字段导出, 强制转换成时间格式
                      excelBodyInfo.setValue(TimeUtilis.getDateStringByForm((Date) value, "YYYY-MM-DD HH:mm:ss"));
                      excelBodyInfo.setType("Date");
                      row.add(excelBodyInfo);
                  }else if(value instanceof BigDecimal){
                      //BigDecimal格式的数据强制转换数字格式,保留两位小数
                      excelBodyInfo.setValue(value.toString());
                      excelBodyInfo.setType("BigDecimal");
                      row.add(excelBodyInfo);
                  }
                  else if(value instanceof Integer){
                      //Integer类型强制转成数字类型
                      excelBodyInfo.setValue(value.toString());
                      excelBodyInfo.setType("Integer");
                      row.add(excelBodyInfo);
                  }else{
                      //String类型,根据注解的type进行转换类型
                      excelBodyInfo.setValue(value.toString());
                      excelBodyInfo.setType(excelTitleInfo.getType());
                      row.add(excelBodyInfo);
                  }

             }
            result.add(row);

        }
        e.setBobyInfo(result);

        return e;
    }


    public static <T> List<T> transferExcelToList(ExcelInfo excelInfo, Class<T> clazz){

        List<String> needTitle = new ArrayList<String>();
        Map<String,Field> map = new HashMap<String, Field>();

        for(Field field :clazz.getDeclaredFields()){
            String fieldName = field.getName();
//            System.out.println("fieldName:"+fieldName);
            for(Annotation annotation : field.getDeclaredAnnotations()){
                //判断是否包含ExcelTitle标签
                if(annotation.annotationType().equals(ExcelTitle.class)){
                    ExcelTitle excelTitle = (ExcelTitle) annotation;
                    String titlename = excelTitle.titlename();

                    //校验titile的名称，如果有插入list，如果没有用field名称
                    if(Strings.isNullOrEmpty(titlename)){
                        needTitle.add(fieldName);
                    }
                    else{
                        needTitle.add(titlename);
                    }
                    map.put(titlename,field);

                }
            }
       }

        List<String> excelTitle = excelInfo.getTitle();
        List<Integer> indexList = new ArrayList<Integer>();
        for(String s : needTitle){
            int index = excelTitle.indexOf(s);
            indexList.add(index);
        }

        List<T> list = new ArrayList<T>();

        for(List<ExcelBodyInfo> row : excelInfo.getBobyInfo()){
            Object o = null;
            try {
                o =  clazz.newInstance();
            } catch (InstantiationException e) {
                //e.printStackTrace();
                log.error("exception",e);
            } catch (IllegalAccessException e) {
//                e.printStackTrace();
                log.error("exception",e);
            }
            if(row.size()<needTitle.size()){
                break;
            }
            for(int i = 0 ; i<needTitle.size();i++){
                String t =  needTitle.get(i);
                Field f =  map.get(t);
                int index = indexList.get(i);
                if(index == -1) {break;}
                String value =  row.get(index).getValue();

                Reflections.setFieldValue(o,f.getName(),value);

            }
            list.add((T)o);

        }



        return list;
    }



    public static void main(String[] args) {
        TestExcel e = new TestExcel();
        e.setAaa("asdsad");
        e.setAa2("12");
        e.setAa3("12");
        List<TestExcel>  list = Lists.<TestExcel>newArrayList();
        list.add(e);
        getExcelInfo(list) ;


//        ExcelInfo excelInfo = new ExcelInfo();
//
//         List<ExcelTitleInfo> titleInfo = new ArrayList<ExcelTitleInfo>();
//         List<List<String>> bobyInfo = new ArrayList<List<String>>();
//        ExcelTitleInfo t = new ExcelTitleInfo();
//        t.setTitle("测试字段");
//        ExcelTitleInfo t1 = new ExcelTitleInfo();
//        t1.setTitle("测试字段4");
//        ExcelTitleInfo t2 = new ExcelTitleInfo();
//        t2.setTitle("测试字段2");
//        ExcelTitleInfo t3 = new ExcelTitleInfo();
//        t3.setTitle("测试字段3");
//        titleInfo.add(t);
//        titleInfo.add(t1);
//        titleInfo.add(t2);
//        titleInfo.add(t3);
//
//        List<String> a = new ArrayList<String>();
//        a.add("12");
//        a.add("3");
//        a.add("4");
//        a.add("5");
//        bobyInfo.add(a);
//        bobyInfo.add(a);
//        bobyInfo.add(a);
//        bobyInfo.add(a);
//
//        excelInfo.setBobyInfo(bobyInfo);
//        excelInfo.setTitleInfo(titleInfo);
//        List<TestExcel> l = index(excelInfo,TestExcel.class);
//
//
//        System.out.println(l.size());

    }
}
