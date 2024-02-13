package br.com.softdesign.interview.domain.books;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Optional;

@Repository
class BookRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookRepository.class);

    private static final String FIND_BY_ID_SQL = "SELECT * FROM books WHERE id = ?";
    private static final String ADD_SQL = "INSERT INTO books (name, author, rented) VALUES (?, ?, ?)";

    private static final RowMapper<Book> BOOK_ROW_MAPPER = (rs, rowNum) -> new Book(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getString("author"),
            rs.getBoolean("rented")
    );

    private final JdbcTemplate jdbcTemplate;

    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Book> findById(Long id) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(FIND_BY_ID_SQL, BOOK_ROW_MAPPER, id));
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("Book {} not found:", id, e);
            return Optional.empty();
        }
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
