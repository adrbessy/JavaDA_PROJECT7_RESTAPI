package com.nnk.springboot.service;

import com.nnk.springboot.model.Bid;
import java.util.List;
import java.util.Optional;

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

  /**
   * Check if the given bid id exists.
   * 
   * @param id The bid id
   * @return true if the id exists, otherwise returns false
   */
  boolean bidExist(Integer id);

  /**
   * Delete a bid
   * 
   * @param id An id
   * @return the deleted bid
   */
  Optional<Bid> deleteBid(Integer id);

  /**
   * Get a bid from an id
   * 
   * @param id The id of the bid table
   * @return The bid
   */
  Bid getBid(Integer id);

}
