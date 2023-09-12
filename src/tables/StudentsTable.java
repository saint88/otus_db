package tables;

import dataobj.Curator;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentsTable extends AbsTable {
  private static final String NAME = "students";

  private static final Map<String, String> COLUMNS = new HashMap(){{
    put("name", "TEXT");
    put("age", "int");
    put("id", "");
  }};

  public StudentsTable() {
    super(NAME, COLUMNS);
  }

  @Override
  public List<Curator> list(String... columns) {
    List<Curator> result = new ArrayList<>();

    ResultSet sqlResult = dbConnector.executeQuery(
        String.format("select %s from %s", this.converColumnsTable(columns), NAME)
    );

    return result;
  }
}
