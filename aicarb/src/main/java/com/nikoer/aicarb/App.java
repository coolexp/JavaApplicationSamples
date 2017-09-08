package com.nikoer.aicarb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.nikoer.aicarb.vo.UserInfoVO;
import com.nikoer.vo.ChildInfoData;

/**
 * Hello world!
 *
 */
@RestController
@EnableAutoConfiguration
public class App 
{
    private static HashMap<String,UserInfoVO> map = new HashMap<String,UserInfoVO>();
    
    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    
    public static void main( String[] args )
    {
    	System.out.println(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis()/1000);
        List<Map.Entry<String, Integer>> infoIds = App.caulcateBrandUseMore();
        for (int i = 0; i < infoIds.size(); i++) {
            String id = infoIds.get(i).getValue().toString();
            System.out.println(id);
        }
        ChildInfoData.ChildInfo.Builder child = ChildInfoData.ChildInfo.newBuilder();
        child.setAge(11);
        child.putArticles("1111", "{title:'article0'}");
        child.putArticles("2222", "{title:'article1'}");
//        child.setName("ss");
        try {
			JsonFormat.parser().merge("{name:'ss'}", child);
		} catch (InvalidProtocolBufferException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
        	System.out.println(JsonFormat.printer().print(child.build()));
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //System.out.println(child.build().toString());
        
        SpringApplication.run(App.class, args);
//        StringBuilder val = new StringBuilder();
//        App.testValue(val);
//        System.out.println(val);
//    	UserInfoVO vo = UserInfoVO.create("sloppy", "bag_one,20");
//    	map.put("userInfo", vo);
//        System.out.println( "Hello World!" );
//        System.out.println( Utils.toJson(map) );
//        
//        UserInfoVO v = Utils.readValue(Utils.toJson(vo), UserInfoVO.class);
//        System.out.println(v.getName() );
//        App.testHtml();
//        App.testAccount();
//        do {
//        	try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//        }while(true);
    }
    private static void testValue(StringBuilder value) {
    	value.delete(0, value.length()).append("state");
    }
    private  static List<Map.Entry<String, Integer>> caulcateBrandUseMore(){
    	Map<String,Integer> brands =new  HashMap<String,Integer>();
    	brands.put("a", 1);
    	brands.put("b", 4);
    	brands.put("c", 2);
    	List<Map.Entry<String, Integer>> infoIds =
    		    new ArrayList<Map.Entry<String, Integer>>(brands.entrySet());
    	
    	Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {   
    	    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {      
    	        return (o2.getValue() - o1.getValue()); 
    	    }
    	}); 

    	return infoIds;
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
