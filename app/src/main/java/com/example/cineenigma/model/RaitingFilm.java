package com.example.cineenigma.model;

public class RaitingFilm {
    String username;
    String filmTitle;
    String filmDate;
    int filmScenario;
    int filmRealisation;
    int filmMusic;
    String filmCommentaire;

    public RaitingFilm(String username, String filmTitle, String filmDate, int filmScenario, int filmRealisation, int filmMusic, String filmCommentaire) {
        this.username = username;
        this.filmTitle = filmTitle;
        this.filmDate = filmDate;
        this.filmScenario = filmScenario;
        this.filmRealisation = filmRealisation;
        this.filmMusic = filmMusic;
        this.filmCommentaire = filmCommentaire;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public String getFilmDate() {
        return filmDate;
    }

    public void setFilmDate(String filmDate) {
        this.filmDate = filmDate;
    }

    public int getFilmScenario() {
        return filmScenario;
    }

    public void setFilmScenario(int filmScenario) {
        this.filmScenario = filmScenario;
    }

    public int getFilmRealisation() {
        return filmRealisation;
    }

    public void setFilmRealisation(int filmRealisation) {
        this.filmRealisation = filmRealisation;
    }

    public int getFilmMusic() {
        return filmMusic;
    }

    public void setFilmMusic(int filmMusic) {
        this.filmMusic = filmMusic;
    }

    public String getFilmCommentaire() {
        return filmCommentaire;
    }

    public void setFilmCommentaire(String filmCommentaire) {
        this.filmCommentaire = filmCommentaire;
    }

    @Override
    public String toString() {
        return "RaitingFilm{" +
                "username='" + username + '\'' +
                ", filmTitle='" + filmTitle + '\'' +
                ", filmDate='" + filmDate + '\'' +
                ", filmScenario=" + filmScenario +
                ", filmRealisation=" + filmRealisation +
                ", filmMusic=" + filmMusic +
                ", filmCommentaire='" + filmCommentaire + '\'' +
                '}';
    }
}
