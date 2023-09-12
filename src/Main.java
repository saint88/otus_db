import dataobj.Curator;
import db.DBConnector;
import tables.AbsTable;
import tables.CuratorsTable;
import tables.GroupsTable;
import tables.StudentsTable;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    AbsTable studentsTable = new StudentsTable();
    AbsTable curatorsTable = new CuratorsTable();
    AbsTable groupsTable = new GroupsTable();

    try {
      studentsTable.create();
      curatorsTable.create();
      groupsTable.create();

      List<Curator> curators = curatorsTable.list("name", "age");

    } finally {
      studentsTable.delete();
      curatorsTable.delete();
      groupsTable.delete();

      DBConnector.close();
    }
  }
}