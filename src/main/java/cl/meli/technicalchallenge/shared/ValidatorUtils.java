package cl.meli.technicalchallenge.shared;

import org.apache.commons.validator.routines.UrlValidator;

public class ValidatorUtils {

  public boolean validateUrl(String url) {
    UrlValidator validator = new UrlValidator();
    return validator.isValid(url);
  }
}
