package com.lab4.book_app.controllers;

import com.lab4.book_app.model.Author;
import com.lab4.book_app.model.Book;
import com.lab4.book_app.services.IAuthorsService;
import com.lab4.book_app.services.IBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("authors")
public class AuthorsController {

    @Autowired
    IAuthorsService authorsService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> addAuthor(@RequestBody Author author){
        boolean result = authorsService.addItem(author);

        if(result) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getAuthors(){
        return new ResponseEntity<>(authorsService.getItems(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getAuthor(@PathVariable("id") int id){
        Author author = authorsService.getItem(id);

        if(author == null) {
            return new ResponseEntity<>(author, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(author, HttpStatus.OK);
        }    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteAuthor(@PathVariable("id") int id){
        return new ResponseEntity<>(authorsService.deleteItem(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<Object> updateAuthor(@PathVariable("id") int id, @RequestBody Author author){
        return new ResponseEntity<>(authorsService.updateItem(id, author), HttpStatus.OK);
    }
}
