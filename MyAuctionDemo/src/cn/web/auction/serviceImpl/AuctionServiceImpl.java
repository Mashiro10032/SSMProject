package cn.web.auction.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.web.auction.mapper.AuctionMapper;
import cn.web.auction.mapper.AuctionRecordMapper;
import cn.web.auction.pojo.Auction;
import cn.web.auction.pojo.AuctionExample;
import cn.web.auction.pojo.Auctionrecord;
import cn.web.auction.service.AuctionService;
import cn.web.auction.mapper.AuctionCustomMapper;

@Service
public class AuctionServiceImpl implements AuctionService {
	
	@Autowired
	AuctionMapper auctionMapper;
	
	@Autowired
	AuctionCustomMapper auctionCustomMapper;
	
	@Autowired
	AuctionRecordMapper auctionRecordMapper;

	@Override
	public List<Auction> findAuctions(Auction condition) {
		// TODO Auto-generated method stub
		AuctionExample example = new AuctionExample();
		AuctionExample.Criteria criteria = example.createCriteria();
		if(condition!=null) {
			//商品名称 模糊查询
			if(condition.getAuctionname()!= null && !condition.getAuctionname().equals("")) {
				criteria.andAuctionnameLike("%"+condition.getAuctionname()+"%");
			}
			//商品描述 模糊查询
			if(condition.getAuctiondesc()!=null &&!condition.getAuctiondesc().equals("")) {
				criteria.andAuctionnameLike("%"+condition.getAuctiondesc()+"%");
			}
			//开始时间 大于
			if(condition.getAuctionstarttime()!=null) {
				criteria.andAuctionstarttimeGreaterThan(condition.getAuctionstarttime());
			}
			//结束时间 小于
			if(condition.getAuctionendtime()!=null) {
				criteria.andAuctionstarttimeLessThan(condition.getAuctionendtime());
			}
			//起拍价 大于
			if(condition.getAuctionstartprice()!=null) {
				criteria.andAuctionstartpriceGreaterThan(condition.getAuctionstartprice());
			}
			
		}
		example.setOrderByClause("auctionStartTime DESC");
		return auctionMapper.selectByExample(example);
	}

	@Override
	public Auction findAuctionAndRecordListById(int auctionid) {
		// TODO Auto-generated method stub
		return auctionCustomMapper.findAuctionAndRecordListById(auctionid);
	}

	@Override
	public void addAuctionRecord(Auctionrecord record) throws Exception {
		// TODO Auto-generated method stub
		Auction auction = auctionCustomMapper.findAuctionAndRecordListById(record.getAuctionid());
		if(auction.getAuctionendtime().after(new Date())== false) {
			throw new Exception("拍卖时间已过期!");
		}else {
			if(auction.getRecordList().size()>0) {   //有记录
				Auctionrecord maxRecord = auction.getRecordList().get(0);
				if(record.getAuctionprice() < maxRecord.getAuctionprice()) {
					throw new Exception("出价必须高于所有记录中的最高价!");
				}
			}else {   //无记录
				if(record.getAuctionprice() < auction.getAuctionstartprice()) {
					throw new Exception("出价必须高于起拍价!");
				}
			}
		}
		
	}




}
