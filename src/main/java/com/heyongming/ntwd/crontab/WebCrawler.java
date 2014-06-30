package com.heyongming.ntwd.crontab;

import com.heyongming.ntwd.dao.PhraseDao;
import com.heyongming.ntwd.dao.PhrasePageDao;
import com.heyongming.ntwd.entity.PhraseEntity;
import com.heyongming.ntwd.entity.PhrasePageEntity;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.io.IOException;
import java.util.List;

/**
 * Created by hym on 14-6-29.
 */
public class WebCrawler extends Thread {
    public static boolean run = true;

    public static int total = 0;

    PhrasePageDao phrasePageDao;
    PhraseDao phraseDao;

    public WebCrawler() {
    }

    public WebCrawler(PhrasePageDao phrasePageDao, PhraseDao phraseDao) {
        this.phrasePageDao = phrasePageDao;
        this.phraseDao = phraseDao;
    }

    @Override
    public void run() {
        List<PhrasePageEntity> list = phrasePageDao.getPhrasePageEntities();
        for (PhrasePageEntity phrasePageEntity : list) {
            getChengyu(phrasePageEntity.getPhraseUrl(), phrasePageEntity.getPhrase());
            total++;
        }
    }

    private void getChengyu(String url, String phrase) {
        int pageNum = -1;
        //创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        //HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

        HttpGet httpGet = new HttpGet(url);
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
                NodeFilter divFilter = new HasAttributeFilter("id", "main");
                NodeList nodeDivs = parser.extractAllNodesThatMatch(divFilter);
                if (nodeDivs != null && nodeDivs.size() > 0) {
                    Div div = (Div) nodeDivs.elementAt(0);
                    TableTag table = (TableTag) div.getChildren().elementAt(1);
                    TableRow[] trs = table.getRows();
                    PhraseEntity phraseEntity = new PhraseEntity();
                    for (TableRow tr : trs) {

                        TableColumn[] tds = tr.getColumns();
                        if ("词目".equals(tds[0].getFirstChild().getText())) {
                            phraseEntity.setPhrase(phrase);
                        }

                        if ("发音".equals(tds[0].getFirstChild().getText())) {
                            if (tds[1].getChildren() != null && tds[1].getChildren().size() > 0) {
                                phraseEntity.setPhraseSpell(tds[1].getFirstChild().getText());
                            } else {
                                phraseEntity.setPhraseSpell("");
                            }
                        }

                        if ("释义".equals(tds[0].getFirstChild().getText())) {
                            if (tds[1].getChildren() != null && tds[1].getChildren().size() > 0) {
                                phraseEntity.setPhraseParaphrase(tds[1].getFirstChild().getText());
                            } else {
                                phraseEntity.setPhraseParaphrase("");
                            }
                        }

                        if ("出处".equals(tds[0].getFirstChild().getText())) {
                            if (tds[1].getChildren() != null && tds[1].getChildren().size() > 0) {
                                phraseEntity.setPhraseProvenance(tds[1].getFirstChild().getText());
                            } else {
                                phraseEntity.setPhraseProvenance("");
                            }
                        }

                        if ("示例".equals(tds[0].getFirstChild().getText())) {
                            if (tds[1].getChildren() != null && tds[1].getChildren().size() > 0) {
                                phraseEntity.setPhraseDemo(tds[1].getChildren().toHtml());
                            } else {
                                phraseEntity.setPhraseDemo("");
                            }
                        }
                    }
                    phraseDao.insertPhraseEntity(phraseEntity);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭流并释放资源
                closeableHttpClient.close();
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

    public static void main(String[] args) {
        WebCrawler webCrawler = new WebCrawler();
        webCrawler.getChengyu("http://chengyu.itlearner.com/cy15/15935.html", "东声西击");
    }

}
