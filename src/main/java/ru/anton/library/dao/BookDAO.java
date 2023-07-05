package ru.anton.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.anton.library.models.Book;
import ru.anton.library.models.Person;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {

        List<Book> books = jdbcTemplate.query("SELECT * FROM book ORDER BY name", new BeanPropertyRowMapper<>(Book.class));

        return books;

    }

    public Optional<Book> show(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE book_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny();
    }
    public List<Book> showPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM book  WHERE person_id=? ORDER BY name", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book (name, author, year) VALUES(?, ?, ?)", book.getName(), book.getAuthor(), book.getYear());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE book SET name=?, author=?, year=? WHERE book_id=?", updatedBook.getName(),
                updatedBook.getAuthor(), updatedBook.getYear(), id);
    }
    public void returnBook(int book_id){
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE book_id=?", 1, book_id);
    }
    public void setPersonIdToBook(int book_id, int person_id){

        jdbcTemplate.update("UPDATE book SET person_id=? WHERE book_id=?", person_id, book_id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }
}
