package com.wx.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by
 *
 * @author You 1269859055@qq.com
 * @date 2018/5/22 17:53
 */
public class Util {

    /**
     * xml转换为map
     * @param request
     * @return
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException{
        Map<String, String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();

        InputStream ins = null;
        try {
            ins = request.getInputStream();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Document doc = null;
        try {
            doc = reader.read(ins);
            Element root = doc.getRootElement();

            List<Element> list = root.elements();

//            for (Element e : list) {
//                map.put(e.getName(), e.getText());
//            }

            for(Element element:list){
                //遍历的结果放到集合中
                map.put(element.getName(), element.getText());
                List<Element> elementsSon = element.elements();
                for(Element elementSon:elementsSon){
                    //遍历的结果放到集合中
                    map.put(elementSon.getName(), elementSon.getText());
                }
            }

            return map;
        } catch (DocumentException e1) {
            e1.printStackTrace();
        }finally{
            ins.close();
        }

        return null;
    }
}
