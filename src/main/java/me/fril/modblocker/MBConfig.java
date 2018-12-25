package me.fril.modblocker;

import net.minecraftforge.common.config.Config;

@Config(modid = ModBlocker.MODID)
public class MBConfig {
	
	public static MBConfig config = new MBConfig();
	
	@Config.Name("Blocked mods")
	@Config.Comment("Here, put a list of mods you do not want the player to have installed when connecting to the server")
	public String[] blockedMods = {"scenter"};
	
	@Config.Name("Disconnection message")
	@Config.Comment("Here you put what message the client recieves when kicked from the server, use '@MOD' to represent the offending modid")
	public String disconnectMessage = "Mod: &s is not allowed on this server.";

	@Config.Name("Ban message")
	@Config.Comment("Here you put what message the client recieves when banned from the server")
	public String banMessage = ""; //Todo : Add a message

	@Config.Name("Action")
	@Config.Comment("What to do if one blacklisted mod is detected ? [1 : disconnect, 2: BAN HAMMER]")
	public int actionID = 1;
}
