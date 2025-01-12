package com.example.literatura.dto;

public class LibroDTO {

    private String title;
    private String language;
    private Integer publicationYear;
    private Integer downloadCount;
    private String autor;

    public LibroDTO() {
    }

    // Getters y Setters


    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }


    public Double getDownloadCount() {
        return downloadCount != null ? downloadCount.doubleValue() : null;  // Convierte a Double
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    @Override
    public String toString() {
        return "LibroDTO{" +
                "title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", publicationYear=" + publicationYear +
                '}';
    }
}
