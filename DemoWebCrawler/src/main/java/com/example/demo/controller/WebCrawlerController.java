package com.example.demo.controller;

import com.example.demo.entity.Item;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/webcrawler/")
@CrossOrigin(origins = "http://localhost:4200")
public class WebCrawlerController {

    @GetMapping("/")
    public boolean retrievedata() throws IOException {
        Document doc = Jsoup.connect("https://thanhnien.vn/thoi-su/khoi-to-nguoi-nuoc-ngoai-cam-dau-duong-day-danh-bac-ngan-ti-qua-trang-fxx88com-1076308.html").get();
        Element content = doc.getElementById("main_detail");
        ArrayList<Item> newArrayList = new ArrayList<>();
//        for(Element content : contents) {
            Item newItem  = new Item();
//            Elements headings = content.getElementsByClass("story__heading");
//            for (Element h : headings) {
//
//                Elements a = h.getElementsByTag("a");
//                newItem.setTitle(a.text());
//                newItem.setLinkOrigin("https://baomoi.com" + a.attr("href"));
//
//            }
            System.out.println(content.toString());

//            Elements thumb = content.getElementsByClass("story__thumb");
//            for (Element h : thumb) {
//                Elements as = h.getElementsByTag("a");
//                for (Element a : as) {
//                    Elements imc = a.getElementsByTag("img");
//                    if(imc.attr("src").contains("https://"))
//                    newItem.setImage(imc.attr("src"));
//                }
//            }
//            New item = new New();
//            item = newService.retrieveByTitle(newItem.getTitle());
//            if(item == null && newItem.getTitle() != null && newItem.getImage() != null) {
//                newService.getRepo().save(newItem);
//                //newArrayList.add(item);
//            }
//        }

        return true;
    }



}
