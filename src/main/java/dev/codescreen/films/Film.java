package dev.codescreen.films;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Film {

    private String name;
    private Integer length;
    private Double rating;
    private String directorName;
    private LocalDate releaseDate;


    public Film(Object name, Object length, Object rating, Object directorName, Object releaseDate) {
        this.name=(String)name;
        this.length=Integer.parseInt(length.toString());
        this.rating=Double.parseDouble(rating.toString());
        this.directorName=directorName.toString();
        this.releaseDate=LocalDate.parse(releaseDate.toString());
    }


}
