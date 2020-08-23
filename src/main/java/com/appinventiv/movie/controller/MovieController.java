package com.appinventiv.movie.controller;

import com.appinventiv.movie.model.Movie;
import com.appinventiv.movie.service.MovieService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;

@RestController
@RequestMapping("/movie")
@Api(value = "/movie", description = "CRUD operation for movie test assessment", consumes = "application/json")
public class MovieController {

    @Autowired
    MovieService movieService;

    /**
     * It will return all movie list in pagination form
     * @param pageable
     * @return
     */
    @GetMapping("/get-all")
    public ResponseEntity<?> getMovie(Pageable pageable){
        Page<Movie> movies = movieService.findAll(pageable);
        if(movies.hasContent()) {
            return new ResponseEntity<Page<Movie>>(movies, HttpStatus.OK);
        }
        return new ResponseEntity<String>("No Movies Found", HttpStatus.OK);
    }

    /**
     * It will return movie by id
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable("id") Long id){
        Movie movie = movieService.findById(id);
        if(movie != null) {
            return new ResponseEntity<Movie>(movie, HttpStatus.OK);
        }
        return new ResponseEntity<String>("No Movie Found", HttpStatus.OK);
    }

    /**
     * It will create new movie
     * @param movie
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<?> addMovie(@RequestBody Movie movie){
        try {
            movie.setId(null);
            Movie movieDB = movieService.save(movie);
            return new ResponseEntity<Movie>(movieDB, HttpStatus.OK);
        }
        catch (ConstraintViolationException e){
            return new ResponseEntity<String>(e.getConstraintViolations().stream().findFirst().get().getMessage(), HttpStatus.OK);

        }
    }

    /**
     * It will update exisiting movie
     * @param movie
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity<?> updateMovie(@RequestBody Movie movie){
        if(movie.getId() == null){
            return new ResponseEntity<String>("Please provide movie id", HttpStatus.OK);
        }
        Movie movieDb = movieService.findById(movie.getId());
        if(movieDb == null){
            return new ResponseEntity<String>("No movie present with this id", HttpStatus.OK);
        }
        try {
            movieDb = movieService.save(movie);
        } catch (ConstraintViolationException e){
            return new ResponseEntity<String>(e.getConstraintViolations().stream().findFirst().get().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e){
            return new ResponseEntity<String>(e.getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Movie>(movieDb, HttpStatus.OK);
    }

    /**
     * It will delete movie by id
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        Movie movieDb = movieService.findById(id);
        if(movieDb == null){
            return new ResponseEntity<String>("Invalid movie id provided", HttpStatus.OK);
        }
        movieService.deleteById(id);
        return new ResponseEntity<String>(movieDb.getTitle() + " has been deleted", HttpStatus.OK);
    }
}
