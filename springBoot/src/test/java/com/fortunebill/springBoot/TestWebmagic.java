package com.fortunebill.springBoot;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

public class TestWebmagic implements PageProcessor{

	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

	@Override
	public Site getSite() {
		return site;
	}

	@Override
	public void process(Page page) {
//		page.addTargetRequests(page.getHtml().links().regex("(http://www.baidu\\.com/\\w+/\\w+)").all());
		Html html = page.getHtml();
		Document document = Jsoup.parse(html.get());
		Elements elements = document.getAllElements();
		List<String> list = elements.eachText();
		for (String string : list) {
			System.out.println(string);
		}
		
		
//		page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
//		page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
		if (page.getResultItems().get("name") == null) {
			// skip this page
			page.setSkip(true);
		}
		page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));

	}
	public static void main(String[] args) {
		Spider.create(new TestWebmagic()).addUrl("http://www.cnblogs.com/zyw-205520/p/3421687.html").thread(5).run();
	}
	
}
