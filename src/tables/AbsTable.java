package tables;

import db.DBConnector;
import db.IDBConnector;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbsTable implements ITable {

  protected IDBConnector dbConnector = null;
  private String tableName = "";
  private Map<String, String> columns = null;

  public AbsTable(String tableName, Map<String, String> columns) {
    dbConnector = new DBConnector();
    this.tableName = tableName;
    this.columns = columns;
  }

  @Override
  public void create() {
    List<String> columnsStr = new ArrayList<>();

    for(Map.Entry<String, String> entry: columns.entrySet()) {
      if(entry.getKey().equals("id")) {
        columnsStr.add(String.format("%s serial primary key", entry.getKey()));
      } else {
        columnsStr.add(String.format("%s %s", entry.getKey(), entry.getValue()));
      }
    }

    dbConnector.execute(
        String.format("create table if not exist %s (%s);",
            this.tableName,
            String.join(", ", columnsStr)
        )
    );
  }

  @Override
  public void delete() {
    dbConnector.execute(String.format("drop table if exist %s;", this.tableName));
  }

  protected String converColumnsTable(String... columns) {
    if(columns.length == 0) {
      return "*";
    }

    return String.join(", ", columns);
  }

  public abstract List list(String... columns);
}
