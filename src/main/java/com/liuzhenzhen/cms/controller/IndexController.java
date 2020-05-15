package com.liuzhenzhen.cms.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.liuzhenzhen.cms.dao.ArticleLight;
import com.liuzhenzhen.cms.entity.Article;
import com.liuzhenzhen.cms.entity.Category;
import com.liuzhenzhen.cms.entity.Channel;
import com.liuzhenzhen.cms.entity.Collect;
import com.liuzhenzhen.cms.entity.Comment;
import com.liuzhenzhen.cms.entity.ContentType;
import com.liuzhenzhen.cms.entity.Order;
import com.liuzhenzhen.cms.entity.Slide;
import com.liuzhenzhen.cms.entity.User;
import com.liuzhenzhen.cms.entity.Vote;
import com.liuzhenzhen.cms.service.ArticleService;
import com.liuzhenzhen.cms.service.ChannelService;
import com.liuzhenzhen.cms.service.CollectService;
import com.liuzhenzhen.cms.service.CommentService;
import com.liuzhenzhen.cms.service.SlideService;
import com.liuzhenzhen.cms.service.VoteService;
import com.liuzhenzhen.cms.util.HLUtils;
import com.zhenzhen.common.utils.NumberUtil;

@SuppressWarnings("all")
@Controller
public class IndexController {
	@Autowired
	private ChannelService channelService;
	@Autowired
	private ArticleService art;
	@Autowired
	private SlideService slide;
	@Autowired
	private CommentService comment;
	@Autowired
	private CollectService collects;
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private VoteService votes;
	@Autowired
	ThreadPoolTaskExecutor executor;
	@Autowired
	ArticleLight articleLight;
	//注入es依赖
	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;
	// 高亮搜索显示
	@RequestMapping("/search")
	public String search(String key,@RequestParam(defaultValue = "1")Integer page,Model m) {
		// 开始es高亮搜索
		// 想要高亮搜索,必须在cms中整合es
		// 1.导入依赖
		// 2.配置文件(检查配置文件的ip地址,和es的端口号)
		// 3.指定仓库接口的包扫描位置
		// 4.再指定的包中创建一个仓库接口,让这个接口继承elasticsearchrepository接口
		// 5.在实体类中指定库名,表名,主键id,还有存储字段
		// 计算搜索耗时
		//计算开始时间
		//实现
		long start = System.currentTimeMillis();
		//高亮显示
		PageInfo<Article> info = (PageInfo<Article>) HLUtils.findByHighLight(elasticsearchTemplate, Article.class, page, 3, new String[] {"title"}, "id", key);
		//计算结束时间
		long end = System.currentTimeMillis();
		//计算总耗时
		System.err.println("搜索共耗时:"+(end-start)+"毫秒");
		List<Article> lll = info.getList();
		m.addAttribute("g", lll);
		m.addAttribute("info", info);
		m.addAttribute("key", key);
		return "index/index";	
	}

	@RequestMapping(value = { "", "/", "index" })
	public String index(Model model, Article article, @RequestParam(defaultValue = "1") int page,HttpSession httpSession) {
		User user = (User) httpSession.getAttribute("user");
		if(user!=null) {
			redisTemplate.opsForValue().set("request.getSession().getId()", user.toString());
		}
		//redis优化左侧栏目
		List<Channel> range = redisTemplate.opsForList().range("cms_channels", 0, -1);
		if (range == null || range.size() == 0) {
			List<Channel> selects = channelService.selects();
			System.err.println("从mysql中查询到数据");
			redisTemplate.opsForList().rightPushAll("cms_channels", selects.toArray());
			model.addAttribute("channels", selects);
		} else {
			// 3.如果有
			System.err.println("从redis中查询了左侧栏目...");
			model.addAttribute("channels", range);
		}
		if (article.getChannelId() != null) {
			List<Category> sele = channelService.sele(article.getChannelId());
			model.addAttribute("categorys", sele);
		}
		List<Slide> li = slide.select();
		article.setStatus(1);
		List<Article> range2 = redisTemplate.opsForList().range("article", 0, -1);
		if (range2 == null || range2.size() == 0) {
			PageHelper.startPage(page, 2);
			List<Article> select = art.select(article);
			PageInfo<Article> pa = new PageInfo<Article>(select);
			redisTemplate.opsForList().rightPushAll("article", select.toArray());
			model.addAttribute("g", select);
			model.addAttribute("info", pa);
			System.err.println("从数据库查询出来热点文章");
		} else {
			System.err.println("从Redis中查询出来热点文章");
			PageInfo<Article> pa = new PageInfo<Article>(range2);
			model.addAttribute("g", range2);
			model.addAttribute("info", pa);
		}
		//redis优化最新文章
		List<Article> newarticles = (List<Article>) redisTemplate.opsForValue().get("new_article");
		if(newarticles==null || newarticles.size()==0) {
			System.err.println("从mysql查询最新文章");
			Article article2 = new Article();
			article2.setStatus(1);
			PageHelper.startPage(page, 3);
			List<Article> select2 = art.selectt(article2);
			redisTemplate.opsForValue().set("new_article", select2);
			redisTemplate.expire("new_article", 5, TimeUnit.MINUTES);
			model.addAttribute("ss", select2);
		}else {
			System.err.println("从redis查询最新文章");
			model.addAttribute("ss", newarticles);
		}
		
		model.addAttribute("li", li);
		// 问卷调查
		Article article3 = new Article();
		article3.setStatus(1);
		article3.setContentType(ContentType.VOTE);
		List<Article> ll = art.selectss(article3);
		model.addAttribute("ll", ll);
		//节目单查询
		PageHelper.startPage(page, 3);
		List<Order> order=art.order();
		model.addAttribute("order", order);
		return "index/index";
	}

