package me.springboot.jpa.repository

import me.springboot.jpa.entity.Account
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.junit.jupiter.SpringExtension

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@SpringBootTest
internal class AccountQueryRepositoryTest(
        val accountRepository: AccountRepository,
        val accountQueryRepository: AccountQueryRepository
) {

    @Test
    @DisplayName("이름으로 조회")
    fun `find account by username`() {
        // given
        accountRepository.save(Account("ncucu"))

        // when
        val accounts = accountQueryRepository.findByName("ncucu")

        // then
        assertThat(accounts.size).isEqualTo(1)
        assertThat(accounts[0].name).isEqualTo("ncucu")
    }

    @Test
    fun findByCreatedAtBefore() {
    }

    @Test
    fun findAll() {
    }

    @Test
    fun findAllWithCountQuery() {
    }
}