package managers;

import constants.Constants;
import models.FTPConectData;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPClientFactory {

  public static FTPClient buildSimpleClient(FTPConectData ftpFileModel)
      throws Exception {

    FTPClient client = new FTPClient();
    client.setDataTimeout(Constants.FTP_TIMEOUT);
    client.setControlKeepAliveTimeout(Constants.FTP_ALIVE_TIMEOUT);
    client.connect(ftpFileModel.getHostname(), ftpFileModel.getPort());
    client.enterLocalPassiveMode();
    client.setSoTimeout(Constants.FTP_SO_TIMEOUT);
    int reply = client.getReplyCode();

    if (!FTPReply.isPositiveCompletion(reply)) {
      return null;
    }

    if (!client.login(ftpFileModel.getUsername(), ftpFileModel.getPassword())) {
     return null;
    }

    client.setKeepAlive(true);
    return client;
  }

}
