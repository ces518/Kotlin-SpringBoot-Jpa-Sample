package me.springboot.jpa.service

import me.springboot.jpa.entity.Order
import me.springboot.jpa.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class OrderService(
        private val orderRepository: OrderRepository
) {

    @Transactional
    fun order(orderRequest: OrderRequest): Order {
        val order = Order.createOrder(orderRequest.accountId, orderRequest.itemId)
        return orderRepository.save(order)
    }
}

data class OrderRequest(
    val accountId: Long,
    val itemId: Long
)