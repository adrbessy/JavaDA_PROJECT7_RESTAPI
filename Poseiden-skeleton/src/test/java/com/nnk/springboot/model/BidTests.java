package com.nnk.springboot.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.nnk.springboot.repositories.BidRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

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
    assertThat(bidRepository.findById(id)).isEmpty();
  }

  @Test
  public void simpleEqualsBid() {
    EqualsVerifier.forClass(Bid.class).suppress(Warning.ALL_FIELDS_SHOULD_BE_USED).verify();
  }

}
