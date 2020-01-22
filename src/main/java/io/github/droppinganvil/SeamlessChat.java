package io.github.droppinganvil;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class SeamlessChat extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        //Block bypass
        if (e.getMessage().contains("§")) e.setMessage(e.getMessage().replace("§", "&"));
        if (e.getMessage().contains("&")) {
            String temp = e.getMessage();
            for (Colors color : Colors.values()) {
                if (e.getMessage().contains(color.toString()) && e.getPlayer().hasPermission("seamlesschat." + color.name())) {
                    temp = temp.replace(color.toString(), ChatColor.translateAlternateColorCodes('&', color.toString()));
                }
            }
            e.setMessage(temp);
        }
    }
}
