package com.nikoer.aicarb;

import java.io.IOException;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.nikoer.aicarb.vo.UserInfoVO;

/**
 * Hello world!
 *
 */
public class App 
{
    private static HashMap<String,UserInfoVO> map = new HashMap<String,UserInfoVO>();
    public static void main( String[] args )
    {
    	UserInfoVO vo = UserInfoVO.create("sloppy", "bag_one,20");
    	map.put("userInfo", vo);
        System.out.println( "Hello World!" );
        System.out.println( Utils.toJson(map) );
        
        UserInfoVO v = Utils.readValue(Utils.toJson(vo), UserInfoVO.class);
        System.out.println(v.getName() );
        App.testHtml();
        App.testAccount();
        do {
        	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }while(true);
    }
    private static void testHtml() {
    	String html = "<html><head><title> 开源中国社区 </title></head>"
                + "<body><p> 这里是 jsoup 项目的相关文章 </p></body></html>";
    	Document doc = Jsoup.parse(html);
        System.out.println(doc.body());
        
		try {
			doc = Jsoup.connect("http://www.baidu.com").get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(doc==null) {
			return;
		}
        String title = doc.body().toString();
        System.out.println(title);
    }
    private static void testAccount() {
    	Account account = new Account("zhang san", 10000.0f);
    	AccountOperator accountOperator = new AccountOperator(account);

    	final int THREAD_NUM = 5;
    	Thread threads[] = new Thread[THREAD_NUM];
    	for (int i = 0; i < THREAD_NUM; i ++) {
    	   threads[i] = new Thread(accountOperator, "Thread" + i);
    	   threads[i].start();
    	}
    }
}
