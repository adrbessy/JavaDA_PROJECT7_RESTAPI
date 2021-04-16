package com.nnk.springboot;

import com.nnk.springboot.model.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
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
    Assert.assertNotNull(trade.getId());
    Assert.assertTrue(trade.getAccount().equals("Trade Account"));

    // Update
    trade.setAccount("Trade Account Update");
    trade = tradeRepository.save(trade);
    Assert.assertTrue(trade.getAccount().equals("Trade Account Update"));

    // Find
    List<Trade> listResult = tradeRepository.findAll();
    Assert.assertTrue(listResult.size() > 0);

    // Delete
    Integer id = trade.getId();
    tradeRepository.delete(trade);
    Optional<Trade> tradeList = tradeRepository.findById(id);
    Assert.assertFalse(tradeList.isPresent());
  }
}
