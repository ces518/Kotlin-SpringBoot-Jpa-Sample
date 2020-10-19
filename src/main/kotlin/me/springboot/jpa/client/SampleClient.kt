package me.springboot.jpa.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(value = "sampleClient", url = "https://www.naver.com")
interface SampleClient {

    @GetMapping("")
    fun index() : String
}
