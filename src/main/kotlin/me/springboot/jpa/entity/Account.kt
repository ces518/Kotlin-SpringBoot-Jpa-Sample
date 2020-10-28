package me.springboot.jpa.entity

import javax.persistence.*

@Entity
@Table(name = "accounts")
class Account(name: String?): BaseEntity() {
    var name: String? = name
        protected set

    fun update(entity: Account) {
        name = entity.name
    }
}