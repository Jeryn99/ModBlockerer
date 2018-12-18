package me.fril.modblocker;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.handshake.NetworkDispatcher;

import java.util.Map;

@Mod(modid = ModBlocker.MODID, name = ModBlocker.NAME, version = ModBlocker.VERSION, acceptableRemoteVersions = "*", serverSideOnly = true)
public class ModBlocker {
   
    public static final String MODID = "modblock";
    public static final String NAME = "Mod Blocker";
    public static final String VERSION = "1.0";
    
    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent e){
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    @SubscribeEvent
    public void onPlayerJoinedWorld(EntityJoinWorldEvent e){
        if(e.getEntity() instanceof EntityPlayerMP) {
            EntityPlayerMP playerMP = (EntityPlayerMP) e.getEntity();
            Map<String, String> modList = getPlayerModList(playerMP);
            
            for(String modid : MBConfig.config.blockedMods){
                if(modList.containsKey(modid)){
                    handleDisconnect(playerMP, modid);
                    return;
                }
            }
           
        }
    }
    
    public static Map<String, String> getPlayerModList(EntityPlayerMP playerMP){
        NetworkDispatcher playerConnection = playerMP.connection.getNetworkManager().channel().attr(NetworkDispatcher.FML_DISPATCHER).get();
        return playerConnection.getModList();
    }
    
    public static void handleDisconnect(EntityPlayerMP entityPlayerMP, String modid){
        String disconnectMessage = MBConfig.config.disconnectMessage.replaceAll("@MOD", modid);
        entityPlayerMP.connection.disconnect(new TextComponentString(disconnectMessage));
    }
    
}
