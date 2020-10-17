package me.springboot.jpa.repository

import me.springboot.jpa.entity.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Long>