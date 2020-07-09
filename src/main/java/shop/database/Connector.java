package shop.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {
  private static final String USER = "mnhyophw";
  private static final String PASS = "CtKLcSYyrr6sFv3VcHX9VPi_5fBZWP6g";
  private static final String DB_URL =
      "jdbc:postgresql://ruby.db.elephantsql.com:5432/mnhyophw";

  public static Connection connect() throws SQLException {

    Properties props = new Properties();
    props.setProperty("user", USER);
    props.setProperty("password", PASS);
    return DriverManager.getConnection(DB_URL, props);
  }
}
