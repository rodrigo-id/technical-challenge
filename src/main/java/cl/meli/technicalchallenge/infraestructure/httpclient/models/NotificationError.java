package cl.meli.technicalchallenge.infraestructure.httpclient.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.OffsetDateTime;

public class NotificationError implements Serializable {

  private static final long serialVersionUID = 1L;
  @JsonProperty("code")
  private String code;
  @JsonProperty("status")
  private String status;
  @JsonProperty("message")
  private String message;
  @JsonProperty("category")
  private String category;
  @JsonProperty("field_name")
  private String fieldName;
  @JsonProperty("timestamp")
  private OffsetDateTime timestamp;
  @JsonProperty("description")
  private String description;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public OffsetDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }
}
