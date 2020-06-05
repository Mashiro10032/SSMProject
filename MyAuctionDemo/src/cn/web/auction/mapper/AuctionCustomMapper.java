package cn.web.auction.mapper;

import java.util.List;

import cn.web.auction.pojo.Auction;

public interface AuctionCustomMapper {
	
	public List<Auction> findAuctions(Auction condition);

	public Auction findAuctionAndRecordListById(int auctionid);
	
}