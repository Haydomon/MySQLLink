package com.minesentinel.mysqllink.commands;

import com.minesentinel.mysqllink.SQLLink;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.platform.PlayerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.RegisteredServiceProvider;

public class UpdateCommand implements Listener {
    private SQLLink plugin;

    @EventHandler
    public void onLogout(PlayerQuitEvent event) {
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        LuckPerms luckPerms = (LuckPerms)provider.getProvider();
        Player player = event.getPlayer();
        PlayerAdapter<Player> playerAdapter = luckPerms.getPlayerAdapter(Player.class);
        User user = playerAdapter.getUser(player);
        Collection<Group> groups = user.getInheritedGroups(playerAdapter.getQueryOptions(player));
        String groupsString = groups.stream().map(Group::getName).collect(Collectors.joining(", "));
        String groupString = groupsString.substring(0, groupsString.indexOf(","));
        UUID uuid = user.getUniqueId();
        new LoadDriver("Rank", String.format("'%s'", new Object[] { groupString.toString() }), "UUID", String.format("'%s'", new Object[] { uuid.toString() }));
    }
}
