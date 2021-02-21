package dev.codescreen.films;

import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
/**
 * Generates various statistics about the films data set returned by the given {@link FilmsAPIService}
 */
final class FilmDataStatsGenerator {

  private final FilmsAPIService filmsAPIService;

  private static List<Film> allFilms;

  static FilmDataStatsGenerator of(FilmsAPIService filmsAPIService) {
    FilmDataStatsGenerator filmDataStatsGenerator= new FilmDataStatsGenerator(filmsAPIService);
    allFilms=filmsAPIService.getAllFilms();
    return filmDataStatsGenerator;
  }

  private FilmDataStatsGenerator(FilmsAPIService filmsAPIService) {
    this.filmsAPIService = filmsAPIService;
  }

  /**
   * Retrieves the name of the best rated film that was directed by the director with the given name.
   *
   * @param directorName the name of the director
   * @return the name of the best rated film that was directed by the director with the given name
   * @throws IllegalArgumentException if the service contains no films directed by the given director
   */
  String getBestRatedFilm(String directorName) {
    Film bestRatedFilm = allFilms.stream()
            .filter(film -> film.getDirectorName().equalsIgnoreCase(directorName))
            .max(Comparator.comparingDouble(Film::getRating))
            .get();

    return bestRatedFilm.getName();
  }

  /**
   * Retrieves the name of the director who has directed the most films in the CodeScreen Film service.
   * Note there will only be one film with the best rating.

   * @return the name of the director who has directed the most films in the CodeScreen Film service.
   */
  String getDirectorWithMostFilms() {
    Map.Entry<String, Long> maxEntry = allFilms.stream()
            .collect(Collectors.groupingBy(Film::getDirectorName, Collectors.counting()))
            .entrySet().stream()
            .max(Comparator.comparing(Map.Entry::getValue))
            .get();
    return maxEntry.getKey();
  }

  /**
   * Retrieves the average rating for the films directed by the given director, rounded to 1 decimal place.
   *
   * @param directorName the name of the director
   * @return the average rating for the films directed by the given director
   * @throws IllegalArgumentException if the service contains no films directed by the given director
   */
  double getAverageRating(String directorName) {
    return Math.round(allFilms.stream()
            .filter(film -> film.getDirectorName().equalsIgnoreCase(directorName))
            .mapToDouble(Film::getRating)
            .average().orElse(0));
  }

  /**
   * Retrieves the shortest number of days between any two film releases directed by the given director.
   *
   * If there is only one film directed by the given director, return 0.
   * Note that no director released more than one film on any given day.
   *
   * For example, if the service returns the following 4 films,
   *
   * {
   *    "name": "Batman Begins",
   *    "length": 140,
   *    "rating": 8.2,
   *    "releaseDate": "2006-06-16",
   *    "directorName": "Christopher Nolan"
   * },
   * {
   *    "name": "Interstellar",
   *    "length": 169,
   *    "rating": 8.6,
   *    "releaseDate": "2014-11-07",
   *    "directorName": "Christopher Nolan"
   * },
   * {
   *    "name": "Prestige",
   *    "length": 130,
   *    "rating": 8.5,
   *    "releaseDate": "2006-11-10",
   *    "directorName": "Christopher Nolan"
   * },
   * {
   *    "name": "Black Hawk Down",
   *    "length": 152,
   *    "rating": 7.7,
   *    "releaseDate": "2001-1-18",
   *    "directorName": "Ridley Scott"
   * }
   *
   * then this method should return 147 for Christopher Nolan, as Prestige was released 147 days after Batman Begins.
   *
   * @param directorName the name of the director
   * @return the shortest number of days between any two film releases directed by the given director
   * @throws IllegalArgumentException if the service contains no films directed by the given director
   */
  int getShortestNumberOfDaysBetweenFilmReleases(String directorName) {

    List<Film> sortedFilms = allFilms.stream()
            .filter(film -> film.getDirectorName().equalsIgnoreCase(directorName))
            .sorted((d1,d2)-> d1.getReleaseDate().compareTo(d2.getReleaseDate()))
            .collect(Collectors.toList());

    long minDiffBetMovie=9999999; //days
    for(int i=0;i<sortedFilms.size()-1;i++){
      long diff=ChronoUnit.DAYS.between(sortedFilms.get(i).getReleaseDate(), sortedFilms.get(i+1).getReleaseDate());
      if(diff<minDiffBetMovie)
        minDiffBetMovie=diff;
    }
    return (int)minDiffBetMovie;

  }

}