	@RequestMapping("show")
	public String show(Article article, Model m, @RequestParam(defaultValue = "1") int page, HttpSession session,
			HttpServletRequest res) {
		List<Comment> list = comment.select(article);
		Article show = art.show(article);
		PageHelper.startPage(page, 5);
		List<Article> lis = art.coCount();
		PageInfo<Article> pages = new PageInfo<Article>(lis);
		User user = (User) session.getAttribute("user");
		Collect collect = null;
		if (user != null) {
			collect = collects.selectByTitleAndUserId(show.getTitle(), user.getId());
		}
		// 文章点击量加1
		// 获取文章的id
		Integer id = article.getId();
		// 获取访问的用户ip地址
		String addr = res.getRemoteAddr();
		// 拼接key值存入到redis中
		String redisKey = "Hits_" + id + "_" + addr;
		// 查询redis中是否存在key
		Boolean hasKey = redisTemplate.hasKey(redisKey);
		if (!hasKey) {
			executor.execute(new Runnable() {

				@Override
				public void run() {
					// 获取文章的id
					Integer hits = show.getHits();
					// 为当前文章点击量+1
					article.setHits(hits + 1);
					// 执行+1方法
					art.update(article);
					// 往Redis保存key为Hits_${文章ID}_${用户IP地址}，value为空值的记录，而且有效时长为5分钟。
					redisTemplate.opsForValue().set(redisKey, "", 5, TimeUnit.MINUTES);
					System.err.println("此文章点击量成功+1");
				}
			});
		}

		m.addAttribute("g", show);
		m.addAttribute("li", list);
		m.addAttribute("ss", lis);
		m.addAttribute("co", collect);
		return "index/show";
	}

	@RequestMapping("collect")
	@ResponseBody
	public int collect(Collect collect, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return 0;
		}
		collect.setUserId(user.getId());
		collect.setCreated(new Date());
		return collects.insert(collect);
	}

	@RequestMapping("deleteCollect")
	@ResponseBody
	public int deleteCollect(Integer id) {
		return collects.delete(id);
	}

	// 查询投票情况
	@RequestMapping("voteDetail")
	public String voteDetail(HttpSession session, Integer id, Model m) {
		Article article = art.sess(id);
		String content = article.getContent();
		Gson gson = new Gson();
		LinkedHashMap<Character, String> mapvote = gson.fromJson(content, LinkedHashMap.class);
		m.addAttribute("vote", mapvote);
		m.addAttribute("article", article);

		// 查询投票情况
		List<Vote> votess = votes.selects(article.getId());
		for (Vote vote : votess) {
			vote.setOption(mapvote.get(vote.getOption()));
			vote.setPercent(new BigDecimal(NumberUtil.getPercent(vote.getOptionNum(), vote.getTotalNum())));
		}
		m.addAttribute("vo", votess);
		return "index/voteDetail";
	}

	// 投票
	@ResponseBody
	@PostMapping("addVote")
	public int addVote(Vote vote, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (null == user) {
			return 0;// 没有登录的用户不能投票
		}
		vote.setUserId(user.getId());
		Vote vote2 = votes.select(vote);
		// 检查用户是否已经投过票
		if (vote2 != null) {
			return 0;
		}
		return votes.insert(vote);
	}

	// 添加点击量
	@RequestMapping("click")
	@ResponseBody
	public int click(HttpSession session, Integer id) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return 0;
		}
		return art.click(id);
	}
	//节目单详情
	@RequestMapping("order")
	public String order(Integer id,Model m,HttpSession session) {
		//根据id查询节目
		User admin = (User) session.getAttribute("admin");
		if(admin!=null) {
			Order order=art.orderid(id);
			m.addAttribute("order", order);
			return "adminorder";
		}else {
			Order order=art.orderid(id);
			m.addAttribute("order", order);
			return "userorder";
		}
	}
	/**
	 * 
	 * @Title: add 
	 * @Description: 添加明细表
	 * @param order
	 * @param file
	 * @return
	 * @throws Exception
	 * @return: String
	 */
	@RequestMapping("add")
	public String add(Order order,@RequestParam("f")MultipartFile file) throws Exception {
		if(file.getSize()>0) {
			String path="D:\\pic";
			String filename = file.getOriginalFilename();
			File file2 = new File(path, filename);
			file.transferTo(file2);
			order.setAddr(filename);
		}
		art.add(order);
		return "redirect:index";
	}
	//高亮显示
	@RequestMapping("gaoliang")
	public String gao(String gao,Model m,@RequestParam(defaultValue = "1")int pageNum) {
		PageInfo<Article> info = (PageInfo<Article>) HLUtils.findByHighLight(elasticsearchTemplate, Article.class, pageNum, 3, new String[] {"title"}, "id", gao);
		List<Article> list = info.getList();
		m.addAttribute("g", list);
		m.addAttribute("gao", gao);
		return "index/index";
	}
}
