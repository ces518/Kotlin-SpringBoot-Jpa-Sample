package me.springboot.jpa.api

import me.springboot.jpa.service.OrderRequest
import me.springboot.jpa.service.OrderService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController(
        private val orderService: OrderService
) {

    @PostMapping("/orders")
    fun order(@RequestBody orderRequest: OrderRequest) =
        orderService.order(orderRequest)
}