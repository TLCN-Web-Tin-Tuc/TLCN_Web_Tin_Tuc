package com.example.demo.job;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.io.IOException;

import java.util.Date;

public class MyJob implements Job  {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException{
        System.out.println("Run my Job: "+ new Date());
        try {
            retrievedata();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
