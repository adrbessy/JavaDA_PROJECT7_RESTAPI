package com.nnk.springboot.repositories;

import com.nnk.springboot.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BidRepository extends JpaRepository<Bid, Integer> {

}
