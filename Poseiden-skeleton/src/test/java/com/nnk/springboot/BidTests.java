package com.nnk.springboot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.nnk.springboot.model.Bid;
import com.nnk.springboot.repositories.BidRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BidTests {

  private Bid bid;

  @Autowired
  private BidRepository bidRepository;

  @BeforeEach
  private void setUp() {
    bid = new Bid();
    bid.setAccount("Account Test");
    bid.setType("Type Test");
    bid.setBidQuantity(10d);
  }

  @Test
  public void bidListTest() {

    // Save
    bid = bidRepository.save(bid);
    assertNotNull(bid.getId());
    assertThat(bid.getBidQuantity()).isEqualTo(10d);

    // Update
    bid.setBidQuantity(20d);
    bid = bidRepository.save(bid);
    assertThat(bid.getBidQuantity()).isEqualTo(20d);

    // Find
    List<Bid> listResult = bidRepository.findAll();
    assertTrue(listResult.size() > 0);

    // Delete
    Integer id = bid.getId();
    bidRepository.delete(bid);
    Optional<Bid> bidList = bidRepository.findById(id);
    assertFalse(bidList.isPresent());
  }
}
