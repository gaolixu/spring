package spring.boot.core.service;

import org.springframework.data.domain.Page;

import spring.boot.core.domain.Book;
import spring.boot.core.domain.BookQuery;

public interface BookQueryService {
    Page<Book> findBookNoCriteria(Integer page,Integer size);
    Page<Book> findBookCriteria(Integer page,Integer size,BookQuery bookQuery);
}