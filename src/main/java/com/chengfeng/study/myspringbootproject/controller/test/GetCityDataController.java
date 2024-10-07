package com.chengfeng.study.myspringbootproject.controller.test;

import com.chengfeng.study.myspringbootproject.common.ResponseResult;
import com.chengfeng.study.myspringbootproject.pojo.Area;
import lombok.SneakyThrows;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * GetCityDataController class
 *
 * @author chengfeng
 * @date 2022/2/20 /0020 18:28
 */
@RestController
public class GetCityDataController {

    @SneakyThrows
    @RequestMapping("/getCity")
    public ResponseResult getCity() {

        //国家统计局地址
        String url = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2021/index.html";

        //链接到目标地址
        Connection connect = Jsoup.connect(url);
        //设置useragent,设置超时时间，并以get请求方式请求服务器
        Document document = null;

        {
            try {
                document = connect.userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)").
                        timeout(6000).ignoreContentType(true).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //获取省份列表
        assert document != null;
        ListIterator<Element> elements = document.getElementsByClass("provincetr").listIterator();
        List<Object> areaList = new ArrayList<>();
        int i = 0;
        while (elements.hasNext()) {
            Element tr = elements.next();
            for (Element element : tr.getElementsByTag("td")) {
                Area province = new Area();
                i++;
                province.setName(element.text());
                province.setId(i);
                province.setParent_id(0);
                areaList.add(province);
                Elements a1 = element.getElementsByTag("a");
                url = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2021/" + a1.attr("href");
                connect = Jsoup.connect(url);
                document = connect.
                        timeout(6000).ignoreContentType(true).get();

                //获取省份下得城市
                for (Element value : document.getElementsByClass("citytr")) {
                    ListIterator<Element> couna = value.getElementsByTag("td").listIterator();
                    Area county = new Area();
                    while (couna.hasNext()) {
                        Element a = couna.next();
                        i++;
                        county.setParent_id(province.getId());
                        county.setId(i);
                        county.setName(a.text().trim());

                        url = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2019/" + a.getElementsByTag("a").attr("href");
                    }
                    areaList.add(county);

                    connect = Jsoup.connect(url);
                    document = connect.userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)").
                            timeout(6000).ignoreContentType(true).get();

                    ListIterator<Element> countys = document.getElementsByClass("countytr").listIterator();
                    ListIterator<Element> towns = document.getElementsByClass("towntr").listIterator();


                }
            }
        }

        System.out.println(areaList);

        ResponseResult result = new ResponseResult();
        result.setSuccess(true);
        result.setData(areaList);

        return result;
    }

    public static void main(String[] args) {
        //国家统计局地址
        String url = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2021/index.html";

        //链接到目标地址
        Connection connect = Jsoup.connect(url);
        //设置useragent,设置超时时间，并以get请求方式请求服务器
        Document document = null;

        {
            try {
                document = connect.userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)").
                        timeout(6000).ignoreContentType(true).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            //获取省份列表
            assert document != null;
            ListIterator<Element> elements = document.getElementsByClass("provincetr").listIterator();
            List<Object> areaList = new ArrayList<>();
            int i = 0;
            while (elements.hasNext()) {
                Element tr = elements.next();
                for (Element element : tr.getElementsByTag("td")) {
                    Area province = new Area();
                    i++;
                    province.setName(element.text());
                    province.setId(i);
                    province.setParent_id(0);
                    areaList.add(province);
                    Elements a1 = element.getElementsByTag("a");
                    url = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2021/" + a1.attr("href");
                    connect = Jsoup.connect(url);
                    document = connect.
                            timeout(6000).ignoreContentType(true).get();

                    //获取省份下得城市
                    for (Element value : document.getElementsByClass("citytr")) {
                        ListIterator<Element> couna = value.getElementsByTag("td").listIterator();
                        Area county = new Area();
                        while (couna.hasNext()) {
                            Element a = couna.next();
                            i++;
                            county.setParent_id(province.getId());
                            county.setId(i);
                            county.setName(a.text().trim());

                            url = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2019/" + a.getElementsByTag("a").attr("href");
                        }
                        areaList.add(county);

                        connect = Jsoup.connect(url);
                        document = connect.userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)").
                                timeout(6000).ignoreContentType(true).get();

                        ListIterator<Element> countys = document.getElementsByClass("countytr").listIterator();

                        for (Element item : document.getElementsByClass("towntr")) {
                            ListIterator<Element> couna1 = item.getElementsByTag("td").listIterator();

                            Area county1 = new Area();
                            int countIndex = 1;
                            while (couna1.hasNext()) {
                                Element a = couna1.next();
                                if (countIndex == 1) {
                                    countIndex++;
                                    county1.setId(Integer.parseInt(a.text().trim()));
                                } else {
                                    county1.setName(a.text().trim());
                                }
                            }
                            county1.setParent_id(county.getId());
                            System.out.println("county1 = " + county1);
                        }

                    }
                }
            }

            System.out.println(areaList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
