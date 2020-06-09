package com.codegym.controller;

import com.codegym.model.Book;
import com.codegym.model.UserBook;
import com.codegym.serviece.book.IBookServiece;
import com.codegym.serviece.userbook.IUserBookServiece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@Controller
public class BookController {
    @Autowired
    IBookServiece bookServiece;

    @Autowired
    IUserBookServiece userBookServiece;

    @GetMapping("/")
    ModelAndView showBook(Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("home");
        Page<Book> listBook = bookServiece.findAll(pageable);
        modelAndView.addObject("list", listBook);
        return modelAndView;
    }

    @GetMapping("/borrow/{id}")
    ModelAndView showBorrow(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("borrow");
        Book book = bookServiece.findById(id);
        modelAndView.addObject("userBook", book);
        return modelAndView;
    }

    @PostMapping("/borrow")
    ModelAndView borrow(@ModelAttribute("userBook") Book book) {
        Book book1 = bookServiece.findById(book.getId());
        if(book1.getNumber()>0){
            book1.setNumber(book1.getNumber() - 1);
            bookServiece.save(book1);
            UserBook userBook = new UserBook();
            Random random = new Random();
            Long code = (long) (random.nextInt(99999));
            userBook.setNumber(1);
            userBook.setCode(code);
            userBook.setBook(book1);
            userBookServiece.save(userBook);
            ModelAndView modelAndView = new ModelAndView("success");
            modelAndView.addObject("userBook", userBook);
            return modelAndView;
        }else{
            return new ModelAndView("error-1");
        }

    }

    @GetMapping("/return")
    ModelAndView returnForm() {
        ModelAndView modelAndView = new ModelAndView("return");
        modelAndView.addObject("UserBook",new UserBook());
        return modelAndView;
    }
    @PostMapping("/return")
    ModelAndView returnBook(@ModelAttribute("UserBook") UserBook userBook){
        UserBook userBook1 = userBookServiece.findByCode(userBook.getCode());
        if(userBook1!=null){
            Book book = bookServiece.findById(userBook1.getBook().getId());
            book.setNumber(book.getNumber()+1);
            bookServiece.save(book);
            userBookServiece.delete(userBook1.getId());
            return new ModelAndView("redirect:/");
        }else{
            return new ModelAndView("error-2");
        }

    }


}
