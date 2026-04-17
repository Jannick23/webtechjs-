package de.htw_berlin.webtechjs;

public class Movie {

    private Long id;
    private String title;
    private String genre;
    private int releaseYear;
    private String status;

    public Movie(Long id, String title, String genre, int releaseYear, String status) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.status = status;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public int getReleaseYear() { return releaseYear; }
    public String getStatus() { return status; }
}
