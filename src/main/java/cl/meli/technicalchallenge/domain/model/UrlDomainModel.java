package cl.meli.technicalchallenge.domain.model;

import java.util.Date;

public class UrlDomainModel {

  private String longUrl;
  private String shortUrl;
  private Date createdDate;
  private boolean isPresent;

  public String getLongUrl() {
    return longUrl;
  }

  public void setLongUrl(String longUrl) {
    this.longUrl = longUrl;
  }

  public String getShortUrl() {
    return shortUrl;
  }

  public void setShortUrl(String shortUrl) {
    this.shortUrl = shortUrl;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public boolean isPresent() {
    return isPresent;
  }

  public void setPresent(boolean present) {
    isPresent = present;
  }

  public static Builder builder() {return new Builder();}

  public static final class Builder {
    private final UrlDomainModel urlDomainModel;

    public Builder() {
      this.urlDomainModel = new UrlDomainModel();
    }

    public Builder setLongUrl(String longUrl) {
      urlDomainModel.setLongUrl(longUrl);
      return this;
    }

    public Builder setShortUrl(String shortUrl) {
      urlDomainModel.setShortUrl(shortUrl);
      return this;
    }

    public Builder setCreatedDate(Date createdDate) {
      urlDomainModel.setCreatedDate(createdDate);
      return this;
    }

    public Builder setIsPresent(boolean isPresent) {
      urlDomainModel.setPresent(isPresent);
      return this;
    }

    public UrlDomainModel build() {
      return urlDomainModel;
    }
  }
}
