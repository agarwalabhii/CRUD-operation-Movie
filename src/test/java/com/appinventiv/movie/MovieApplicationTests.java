package com.appinventiv.movie;

import com.appinventiv.movie.model.Movie;
import com.appinventiv.movie.service.CategoryService;
import com.appinventiv.movie.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class MovieApplicationTests {

	@Autowired MovieService movieService;
	@Autowired
	CategoryService categoryService;

	final String movieTitle = "Test";
	final String updatedMovieTitle = "Test Update";
	final String category = "Test Category";
	final Double rating = 4.6;

	@Test
	void contextLoads() {
	}

	@Test
	void getAll(){
		Movie movie = new Movie();
		// insert movie
		movie = addMovie(movie);
		List<Movie> movies = movieService.findAll();
		assertThat(movies.size() > 0);
		assertThat(movie.getTitle().equals(movieTitle));
		// going to update movie
		movie = updateMovie(movie.getId());
		assertThat(movie.getTitle().equals(updatedMovieTitle));

		// going to delete movie
		deleteMovie(movie.getId());
		Movie movieDB = movieService.findById(movie.getId());
		assertThat(movieDB == null);

	}

	private Movie addMovie(Movie movie){
		movie.setTitle(movieTitle);
		movie.setCategoryType(category);
		movie.setRating(3.0);
		return movieService.save(movie);
	}

	private Movie updateMovie(Long movieId) {
		Movie movie = movieService.findById(movieId);
		movie.setTitle(updatedMovieTitle);
		movie.setCategoryType(category);
		movie.setRating(3.0);
		return movieService.save(movie);
	}

	private void deleteMovie(Long id) {
		movieService.deleteById(id);
		categoryService.deleteByName(category);
	}

}
