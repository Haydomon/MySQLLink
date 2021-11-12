package com.minesentinel.mysqllink;

import com.minesentinel.mysqllink.commands.LinkCommand;
import com.minesentinel.mysqllink.commands.UpdateCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class SQLLink extends JavaPlugin {
    public void onEnable() {
        new LinkCommand(this);
        getServer().getPluginManager().registerEvents((Listener) new UpdateCommand(), (Plugin) this);
    }
}