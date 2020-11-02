package me.springboot.jpa

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class JpaApplicationKtTest {

    @Test
    fun run() {
        println("hello")
        assertThat("hello").isEqualTo("hello")
    }
}