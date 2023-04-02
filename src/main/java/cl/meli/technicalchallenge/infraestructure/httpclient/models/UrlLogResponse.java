package cl.meli.technicalchallenge.infraestructure.httpclient.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class UrlLogResponse {

  private Status status;

  @JsonProperty("visited_dates")
  private List<Visited> visitedList;


  public List<Visited> getVisitedList() {
    return visitedList;
  }

  public void setVisitedList(List<Visited> visitedList) {
    this.visitedList = visitedList;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
}
