package group.manager;

/**
 * @author sheol on 9/26/17 at 4:47 PM
 * @project JavaGroupManager
 */
public interface RightListener {
    void onAddRight(Right instance, String right);

    void onDeleteRight(Right instance, String right);
}
