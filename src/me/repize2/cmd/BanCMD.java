package me.repize2.cmd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.repize2.mysql.BanAPI;

public class BanCMD implements CommandExecutor {

	@SuppressWarnings("unused")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if (sender instanceof Player) {

			if (cmd.getName().equalsIgnoreCase("ban")) {
				Player p = (Player) sender;
				if (p.hasPermission("voke.ban")) {
					if (args.length < 2) {
						p.sendMessage(ChatColor.RED + "Utilize /ban <nick> <motivo>");
						return true;
					} else {
						Player target = Bukkit.getPlayer(args[0]);
						if (target == null) {
							p.sendMessage(ChatColor.RED + "Este jogador nao existe.");
							return true;
						} else {
							String motivo = args[1];
							// Bane
							if(!BanAPI.contains(target)) {
								p.sendMessage("Este jogador nao foi registrado, estamos registrando ele..");
								BanAPI.addPlayer(target);
								p.sendMessage("Jogador registrado, tente novamente.");
								return true;
							} else if(BanAPI.getBannedNumber(target) == 0) {
								p.sendMessage("§aVoce baniu o jogador §c" + target.getName() + " §acom sucesso.");
								Bukkit.broadcastMessage("");
								Bukkit.broadcastMessage("§a§lVoke§f§lPvP\n\n§aO jogador §c" + target.getName()
										+ " §f(§c" + target.getUniqueId().toString()
										+ "§f) §afoi §c§lBANIDO.\n§aBanido por: §c" + p.getName());
								Bukkit.broadcastMessage("");
								BanAPI.setBanned(target, 1);
								target.kickPlayer("§a§lVoke§f§lPvP\n\n§cVoce foi banido permanentemente.\n§aStaffer: §c" + p.getName() + "\n§aMotivo: §c" + motivo);
								return true;
							} else {
								p.sendMessage("§cEste jogador ja esta banido.");
								return true;
							}
						}
					}
				} else {
					p.sendMessage("§cSem permissao amigo.");
					return true;
				}
			}
		}
		return false;
	}

}
