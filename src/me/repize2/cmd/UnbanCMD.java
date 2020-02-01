package me.repize2.cmd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.repize2.mysql.BanAPI;

public class UnbanCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if (sender instanceof Player) {
			if (cmd.getName().equalsIgnoreCase("unban")) {
				Player p = (Player) sender;
				if (p.hasPermission("voke.ban")) {
					if (args.length < 0) {
						p.sendMessage(ChatColor.RED + "Utilize /unban <Nick>");
						return true;
					} else {
						Player target = Bukkit.getPlayer(args[0]);
						if (target == null) {
							p.sendMessage(ChatColor.RED + "Jogador invalido.");
							return true;
						} else {
							if (BanAPI.getBannedNumber(target) == 1) {
								p.sendMessage("§aVoce desbaniu o jogador §c" + target.getName() + " §acom sucesso.");
								Bukkit.broadcastMessage("");
								Bukkit.broadcastMessage("§a§lVoke§f§lPvP\n\n§aO jogador §c" + target.getName()
										+ " §f(§c" + target.getUniqueId().toString()
										+ "§f) §afoi §e§lDESBANIDO§a.\n§aDesbanido por: §c" + p.getName());
								Bukkit.broadcastMessage("");
								return true;
							} else {
								p.sendMessage("§cEste jogador nao esta banido.");
								return true;
							}
						}
					}

				} else {
					p.sendMessage(ChatColor.RED + "Voce nao tem permissao.");
					return true;
				}
			}
		} else {
			return true;
		}
		return false;
	}

}
