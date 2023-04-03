package cl.meli.technicalchallenge.infraestructure.data.entities;

import java.time.LocalDateTime;
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

  @Column( columnDefinition = "TIMESTAMP")
  private LocalDateTime visitedDate;

  @Column(columnDefinition = "boolean default false")
  private Boolean active;

  @Column( columnDefinition = "TIMESTAMP")
  private LocalDateTime deactivateDate;

  @Column(nullable = false)
  private String campaignType;

  public String getCampaignType() {
    return campaignType;
  }

  public void setCampaignType(String campaignType) {
    this.campaignType = campaignType;
  }

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

  public LocalDateTime getVisitedDate() {
    return visitedDate;
  }

  public void setVisitedDate(LocalDateTime visitedDate) {
    this.visitedDate = visitedDate;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public LocalDateTime getDeactivateDate() {
    return deactivateDate;
  }

  public void setDeactivateDate(LocalDateTime deactivateDate) {
    this.deactivateDate = deactivateDate;
  }
}
