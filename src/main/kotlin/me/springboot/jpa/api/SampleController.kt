package me.springboot.jpa.api

import me.springboot.jpa.client.SampleClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController (
        private val client: SampleClient
){
    @GetMapping("/sample")
    fun sample() : String = client.index()
}