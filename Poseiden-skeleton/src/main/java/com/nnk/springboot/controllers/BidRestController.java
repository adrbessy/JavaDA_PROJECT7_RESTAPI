package com.nnk.springboot.controllers;


import com.nnk.springboot.exceptions.IsForbiddenException;
import com.nnk.springboot.exceptions.NonexistentException;
import com.nnk.springboot.model.Bid;
import com.nnk.springboot.service.BidService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
      logger.error("The new bid has to get an account with a type.");
      throw new IsForbiddenException(
          "The new bid has to get an account with a type.");
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

  /**
   * Delete - Delete a bid
   * 
   * @param id An id
   * @return - The deleted bid
   */
  @DeleteMapping("/bid")
  public Optional<Bid> deleteBid(@RequestParam Integer id) {
    Optional<Bid> bid = null;
    boolean existingBid = false;
    try {
      logger.info("Delete request with the endpoint 'bid'");
      existingBid = bidService.bidExist(id);
      if (existingBid) {
        bid = bidService.deleteBid(id);
        logger.info(
            "response following the DELETE on the endpoint 'bid'.");
      }
    } catch (Exception exception) {
      logger.error("Error in the BidRestController in the method deleteBid :"
          + exception.getMessage());
    }
    if (!existingBid) {
      logger.error("The bid with the id " + id + " doesn't exist.");
      throw new NonexistentException(
          "The bid with the id " + id + " doesn't exist.");
    }
    return bid;
  }

  /**
   * Update an existing bid from a given id
   * 
   * @param id  An id
   * @param bid A bid object with modifications
   * @return The updated bid object
   */
  @PutMapping("/bid/{id}")
  public Bid updateBid(@PathVariable("id") final Integer id,
      @RequestBody Bid bid) {
    Bid bidToUpdate = null;
    boolean existingBidId = false;
    try {
      logger.info(
          "Put request of the endpoint 'bid' with the id : {" + id + "}");
      existingBidId = bidService.bidExist(id);
      if (existingBidId) {
        bidToUpdate = bidService.getBid(id);
        logger.info(
            "response following the Put on the endpoint 'bid' with the given id : {"
                + id + "}");
        if (bidToUpdate != null) {
          String account = bid.getAccount();
          if (account != null) {
            bidToUpdate.setAccount(account);
          }
          String type = bid.getType();
          if (type != null) {
            bidToUpdate.setType(type);
          }
          double bidQuantity = bid.getBidQuantity();
          if (bidQuantity != 0) {
            bidToUpdate.setBidQuantity(bidQuantity);
          }
          bidService.saveBid(bidToUpdate);
        }
      }
    } catch (Exception exception) {
      logger.error("Error in the BidRestController in the method updateBid :"
          + exception.getMessage());
    }
    if (!existingBidId) {
      logger.error("The bid with the id " + id + " doesn't exist.");
      throw new NonexistentException("The bid with the id " + id + " doesn't exist.");
    }
    return bidToUpdate;
  }

}
