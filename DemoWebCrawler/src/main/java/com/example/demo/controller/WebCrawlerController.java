package com.example.demo.controller;

import com.example.demo.model.New;
import com.example.demo.service.NewCategoryService;
import com.example.demo.service.NewService;
import com.example.demo.service.WebsiteUrlService;
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

    @Autowired
    private WebsiteUrlService websiteUrlService;

    @Autowired
    private NewService newService;

    @Autowired
    private NewCategoryService newCategoryService;

    @GetMapping("/")
    public boolean retrieveUserByIdOrEmail() throws IOException {
        Document doc = Jsoup.connect("https://baomoi.com/").get();
        Elements contents = doc.getElementsByClass("story");
        ArrayList<New> newArrayList = new ArrayList<>();
        for(Element content : contents) {
            New newItem  = new New();
            Elements headings = content.getElementsByClass("story__heading");
            for (Element h : headings) {
                Elements a = h.getElementsByTag("a");
                newItem.setTitle(a.text());
                newItem.setLinkOrigin("https://baomoi.com" + a.attr("href"));

            }

            Elements thumb = content.getElementsByClass("story__thumb");
            for (Element h : thumb) {
                Elements as = h.getElementsByTag("a");
                for (Element a : as) {
                    Elements imc = a.getElementsByTag("img");
                    if(imc.attr("src").contains("https://"))
                    newItem.setImage(imc.attr("src"));
                }
            }
            New item = new New();
            item = newService.retrieveByTitle(newItem.getTitle());
            if(item == null && newItem.getTitle() != null && newItem.getImage() != null) {
                newService.getRepo().save(newItem);
            }
        }

        return true;
    }
}
