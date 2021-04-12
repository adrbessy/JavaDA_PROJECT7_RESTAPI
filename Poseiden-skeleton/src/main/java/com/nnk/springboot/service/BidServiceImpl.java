package com.nnk.springboot.service;

import com.nnk.springboot.model.Bid;
import com.nnk.springboot.repositories.BidRepository;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidServiceImpl implements BidService {

  private static final Logger logger = LogManager.getLogger(BidServiceImpl.class);

  @Autowired
  private BidRepository bidRepository;

  /**
   * Get all bids
   * 
   * @return all bids
   */
  @Override
  public List<Bid> getBids() {
    logger.debug("in the method getBankAccounts in the class BankAccountServiceImpl");
    List<Bid> bidList = new ArrayList<>();
    try {
      bidList = bidRepository.findAll();
    } catch (Exception exception) {
      logger.error("Error in the method getBids :" + exception.getMessage());
    }
    return bidList;
  }

  /**
   * Save a bid
   * 
   * @param bid A bid to save
   * @return the saved bid
   */
  @Override
  public Bid saveBid(Bid bid) {
    logger.debug("in the method saveBid in the class BidServiceImpl");
    Bid savedBid = null;
    try {
      savedBid = bidRepository.save(bid);
    } catch (Exception exception) {
      logger.error("Error when we try to save a bid :" + exception.getMessage());
    }
    return savedBid;
  }

}
