package me.springboot.jpa.repository

import me.springboot.jpa.config.Querydsl4RepositorySupport
import me.springboot.jpa.entity.Account
import me.springboot.jpa.entity.QAccount.account
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.function.Function

@Repository
class AccountQueryRepository: Querydsl4RepositorySupport(Account::class.java) {

    fun findByName(name: String): List<Account> =
            queryFactory.select(account)
                    .from(account)
                    .where(eqName(name))
                    .fetch()


    fun findByCreatedAtBefore(createdAt: LocalDateTime): List<Account> =
            queryFactory.select(account)
                    .from(account)
                    .where(account.createdAt.before(createdAt))
                    .fetch()

    fun findAll(pageable: Pageable): Page<Account> =
            applyPagination(pageable,
                    Function { query -> query.select(account).from(account) }
            )

    fun findAllWithCountQuery(pageable: Pageable): Page<Account> =
            applyPagination(pageable,
                    Function { query -> query.select(account).from(account) },
                    Function { query -> query.select(account).from(account) }
            )


    private fun eqName(name: String) = account.name.eq(name)
}