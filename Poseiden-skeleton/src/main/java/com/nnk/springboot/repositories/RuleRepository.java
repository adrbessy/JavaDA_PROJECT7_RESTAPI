package com.nnk.springboot.repositories;

import com.nnk.springboot.model.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Long>, JpaSpecificationExecutor<Rule> {

  void deleteById(Integer id);

  boolean existsById(Integer id);

  Rule findById(Integer id);
}
