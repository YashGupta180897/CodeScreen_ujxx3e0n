package dev.codescreen.films;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Service that retrieves data from the CodeScreen Films API.
 */
final class FilmsAPIService {

  private static final String FILMS_ENDPOINT_URL = "https://app.codescreen.dev/api/assessments/films";

  private static final Logger LOG = LoggerFactory.getLogger(FilmsAPIService.class);

  //Your API token. Needed to successfully authenticate when calling the films endpoint.
  //This needs to be included in the Authorization header (using the Bearer authentication scheme) in the request you send to the films endpoint.
  private final String API_TOKEN = "8c5996d5-fb89-46c9-8821-7063cfbc18b1";

  FilmsAPIService() {}

  /**
   * Retrieves the data for all films by calling the https://app.codescreen.dev/api/assessments/films endpoint.
   *
   * @return a list containing the data for all films
   */
  List<Film> getAllFilms() {

    OkHttpClient client = new OkHttpClient();

    Request request = new Request.Builder()
            .url(FILMS_ENDPOINT_URL)
            .addHeader("Authorization","Bearer "+API_TOKEN)
            .build();

    try {
      Response response = client.newCall(request).execute();
      String responseBody = response.body().string();
      ObjectMapper objectMapper = new ObjectMapper();

      List<Object> filmsObject= objectMapper.readValue(responseBody, objectMapper.getTypeFactory().constructCollectionType(List.class, Object.class));

      List<Film> allFilms = new ArrayList<>();
      for(Object map:filmsObject) {
        LinkedHashMap linkedHashMap = (LinkedHashMap) map;
        Film f = new Film(linkedHashMap.get("name"), linkedHashMap.get("length"), linkedHashMap.get("rating"),
                linkedHashMap.get("directorName"), linkedHashMap.get("releaseDate"));
        allFilms.add(f);
      }
      return allFilms;

    } catch (IOException e) {
      LOG.error(e.getMessage());
      LOG.info("Error in getting movies");
    }
    throw new UnsupportedOperationException("Error in getting movies..");

  }

}
