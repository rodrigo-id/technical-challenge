package cl.meli.technicalchallenge.infraestructure.httpclient.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Status {
  private boolean active;
  @JsonProperty("deactivate_date")
  private String deactivateDate;

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public String getDeactivateDate() {
    return deactivateDate;
  }

  public void setDeactivateDate(String deactivateDate) {
    this.deactivateDate = deactivateDate;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private final Status status;

    private Builder() {
      this.status = new Status();
    }

    public Builder setActive(boolean active) {
      status.setActive(active);
      return this;
    }

    public Builder setDeactivateDate(String deactivateDate) {
      status.setDeactivateDate(deactivateDate);
      return this;
    }

    public Status build() {
      return status;
    }
  }
}
