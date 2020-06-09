package com.codegym.repository.userBook;

import com.codegym.model.UserBook;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserBookRepository extends PagingAndSortingRepository<UserBook,Long> {
    UserBook findByCode(Long code);
}
