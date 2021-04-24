package com.nnk.springboot.controllers;

import com.nnk.springboot.exceptions.NonexistentException;
import com.nnk.springboot.model.Trade;
import com.nnk.springboot.service.TradeService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TradeRestController {

  private static final Logger logger = LogManager.getLogger(TradeRestController.class);

  @Autowired
  private TradeService tradeService;

  /**
   * Read - Get all trades
   * 
   * @return - An Iterable object of trades full filled
   */
  @GetMapping("/trades")
  public List<Trade> getTrades() {
    List<Trade> tradeList = new ArrayList<>();
    try {
      logger.info("Get request with the endpoint 'trades'");
      tradeList = tradeService.getTrades();
      logger.info(
          "response following the GET on the endpoint 'trades'.");
    } catch (Exception exception) {
      logger.error("Error in the TradeRestController in the method getTrades :"
          + exception.getMessage());
    }
    return tradeList;
  }

  /**
   * Add a new trade
   * 
   * @param trade An object trade
   * @return The trade object saved
   */
  @PostMapping("/trade")
  public Trade createTrade(@RequestBody Trade trade) {
    Trade newTrade = null;
    try {
      logger.info("Post request with the endpoint 'trade'");
      newTrade = tradeService.saveTrade(trade);
      logger.info(
          "response following the Post on the endpoint 'trade' with the given trade : {"
              + trade.toString() + "}");
    } catch (Exception exception) {
      logger.error("Error in the TradeRestController in the method createTrade :"
          + exception.getMessage());
    }
    return newTrade;
  }

  /**
   * Delete - Delete a trade
   * 
   * @param id An id
   * @return - The deleted trade
   */
  @DeleteMapping("/trade")
  public Trade deleteTrade(@RequestParam Integer id) {
    Trade trade = null;
    boolean existingTrade = false;
    try {
      logger.info("Delete request with the endpoint 'trade'");
      existingTrade = tradeService.tradeExist(id);
      if (existingTrade) {
        trade = tradeService.deleteTrade(id);
        logger.info(
            "response following the DELETE on the endpoint 'trade'.");
      }
    } catch (Exception exception) {
      logger.error("Error in the TradeRestController in the method deleteTrade :"
          + exception.getMessage());
    }
    if (!existingTrade) {
      logger.error("The trade with the id " + id + " doesn't exist.");
      throw new NonexistentException(
          "The trade with the id " + id + " doesn't exist.");
    }
    return trade;
  }

  /**
   * Update an existing trade from a given id
   * 
   * @param id    An id
   * @param trade A trade object with modifications
   * @return The updated trade object
   */
  @PutMapping("/trade/{id}")
  public Trade updateTrade(@PathVariable("id") final Integer id,
      @RequestBody Trade trade) {
    Trade tradeToUpdate = null;
    boolean existingTradeId = false;
    try {
      logger.info(
          "Put request of the endpoint 'trade' with the id : {" + id + "}");
      existingTradeId = tradeService.tradeExist(id);
      if (existingTradeId) {
        tradeToUpdate = tradeService.getTrade(id);
        logger.info(
            "response following the Put on the endpoint 'trade' with the given id : {"
                + id + "}");
        if (tradeToUpdate != null) {
          String account = trade.getAccount();
          if (account != null) {
            tradeToUpdate.setAccount(account);
          }
          String type = trade.getType();
          if (type != null) {
            tradeToUpdate.setType(type);
          }
          double buyQuantity = trade.getBuyQuantity();
          if (buyQuantity != 0) {
            tradeToUpdate.setBuyQuantity(buyQuantity);
          }
          double sellQuantity = trade.getSellQuantity();
          if (sellQuantity != 0) {
            tradeToUpdate.setSellQuantity(sellQuantity);
          }
          double buyPrice = trade.getBuyPrice();
          if (buyPrice != 0) {
            tradeToUpdate.setBuyPrice(buyPrice);
          }
          double sellPrice = trade.getSellPrice();
          if (sellPrice != 0) {
            tradeToUpdate.setSellPrice(sellPrice);
          }
          String benchmark = trade.getBenchmark();
          if (benchmark != null) {
            tradeToUpdate.setBenchmark(benchmark);
          }
          Timestamp tradeDate = trade.getTradeDate();
          if (tradeDate != null) {
            tradeToUpdate.setTradeDate(tradeDate);
          }
          String security = trade.getSecurity();
          if (security != null) {
            tradeToUpdate.setSecurity(security);
          }
          String status = trade.getStatus();
          if (status != null) {
            tradeToUpdate.setStatus(status);
          }
          String trader = trade.getTrader();
          if (trader != null) {
            tradeToUpdate.setTrader(trader);
          }
          String book = trade.getBook();
          if (book != null) {
            tradeToUpdate.setBook(book);
          }
          String creationName = trade.getCreationName();
          if (creationName != null) {
            tradeToUpdate.setCreationName(creationName);
          }
          Timestamp creationDate = trade.getCreationDate();
          if (creationDate != null) {
            tradeToUpdate.setCreationDate(creationDate);
          }
          String revisionName = trade.getRevisionName();
          if (revisionName != null) {
            tradeToUpdate.setRevisionName(revisionName);
          }
          Timestamp revisionDate = trade.getRevisionDate();
          if (revisionDate != null) {
            tradeToUpdate.setRevisionDate(revisionDate);
          }
          String dealName = trade.getDealName();
          if (dealName != null) {
            tradeToUpdate.setDealName(dealName);
          }
          String dealType = trade.getDealType();
          if (dealType != null) {
            tradeToUpdate.setDealType(dealType);
          }
          String sourceListId = trade.getSourceListId();
          if (sourceListId != null) {
            tradeToUpdate.setSourceListId(sourceListId);
          }
          String side = trade.getSide();
          if (side != null) {
            tradeToUpdate.setSide(side);
          }
          tradeService.saveTrade(tradeToUpdate);
        }
      }
    } catch (Exception exception) {
      logger.error("Error in the TradeRestController in the method updateTrade :"
          + exception.getMessage());
    }
    if (!existingTradeId) {
      logger.error("The trade with the id " + id + " doesn't exist.");
      throw new NonexistentException("The trade with the id " + id + " doesn't exist.");
    }
    return tradeToUpdate;
  }

}
