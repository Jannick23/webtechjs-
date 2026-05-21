package de.htw_berlin.webtechjs;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class MovieController {

    @GetMapping("/movies")
    public List<Movie> getMovies() {

        return List.of(
                new Movie(1L, "Inception", "Sci-Fi", 2010, "Watched"),
                new Movie(2L, "Interstellar", "Sci-Fi", 2014, "Planned"),
                new Movie(3L, "The Batman", "Action", 2022, "Watching")
        );
    }
}