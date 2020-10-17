package me.springboot.jpa.mapper

import me.springboot.jpa.api.AccountCreateRequest
import me.springboot.jpa.api.AccountUpdateRequest
import me.springboot.jpa.api.AccountView
import me.springboot.jpa.entity.Account
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper

@Mapper(componentModel = "spring",  injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface AccountMapper {
    fun dtoToEntity(dto: AccountCreateRequest) : Account
    fun dtoToEntity(dto: AccountUpdateRequest) : Account
    fun entityToView(account: Account) : AccountView
}