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
    logger.debug("in the method getBids in the class BidServiceImpl");
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

  /**
   * Check if the given bid id exists.
   * 
   * @param id The bid id
   * @return true if the id exists, otherwise returns false
   */
  @Override
  public boolean bidExist(Integer id) {
    logger.debug("in the method bidExist in the class BidServiceImpl");
    boolean bidExist = false;
    try {
      bidExist = bidRepository.existsById(id);
    } catch (Exception exception) {
      logger.error("Error in the method bidExist :" + exception.getMessage());
    }
    return bidExist;
  }

  /**
   * Delete a bid
   * 
   * @param id An id
   * @return the deleted bid
   */
  @Override
  public Bid deleteBid(Integer id) {
    logger.debug("in the method deleteBid in the class BidServiceImpl");
    Bid bid = null;
    try {
      bid = bidRepository.findById(id);
      bidRepository.deleteById(id);
    } catch (Exception exception) {
      logger.error("Error in the method deleteBid :" + exception.getMessage());
    }
    return bid;
  }

  /**
   * Get a bid from an id
   * 
   * @param id The id of the bid table
   * @return The bid
   */
  @Override
  public Bid getBid(Integer id) {
    logger.debug("in the method getBid in the class BidServiceImpl");
    Bid bid = null;
    try {
      bid = bidRepository.findById(id);
    } catch (Exception exception) {
      logger.error("Error in the method getBid :" + exception.getMessage());
    }
    return bid;
  }

}
