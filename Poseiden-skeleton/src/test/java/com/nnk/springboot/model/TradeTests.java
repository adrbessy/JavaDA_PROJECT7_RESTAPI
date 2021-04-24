package com.nnk.springboot.model;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.nnk.springboot.repositories.TradeRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

@SpringBootTest
public class TradeTests {

  private Trade trade;

  @Autowired
  private TradeRepository tradeRepository;

  @BeforeEach
  private void setUp() {
    trade = new Trade();
    trade.setAccount("Trade Account");
    trade.setType("Type");
  }

  @Test
  public void tradeTest() {

    // Save
    trade = tradeRepository.save(trade);
    assertNotNull(trade.getId());
    assertTrue(trade.getAccount().equals("Trade Account"));

    // Update
    trade.setAccount("Trade Account Update");
    trade = tradeRepository.save(trade);
    assertTrue(trade.getAccount().equals("Trade Account Update"));

    // Find
    List<Trade> listResult = tradeRepository.findAll();
    assertTrue(listResult.size() > 0);

    // Delete
    Integer id = trade.getId();
    tradeRepository.delete(trade);
    Trade tradeList = tradeRepository.findById(id);
    assertNull(tradeList);
  }

  @Test
  public void simpleEqualsTrade() {
    EqualsVerifier.forClass(Trade.class).suppress(Warning.ALL_FIELDS_SHOULD_BE_USED).verify();
  }

}
