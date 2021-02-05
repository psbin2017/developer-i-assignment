package com.developer.assignment.repository.master;

import com.developer.assignment.domain.order.Order;
import com.developer.assignment.domain.order.model.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMasterRepository extends JpaRepository<Order, OrderId> {

}
