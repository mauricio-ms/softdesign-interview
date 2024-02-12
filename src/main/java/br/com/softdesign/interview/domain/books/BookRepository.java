package br.com.softdesign.interview.domain.books;

import br.com.softdesign.interview.http.BookDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
class BookRepository {
    private static final String ADD_SQL = "INSERT INTO books (name, author, rented) VALUES (?, ?, false)";

    private final JdbcTemplate jdbcTemplate;

    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(BookDto bookDto) {
        jdbcTemplate.update(ADD_SQL, ps -> {
            ps.setString(1, bookDto.name());
            ps.setString(2, bookDto.author());
        });

        // TODO validate not inserted
    }
}
