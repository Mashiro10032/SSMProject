package cn.web.auction.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.web.auction.mapper.AuctionMapper;
import cn.web.auction.pojo.Auction;
import cn.web.auction.pojo.AuctionExample;
import cn.web.auction.service.AuctionService;

@Service
public class AuctionServiceImpl implements AuctionService {
@Autowired
AuctionMapper auctionMapper;
	@Override
	public List<Auction> findAuctions() {
		// TODO Auto-generated method stub
		AuctionExample example = new AuctionExample();
		return auctionMapper.selectByExample(example);

	}

}
