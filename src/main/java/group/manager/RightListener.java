package group.manager;

/**
 * @author sheol on 9/26/17 at 4:47 PM
 * @project JavaGroupManager
 */
public interface RightListener {
  interface Add {
    void onAddRight(Right instance, String rightToAdd, long rightLvl);
  }

  interface Delete {
    void onDeleteRight(Right instance, String rightToDelete, long rightLvl);
  }
}
