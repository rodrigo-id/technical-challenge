package cl.meli.technicalchallenge.domain.model;

import java.util.Date;

public class LogDomainModel {

  private String shortUrlVisited;
  private Date visitedDate;
  private boolean isActive;
  private Date deactivateDate;

  public String getShortUrlVisited() {
    return shortUrlVisited;
  }

  public void setShortUrlVisited(String shortUrlVisited) {
    this.shortUrlVisited = shortUrlVisited;
  }

  public Date getVisitedDate() {
    return visitedDate;
  }

  public void setVisitedDate(Date visitedDate) {
    this.visitedDate = visitedDate;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  public Date getDeactivateDate() {
    return deactivateDate;
  }

  public void setDeactivateDate(Date deactivateDate) {
    this.deactivateDate = deactivateDate;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private final LogDomainModel logDomainModel;

    public Builder() {
      this.logDomainModel = new LogDomainModel();
    }

    public Builder setShortUrlVisited(String shortUrlVisited) {
      logDomainModel.setShortUrlVisited(shortUrlVisited);
      return this;
    }

    public Builder setVisitedDate(Date visitedDate) {
      logDomainModel.setVisitedDate(visitedDate);
      return this;
    }

    public Builder setIsActive(boolean isActive) {
      logDomainModel.setActive(isActive);
      return this;
    }

    public Builder setDeactivateDate(Date deactivateDate) {
      logDomainModel.setDeactivateDate(deactivateDate);
      return this;
    }

    public LogDomainModel build() {
      return logDomainModel;
    }
  }
}
