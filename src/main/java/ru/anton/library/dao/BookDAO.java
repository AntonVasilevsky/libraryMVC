package ru.anton.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.anton.library.models.Book;
import ru.anton.library.models.Person;

import java.util.List;
@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {

        List<Book> books = jdbcTemplate.query("SELECT * FROM book ORDER BY name", new BeanPropertyRowMapper<>(Book.class));
        //books.forEach(System.out::println);
        return books;

    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM book ORDER BY name WHERE book_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }
    public List<Book> showId(int id) {
        return jdbcTemplate.query("SELECT * FROM book  WHERE person_id=? ORDER BY name", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book (name, author, year) VALUES(?, ?, ?)", book.getName(), book.getAuthor(), book.getYear());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE book SET name=?, author=?, year=? WHERE book_id=?", updatedBook.getName(),
                updatedBook.getAuthor(), updatedBook.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", id);
    }
}
