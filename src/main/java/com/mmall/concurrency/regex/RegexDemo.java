package com.mmall.concurrency.regex;

import lombok.extern.slf4j.Slf4j;
import org.docx4j.XmlUtils;
import org.docx4j.jaxb.Context;
import org.docx4j.model.datastorage.migration.VariablePrepare;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Document;
import org.junit.Test;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: lsl
 * @Description: 正则表达式
 * @Date: Created on 21:58 2019/4/23
 */
@Slf4j
public class RegexDemo {
    @Test
    public void test() {
        //匹配
        String tel = "15738825604";
        String match = "1[358][0-9]{9}";
        boolean matches = tel.matches(match);
        log.info(tel+ "===={}", matches);
    }
    @Test
    public void test1() {
        //切割
        String string = "lishuai liling            peng";
        String[] split = string.split(" +");
        log.info("数组的长度为{}", split.length);
    }
    @Test
    public void test2() {
        //切割
        String string = "lishuaizzzzzzzzzlilingdddddddddddddddddpengge";
        String[] split = string.split("(.)\\1+");
        log.info("数组的长度为{}", split.length);
    }
    @Test
    public void test3() {
        //替换
        String string = "lishuaizzzzzzzzzlilingdddddddddddddddddpenge";
        String $1 = string.replaceAll("(.)\\1+", "$1");
        log.info($1);
        //匹配电视台抽奖
        String s = "15738825604";
        String s1 = s.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        log.info(s1);
    }
    @Test
    public void test4() {
        //获取
      String s = "ain peng hui fang shi, ge hao ren";
      String regex = "\\b[a-z]{3}\\b";
      //将正则表达式封装在pattern对象
        Pattern pattern = Pattern.compile(regex);
        //调用匹配器对象
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
    @Test
    public void test5() {
        //获取
        String s = "nfksdfods ${dasAj.dlas}dsaj${hfdfhk.dfhksd}dasj";
//        String regex = "\\$\\{[a-zA-Z.]+}";
        String regex = "[${]{2}?([a-zA-Z.]+)[}]";
        //将正则表达式封装在pattern对象
        Pattern pattern = Pattern.compile(regex);
        //调用匹配器对象
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    @Test
    public void test6() {
        WordprocessingMLPackage newWord = null;
        try {
            File file = new File("D:\\学习资料\\demo.docx");
            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(file);
            VariablePrepare.prepare(wordMLPackage);
            MainDocumentPart mdp = wordMLPackage.getMainDocumentPart();
            Document document = mdp.getContents();
            String xmlContent = XmlUtils.marshaltoString(document,true, false, Context.jc);

            document = (Document) XmlUtils.unwrap(DocxVariableClearUtils.doCleanDocumentPart(xmlContent, Context.jc));
            mdp.setContents(document);
            xmlContent = XmlUtils.marshaltoString(document);
            String regex = "[${]{2}?([a-zA-Z.]+)[}]";
            Pattern pattern = Pattern.compile(regex);
            //调用匹配器对象
            Matcher matcher = pattern.matcher(xmlContent);
            while (matcher.find()) {
                System.out.println(matcher.group());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
