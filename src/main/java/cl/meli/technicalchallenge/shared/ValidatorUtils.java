package cl.meli.technicalchallenge.shared;

import cl.meli.technicalchallenge.shared.error.DomainException;
import cl.meli.technicalchallenge.shared.error.ErrorCodes;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.MessageFormat;

public class ValidatorUtils {

  private static ValidatorUtils validatorUtils;
  private ValidatorUtils(){}

  public static ValidatorUtils getInstance(){
    if (validatorUtils == null) {
      return new ValidatorUtils();
    }
    return validatorUtils;
  }

  public boolean validateUrl(String url) {
    try {
      new URL(url).toURI();
      return true;
    } catch (MalformedURLException | URISyntaxException e) {
      throw new DomainException(
          ErrorCodes.DOMAIN_INVALID_URL.getCode(),
          MessageFormat.format(ErrorCodes.DOMAIN_INVALID_URL.getMessage(), url),
          "ValidatorUtils.validateUrl",
          e,
          ErrorCodes.DOMAIN_INVALID_URL.getStatus());
    }
  }
}
