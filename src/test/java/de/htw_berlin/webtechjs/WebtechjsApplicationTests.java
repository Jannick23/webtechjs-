package de.htw_berlin.webtechjs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WebtechjsApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovieRepository movieRepository;

    @BeforeEach
    void setUp() {
        movieRepository.deleteAll();

        movieRepository.save(new Movie(null, "Inception", "Sci-Fi", 2010, "watched"));
        movieRepository.save(new Movie(null, "Star Wars", "Action", 2000, "watched"));
        movieRepository.save(new Movie(null, "Spiderman", "Action", 2001, "watched"));
    }

    @Test
    void contextLoads() {
    }

    @Test
    void showsAllMovies() throws Exception {
        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void movieListContainsInception() throws Exception {
        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].title", hasItem("Inception")));
    }

    @Test
    void createsMovie() throws Exception {
        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Batman\",\"genre\":\"Action\",\"releaseYear\":2008,\"status\":\"watched\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Batman"));
    }

    @Test
    void createdMovieAppearsInMovieList() throws Exception {
        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Dune\",\"genre\":\"Sci-Fi\",\"releaseYear\":2021,\"status\":\"watchlist\"}"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].title", hasItem("Dune")));
    }

    @Test
    void savesMovieGenreCorrectly() throws Exception {
        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Avatar\",\"genre\":\"Fantasy\",\"releaseYear\":2009,\"status\":\"watched\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.genre").value("Fantasy"));
    }

    @Test
    void savesMovieReleaseYearCorrectly() throws Exception {
        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Titanic\",\"genre\":\"Drama\",\"releaseYear\":1997,\"status\":\"watched\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.releaseYear").value(1997));
    }

    @Test
    void savesMovieStatusCorrectly() throws Exception {
        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Interstellar\",\"genre\":\"Sci-Fi\",\"releaseYear\":2014,\"status\":\"watchlist\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("watchlist"));
    }
    @Test
    void createdMovieGetsAnId() throws Exception {
        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Matrix\",\"genre\":\"Sci-Fi\",\"releaseYear\":1999,\"status\":\"watched\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void createdMovieHasCorrectTitleAndYear() throws Exception {
        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Gladiator\",\"genre\":\"Action\",\"releaseYear\":2000,\"status\":\"watched\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Gladiator"))
                .andExpect(jsonPath("$.releaseYear").value(2000));
    }

    @Test
    void movieListContainsSpiderman() throws Exception {
        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].title", hasItem("Spiderman")));
    }
}