package text;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.SimpleFormatter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.liuzhenzhen.cms.entity.Article;
import com.zhenzhen.common.utils.DateUtil;
import com.zhenzhen.common.utils.FileUtilIO;
import com.zhenzhen.common.utils.RandomUtil;
import com.zhenzhen.common.utils.StreamUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
@SuppressWarnings("all")
public class ArticleText {
	@Autowired
	KafkaTemplate kafkaTemplate;
	@Test
	public void test() throws Exception {
		File file = new File("D:\\文章");
		File[] files = file.listFiles();
		List<Article> list=new ArrayList<Article>();
		for (File file2 : files) {
			Article article = new Article();
			String title = file2.getName();
			title = title.replace(".txt", "");
			article.setTitle(title);
			String content = FileUtilIO.readFile(file2, "utf-8");
			article.setContent(content);
			GregorianCalendar gre=new GregorianCalendar(2019, 1, 1);
			Date date = gre.getTime();
			Date date2 = DateUtil.randomDate(date, new Date());
			article.setCreated(date2);
			//是否热门随机
			int jj = RandomUtil.random(0, 1);
			article.setHot(jj);
			//点击量随机
			int j = RandomUtil.random(0, 100);
			article.setHits(j);
			list.add(article);
			String jsonString = JSON.toJSONString(article);
			kafkaTemplate.send("ssn", "art="+jsonString);
		}
	}

}
