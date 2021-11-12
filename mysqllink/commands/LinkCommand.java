package com.minesentinel.mysqllink.commands;

import com.minesentinel.mysqllink.SQLLink;
import java.util.UUID;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LinkCommand implements CommandExecutor {
    private SQLLink plugin;

    public LinkCommand(SQLLink plugin) {
        this.plugin = plugin;
        plugin.getCommand("link").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players may execute this command!");
            return true;
        }
        Player user = (Player)sender;
        if (user.hasPermission("Link.use")) {
            UUID uuid = user.getUniqueId();
            new LoadDriver("UUID", String.format("'%s'", new Object[] { uuid.toString() }), "register_date", args[0]);
            user.sendMessage(uuid.toString());
            return true;
        }
        user.sendMessage("You do not have permission to execute this command!");
        return false;
    }
}
