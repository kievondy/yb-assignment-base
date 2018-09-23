package com.yieldbroker.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yieldbroker.assignment.model.YbOrder;

public interface YbOrderRepository extends JpaRepository<YbOrder, Long> {

}
