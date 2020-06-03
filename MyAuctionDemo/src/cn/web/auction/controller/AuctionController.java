package cn.web.auction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.web.auction.pojo.Auction;
import cn.web.auction.service.AuctionService;

@Controller
@RequestMapping("/auction")
public class AuctionController {
	@Autowired
	AuctionService auctionService;
	
	public static final int PAGE_SIZE=10;
	
	@RequestMapping("/queryAuctions")
	public ModelAndView queryAuction(@RequestParam(defaultValue = "1",required = false,value = "pageNum") int pageNum) {
		//modelAndView:数据在前端和后端之间的载体
		ModelAndView modelAndView = new ModelAndView();
		//pageNum:当前页码
		//pageSize:每页显示多少条
		PageHelper.startPage(pageNum,PAGE_SIZE);
		List<Auction> auctionsList = auctionService.findAuctions();
		modelAndView.addObject("auction",auctionsList);
		PageInfo pageInfo = new PageInfo<>(auctionsList);
		modelAndView.addObject("pageInfo",pageInfo);
		//指定modeAndView
		modelAndView.setViewName("index");
		return modelAndView;
	}
}
