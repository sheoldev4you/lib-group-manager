import group.manager.Right;
import group.manager.RightListener;

/**
 * @author sheol on 9/26/17 at 4:48 PM
 * @project JavaGroupManager
 */
public class Main {
    public static void main(String[] args) {
        Right right = new Right(new RightListener() {
            @Override
            public void onAddRight(Right instance, String right) {
                System.out.println("add : " + right);
                System.out.println("Binary path : " + instance.getRightLvlBinary());
                System.out.println(instance.getAllRights().toString());
            }

            @Override
            public void onDeleteRight(Right instance, String right) {
                System.out.println("del : " + right);
                System.out.println("Binary path : " + instance.getRightLvlBinary());
                System.out.println(instance.getAllRights().toString());
            }
        });

        right.add("ACCESS_TWITTER");
        right.add("TEST_DATA");
        System.out.println("======================================");

        right.add("SHOW_BLOG");
        System.out.println("======================================");

        right.delete("ACCESS_TWITTER");
        System.out.println("======================================");
    }
}
