package com.codegym.serviece.userbook;

import com.codegym.model.UserBook;
import com.codegym.repository.userBook.UserBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class UserBookServiece implements IUserBookServiece{
    @Autowired
    UserBookRepository userBookRepository;


    @Override
    public Page<UserBook> findAll(Pageable pageable) {
        return userBookRepository.findAll(pageable);
    }

    @Override
    public UserBook findById(Long id) {
        return userBookRepository.findOne(id);
    }

    @Override
    public void save(UserBook model) {
        userBookRepository.save(model);
    }

    @Override
    public void delete(Long id) {
        userBookRepository.delete(id);
    }

    @Override
    public UserBook findByCode(Long code) {
        return userBookRepository.findByCode(code);
    }

}
