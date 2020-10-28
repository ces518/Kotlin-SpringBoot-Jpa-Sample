package me.springboot.jpa.repository

import me.springboot.jpa.config.Querydsl4RepositorySupport
import me.springboot.jpa.entity.Account
import me.springboot.jpa.entity.QAccount.account
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class AccountQueryRepository: Querydsl4RepositorySupport(Account::class.java) {

    fun findByName(name: String): List<Account> =
            queryFactory.select(account)
                    .from(account)
                    .where(account.name.eq(name))
                    .fetch()


    fun findByCreatedAtBefore(createdAt: LocalDateTime): List<Account> =
            queryFactory.select(account)
                    .from(account)
                    .where(account.createdAt.before(createdAt))
                    .fetch()
}