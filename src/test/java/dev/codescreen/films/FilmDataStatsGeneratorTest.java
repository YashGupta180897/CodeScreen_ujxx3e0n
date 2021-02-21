package dev.codescreen.films;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link FilmDataStatsGenerator}
 *
 * Note this file should not be modified. If you would like to add your own unit tests you can add these in a separate class.
 */
public class FilmDataStatsGeneratorTest {

  private static final String RIDLEY_SCOTT_DIRECTOR = "Ridley Scott";

  private static FilmDataStatsGenerator filmDataStatsGenerator;

  @BeforeClass
  public static void setUp() {
    FilmsAPIService filmsAPIService = new FilmsAPIService();
    filmDataStatsGenerator = FilmDataStatsGenerator.of(filmsAPIService);
  }

  @Test
  public void testBestRatedFilm() {
    assertEquals("Gladiator", filmDataStatsGenerator.getBestRatedFilm(RIDLEY_SCOTT_DIRECTOR));
  }

  @Test
  public void testDirectorWithMostFilms() {
    assertEquals(RIDLEY_SCOTT_DIRECTOR, filmDataStatsGenerator.getDirectorWithMostFilms());
  }

  @Test
  public void testGetAverageRating() {
    assertEquals(7.0, filmDataStatsGenerator.getAverageRating(RIDLEY_SCOTT_DIRECTOR), 0.0);
  }

  @Test
  public void testGetShortestDaysBetweenReleases() {
    assertEquals(29, filmDataStatsGenerator.getShortestNumberOfDaysBetweenFilmReleases(RIDLEY_SCOTT_DIRECTOR));
  }

}
