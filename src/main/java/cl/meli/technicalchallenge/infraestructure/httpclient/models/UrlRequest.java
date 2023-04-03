package cl.meli.technicalchallenge.infraestructure.httpclient.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotBlank;

//@ApiModel(description = "Objeto de request para crear la url corta")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UrlRequest {
  //@ApiModelProperty(required = true, notes = "Url para convertir en pequeña")
  @NotBlank(message = "url no puede ser vacía")
  private String url;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
