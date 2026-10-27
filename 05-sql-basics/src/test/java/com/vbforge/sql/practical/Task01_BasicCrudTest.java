package com.vbforge.sql.practical;

import com.vbforge.sql.support.TestDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Disabled
class Task01_BasicCrudTest {

    private TestDatabase db;
    private Connection conn;

    @BeforeEach
    void setUp() throws SQLException {
        db = TestDatabase.create();
        conn = db.connection();
    }

    @AfterEach
    void tearDown() throws SQLException {
        db.close();
    }

    @Test
    void insertAuthor() throws SQLException {
        Task01_BasicCrud.insertAuthor(conn, 10, "Herbert");
        assertThat(Task01_BasicCrud.findAuthorName(conn, 10)).contains("Herbert");
    }

    @Test
    void insertAuthorDuplicateIdThrows() {
        assertThatThrownBy(() -> Task01_BasicCrud.insertAuthor(conn, 1, "Other"))
                .isInstanceOf(SQLException.class);
    }

    @Test
    void updateBookPrice() throws SQLException {
        int updated = Task01_BasicCrud.updateBookPrice(conn, 1, 9999);
        assertThat(updated).isEqualTo(1);
        assertThat(Task01_BasicCrud.updateBookPrice(conn, 9999, 100)).isZero();
    }

    @Test
    void deleteBook() throws SQLException {
        assertThat(Task01_BasicCrud.deleteBook(conn, 7)).isEqualTo(1);
        assertThat(Task01_BasicCrud.deleteBook(conn, 9999)).isZero();
    }

    @Test
    void findAuthorName() throws SQLException {
        assertThat(Task01_BasicCrud.findAuthorName(conn, 1)).contains("Tolkien");
        assertThat(Task01_BasicCrud.findAuthorName(conn, 9999)).isEqualTo(Optional.empty());
    }

    @Test
    void allAuthorsAsStrings() throws SQLException {
        assertThat(Task01_BasicCrud.allAuthorsAsStrings(conn)).containsExactly(
                "1:Tolkien",
                "2:Rowling",
                "3:Martin",
                "4:Orwell",
                "5:Herbert"
        );
    }
}