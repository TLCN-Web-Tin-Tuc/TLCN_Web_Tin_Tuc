package hcmute.edu.vn.cwservice.bootstrap;

import hcmute.edu.vn.cwservice.entity.Cat;
import hcmute.edu.vn.cwservice.entity.CatWeb;
import hcmute.edu.vn.cwservice.entity.CatWebId;
import hcmute.edu.vn.cwservice.entity.Web;
import hcmute.edu.vn.cwservice.exception.NotFoundException;
import hcmute.edu.vn.cwservice.service.CatService;
import hcmute.edu.vn.cwservice.service.CatWebService;
import hcmute.edu.vn.cwservice.service.ItemService;
import hcmute.edu.vn.cwservice.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;

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

        Web web1 = createWebIfNotFound("Vn Express");
        Web web2 = createWebIfNotFound("Thanh niên");
        createCatIfNotFound("Thời Sự", "https://thanhnien.vn/rss/viet-nam.rss", "https://vnexpress.net/rss/thoi-su.rss");
        createCatIfNotFound("Thế Giới", "https://thanhnien.vn/rss/the-gioi.rss", "https://vnexpress.net/rss/the-gioi.rss");
        createCatIfNotFound("Kinh Doanh", "https://thanhnien.vn/rss/kinh-doanh.rss", "https://vnexpress.net/rss/kinh-doanh.rss");
        createCatIfNotFound("Đời Sống", "https://thanhnien.vn/rss/doi-song.rss", "https://vnexpress.net/rss/gia-dinh.rss");
        createCatIfNotFound("Thể Thao", "https://thethao.thanhnien.vn/rss/home.rss", "https://vnexpress.net/rss/the-thao.rss");
        createCatIfNotFound("Sức Khỏe", "https://thanhnien.vn/rss/doi-song/suc-khoe.rss", "https://vnexpress.net/rss/suc-khoe.rss");
        createCatIfNotFound("Du Lịch", "https://vnexpress.net/rss/du-lich.rss", "https://thanhnien.vn/rss/doi-song/du-lich.rss");

    }

    @Transactional
    Web createWebIfNotFound(String webtitle) {
        Web webb;
        try {
            webb = webService.retrieveWebByTitle(webtitle);
        } catch (NotFoundException ex) {
            Web newWeb = new Web();
            if(webtitle == "Vn Express") {
                newWeb.setClassContent("content_detail");
                newWeb.setUrl("https://vnexpress.net");
                newWeb.setTitle("Vn Express");
            }

            if(webtitle == "Thanh niên") {
                newWeb.setClassContent("pswp-content");
                newWeb.setUrl("https://thanhnien.vn");
                newWeb.setTitle("Thanh niên");
            }
            return webService.getRepo().save(newWeb);
        }
        return webb;
    }

    @Transactional
    boolean createCatIfNotFound(String catName, String thanhNienRSS, String vnExpressRSS) {
        Cat cat = new Cat() ;
        try {
            cat = catService.retrieveCatByName(catName);
        } catch (NotFoundException ex) {
            cat.setName(catName);
            cat.setCheckCat(1);
            cat.setDateCreated(new Date());
            cat.setParentId((long) 0);
            cat.setUserCreated("admin");
            Cat catCreate = catService.createCat(cat);
            Web webThanhNien = webService.retrieveWebByTitle("Thanh niên");
            Web webVNExpress = webService.retrieveWebByTitle("Vn Express");
            CatWeb catWeb = new CatWeb();
            CatWebId catWebId = new CatWebId();
            catWebId.setCat(catCreate);
            catWebId.setWeb(webThanhNien);
            catWeb.setId(catWebId);
            catWeb.setRssLink(thanhNienRSS);
            catWebService.createCatWeb(catWeb);
            catWebId.setWeb(webVNExpress);
            catWeb.setRssLink(vnExpressRSS);
            catWeb.setId(catWebId);
            catWebService.createCatWeb(catWeb);
        }
        return true;
    }
}
