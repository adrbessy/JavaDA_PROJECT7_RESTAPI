package com.nnk.springboot.controllers;

import com.nnk.springboot.model.Bid;
import com.nnk.springboot.service.BidService;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
      logger.error("Error in the BidController in the method getBids :"
          + exception.getMessage());
    }
    return bidList;
  }

}
