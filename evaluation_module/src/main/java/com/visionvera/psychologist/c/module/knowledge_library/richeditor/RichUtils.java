package com.visionvera.psychologist.c.module.knowledge_library.richeditor;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by leo
 * on 2020/9/18.
 */

public class RichUtils {

    /**
     * 截取富文本中的图片链接
     */
    public static ArrayList<String> returnImageUrlsFromHtml(String content) {
        List<String> imageSrcList = new ArrayList<String>();
        if (TextUtils.isEmpty(content)) {
            return (ArrayList<String>) imageSrcList;
        }
        String htmlCode = content;
        Pattern p = Pattern.compile("<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic|\\b)\\b)[^>]*>", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(htmlCode);
        String quote = null;
        String src = null;
        while (m.find()) {
            quote = m.group(1);
            src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("//s+")[0] : m.group(2);
            imageSrcList.add(src);
        }

        return (ArrayList<String>) imageSrcList;
    }


    /**
     * 截取富文本中的纯文本内容
     */
    public static String returnOnlyText(String htmlStr) {
        if (TextUtils.isEmpty(htmlStr)) {
            return "";
        } else {
            String regFormat = "\\s*|\t|\r|\n";
            String regTag = "<[^>]*>";
            String text = htmlStr.replaceAll(regFormat, "").replaceAll(regTag, "");
            text = text.replace("&nbsp;", "");
            return text;
        }
    }


    public static boolean isEmpty(String htmlStr) {
        ArrayList<String> images = returnImageUrlsFromHtml(htmlStr);
        String text = returnOnlyText(htmlStr);
        if (TextUtils.isEmpty(text) && images.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    //从html中提取纯文本
    public static String Html2Text(String inputString) {
        String htmlStr = inputString; // 含html标签的字符串
        String textStr = "";
        Pattern p_script;
        Matcher m_script;
        Pattern p_style;
        Matcher m_style;
        Pattern p_html;
        Matcher m_html;
        try {
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签
            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签
            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签
            textStr = htmlStr;
        } catch (Exception e) {
            System.err.println("Html2Text: " + e.getMessage());
        }
        //剔除空格行
        textStr = textStr.replaceAll("[ ]+", " ");
        textStr = textStr.replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "");
        return textStr;// 返回文本字符串
    }
}
