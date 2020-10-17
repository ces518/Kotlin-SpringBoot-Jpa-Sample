package me.springboot.jpa.entity

import javax.persistence.*

@Entity
@Table(name = "accounts")
class Account(name: String?) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

    var name: String? = name
        protected set

    fun update(entity: Account) {
        name = entity.name
    }
}