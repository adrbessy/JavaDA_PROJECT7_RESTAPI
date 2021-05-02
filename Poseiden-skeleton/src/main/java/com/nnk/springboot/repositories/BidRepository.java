package com.nnk.springboot.repositories;

import com.nnk.springboot.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long>, JpaSpecificationExecutor<Bid> {

  void deleteById(Integer id);

  boolean existsById(Integer id);

  Bid findById(Integer id);

}
