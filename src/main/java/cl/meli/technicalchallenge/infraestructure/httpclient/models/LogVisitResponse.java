package cl.meli.technicalchallenge.infraestructure.httpclient.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class LogVisitResponse {

  @JsonProperty("short_url")
  private String shortUrl;
  @JsonProperty("time_visited")
  private Integer timeVisited;
  @JsonProperty("last_time_visited")
  private LocalDateTime lastTimeVisited;
  private Boolean active;


  public String getShortUrl() {
    return shortUrl;
  }

  public void setShortUrl(String shortUrl) {
    this.shortUrl = shortUrl;
  }

  public Integer getTimeVisited() {
    return timeVisited;
  }

  public void setTimeVisited(Integer timeVisited) {
    this.timeVisited = timeVisited;
  }

  public LocalDateTime getLastTimeVisited() {
    return lastTimeVisited;
  }

  public void setLastTimeVisited(LocalDateTime lastTimeVisited) {
    this.lastTimeVisited = lastTimeVisited;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private final LogVisitResponse logVisitResponse;

    public Builder() {
      this.logVisitResponse = new LogVisitResponse();
    }

    public Builder setShortUrl(String shortUrl) {
      logVisitResponse.setShortUrl(shortUrl);
      return this;
    }

    public Builder setTimeVisited(Integer timeVisited) {
      logVisitResponse.setTimeVisited(timeVisited);
      return this;
    }

    public Builder setLastTimeVisited(LocalDateTime lastTimeVisited) {
      logVisitResponse.setLastTimeVisited(lastTimeVisited);
      return this;
    }

    public Builder setActive(Boolean active) {
      logVisitResponse.setActive(active);
      return this;
    }

    public LogVisitResponse build() {
      return logVisitResponse;
    }
  }
}
