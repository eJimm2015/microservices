package fr.dauphine.microservice.model;

public enum Genre {
    M("M"),F("F");
    private String genre;

    Genre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return genre;
    }
}
