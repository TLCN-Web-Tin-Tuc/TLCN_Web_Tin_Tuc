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
        Document doc = Jsoup.connect("https://thanhnien.vn/doi-song/xac-minh-thong-tin-nguoi-dan-giet-mo-loai-ca-quy-hiem-1078446.html").get();
        doc.select("details__author").remove();
        Elements content = doc.getElementsByClass("pswp-content");
        //content = content.removeClass("details__author");
        for( Element img : content.select("img") )
        {
            if(img.attr("data-src") != "") {
                img.attr("src", img.attr("data-src"));
                System.out.println(img.attr("src"));
            }
            System.out.println();
            // set attribute 'src' to 'your-source-here'

        }


        String t = "";
        for (Element h : content) {

            t = h.toString();

            System.out.println(t);
        }

        return true;
    }



}
