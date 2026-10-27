package com.vbforge.exam.practical;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.vbforge.exam.practical.Task01_LibrarySystem.Book;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Disabled
class Task01_LibrarySystemTest {

    private Task01_LibrarySystem lib;

    @BeforeEach
    void setUp() {
        lib = new Task01_LibrarySystem();
        lib.addBook(new Book(1, "The Hobbit",               "Tolkien", 1937, 1999));
        lib.addBook(new Book(2, "The Lord of the Rings",    "Tolkien", 1954, 2999));
        lib.addBook(new Book(3, "1984",                     "Orwell",  1949, 1299));
        lib.addBook(new Book(4, "Animal Farm",              "Orwell",  1945,  999));
        lib.addBook(new Book(5, "Harry Potter",             "Rowling", 1997, 1599));
    }

    // ---------------------------------------------------------------------
    // Mutation & lookup
    // ---------------------------------------------------------------------

    @Test void addBookRejectsNull() {
        assertThatThrownBy(() -> lib.addBook(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test void addBookRejectsDuplicateId() {
        assertThatThrownBy(() -> lib.addBook(new Book(1, "Duplicate", "X", 2000, 100)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test void findById() {
        assertThat(lib.findById(1)).hasValueSatisfying(b -> assertThat(b.title()).isEqualTo("The Hobbit"));
        assertThat(lib.findById(999)).isEmpty();
    }

    @Test void size() {
        assertThat(lib.size()).isEqualTo(5);
    }

    // ---------------------------------------------------------------------
    // Views
    // ---------------------------------------------------------------------

    @Test void allBooksSortedByTitle() {
        List<String> titles = lib.allBooksSortedByTitle().stream()
                .map(Book::title)
                .toList();
        assertThat(titles).containsExactly(
                "1984",
                "Animal Farm",
                "Harry Potter",
                "The Hobbit",
                "The Lord of the Rings");
    }

    @Test void allBooksSortedByTitleReturnsCopy() {
        List<Book> view = lib.allBooksSortedByTitle();
        view.clear();
        assertThat(lib.size()).isEqualTo(5);
    }

    @Test void booksByAuthorPreservesInsertionOrderWithinGroup() {
        var byAuthor = lib.booksByAuthor();

        assertThat(byAuthor.get("Tolkien")).extracting(Book::title)
                .containsExactly("The Hobbit", "The Lord of the Rings");
        assertThat(byAuthor.get("Orwell")).extracting(Book::title)
                .containsExactly("1984", "Animal Farm");
        assertThat(byAuthor.get("Rowling")).extracting(Book::title)
                .containsExactly("Harry Potter");
    }

    @Test void topAuthorsRanksByCountThenName() {
        // Tolkien: 2, Orwell: 2, Rowling: 1 — tie at 2 broken alphabetically
        assertThat(lib.topAuthors(1)).containsExactly("Orwell");
        assertThat(lib.topAuthors(2)).containsExactly("Orwell", "Tolkien");
        assertThat(lib.topAuthors(3)).containsExactly("Orwell", "Tolkien", "Rowling");
    }

    @Test void topAuthorsEdgeCases() {
        assertThat(lib.topAuthors(0)).isEmpty();
        assertThat(lib.topAuthors(-1)).isEmpty();
        assertThat(lib.topAuthors(100)).containsExactly("Orwell", "Tolkien", "Rowling");
    }

    @Test void booksPublishedAfter() {
        assertThat(lib.booksPublishedAfter(1950)).extracting(Book::title)
                .containsExactly("The Lord of the Rings");
        assertThat(lib.booksPublishedAfter(1940)).extracting(Book::title)
                .containsExactlyInAnyOrder(
                        "The Lord of the Rings",
                        "Harry Potter");
        assertThat(lib.booksPublishedAfter(2000)).isEmpty();
    }

    @Test void totalPriceCents() {
        // 1999 + 2999 + 1299 + 999 + 1599 = 8895
        assertThat(lib.totalPriceCents()).isEqualTo(8_895L);
    }

    @Test void totalPriceCentsOnEmptyLibrary() {
        assertThat(new Task01_LibrarySystem().totalPriceCents()).isZero();
    }
}