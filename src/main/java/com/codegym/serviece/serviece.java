package com.codegym.serviece;

import com.codegym.model.Book;
import com.codegym.model.UserBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface serviece<T> {
    Page<T> findAll(Pageable pageable);
    T findById(Long id);
    void save(T model);
    void delete(Long id);
    T findByCode(Long code);

}
