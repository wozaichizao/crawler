package com.mobai.xhelper.util;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.net.URL;
import java.util.*;
import java.util.List;

public interface Monster {

    String GAKKI = "http://diaodiaode.me/rss/feed/36980";
    String TBBT12 = "http://diaodiaode.me/rss/feed/11005";

    /**
     * 获取某一集的磁力链接
     * @param rssUrl
     * @param episode
     * @return
     */
    static Map<String,String> getDownLoadUrlByEpisode(String rssUrl,Integer episode){
        Map<String,String> map = new HashMap<>();
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(new URL(rssUrl));
            Element rss = document.getRootElement();
            Element channel = rss.element("channel");
            List<Element> itemList = channel.elements("item");
            Element episodeItem = itemList.get(episode - 1);

            map.put("title",episodeItem.element("title").getText());
            map.put("magnet",episodeItem.element("magnet").getText());
            map.put("pan",episodeItem.element("pan").getText());
            map.put("ed2k",episodeItem.element("ed2k").getText());

            //将磁力链接复制到剪贴板
            Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable text = new StringSelection(map.get("magnet"));
            clip.setContents(text, null);

        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    static List<Map<String,String>> getDownLoadUrl(String rssUrl){

        List<Map<String,String>> list = new LinkedList<>();
        try {
            Map<String,String> map = new HashMap<>();
            SAXReader reader = new SAXReader();
            Document document = reader.read(new URL(rssUrl));
            Element rss = document.getRootElement();
            Element channel = rss.element("channel");
            List<Element> itemList = channel.elements("item");
            for (int i = 0; i < itemList.size(); i++) {
                Element episodeItem = itemList.get(i);
                map.put("title",episodeItem.element("title").getText());
                map.put("magnet",episodeItem.element("magnet").getText());
                map.put("pan",episodeItem.element("pan").getText());
                map.put("ed2k",episodeItem.element("ed2k").getText());
                list.add(map);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    static List<Map<String,String>> getDownLoadUrlS(String rssUrl,Integer season){

        List<Map<String,String>> list = new LinkedList<>();
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(new URL(rssUrl));
            Element rss = document.getRootElement();
            Element channel = rss.element("channel");
            List<Element> itemList = channel.elements("item");

            String se = "S"+season+"E";

            for (int i = 0; i < itemList.size(); i++) {

                Element episodeItem = itemList.get(i);
                if(episodeItem.element("title").getText().contains(se)){
                    Map<String,String> map = new HashMap<>();
                    map.put("title",episodeItem.element("title").getText());
                    map.put("magnet",episodeItem.element("magnet").getText());
                    //map.put("pan",episodeItem.element("pan").getText());
                    map.put("ed2k",episodeItem.element("ed2k").getText());
                    list.add(map);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
