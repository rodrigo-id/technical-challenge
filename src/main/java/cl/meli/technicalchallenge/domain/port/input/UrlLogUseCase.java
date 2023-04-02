package cl.meli.technicalchallenge.domain.port.input;

public interface UrlLogUseCase {

  void save(String url);
  void deactivate(String url);

}
