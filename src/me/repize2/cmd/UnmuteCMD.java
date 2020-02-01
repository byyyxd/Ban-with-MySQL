package me.repize2.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.repize2.mysql.BanAPI;

public class UnmuteCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if (sender instanceof Player) {

			if (cmd.getName().equalsIgnoreCase("unmute")) {
				Player p = (Player) sender;
				if (args.length < 0) {
					p.sendMessage("§cUtilize /unmute <Nick>");
					return true;
				}
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					p.sendMessage("§cJogador invalido.");
					return true;
				} else {
					if (BanAPI.getMutedNumber(target) == 1 && BanAPI.contains(target)) {
						BanAPI.setMutado(target, 0);
						Bukkit.broadcastMessage("");
						Bukkit.broadcastMessage("§a§lVoke§f§lPvP\n\n§aO jogador §c" + target.getName() + " §f(§c"
								+ target.getUniqueId().toString() + "§f) §afoi §e§lDESMUTADO§a.\n§aDesmutado por: §c"
								+ p.getName());
						Bukkit.broadcastMessage("");
						MuteCMD.mutado.remove(p.getName());
						return true;
					} else {
						p.sendMessage("§cEste jogador nao esta mutado.");
						return true;
					}
				}
			}

		} else {
			return true;
		}
		return false;
	}

}
