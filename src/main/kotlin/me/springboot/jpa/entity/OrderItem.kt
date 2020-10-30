package me.springboot.jpa.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "order_items")
class OrderItem(
        @JsonManagedReference
        @ManyToOne
        @JoinColumn(name = "order_id")
        val order: Order,
        val itemId: Long
): BaseEntity()