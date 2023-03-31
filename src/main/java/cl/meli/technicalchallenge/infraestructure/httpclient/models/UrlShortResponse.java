package cl.meli.technicalchallenge.infraestructure.httpclient.models;

public class UrlShortResponse {
  private String shortUrl;

  public UrlShortResponse(String shortUrl) {
    this.shortUrl = shortUrl;
  }

  public String getShortUrl() {
    return shortUrl;
  }

  public void setShortUrl(String shortUrl) {
    this.shortUrl = shortUrl;
  }
}
