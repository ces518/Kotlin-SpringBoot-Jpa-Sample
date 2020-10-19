package me.springboot.jpa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class JpaApplication

fun main(args: Array<String>) {
    runApplication<JpaApplication>(*args)
}
