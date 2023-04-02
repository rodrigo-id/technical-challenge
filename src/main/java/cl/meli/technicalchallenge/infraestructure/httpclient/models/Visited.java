package cl.meli.technicalchallenge.infraestructure.httpclient.models;

public class Visited {
  private String visitedDate;

  public String getVisitedDate() {
    return visitedDate;
  }

  public void setVisitedDate(String visitedDate) {
    this.visitedDate = visitedDate;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private final Visited visited;

    private Builder() {
      this.visited = new Visited();
    }

    public Builder setVisitedDate(String visitedDate) {
      visited.setVisitedDate(visitedDate);
      return this;
    }
    public Visited build() {
      return visited;
    }
  }
}
