package cl.meli.technicalchallenge.shared.utils;

import cl.meli.technicalchallenge.shared.error.DomainException;
import cl.meli.technicalchallenge.shared.error.ErrorCodes;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.MessageFormat;

public class ValidatorUtil {

  private static ValidatorUtil validatorUtil;
  private ValidatorUtil(){}

  public static ValidatorUtil getInstance(){
    if (validatorUtil == null) {
      return new ValidatorUtil();
    }
    return validatorUtil;
  }

  public boolean validateUrl(String url) {
    try {
      new URL(url).toURI();
      return true;
    } catch (MalformedURLException | URISyntaxException e) {
      throw new DomainException(
          ErrorCodes.DOMAIN_INVALID_URL.getCode(),
          MessageFormat.format(ErrorCodes.DOMAIN_INVALID_URL.getMessage(), url),
          "ValidatorUtil.validateUrl",
          e,
          ErrorCodes.DOMAIN_INVALID_URL.getStatus());
    }
  }
}
