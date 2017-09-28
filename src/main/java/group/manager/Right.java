package group.manager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.*;

/**
 * @author sheol on 9/26/17 at 4:48 PM
 * @project JavaGroupManager
 */
public class Right {
    private int rightLvl = 0x00;
    private Map<String, RightMapper> rightList = new HashMap<>();
    private RightListener rightListener;

    public Right(RightListener rightListener) {
        this.rightListener = rightListener;
        try {
            JSONArray jsonArray = new JSONArray(new String(
                    Files.readAllBytes(FileSystems.getDefault().getPath(".", "rights.json"))));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.keys().next();
                JSONObject data = jsonObject.getJSONObject(name);
                RightMapper rightMapper = new RightMapper();
                rightMapper.setName(name);
                rightMapper.setDescription(data.getString("description"));
                rightMapper.setLevel(data.getInt("level"));
                rightList.put(name, rightMapper);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getGoodRight(String right) {
        if (rightList.containsKey(right)) {
            return (int) Math.pow(2, rightList.get(right).getLevel() + 1);
        }
        return 0;
    }

    public List<String> getAllRights() {
        List<String> rights = new ArrayList<>();
        Iterator<Map.Entry<String, RightMapper>> iterator = rightList.entrySet().iterator();
        for (; iterator.hasNext(); ) {
            String right = iterator.next().getKey();
            if (hasRight(right)) {
                rights.add(right);
            }
        }
        return rights;
    }

    private boolean hasRight(String right) {
        return (getGoodRight(right) & rightLvl) > 0;
    }

    public void add(String right) {
        if (rightList.containsKey(right)) {
            rightLvl |= getGoodRight(rightList.get(right).getName());
            rightListener.onAddRight(this, right);
        }
    }

    public void delete(String right) {
        if (rightList.containsKey(right)) {
            rightLvl ^= getGoodRight(rightList.get(right).getName());
            rightListener.onDeleteRight(this, right);
        }
    }

    public void set(int rightLvl) {
        this.rightLvl = rightLvl;
    }

    public int getRightLvl() {
        return rightLvl;
    }

    public String getRightLvlBinary() {
        return Integer.toBinaryString(rightLvl);
    }
}
