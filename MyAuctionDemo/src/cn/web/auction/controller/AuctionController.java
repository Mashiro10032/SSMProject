package cn.web.auction.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.web.auction.pojo.Auction;
import cn.web.auction.pojo.Auctionrecord;
import cn.web.auction.pojo.User;
import cn.web.auction.service.AuctionService;

@Controller
@RequestMapping("/auction")
public class AuctionController {
	
	@Autowired
	AuctionService auctionService;
	
	@Autowired
	Model model;
	
	public static final int PAGE_SIZE = 5;
	@RequestMapping("/queryAuctions")
	public ModelAndView qusetAuctions(@RequestParam(defaultValue="1",required=false,value="pageNum")int pageNum,@ModelAttribute("condition") Auction condition) {
		ModelAndView modelAndView = new ModelAndView();
		//pageNum:当前的页码
		//pageSize:每页显示多少
		PageHelper.startPage(pageNum,PAGE_SIZE);
		List<Auction> auctionList = auctionService.findAuctions(condition);
		modelAndView.addObject("auctonList",auctionList);
		PageInfo<?> pageInfo = new PageInfo<>(auctionList);		
		modelAndView.addObject("pageInfo",pageInfo);
		
		//指定ModelAndView
		modelAndView.setViewName("index");

		return modelAndView;
	}
	
	@RequestMapping("/toDetail/{auction}")
	public ModelAndView toDetail(@PathVariable int auctionid) {
		Auction auction = auctionService.findAuctionAndRecordListById(auctionid);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auctionDetail",auction);
		modelAndView.setViewName("auctionDetail");
		return modelAndView;
	}
	
	@RequestMapping("/saveAuctionRecord")
	public String saveAuctionRecord(Auctionrecord record,HttpSession session) {
		try {
		User user = (User) session.getAttribute("user");
		record.setUserid(user.getUserid());
		record.setAuctiontime(new Date());
		auctionService.addAuctionRecord(record);
		} catch (Exception e){
			model.addAttribute("errorMsg",e.getMessage());
			return "error";
		}
		return "redirect:/auction/toDetail/"+record.getAuctionid();
		
	}
	
}
