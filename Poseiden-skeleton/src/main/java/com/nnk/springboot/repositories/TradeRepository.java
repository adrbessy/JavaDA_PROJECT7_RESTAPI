package com.nnk.springboot.repositories;

import com.nnk.springboot.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Integer> {

  @Override
  void deleteById(Integer id);

  @Override
  boolean existsById(Integer id);
}
