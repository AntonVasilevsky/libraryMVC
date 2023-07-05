package ru.anton.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.anton.library.dao.BookDAO;
import ru.anton.library.dao.PersonDAO;
import ru.anton.library.models.Book;
import ru.anton.library.models.Person;
import ru.anton.library.util.BookValidator;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    private final BookValidator bookValidator;
    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO, BookValidator bookValidator) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
        this.bookValidator = bookValidator;
    }

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());
        return "/books/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model modelBook, Model modelPerson) {

        Book book = bookDAO.show(id).orElseThrow(NoSuchElementException::new);
        Person person = personDAO.show(book.getPerson_id()).orElseThrow(NoSuchElementException::new);
        modelBook.addAttribute("book", book);
        modelPerson.addAttribute("people", personDAO.index());
        modelPerson.addAttribute("person", person);
       // modelPerson.addAttribute("person", personDAO.show(bookDAO.show(id).map(Book::getPerson_id).orElseThrow(NoSuchElementException::new)));
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "books/new";

        bookDAO.save(book);
        return "redirect:/books";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id).orElseThrow(NoSuchElementException::new));
        return "books/edit";
    }
    @PostMapping("/{id}/return")
    public String returnBook(@PathVariable("id") int book_id){
        bookDAO.returnBook(book_id);
        return "redirect:/books/"+ book_id;
    }
    @PostMapping("/{id}/set-person")
    public String setPersonIdToBook(@PathVariable("id") int book_id, @RequestParam("selectedId") int person_id){
        bookDAO.setPersonIdToBook(book_id, person_id);
        return "redirect:/books/"+ book_id;
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "books/edit";

        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }
}
