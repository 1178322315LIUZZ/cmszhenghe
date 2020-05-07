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
public class EsText {
	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;
	@Autowired
	ArticleLight articleLight;
	@Autowired
	ArticleDao articleDao;
	/**
	 * 
	 * @Title: test 
	 * @Description: 插入数据
	 * @return: void
	 */
	@Test
	public void test() {
		List<Article> list = articleDao.select(null);
		articleLight.saveAll(list);
	}
	/**
	 * 
	 * @Title: test1 
	 * @Description:读取数据
	 * @return: void
	 */
	@Test
	public void test1() {
		Iterable<Article> all = articleLight.findAll();
		for (Article article : all) {
			System.out.println(article);
		}
	}
}