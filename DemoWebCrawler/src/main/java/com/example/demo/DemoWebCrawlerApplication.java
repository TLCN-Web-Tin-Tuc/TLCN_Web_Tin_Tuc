package com.example.demo;

import com.rometools.rome.io.FeedException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DemoWebCrawlerApplication {

    public static void main(String[] args) throws IOException, FeedException {
        SpringApplication.run(DemoWebCrawlerApplication.class, args);
//        RSSFeedParser parser = new RSSFeedParser(
//                "https://thanhnien.vn/rss/phap-luat/trong-an.rss");
//        Feed feed = parser.readFeed();
//        //System.out.println(feed);
//        for (FeedMessage message : feed.getFeedMessages()) {
//           // String str1 = message.getDescription();
//            String str2 = message.getTitle();
////            String[] arStr = str1.split("\"");
//
//         //  System.out.println(message.getDescription());
//
//            System.out.println(message.getLink());
//
//            System.out.println();
//            System.out.println();
////            System.out.println(arStr[1]);
////            System.out.println(arStr[3]);
////
////            String[] arStr1 = str1.split(">");
////
////                System.out.println(arStr1[3]);

      //  }

       // String url = "https://stackjava.com/feed";
   //String url = "https://www.24h.com.vn/upload/rss/trangchu24h.rss";
//   String url = "https://www.tienphong.vn/rss/xa-hoi-2.rss";
//        URL feedUrl = new URL(url);
//        SyndFeedInput input = new SyndFeedInput();
//        SyndFeed feed = input.build(new XmlReader(feedUrl));
//        for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {
//            System.out.println("-----------------------------");
//            System.out.println("Title : " + entry.getTitle());
//
//           // Date date = new Date(String.valueOf(entry.getPublishedDate()));
//            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//           String strDate = formatter.format(entry.getPublishedDate());
//          //  System.out.println("Date Format with MM/dd/yyyy : "+strDate);
//
//            System.out.println("Author: "+strDate);
//            System.out.println("Link  : "+entry.getLink());
//            System.out.println("Decription  : "+entry.getDescription().getValue());
//            String str1 = entry.getDescription().getValue();
           // String[] arStr1 = str1.split(">");
          //  System.out.println(arStr1[arStr1.length - 1]);
           // System.out.println(entry);
//        }
    }
}
