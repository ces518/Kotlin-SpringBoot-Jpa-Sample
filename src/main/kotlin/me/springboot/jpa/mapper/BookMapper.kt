package me.springboot.jpa.mapper

import me.springboot.jpa.entity.Book
import me.springboot.jpa.service.BookUpdateRequest
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface BookMapper {
    fun dtoToEntity(dto: BookUpdateRequest): Book
}