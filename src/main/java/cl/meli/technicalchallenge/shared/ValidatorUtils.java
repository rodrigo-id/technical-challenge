package cl.meli.technicalchallenge.shared;

import cl.meli.technicalchallenge.shared.error.DomainException;
import cl.meli.technicalchallenge.shared.error.ErrorCodes;
import java.text.MessageFormat;
import org.apache.commons.validator.routines.UrlValidator;

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
    UrlValidator validator = new UrlValidator();

    if (validator.isValid(url)) {
        return true;
    }

    throw new DomainException(
        ErrorCodes.DOMAIN_INVALID_URL.getCode(),
        MessageFormat.format(ErrorCodes.DOMAIN_INVALID_URL.getMessage(), url),
        "ValidatorUtils.validateUrl",
        ErrorCodes.DOMAIN_INVALID_URL.getStatus());
  }
}
