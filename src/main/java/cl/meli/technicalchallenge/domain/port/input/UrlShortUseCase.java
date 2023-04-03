package cl.meli.technicalchallenge.domain.port.input;

public interface UrlShortUseCase {

  String createShortUrl(String requestUrl, String serverUrl);
}
