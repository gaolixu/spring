package com.restful.jersey.service;

import java.util.List;

import com.restful.jersey.domain.Order;


public interface IOrderService {

    List<Order> getOrderList();

    Order findOrderById(Long id);

    long insert(Order order);

    int update(Order order);

    long delete();

    int deleteOrderById(Long id);
}
