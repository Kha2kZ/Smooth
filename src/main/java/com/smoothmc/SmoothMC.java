package com.smoothmc;

import com.smoothmc.chunk.ChunkCacheManager;
import com.smoothmc.hit.NoHitLagHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = SmoothMC.MODID, version = SmoothMC.VERSION, clientSideOnly = true)
public class SmoothMC {
    public static final String MODID = "smoothmc";
    public static final String VERSION = "1.0.0";
    
    private static Logger logger;
    
    private ChunkCacheManager chunkCacheManager;
    private NoHitLagHandler noHitLagHandler;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        logger.info("SmoothMC is initializing...");
        
        chunkCacheManager = new ChunkCacheManager();
        noHitLagHandler = new NoHitLagHandler();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
        logger.info("SmoothMC initialization complete!");
        logger.info("ChunkCache system: ENABLED");
        logger.info("NoHitLag system: ENABLED");
    }
    
    public static Logger getLogger() {
        return logger;
    }
}
