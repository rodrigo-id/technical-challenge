package cl.meli.technicalchallenge.domain.model;

import java.time.LocalDateTime;

public class LogDomainModel {

  private String shortUrlVisited;
  private LocalDateTime visitedDate;
  private Boolean isActive;
  private LocalDateTime deactivateDate;

  public String getShortUrlVisited() {
    return shortUrlVisited;
  }

  public void setShortUrlVisited(String shortUrlVisited) {
    this.shortUrlVisited = shortUrlVisited;
  }

  public LocalDateTime getVisitedDate() {
    return visitedDate;
  }

  public void setVisitedDate(LocalDateTime visitedDate) {
    this.visitedDate = visitedDate;
  }

  public Boolean isActive() {
    return isActive;
  }

  public void setActive(Boolean active) {
    isActive = active;
  }

  public LocalDateTime getDeactivateDate() {
    return deactivateDate;
  }

  public void setDeactivateDate(LocalDateTime deactivateDate) {
    this.deactivateDate = deactivateDate;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private final LogDomainModel logDomainModel;

    private Builder() {
      this.logDomainModel = new LogDomainModel();
    }

    public Builder setShortUrlVisited(String shortUrlVisited) {
      logDomainModel.setShortUrlVisited(shortUrlVisited);
      return this;
    }

    public Builder setVisitedDate(LocalDateTime visitedDate) {
      logDomainModel.setVisitedDate(visitedDate);
      return this;
    }

    public Builder setIsActive(boolean isActive) {
      logDomainModel.setActive(isActive);
      return this;
    }

    public Builder setDeactivateDate(LocalDateTime deactivateDate) {
      logDomainModel.setDeactivateDate(deactivateDate);
      return this;
    }

    public LogDomainModel build() {
      return logDomainModel;
    }
  }
}
