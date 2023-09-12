package db;

import settings.Settings;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class DBConnector implements IDBConnector {

  private Map<String, String> settings;

  private static Connection connection = null;
  private static Statement statement = null;

  public DBConnector() {
    this.settings = new Settings().getDbSettings();
  }

  private void open() {
    try {
      if(connection == null) {
        connection = DriverManager.getConnection(settings.get("db_url") + "/" + settings.get("db_name"), settings.get("username"), settings.get("password"));
      }
      if(statement == null) {
        statement = connection.createStatement();
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  private void close() {
    if(statement != null) {
      try {
        statement.close();
        statement = null;
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    }
    if(connection != null) {
      try {
        connection.close();
        connection = null;
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    }
  }

//  public List executeQuery() {
//
//  }

  public void execute(String sqlRequest) {
    this.open();
    try {
      statement.execute(sqlRequest);
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      this.close();
    }
  }

}
