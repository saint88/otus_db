package tables;

import dataobj.Curator;
import db.DBConnector;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CuratorsTable extends AbsTable {

  private static final String NAME = "curators";

  private static final Map<String, String> COLUMNS = new HashMap(){{
    put("name", "TEXT");
    put("age", "int");
    put("id", "");
  }};

  public CuratorsTable() {
    super(NAME, COLUMNS);
  }

  @Override
  public List<Curator> list(String... columns) {
    List<Curator> result = new ArrayList<>();

    ResultSet sqlResult = dbConnector.executeQuery(
        String.format("select %s from %s", this.converColumnsTable(columns), NAME)
    );

    try {
      while(sqlResult.next()) {
        result.add(new Curator(
                sqlResult.getString(1),
                sqlResult.getString(2),
                sqlResult.getString(3)
            )
        );
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }

    return result;
  }
}
