package de.htw_berlin.webtechjs;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @GetMapping
    public List<Movie> getAllMovies() {
        return List.of(
                new Movie(1L, "Inception", "Sci-Fi", 2010, "WATCHED"),
                new Movie(2L, "The Dark Knight", "Action", 2008, "WATCHED"),
                new Movie(3L, "Interstellar", "Sci-Fi", 2014, "WATCHLIST"),
                new Movie(4L, "Parasite", "Thriller", 2019, "WATCHLIST")
        );
    }
}