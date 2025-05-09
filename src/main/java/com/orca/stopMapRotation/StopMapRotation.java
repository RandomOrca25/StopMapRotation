package com.orca.stopMapRotation;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class StopMapRotation extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("MapArtRotationBlocker has been enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("MapArtRotationBlocker has been disabled.");
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Entity clicked = event.getRightClicked();

        if (clicked instanceof ItemFrame itemFrame) {
            ItemStack item = itemFrame.getItem();

            if (item != null && item.getType() == Material.FILLED_MAP) {
                Player player = event.getPlayer();
                if (!player.getScoreboardTags().contains("dev")) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
