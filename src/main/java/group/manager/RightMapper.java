package group.manager;

/**
 * @author sheol on 9/26/17 at 4:48 PM
 * @project JavaGroupManager
 */
public class RightMapper {
  private String name;
  private String description;
  private int level;

  public int getLevel() {
    return level;
  }

  public String getDescription() {
    return description;
  }

  public String getName() {
    return name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RightMapper withLevel(int level) {
    this.level = level;
    return this;
  }

  public RightMapper withName(String name) {
    this.name = name;
    return this;
  }

  public RightMapper withDescription(String description) {
    this.description = description;
    return this;
  }
}
