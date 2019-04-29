package hcmute.edu.vn.cwservice.bootstrap;

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
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class InitDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    WebService webService;

    @Autowired
    CatWebService catWebService;

    @Autowired
    CatService catService;

    @Autowired
    ItemService itemService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        try {
//            crawlerWeb();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (FeedException e) {
//            e.printStackTrace();
//        }
    }

    @Transactional
    Boolean crawlerWeb() throws IOException, FeedException {
        List<CatWeb> catWebList = catWebService.retrieveAll();
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
                    Elements content = doc.getElementsByClass(web.getClassContent());
                    String t = "";
                    for (Element h : content) {
                        t = h.toString();
                    }
                    item.setFullDesc(t);
                    itemService.createItem(item, cat.getId());
                    //System.out.println(t);
                   // System.out.println("Title : " + entry.getTitle());
                    System.out.println("Link  : " + entry.getLink());
                   // System.out.println("Decription  : " + entry.getDescription().getValue());
                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                    String strDate = formatter.format(entry.getPublishedDate());
                    // System.out.println("Date Format with MM/dd/yyyy : "+strDate);

                } else {

                    i++;
                }
//                else {
//                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//                    String strDate = formatter.format(entry.getUpdatedDate());
//                    System.out.println("Date Format with MM/dd/yyyy : "+strDate);
//                }


            }
            System.out.println("**************************************************-----------------------------");
        }
        System.out.println(i);
        return true;
    }

}
