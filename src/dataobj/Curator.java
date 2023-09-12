package dataobj;

public class Curator {

  private String name = "";
  private String age = "";
  private String id = "";

  public Curator(String name, String age, String id) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public String getAge() {
    return age;
  }

  public String getId() {
    return id;
  }

}
