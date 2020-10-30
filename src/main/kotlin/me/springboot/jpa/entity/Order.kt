package me.springboot.jpa.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "orders")
class Order(
    val accountId: Long
): BaseEntity(){
    @JsonBackReference
    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true)
    val orderItems: MutableList<OrderItem> = arrayListOf()

    companion object {
        fun createOrder(accountId: Long, itemId: Long): Order {
            val order = Order(accountId)
            val orderItem = OrderItem(order, itemId)
            order.addOrderItem(orderItem)
            return order
        }
    }

    fun addOrderItem(orderItem: OrderItem) {
        orderItems.add(orderItem)
    }
}