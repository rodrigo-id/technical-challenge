package cl.meli.technicalchallenge.domain.model;

import java.time.LocalDateTime;
import java.util.Date;

public class LogVisitDomainModel {
  private String shortUrl;
  private Integer timesVisited;
  private Boolean isActive;
  private LocalDateTime lastTimeVisited;


  public String getShortUrl() {
    return shortUrl;
  }

  public void setShortUrl(String shortUrl) {
    this.shortUrl = shortUrl;
  }

  public Integer getTimesVisited() {
    return timesVisited;
  }

  public void setTimesVisited(Integer timesVisited) {
    this.timesVisited = timesVisited;
  }

  public Boolean getActive() {
    return isActive;
  }

  public void setActive(Boolean active) {
    isActive = active;
  }

  public LocalDateTime getLastTimeVisited() {
    return lastTimeVisited;
  }

  public void setLastTimeVisited(LocalDateTime lastTimeVisited) {
    this.lastTimeVisited = lastTimeVisited;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private final LogVisitDomainModel logVisitDomainModel;

    public Builder() {
      this.logVisitDomainModel = new LogVisitDomainModel();
    }

    public Builder setShortUrl(String shortUrl) {
      logVisitDomainModel.setShortUrl(shortUrl);
      return this;
    }

    public Builder setTimesVisited(Integer timesVisited) {
      logVisitDomainModel.setTimesVisited(timesVisited);
      return this;
    }

    public Builder setIsActive(Boolean isActive) {
      logVisitDomainModel.setActive(isActive);
      return this;
    }

    public Builder setLastTimeVisited(LocalDateTime lastTimeVisited) {
      logVisitDomainModel.setLastTimeVisited(lastTimeVisited);
      return this;
    }

    public LogVisitDomainModel build() {
      return logVisitDomainModel;
    }
  }
}
