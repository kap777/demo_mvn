package com.example.demo.controller;

import com.example.demo.domain.ErrorResponse;
import com.example.demo.domain.dto.BookDto;
import com.example.demo.domain.entity.AuthorEntity;
import com.example.demo.domain.entity.BookEntity;
import com.example.demo.mapper.Mapper;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class BookController {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    private Mapper<BookEntity, BookDto> bookMapper;

    private BookService bookService;
    private AuthorService authorService;

    @PutMapping("/books/{isbn}")
    public ResponseEntity<BookDto> createUpdateBook(
            @PathVariable("isbn") String isbnId,
            @RequestBody BookDto bookDto
    ) {
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        boolean bookExists = bookService.isExists(isbnId);
        BookEntity savedBookEntity = bookService.createUpdateBook(isbnId, bookEntity);
        BookDto savedUpdatedBookDto = bookMapper.mapTo(savedBookEntity);

        if (bookExists) {
            return new ResponseEntity<>(savedUpdatedBookDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(savedUpdatedBookDto, HttpStatus.CREATED);
        }
    }

    @PutMapping("/books/{isbn}/{authorId}")
    public ResponseEntity<?> createUpdateBookWithAuthorId(
            @PathVariable String isbn,
            @PathVariable String authorId,
            @RequestBody BookDto bookDto
    ) {
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        boolean bookExists = bookService.isExists(isbn);
        long id;
        try {
            id = Long.parseLong(authorId);
        } catch (NumberFormatException e) {
            log.error("Error parsing author ID", e);
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponse("Invalid author ID format"));
        }

        Optional<AuthorEntity> author = authorService.findOne(id);

        BookEntity savedBookEntity;
        if (author.isPresent()) {
            bookEntity.setAuthor(author.get());
            savedBookEntity = bookService.createUpdateBook(isbn, bookEntity);
        } else {
            bookEntity.setAuthor(null);
            return ResponseEntity
                    .badRequest()
                    .body( new ErrorResponse("Author not found") );
        }

        if (savedBookEntity == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponse("Book could not be saved, error createUpdateBook(isbn, bookEntity)"));
        }
        BookDto savedUpdatedBookDto = bookMapper.mapTo(savedBookEntity);

        return new ResponseEntity<>(
                savedUpdatedBookDto,
                bookExists ? HttpStatus.OK : HttpStatus.CREATED
        );
    }

    @PatchMapping("/books/{isbn}")
    public ResponseEntity<BookDto> partialUpdateBook(
            @PathVariable String isbn,
            @RequestBody BookDto bookDto
    ) {
        if (!bookService.isExists(isbn)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        BookEntity updatedBookEntity = bookService.partialUpdate(isbn, bookEntity);
        return new ResponseEntity<>(
                bookMapper.mapTo(updatedBookEntity),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/books") // query parameters: ?size=5&page=0
    public Page<BookDto> listBooks(@PageableDefault(value = 10) Pageable pageable) {
        Page<BookEntity> books = bookService.findAll(pageable);
        return books.map(bookMapper::mapTo);
//        return books.stream()
//                .map(bookMapper::mapTo)
//                .collect(Collectors.toList());
    }

    @GetMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDto> getBook(@PathVariable String isbn) {
        Optional<BookEntity> foundBook = bookService.findOne(isbn);
        return foundBook.map( bookEntity -> {
                BookDto bookDto = bookMapper.mapTo(bookEntity);
                return new ResponseEntity<>(bookDto, HttpStatus.OK);
            })
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/books/{isbn}")
    public ResponseEntity<String> deleteBook(@PathVariable String isbn) {
        if (isbn != null && bookService.isExists(isbn)) {
            bookService.delete(isbn);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Wrong isbn ID", HttpStatus.BAD_REQUEST);
        }
    }

}
