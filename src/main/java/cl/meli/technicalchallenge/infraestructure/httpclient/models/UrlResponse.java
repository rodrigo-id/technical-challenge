package cl.meli.technicalchallenge.infraestructure.httpclient.models;

public class UrlResponse {
  private String shortUrl;

  public UrlResponse(String shortUrl) {
    this.shortUrl = shortUrl;
  }

  public String getShortUrl() {
    return shortUrl;
  }

  public void setShortUrl(String shortUrl) {
    this.shortUrl = shortUrl;
  }
}
