package cl.meli.technicalchallenge.infraestructure.httpclient.models;

public class Visited {
  private String date;

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private final Visited visited;

    private Builder() {
      this.visited = new Visited();
    }

    public Builder setDate(String date) {
      visited.setDate(date);
      return this;
    }
    public Visited build() {
      return visited;
    }
  }
}
