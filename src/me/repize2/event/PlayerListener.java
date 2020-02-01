package me.repize2.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import me.repize2.cmd.MuteCMD;
import me.repize2.mysql.BanAPI;

public class PlayerListener implements Listener {

	
	@EventHandler
	private void chatEvent(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		
		if(BanAPI.getMutedNumber(p) == 1 || MuteCMD.mutado.contains(p.getName())) {
			p.sendMessage("§a§lVoke§f§lPvP §cVoce esta mutado.");
			e.setCancelled(true);
		}
	}
	
	//Checka se foi banido, se sim kicka dnv, se n deixa entrar
	@EventHandler
	private void loginEvent(PlayerLoginEvent e) {
		Player p = e.getPlayer();

		if (BanAPI.getBannedNumber(p) == 0) {
			e.allow();
		} else {
			e.setKickMessage("§a§lVoke§f§lPvP\n\n§cVoce foi banido permanentemente.");
			e.disallow(Result.KICK_BANNED, e.getKickMessage());
			for (Player staffs : Bukkit.getOnlinePlayers()) {
				if (staffs.hasPermission("voke.ban")) {
					staffs.sendMessage("§a§lVoke§f§lPvP §fO Jogador §c" + p.getName()
							+ " §ftentou entrar no servidor. (§cBANIDO§f)");
				} else {
					return;
				}
			}
		}
	}
	
	
	
}
