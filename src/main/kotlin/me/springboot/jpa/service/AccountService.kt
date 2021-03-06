package me.springboot.jpa.service

import me.springboot.jpa.api.AccountCreateRequest
import me.springboot.jpa.api.AccountUpdateRequest
import me.springboot.jpa.entity.Account
import me.springboot.jpa.mapper.AccountMapper
import me.springboot.jpa.repository.AccountRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AccountService(
        private val accountRepository: AccountRepository,
        private val accountMapper: AccountMapper
) {

    fun findAccount(id: Long) : Account =
        accountRepository.findById(id).orElseThrow { AccountNotFoundException() }

    @Transactional
    fun create(dto: AccountCreateRequest) : Long =
        accountRepository.save(accountMapper.dtoToEntity(dto)).id!!

    @Transactional
    fun update(id: Long, dto: AccountUpdateRequest) = findAccount(id).let {
        val entity = accountMapper.dtoToEntity(dto)
        it.update(entity)
    }

    @Transactional
    fun delete(id: Long) = findAccount(id).let {
        accountRepository.delete(it)
    }

}

class AccountNotFoundException: RuntimeException()