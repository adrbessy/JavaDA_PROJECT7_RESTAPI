package com.nnk.springboot.service;

import com.nnk.springboot.model.Trade;
import java.util.List;
import java.util.Optional;

public interface TradeService {

  /**
   * Get all trades
   * 
   * @return all trades
   */
  List<Trade> getTrades();

  /**
   * Save a trade
   * 
   * @param trade A trade to save
   * @return the saved trade
   */
  Trade saveTrade(Trade trade);

  /**
   * Check if the given trade id exists.
   * 
   * @param id The trade id
   * @return true if the id exists, otherwise returns false
   */
  boolean tradeExist(Integer id);

  /**
   * Delete a trade
   * 
   * @param id An id
   * @return the deleted trade
   */
  Optional<Trade> deleteTrade(Integer id);

  /**
   * Get a trade from an id
   * 
   * @param id The id of the trade table
   * @return The bid
   */
  Trade getTrade(Integer id);

}
