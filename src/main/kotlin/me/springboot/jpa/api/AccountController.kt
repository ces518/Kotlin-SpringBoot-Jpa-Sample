package me.springboot.jpa.api

import me.springboot.jpa.mapper.AccountMapper
import me.springboot.jpa.repository.AccountQueryRepository
import me.springboot.jpa.repository.AccountRepository
import me.springboot.jpa.service.AccountService
import org.springframework.cache.annotation.Cacheable
import org.springframework.web.bind.annotation.*
import java.io.Serializable
import java.time.LocalDateTime

@RestController
class AccountController(
        private val accountRepository: AccountRepository,
        private val accountService: AccountService,
        private val accountQueryRepository: AccountQueryRepository,
        private val accountMapper: AccountMapper
) {

    @GetMapping("/accounts")
    fun getAccounts() : List<AccountView> =
            accountRepository.findAll()
                    .map(accountMapper::entityToView)

    @GetMapping("/accounts/{id}")
    @Cacheable(value = ["getAccount"], key = "#id")
    fun getAccount(@PathVariable id: Long) : AccountView =
            accountService.findAccount(id)
                    .let(accountMapper::entityToView)

    @GetMapping("/accounts/search")
    fun findAccount(name: String) =
            accountQueryRepository.findByName(name).map(accountMapper::entityToView)

    @GetMapping("/accounts/created-at-before")
    fun findAccountCreatedAtBefore() =
            accountQueryRepository.findByCreatedAtBefore(LocalDateTime.now()).map(accountMapper::entityToView)

    @PostMapping("/accounts")
    fun createAccount(@RequestBody dto: AccountCreateRequest) : AccountView =
            accountService.findAccount(accountService.create(dto))
                    .let(accountMapper::entityToView)

    @PutMapping("/accounts/{id}")
    fun updateAccount(@PathVariable id: Long, @RequestBody dto: AccountUpdateRequest) : AccountView {
        accountService.update(id, dto)
        return accountService.findAccount(id)
                .let(accountMapper::entityToView)
    }

    @DeleteMapping("/accounts/{id}")
    fun deleteAccount(@PathVariable id: Long) {
        accountService.delete(id)
    }
}

data class AccountView (
    val id: Long,
    val name: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
): Serializable

data class AccountCreateRequest(
    val name: String
)

data class AccountUpdateRequest(
    val name: String
)