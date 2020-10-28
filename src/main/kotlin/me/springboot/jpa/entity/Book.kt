package me.springboot.jpa.entity

import javax.persistence.*

@Entity
@Table(name = "books")
class Book(
    var author: String
): BaseEntity()