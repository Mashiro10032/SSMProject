package cn.web.auction.service;

import java.util.List;

import cn.web.auction.pojo.Auction;
import cn.web.auction.pojo.Auctionrecord;

public interface AuctionService {
	
	public List<Auction> findAuctions(Auction condition);

	public Auction findAuctionAndRecordListById(int auctionid);

	public void addAuctionRecord(Auctionrecord record) throws Exception;

}
