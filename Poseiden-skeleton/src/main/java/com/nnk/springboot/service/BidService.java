package com.nnk.springboot.service;

import com.nnk.springboot.model.Bid;
import java.util.List;

public interface BidService {

  /**
   * Get all bids
   * 
   * @return all bids
   */
  List<Bid> getBids();

  /**
   * Save a bid
   * 
   * @param bid A bid to save
   * @return the saved bid
   */
  Bid saveBid(Bid bid);

}
