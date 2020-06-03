package cn.web.auction.service;

import java.util.List;

import cn.web.auction.pojo.Auction;

public interface AuctionService {
	//列表的展示
	public List<Auction> findAuctions();
	
}
