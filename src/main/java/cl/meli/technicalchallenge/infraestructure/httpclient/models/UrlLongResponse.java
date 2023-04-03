package cl.meli.technicalchallenge.infraestructure.httpclient.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UrlLongResponse {
  @JsonProperty("long_url")
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
