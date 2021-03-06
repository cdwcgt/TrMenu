package me.arasple.mc.trmenu.data;

import me.arasple.mc.trmenu.display.Button;
import me.arasple.mc.trmenu.display.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author Arasple
 * @date 2019/10/4 20:04
 */
public class ArgsCache {

    private static HashMap<UUID, String[]> args = new HashMap<>();
    private static HashMap<UUID, InventoryClickEvent> event = new HashMap<>();
    private static HashMap<UUID, Integer> heldSlot = new HashMap<>();
    private static HashMap<UUID, Item> clickedItem = new HashMap<>();
    private static HashMap<UUID, Button> clickedButtons = new HashMap<>();
    private static HashMap<UUID, String> input = new HashMap<>();

    public static HashMap<UUID, String[]> getArgs() {
        return args;
    }

    public static String[] getPlayerArgs(Player player) {
        return args.getOrDefault(player.getUniqueId(), new String[0]);
    }

    public static HashMap<UUID, InventoryClickEvent> getEvent() {
        return event;
    }

    public static HashMap<UUID, Integer> getHeldSlot() {
        return heldSlot;
    }

    public static HashMap<UUID, Item> getClickedItem() {
        return clickedItem;
    }

    public static HashMap<UUID, Button> getClickedButtons() {
        return clickedButtons;
    }

    public static HashMap<UUID, String> getInput() {
        return input;
    }

    public static void clear(Player player) {
        getEvent().remove(player.getUniqueId());
        getClickedItem().remove(player.getUniqueId());
        getClickedButtons().remove(player.getUniqueId());
    }

    public static void updateArgs(UUID uniqueId, String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            strings[i] = filterAsValidArgument(strings[i]);
        }
        args.put(uniqueId, strings);
    }

    private static String filterAsValidArgument(String string) {
        return string.replaceAll("bukkitServer", "").replace("\"", "").replace(")", "").replace("(", "");
    }

}
