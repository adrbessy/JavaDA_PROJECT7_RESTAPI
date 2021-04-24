package com.nnk.springboot.service;

import com.nnk.springboot.model.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeServiceImpl implements TradeService {

  private static final Logger logger = LogManager.getLogger(TradeServiceImpl.class);

  @Autowired
  private TradeRepository tradeRepository;

  /**
   * Get all trades
   * 
   * @return all trades
   */
  @Override
  public List<Trade> getTrades() {
    logger.debug("in the method getTrades in the class TradeServiceImpl");
    List<Trade> tradeList = new ArrayList<>();
    try {
      tradeList = tradeRepository.findAll();
    } catch (Exception exception) {
      logger.error("Error in the method getTrades :" + exception.getMessage());
    }
    return tradeList;
  }

  /**
   * Save a trade
   * 
   * @param trade A trade to save
   * @return the saved trade
   */
  @Override
  public Trade saveTrade(Trade trade) {
    logger.debug("in the method saveTrade in the class TradeServiceImpl");
    Trade savedTrade = null;
    try {
      savedTrade = tradeRepository.save(trade);
    } catch (Exception exception) {
      logger.error("Error when we try to save a trade :" + exception.getMessage());
    }
    return savedTrade;
  }

  /**
   * Check if the given trade id exists.
   * 
   * @param id The trade id
   * @return true if the id exists, otherwise returns false
   */
  @Override
  public boolean tradeExist(Integer id) {
    logger.debug("in the method tradeExist in the class TradeServiceImpl");
    boolean tradeExist = false;
    try {
      tradeExist = tradeRepository.existsById(id);
    } catch (Exception exception) {
      logger.error("Error in the method tradeExist :" + exception.getMessage());
    }
    return tradeExist;
  }

  /**
   * Delete a trade
   * 
   * @param id An id
   * @return the deleted trade
   */
  @Override
  public Trade deleteTrade(Integer id) {
    logger.debug("in the method deleteTrade in the class TradeServiceImpl");
    Trade trade = null;
    try {
      trade = tradeRepository.findById(id);
      tradeRepository.deleteById(id);
    } catch (Exception exception) {
      logger.error("Error in the method deleteTrade :" + exception.getMessage());
    }
    return trade;
  }

  /**
   * Get a trade from an id
   * 
   * @param id The id of the trade table
   * @return The bid
   */
  @Override
  public Trade getTrade(Integer id) {
    logger.debug("in the method getTrade in the class TradeServiceImpl");
    Trade trade = null;
    try {
      trade = tradeRepository.findById(id);
    } catch (Exception exception) {
      logger.error("Error in the method getTrade :" + exception.getMessage());
    }
    return trade;
  }

}
