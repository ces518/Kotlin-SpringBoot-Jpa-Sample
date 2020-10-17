package me.springboot.jpa.api

import me.springboot.jpa.mapper.AccountMapper
import me.springboot.jpa.repository.AccountRepository
import me.springboot.jpa.service.AccountService
import org.springframework.web.bind.annotation.*

@RestController
class AccountController(
        private val accountRepository: AccountRepository,
        private val accountService: AccountService,
        private val accountMapper: AccountMapper
) {

    @GetMapping("/accounts")
    fun getAccounts() : List<AccountView> =
        accountRepository.findAll()
                .map(accountMapper::entityToView)

    @GetMapping("/accounts/{id}")
    fun getAccount(@PathVariable id: Long) : AccountView =
        accountService.findAccount(id)
                .let(accountMapper::entityToView)

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
    val name: String
)

data class AccountCreateRequest(
    val name: String
)

data class AccountUpdateRequest(
    val name: String
)