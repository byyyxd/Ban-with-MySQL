package me.repize2.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Bukkit;

public class MySQLMain {
	
	public static Connection con = null;
	
	public static void open() {
		
		String url = "jdbc:mysql://144.217.170.82:3306/s391_VOKEPVP";
		String user = "u391_rpz9PWe24P";
		String pass = "7r5gWD183PZJQj9uDpF1UTs4";
		
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
