package com.adun.test_jsonp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * jsonp解析html
 *
 * @author Zhu Dunfeng
 * @date 2021/9/1 23:24
 */
public class JsoupParseHtml {

    public static void main(String[] args) throws Exception {

        List<Good> goodList = getGoodListFromJD("电脑");
        goodList.forEach(System.out::println);


    }


    public static List<Good> getGoodListFromJD(String keywords) throws Exception {
        //https://search.jd.com/Search?keyword=java
        String url="https://search.jd.com/Search?keyword="+keywords;

        Document document = Jsoup.parse(new URL(url), 30000);
        Element goodsListDiv = document.getElementById("J_goodsList");
        Elements liElements = goodsListDiv.getElementsByTag("li");

        List<Good> goodList = new ArrayList<>();

        for (Element el : liElements) {

            String img = el.getElementsByTag("img").get(0).attr("data-lazy-img");
            String name = el.getElementsByClass("p-name").text();
            String price = el.getElementsByClass("p-price").text();
            Good good = new Good();
            good.setImg(img);
            good.setName(name);
            good.setPrice(price);
            goodList.add(good);

        }

        return goodList;
    }




}
