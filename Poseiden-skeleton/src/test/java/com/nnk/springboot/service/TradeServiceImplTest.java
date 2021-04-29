package com.nnk.springboot.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import com.nnk.springboot.model.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest()
public class TradeServiceImplTest {

  @Autowired
  private TradeService tradeService;

  @MockBean
  private TradeRepository tradeRepositoryMock;

  private Trade trade;

  /**
   * test to get all the trades.
   * 
   */
  @Test
  public void testGetTrades() {
    trade = new Trade();
    List<Trade> tradeList = new ArrayList<>();
    tradeList.add(trade);

    when(tradeRepositoryMock.findAll()).thenReturn(tradeList);

    List<Trade> result = tradeService.getTrades();
    assertThat(result).isEqualTo(tradeList);
  }

  /**
   * test to save a trade
   * 
   */
  @Test
  public void testSaveTrade() {
    trade = new Trade();

    when(tradeRepositoryMock.save(trade)).thenReturn(trade);

    Trade result = tradeService.saveTrade(trade);
    assertThat(result).isEqualTo(trade);
  }

  /**
   * test to know if a trade exists.
   * 
   */
  @Test
  public void testTradeExist() {
    Integer id = 1;

    when(tradeRepositoryMock.existsById(id)).thenReturn(true);

    boolean result = tradeService.tradeExist(id);
    assertTrue(result);
  }


  /**
   * test to delete a trade.
   * 
   */
  @Test
  public void testDeleteTrade() {
    Integer id = 1;
    trade = new Trade();
    Optional<Trade> optionalTrade = Optional.of(trade);

    when(tradeRepositoryMock.findById(id)).thenReturn(optionalTrade);
    doNothing().when(tradeRepositoryMock).deleteById(id);

    Trade result = tradeService.deleteTrade(id);
    assertThat(result).isEqualTo(trade);
  }

  /**
   * test to get a trade.
   * 
   */
  @Test
  public void testGetTrade() {
    Integer id = 1;
    trade = new Trade();
    Optional<Trade> optionalTrade = Optional.of(trade);

    when(tradeRepositoryMock.findById(id)).thenReturn(optionalTrade);

    assertThat(tradeService.getTrade(id)).isEqualTo(trade);
  }

}
