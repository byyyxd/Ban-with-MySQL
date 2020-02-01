package me.repize2.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Bukkit;

public class MySQLMain {
	
	public static Connection con = null;
	
	public static void open() {
		
		String url = "jdbc:mysql://mysql_private_user/mysql_private_database";
		String user = "mysql_private_user";
		String pass = "mysql_private_password";
		
		try {
			con = DriverManager.getConnection(url, user, pass);
			Bukkit.getConsoleSender().sendMessage("§a[REPIZE] Conexao com MySQL (Banimentos) sucessida.");
			tabela();
		} catch (SQLException e) {
			Bukkit.getConsoleSender().sendMessage("§a[REPIZE] §cConexao com MySQL (Banimentos) nao sucessida.");
		}
	}
	
	public static void tabela() {
		PreparedStatement stm = null;
		
		try {
			stm = con.prepareStatement("create table if not exists `banimentos`(UUID varchar(64), Nick text, Mutado INT, Banido INT)");
			stm.executeUpdate();
			Bukkit.getConsoleSender().sendMessage("§a[REPIZE] §aTebela de banimentos concluida.");
		} catch (SQLException e) {
			Bukkit.getConsoleSender().sendMessage("§a[REPIZE] §cTebela de banimentos nao foi concluida.");
		}
	}
}
