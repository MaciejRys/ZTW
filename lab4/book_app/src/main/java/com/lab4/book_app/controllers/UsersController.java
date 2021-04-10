package com.lab4.book_app.controllers;

        import com.lab4.book_app.model.Author;
        import com.lab4.book_app.model.Book;
        import com.lab4.book_app.model.User;
        import com.lab4.book_app.services.IAuthorsService;
        import com.lab4.book_app.services.IBooksService;
        import com.lab4.book_app.services.IUsersService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("users")
public class UsersController {

    @Autowired
    IUsersService usersService;
    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> addUser(@RequestBody String username){
        boolean result = usersService.addUser(username);

        if(result) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.CONFLICT);
        }
    }
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getUsers(){
        return new ResponseEntity<>(usersService.getItems(), HttpStatus.OK);
    }
    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUser(@PathVariable("id") int id){
        User user = usersService.getItem(id);

        if(user == null) {
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }    }


}
