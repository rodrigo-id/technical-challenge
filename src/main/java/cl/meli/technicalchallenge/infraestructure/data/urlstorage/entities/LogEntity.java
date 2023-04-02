package cl.meli.technicalchallenge.infraestructure.data.urlstorage.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Log")
public class LogEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(nullable = false)
  private String shortUrlVisited;

  @Column
  private Date visitedDate;

  @Column(columnDefinition = "boolean default false")
  private Boolean isActive;

  @Column
  private Date deactivateDate;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

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

  public Boolean isActive() {
    return isActive;
  }

  public void setActive(Boolean active) {
    isActive = active;
  }

  public Date getDeactivateDate() {
    return deactivateDate;
  }

  public void setDeactivateDate(Date deactivateDate) {
    this.deactivateDate = deactivateDate;
  }
}
