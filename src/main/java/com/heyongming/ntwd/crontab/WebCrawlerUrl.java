package com.heyongming.ntwd.crontab;

import com.heyongming.ntwd.dao.PhrasePageDao;
import com.heyongming.ntwd.entity.PhrasePageEntity;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.Span;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.io.IOException;

/**
 * Created by hym on 14-6-29.
 */
public class WebCrawlerUrl extends Thread {

    public static boolean run = true;

    public static int total = 0;

    PhrasePageDao phrasePageDao;

    public WebCrawlerUrl(PhrasePageDao phrasePageDao) {
        this.phrasePageDao = phrasePageDao;
    }

    String domain = "http://chengyu.itlearner.com";

    @Override
    public void run() {
        total = 0;
        run = true;
        String[] chs = {"A", "B", "C", "D", "E", "F", "G",
                "H", "I", "J", "K", "L", "M", "N",
                "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        for (String ch : chs) {
            int nextPage = 1;
            while (true) {
                nextPage = getChengyu(ch, nextPage);
                if (nextPage == 0) {
                    break;
                }
            }
        }
        run = false;
    }

    private int getChengyu(String ch, int page) {
        int pageNum = -1;
        //http://chengyu.itlearner.com/list/A_1.html
        //创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        //HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

        HttpGet httpGet = new HttpGet("http://chengyu.itlearner.com/list/" + ch + "_" + page + ".html");
        System.out.println(httpGet.getRequestLine());
        try {
            //执行get请求
            HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
            //获取响应消息实体
            HttpEntity entity = httpResponse.getEntity();
            //响应状态
            System.out.println("status:" + httpResponse.getStatusLine());
            //判断响应实体是否为空
            if (entity != null) {
                String text = EntityUtils.toString(entity, "GBK");

                Parser parser = Parser.createParser(text, "UTF-8");
                NodeFilter currentPageFilter = new HasAttributeFilter("class", "curpage");
                NodeList nodePages = parser.extractAllNodesThatMatch(currentPageFilter);
                if (nodePages != null && nodePages.size() > 0) {
                    Span span = (Span) nodePages.elementAt(0);
                    pageNum = NumberUtils.toInt(span.getFirstChild().getText());
                }

                parser = Parser.createParser(text, "UTF-8");
                NodeFilter chengyuList = new HasAttributeFilter("class", "listw");
                NodeList nodes = parser.extractAllNodesThatMatch(chengyuList);
                if (nodes != null) {
                    for (int i = 0; i < nodes.size(); i++) {
                        Node textnode = (Node) nodes.elementAt(i);
                        System.out.println("getText:" + textnode.getText());
                        NodeList lis = textnode.getChildren().elementAt(0).getChildren();
                        for (int j = 0; j < lis.size(); j++) {
                            Node node = lis.elementAt(j).getFirstChild();
                            LinkTag a = (LinkTag) node;
                            System.out.println(domain + a.getLink() + " " + a.getLinkText());

                            PhrasePageEntity phrasePageEntity = new PhrasePageEntity();
                            phrasePageEntity.setPhrase(a.getLinkText());
                            phrasePageEntity.setPhraseUrl(domain + a.getLink());
                            phrasePageEntity.setPhraseInitial(ch);
                            phrasePageEntity.setPhrasePage(pageNum);
                            phrasePageDao.insertPhrasePageEntity(phrasePageEntity);
                            total++;
                        }
                    }
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭流并释放资源
                closeableHttpClient.close();
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        return pageNum + 1;
    }
}

