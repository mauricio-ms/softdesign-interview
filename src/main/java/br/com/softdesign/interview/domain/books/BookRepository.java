package br.com.softdesign.interview.domain.books;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
class BookRepository {
    private static final String ADD_SQL = "INSERT INTO books (name, author, rented) VALUES (?, ?, ?)";

    private final JdbcTemplate jdbcTemplate;

    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Book add(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int update = jdbcTemplate.update(psc -> {
            PreparedStatement ps = psc.prepareStatement(ADD_SQL, new String[]{"id"});
            ps.setString(1, book.name());
            ps.setString(2, book.author());
            ps.setBoolean(3, book.rented());

            return ps;
        }, keyHolder);

        if (update == 0) {
            throw new RuntimeException("Book not added: " + book);
        }

        return new Book(
                keyHolder.getKeyAs(Long.class),
                book.name(),
                book.author(),
                book.rented()
        );
    }
}
