package models;


public class FTPConectData {
  private final String hostname;
  private final int port;
  private final String username;
  private final String password;

  public FTPConectData(String hostname, String port,  String username,
      String password) {
    this.hostname = hostname;
    this.port = Integer.parseInt(port);
    this.username = username.isEmpty()? "anonymous": username;
    this.password = password;
  }

  public String getHostname() {
    return hostname;
  }

  public int getPort() {
    return port;
  }


  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }


}
