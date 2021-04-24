package com.nnk.springboot.repositories;

import com.nnk.springboot.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

  Bid findById(Integer id);

  void deleteById(Integer id);

  boolean existsById(Integer id);

}
