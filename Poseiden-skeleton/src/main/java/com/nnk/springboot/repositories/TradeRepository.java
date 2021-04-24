package com.nnk.springboot.repositories;

import com.nnk.springboot.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {

  Trade findById(Integer id);

  void deleteById(Integer id);

  boolean existsById(Integer id);
}
