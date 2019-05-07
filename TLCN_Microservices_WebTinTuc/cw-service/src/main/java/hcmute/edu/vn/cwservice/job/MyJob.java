package hcmute.edu.vn.cwservice.job;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import hcmute.edu.vn.cwservice.entity.Cat;
import hcmute.edu.vn.cwservice.entity.CatWeb;
import hcmute.edu.vn.cwservice.entity.Item;
import hcmute.edu.vn.cwservice.entity.Web;
import hcmute.edu.vn.cwservice.service.CatService;
import hcmute.edu.vn.cwservice.service.CatWebService;
import hcmute.edu.vn.cwservice.service.ItemService;
import hcmute.edu.vn.cwservice.service.WebService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class MyJob  {

    @Autowired
    WebService webService;

    @Autowired
    CatWebService catWebService;

    @Autowired
    CatService catService;

    @Autowired
    ItemService itemService;

    @Scheduled(fixedDelay = 600000)
    public void execute() {
        System.out.println("Run my Job: " + new Date());
        try {
            List<CatWeb> catWebList = new ArrayList<CatWeb>();
            catWebList = catWebService.retrieveAll();

            int i = 0;
            for (CatWeb catWeb : catWebList) {
                URL feedUrl = new URL(catWeb.getRssLink());
                SyndFeedInput input = new SyndFeedInput();
                SyndFeed feed = input.build(new XmlReader(feedUrl));
                Web web = webService.retieveWebByID(catWeb.getId().getWeb().getId());
                Cat cat = catService.retrieveCatById(catWeb.getId().getCat().getId());
                for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {
                    // System.out.println("-----------------------------");
                    if (entry.getPublishedDate() != null && entry.getLink() !=  null&& entry.getLink() != "") {
                        Item item = new Item();
                        item.setDecription(entry.getDescription().getValue());
                        item.setLinkOrigin(entry.getLink());
                        item.setTitle(entry.getTitle());
                        item.setStatus(2);
                        item.setOriginName(web.getTitle());
                        item.setDateUpdated(entry.getPublishedDate());
                        Document doc = Jsoup.connect(entry.getLink()).get();
                        if(web.getTitle()== "Thanh niên" && cat.getName() == "Thể Thao") {
                            web.setClassContent("details-content");
                        }
                        Elements content = doc.getElementsByClass(web.getClassContent());
                        String fullDesc = "";
                        String linkImages = "";
                        for( Element img : content.select("img") )
                        {
                            if(img.attr("data-src") != "") {
                                img.attr("src", img.attr("data-src"));
                            }
                        }
                        for (Element h : content) {
                            h.select("div.details__author , div.details__meta , div.details__morenews , article.story, video").remove();
                            fullDesc = h.toString();
                            linkImages = h.select("img[src^=http]:first-child").attr("abs:src");
                        }
                        item.setImage(linkImages);
                        item.setFullDesc(fullDesc);
                        if(fullDesc != "" && fullDesc != null && linkImages != "" && linkImages != null)
                            itemService.createItem(item, cat.getId());

                        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                        String strDate = formatter.format(entry.getPublishedDate());

                    } else {
                        i++;
                    }
                }
            }
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FeedException e) {
            e.printStackTrace();
        }
    }

}