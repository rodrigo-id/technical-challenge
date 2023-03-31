package cl.meli.technicalchallenge.shared.utils;

public class UrlConverterUtil {

  private static UrlConverterUtil urlConverterUtil;

  private UrlConverterUtil(){}

  public static UrlConverterUtil getInstance() {
    if (urlConverterUtil == null) {
      return new UrlConverterUtil();
    }

    return urlConverterUtil;
  }

  public String convertToServerUrl(String uri, String url) {
    return url.replace(uri, "");
  }
}
