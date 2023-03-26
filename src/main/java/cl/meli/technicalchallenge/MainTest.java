package cl.meli.technicalchallenge;

import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.validator.routines.UrlValidator;

public class MainTest {
  public static void main(String[] args) throws MalformedURLException {
    String url = "https://www.baeldung.com/spring-boot-h2-database#:~:text=Accessing%20the%20H2%20Console&text=By%20default%2C%20the%20H2%20console%20is%20not%20enabled%20in%20Spring.&text=Then%2C%20after%20starting%20the%20application,us%20with%20a%20login%20page.&text=The%20web%20console%20has%20an,feature%20that%20suggests%20SQL%20keywords.";
    System.out.println(validateUrl(url));

    System.out.println(isValidURL(url));
  }

  public static boolean validateUrl(String url) {
    try {
      new URL(url);
      return true;
    } catch (MalformedURLException e) {
      return false;
    }
  }

  static boolean isValidURL(String url) throws MalformedURLException {
    UrlValidator validator = new UrlValidator();
    return validator.isValid(url);
  }
}
