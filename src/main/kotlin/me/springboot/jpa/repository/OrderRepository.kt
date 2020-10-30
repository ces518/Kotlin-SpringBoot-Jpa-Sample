package me.springboot.jpa.repository

import me.springboot.jpa.entity.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long>