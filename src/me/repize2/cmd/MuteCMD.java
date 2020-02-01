package me.repize2.cmd;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.repize2.mysql.BanAPI;

public class MuteCMD implements CommandExecutor {

	public static ArrayList<String> mutado = new ArrayList<>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if(sender instanceof Player) {
			if(cmd.getName().equalsIgnoreCase("mute")) {
				Player p = (Player)sender;
				if(args.length < 1) {
					p.sendMessage("§cUtilize /mute <nick> <motivo>");
					return true;
				} else {
					Player target = Bukkit.getPlayer(args[0]);
					if(target == null) {
						p.sendMessage("§cJogador invalido");
						return true;
					} else {
						String motivo = args[1];
						if(!BanAPI.contains(target)) {
							p.sendMessage("Este jogador nao foi registrado, estamos registrando ele..");
							BanAPI.addPlayer(target);
							return true;
						} else if(BanAPI.getMutedNumber(target) == 0 && BanAPI.contains(target)) {
							Bukkit.broadcastMessage("");
							Bukkit.broadcastMessage("§a§lVoke§f§lPvP\n\n§aO jogador §c" + target.getName()
									+ " §f(§c" + target.getUniqueId().toString()
									+ "§f) §afoi §c§lMUTADO§a.\n§aMutado por: §c" + p.getName() + "\n§aMotivo: §c" + motivo);
							Bukkit.broadcastMessage("");
							mutado.add(target.getName());
							BanAPI.setMutado(target, 1);
						}
					}
				}
			}
		} else {
			return true;
		}
		return false;
	}

}
