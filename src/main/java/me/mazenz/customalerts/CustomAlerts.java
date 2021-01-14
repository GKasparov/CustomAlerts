package me.mazenz.customalerts;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class CustomAlerts extends JavaPlugin implements Listener {

    public void onEnable() {
        getConfig();
        saveDefaultConfig();
        Bukkit.getServer().getPluginManager().registerEvents(this, this);

    }


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {

            event.setJoinMessage(Objects.requireNonNull(getConfig()
                    .getString("PublicJoinMessage"))
                    .replace("&", "§")
                    .replace("%player%", player.getDisplayName()));
            player.sendMessage(Objects.requireNonNull(getConfig()
                    .getString("PlayerJoinMessage")
            .replace("&", "§")
            .replace("%player%", player.getDisplayName())));


        }
    }


    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(Objects.requireNonNull(getConfig()
                .getString("PublicLeaveMessage"))
                .replace("&", "§")
                .replace("%player%", player.getDisplayName()));
    }

}