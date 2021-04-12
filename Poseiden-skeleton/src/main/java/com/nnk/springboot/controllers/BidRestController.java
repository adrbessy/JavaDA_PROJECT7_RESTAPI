package com.nnk.springboot.controllers;

import com.nnk.springboot.exceptions.IsForbiddenException;
import com.nnk.springboot.model.Bid;
import com.nnk.springboot.service.BidService;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BidRestController {

  private static final Logger logger = LogManager.getLogger(BidRestController.class);

  @Autowired
  private BidService bidService;

  /**
   * Read - Get all bids
   * 
   * @return - An Iterable object of bids full filled
   */
  @GetMapping("/bids")
  public List<Bid> getBids() {
    List<Bid> bidList = new ArrayList<>();
    try {
      logger.info("Get request with the endpoint 'bids'");
      bidList = bidService.getBids();
      logger.info(
          "response following the GET on the endpoint 'bids'.");
    } catch (Exception exception) {
      logger.error("Error in the BidRestController in the method getBids :"
          + exception.getMessage());
    }
    return bidList;
  }

  /**
   * Add a new bid
   * 
   * @param bid An object bid
   * @return The bid object saved
   */
  @PostMapping("/bid")
  public Bid createBid(@RequestBody Bid bid) {
    Bid newBid = null;
    if (bid.getAccount() == null || bid.getType() == null) {
      logger.error("The new bid has to get an account with an type.");
      throw new IsForbiddenException(
          "The new bid has to get an account with an type.");
    }
    try {
      logger.info("Post request with the endpoint 'bid'");
      newBid = bidService.saveBid(bid);
      logger.info(
          "response following the Post on the endpoint 'bid' with the given bid : {"
              + bid.toString() + "}");
    } catch (Exception exception) {
      logger.error("Error in the BidRestController in the method createBid :"
          + exception.getMessage());
    }
    return newBid;
  }

}
