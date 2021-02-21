package dev.codescreen.films;

import java.util.List;

/**
 * Service that retrieves data from the CodeScreen Films API.
 */
final class FilmsAPIService {

  private static final String FILMS_ENDPOINT_URL = "https://app.codescreen.dev/api/assessments/films";

  //Your API token. Needed to successfully authenticate when calling the films endpoint.
  //This needs to be included in the Authorization header (using the Bearer authentication scheme) in the request you send to the films endpoint.
  private final String API_TOKEN = "8c5996d5-fb89-46c9-8821-7063cfbc18b1";

  FilmsAPIService() {}

  /**
   * Retrieves the data for all films by calling the https://app.codescreen.dev/api/assessments/films endpoint.
   *
   * @return a list containing the data for all films
   */
  List<Object> getAllFilms() {
    //TODO Implement
    //Note that the type of the returned list should be something that better represents film data.
    throw new UnsupportedOperationException("Not implemented, yet...");
  }

}
