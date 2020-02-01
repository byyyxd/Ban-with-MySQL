package me.repize2.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

public class BanAPI extends MySQLMain {

	public static boolean IsBanned(Player p) {
		PreparedStatement stm = null;

		try {
			stm = con.prepareStatement("select * from banimentos where UUID = '" + p.getUniqueId().toString() + "'");
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			return false;
		}
	}

	public static void setMutado(Player p, int number) {
		PreparedStatement stm = null;

		try {
			stm = con.prepareStatement("update banimentos set Mutado = '" + number + "'");
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void addPlayer(Player p) {
		PreparedStatement stm = null;

		try {
			stm = con.prepareStatement("insert into banimentos(UUID, Nick, Mutado, Banido) values ('"
					+ p.getUniqueId().toString() + "', '" + p.getName() + "', '0', '0')");
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int getMutedNumber(Player p) {
		PreparedStatement stm = null;

		try {
			stm = con.prepareStatement("select * from banimentos where UUID = '" + p.getUniqueId().toString() + "'");
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				return rs.getInt("Mutado");
			}
			return 0;
		} catch (SQLException e) {
			return 0;
		}
	}

	public static int getBannedNumber(Player p) {
		PreparedStatement stm = null;

		try {
			stm = con.prepareStatement("select * from banimentos where UUID = '" + p.getUniqueId().toString() + "'");
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				return rs.getInt("Banido");
			}
			return 0;
		} catch (SQLException e) {
			return 0;
		}
	}
	
	public static boolean contains(Player p) {
		PreparedStatement stm = null;

		try {
			stm = con.prepareStatement("select * from banimentos where UUID = '" + p.getUniqueId().toString() + "'");
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			return false;
		}
	}

	public static void setBanned(Player p, int number) {
		PreparedStatement stm = null;

		try {
			stm = con.prepareStatement("update banimentos set Banido = '" + number + "'");
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
