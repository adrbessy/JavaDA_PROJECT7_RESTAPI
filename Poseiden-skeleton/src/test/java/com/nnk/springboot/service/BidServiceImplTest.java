package com.nnk.springboot.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import com.nnk.springboot.model.Bid;
import com.nnk.springboot.repositories.BidRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest()
public class BidServiceImplTest {

  @Autowired
  private BidService bidService;

  @MockBean
  private BidRepository bidRepositoryMock;

  private Bid bid;

  /**
   * test to get all the bids.
   * 
   */
  @Test
  public void testGetBids() {
    bid = new Bid();
    List<Bid> bidList = new ArrayList<>();
    bidList.add(bid);

    when(bidRepositoryMock.findAll()).thenReturn(bidList);

    List<Bid> result = bidService.getBids();
    assertThat(result).isEqualTo(bidList);
  }

  /**
   * test to save a bid
   * 
   */
  @Test
  public void testSaveBid() {
    bid = new Bid();

    when(bidRepositoryMock.save(bid)).thenReturn(bid);

    Bid result = bidService.saveBid(bid);
    assertThat(result).isEqualTo(bid);
  }

  /**
   * test to know if a bid exists.
   * 
   */
  @Test
  public void testBidExist() {
    Integer id = 1;

    when(bidRepositoryMock.existsById(id)).thenReturn(true);

    boolean result = bidService.bidExist(id);
    assertTrue(result);
  }

  /**
   * test to delete a bid.
   * 
   */
  @Test
  public void testDeleteBid() {
    Integer id = 1;
    bid = new Bid();
    Optional<Bid> optionalBid = Optional.of(bid);

    when(bidRepositoryMock.findById(id)).thenReturn(optionalBid);
    doNothing().when(bidRepositoryMock).deleteById(id);

    Bid result = bidService.deleteBid(id);
    assertThat(result).isEqualTo(bid);
  }

  /**
   * test to get a bid.
   * 
   */
  @Test
  public void testGetBid() {
    Integer id = 1;
    bid = new Bid();
    Optional<Bid> optionalBid = Optional.of(bid);

    when(bidRepositoryMock.findById(id)).thenReturn(optionalBid);

    assertThat(bidService.getBid(id)).isEqualTo(bid);
  }

}
