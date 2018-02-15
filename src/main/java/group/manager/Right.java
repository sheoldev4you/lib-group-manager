package group.manager;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sheol on 9/26/17 at 4:48 PM
 * @project JavaGroupManager
 */
public class Right {
  private static final Logger LOGGER = LoggerFactory.getLogger(Right.class);
  private static final String INTERNAL_RIGHTS = "internal-rights.json";
  private static final String TYPE = "right";
  private static final String FIELD_TYPE = "type";
  private static final String FIELD_DESCRIPTION = "description";
  private static final String FIELD_LEVEL = "level";
  private Path internalRights;
  private int rightLvl = 0x00;
  private Map<String, RightMapper> rightList = new HashMap<>();
  private RightListener.Add add;
  private RightListener.Delete delete;

  public Right(RightListener.Add add, RightListener.Delete delete) {
    URL url = Right.class.getClassLoader().getResource(INTERNAL_RIGHTS);
    if (url != null) {
      try {
        internalRights = Paths.get(url.toURI());
      } catch (URISyntaxException e) {
        LOGGER.error("{} was not found !", INTERNAL_RIGHTS);
      }
    }
    this.add = add;
    this.delete = delete;
  }

  public static void toString(Right instance, String rightToAdd) {
    LOGGER.info("Right : {}.", rightToAdd);
    LOGGER.info("Binary path : {}.",instance.getRightLvlBinary());
    LOGGER.info("Rights list : {}.", instance.getAllRights());
  }

  private boolean isJsonStructureValid(JSONObject data) {
    boolean isValid = false;
    if (data != null && data.has(FIELD_TYPE)) {
      isValid = true;
      if (!TYPE.equals(data.getString(FIELD_TYPE))) {
        isValid = false;
        LOGGER.error("Data isn't '{}' type !", TYPE);
      }
      if (!data.has("description")) {
        isValid = false;
        LOGGER.error("Field '{}' was not found.", FIELD_DESCRIPTION);
      }
      if (!data.has("level")) {
        isValid = false;
        LOGGER.error("Field '{}' was not found.", FIELD_LEVEL);
      }
    } else {
      LOGGER.error("Data is null or field '{}' was not found.", FIELD_TYPE);
    }
    return isValid;
  }

  private void loadRight(Object o) {
    if (o instanceof JSONObject) {
      loadRight((JSONObject) o);
    }
  }

  public void loadRight(JSONObject jsonObject) {
    String name = jsonObject.keys().next();
    JSONObject data = jsonObject.getJSONObject(name);
    if (isJsonStructureValid(data)) {
      rightList.put(name, new RightMapper()
          .withName(name)
          .withDescription(data.getString(FIELD_DESCRIPTION))
          .withLevel(data.getInt(FIELD_LEVEL))
      );
    }
  }

  public void loadInternalRights() throws IOException {
    if (internalRights != null) {
      loadRights(internalRights);
    }
  }

  public void loadRights(JSONArray jsonArray) {
    jsonArray.forEach(this::loadRight);
  }

  public void loadRights(File file) throws IOException {
    loadRights(new JSONArray(new String(Files.readAllBytes(Paths.get(file.getPath())))));
  }

  public void loadRights(Path path) throws IOException {
    loadRights(new JSONArray(new String(Files.readAllBytes(path))));
  }

  private int getGoodRight(String right) {
    if (rightList.containsKey(right)) {
      return (int) Math.pow(2, rightList.get(right).getLevel() + 1d);
    }
    return 0;
  }

  public List<String> getAllRights() {
    List<String> rights = new ArrayList<>();
    rightList.forEach((right, ignored) -> {
      if (hasRight(right)) {
        rights.add(right);
      }
    });
    return rights;
  }

  private boolean hasRight(String right) {
    return (getGoodRight(right) & rightLvl) > 0;
  }

  public void add(String right) {
    if (rightList.containsKey(right)) {
      rightLvl |= getGoodRight(rightList.get(right).getName());
      add.onAddRight(this, right);
    }
  }

  public void delete(String right) {
    if (rightList.containsKey(right)) {
      rightLvl ^= getGoodRight(rightList.get(right).getName());
      delete.onDeleteRight(this, right);
    }
  }

  public void set(int rightLvl) {
    this.rightLvl = rightLvl;
  }

  public Right withRightLvl(int rightLvl) {
    this.rightLvl = rightLvl;
    return this;
  }

  public int getRightLvl() {
    return rightLvl;
  }

  public String getRightLvlBinary() {
    return Integer.toBinaryString(rightLvl);
  }
}
