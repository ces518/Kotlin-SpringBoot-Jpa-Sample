package me.springboot.jpa.api

import me.springboot.jpa.service.BookCreateRequest
import me.springboot.jpa.service.BookService
import org.springframework.web.bind.annotation.*

@RestController
class BookController(
    private val bookService: BookService
) {

    @GetMapping("/books/{id}")
    fun getBooks(@PathVariable id: Long) =
            bookService.findBook(id)

    @PostMapping("/books")
    fun createBook(@RequestBody dto: BookCreateRequest) =
            bookService.createBook(dto)
}