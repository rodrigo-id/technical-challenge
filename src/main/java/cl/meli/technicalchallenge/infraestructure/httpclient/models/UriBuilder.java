package cl.meli.technicalchallenge.infraestructure.httpclient.models;

import java.text.MessageFormat;

public class UriBuilder {
  private String protocol;
  private String server;
  private int port;
  private String path;

  public UriBuilder(String protocol, String server, int port, String path) {
    this.protocol = protocol;
    this.server = server;
    this.port = port;
    this.path = path;
  }

  @Override
  public String toString() {
    return MessageFormat.format("{0}://{1}:{2}/{3}", this.protocol, this.server, this.port, this.path);
  }
}
