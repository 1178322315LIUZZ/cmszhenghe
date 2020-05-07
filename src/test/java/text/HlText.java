package text;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liuzhenzhen.cms.dao.ArticleDao;
import com.liuzhenzhen.cms.dao.ArticleLight;
import com.liuzhenzhen.cms.entity.Article;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class HlText {
	@Autowired
	ArticleDao articleDao;
	@Autowired
	ElasticsearchTemplate  elasticsearchTemplate;
	@Autowired
	ArticleLight articleLight;
	@Test
	public void test() {
		//查询全部文章
		List<Article> articles = articleDao.select(null);
		//讲文章添加到es数据库中
		articleLight.saveAll(articles);
	}

}