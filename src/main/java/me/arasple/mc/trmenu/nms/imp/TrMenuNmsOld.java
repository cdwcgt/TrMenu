package me.arasple.mc.trmenu.nms.imp;

import io.izzel.taboolib.module.lite.SimpleReflection;
import me.arasple.mc.trmenu.nms.TrMenuNms;
import net.minecraft.server.v1_13_R2.ChatComponentText;
import net.minecraft.server.v1_13_R2.EntityPlayer;
import net.minecraft.server.v1_13_R2.PacketPlayOutCloseWindow;
import net.minecraft.server.v1_13_R2.PacketPlayOutOpenWindow;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * @author Arasple
 * @date 2020/1/20 14:24
 * for versions below Minecraft 1.14
 */
public class TrMenuNmsOld extends TrMenuNms {


    static {
        SimpleReflection.checkAndSave(PacketPlayOutOpenWindow.class);
    }

    @Override
    public void setInventoryTitle(Player player, Inventory inventory, String title) {
        EntityPlayer handle = ((CraftPlayer) player).getHandle();
        PacketPlayOutOpenWindow packet = new PacketPlayOutOpenWindow(
                handle.activeContainer.windowId,
                getByInventory(inventory),
                new ChatComponentText(title),
                inventory.getSize()
        );
        handle.playerConnection.sendPacket(packet);
        handle.updateInventory(handle.activeContainer);
    }

    @Override
    public void closeInventory(Player player) {
        PacketPlayOutCloseWindow packet = new PacketPlayOutCloseWindow();
        EntityPlayer handle = ((CraftPlayer) player).getHandle();
        handle.playerConnection.sendPacket(packet);
    }

    private String getByInventory(Inventory inventory) {
        return "minecraft:" + inventory.getType().name().toLowerCase();
    }

}
