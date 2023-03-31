package cl.meli.technicalchallenge.infraestructure.httpclient.models;
public class UrlLongResponse {
  private String longUrl;

  public UrlLongResponse(String longUrl) {
    this.longUrl = longUrl;
  }

  public String getLongUrl() {
    return longUrl;
  }

  public void setLongUrl(String longUrl) {
    this.longUrl = longUrl;
  }
}
