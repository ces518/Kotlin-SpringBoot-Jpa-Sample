package me.springboot.jpa.entity

import javax.persistence.*

@Entity
@Table(name = "books")
class Book(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var author: String
)