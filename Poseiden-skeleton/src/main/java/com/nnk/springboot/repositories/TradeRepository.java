package com.nnk.springboot.repositories;

import com.nnk.springboot.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long>, JpaSpecificationExecutor<Trade> {

  void deleteById(Integer id);

  boolean existsById(Integer id);

  Trade findById(Integer id);
}
