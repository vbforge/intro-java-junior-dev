package com.vbforge.exam.practical;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * PRACTICAL TASK 01 — OOP + Collections + Streams + Optional.
 *
 * <p>A small library manager. Each method exercises a different facet of
 * the course so far: encapsulation, {@code Optional} return types, and
 * common stream pipelines.
 *
 * <p>Internally you'll want a {@code Map<Integer, Book>} keyed by book id.
 * How you iterate/group/sort is up to you — loops, streams, or a mix are all fine.
 */
public final class Task01_LibrarySystem {

    /** A book — plain value object. Do not modify. */
    public record Book(int id, String title, String author, int year, int priceCents) {}

    // ---------------------------------------------------------------------
    // Mutation
    // ---------------------------------------------------------------------

    /**
     * Add a book to the library.
     * @throws NullPointerException     if {@code book} is null.
     * @throws IllegalArgumentException if a book with the same {@code id} is already present.
     */
    public void addBook(Book book) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    // ---------------------------------------------------------------------
    // Lookup
    // ---------------------------------------------------------------------

    /** Find a book by id; return {@code Optional.empty()} if absent. */
    public Optional<Book> findById(int id) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /** Number of books currently in the library. */
    public int size() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    // ---------------------------------------------------------------------
    // Views
    // ---------------------------------------------------------------------

    /**
     * All books sorted by title (case-sensitive {@link String#compareTo} order).
     * Returns a fresh list — mutating it must NOT affect the library.
     */
    public List<Book> allBooksSortedByTitle() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Group books by author. Within each author's list, books must be in
     * insertion order (the order they were added).
     * The returned map itself has no ordering requirement.
     */
    public Map<String, List<Book>> booksByAuthor() {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Return the top {@code n} author names by book count DESC, ties broken by
     * name ASCENDING (alphabetical). If fewer than {@code n} distinct authors exist,
     * return all of them. {@code n <= 0} → empty list.
     *
     * <p>Example: if Alice and Bob both have 3 books, and Cara has 1, then
     * {@code topAuthors(2)} → {@code ["Alice", "Bob"]}.
     */
    public List<String> topAuthors(int n) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /** All books whose {@code year} is strictly greater than {@code year}. Order not specified. */
    public List<Book> booksPublishedAfter(int year) {
        throw new UnsupportedOperationException("TODO: implement");
    }

    /**
     * Sum of {@code priceCents} across all books. Empty library → 0.
     * Watch out: the return type is {@code long} — use it correctly for the sum.
     */
    public long totalPriceCents() {
        throw new UnsupportedOperationException("TODO: implement");
    }
}