package com.nnk.springboot.repositories;

import com.nnk.springboot.model.Rule;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RuleRepository extends JpaRepository<Rule, Integer> {
}
