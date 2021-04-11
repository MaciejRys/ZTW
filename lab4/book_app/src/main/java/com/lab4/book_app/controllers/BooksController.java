package com.lab4.book_app.controllers;

import com.lab4.book_app.model.Author;
import com.lab4.book_app.model.Book;
import com.lab4.book_app.services.IBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("books")
public class BooksController {
    @Autowired
    IBooksService booksService;
    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> addBook(String title, int authorId, int pages){
        boolean result = booksService.addItem(title, authorId, pages);

        if(result) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.CONFLICT);
        }
    }
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getBooks(){
        return new ResponseEntity<>(booksService.getItems(), HttpStatus.OK);
    }
    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getBook(@PathVariable("id") int id){
        Book book = booksService.getItem(id);

        if(book == null) {
            return new ResponseEntity<>(book, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(book, HttpStatus.OK);
        }    }

    @CrossOrigin
    @RequestMapping(value = "book/{name}", method = RequestMethod.GET)
    public ResponseEntity<Object> getBook(@PathVariable("name") String name){
        Book book = booksService.getItemByName(name);

        if(book == null) {
            return new ResponseEntity<>(book, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(book, HttpStatus.OK);
        }    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteBook(@PathVariable("id") int id){
        return new ResponseEntity<>(booksService.deleteItem(id), HttpStatus.OK);
    }
    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<Object> updateBook(@PathVariable("id") int id, @RequestBody Book book){
        return new ResponseEntity<>(booksService.updateItem(id, book), HttpStatus.OK);
    }
}
