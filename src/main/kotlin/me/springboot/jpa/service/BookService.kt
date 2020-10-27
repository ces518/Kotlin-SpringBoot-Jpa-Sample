package me.springboot.jpa.service

import me.springboot.jpa.entity.Book
import me.springboot.jpa.mapper.BookMapper
import me.springboot.jpa.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException

@Service
@Transactional(readOnly = true)
class BookService(
    private val bookRepository: BookRepository,
    private val bookMapper: BookMapper
) {

    fun findBook(id: Long) : Book =
        bookRepository.findById(id).orElseThrow { RuntimeException() }

    @Transactional
    fun createBook(dto: BookCreateRequest): Book {
        val entity = bookMapper.dtoToEntity(dto)
        return bookRepository.save(entity)
    }

    @Transactional
    fun update(id: Long, dto: BookUpdateRequest) {
        val findBook = findBook(id)
        val entity = bookMapper.dtoToEntity(dto)
        findBook.apply {
            author = entity.author
        }
    }
}

data class BookCreateRequest(
    val author: String
)

data class BookUpdateRequest(
    val author: String
)