package com.smoothmc.chunk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smoothmc.SmoothMC;
import net.minecraft.client.Minecraft;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ChunkCacheManager {
    
    private final Gson gson;
    private final File cacheDirectory;
    private final Map<String, ChunkData> chunkCache;
    
    public ChunkCacheManager() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.cacheDirectory = new File(Minecraft.getMinecraft().mcDataDir, "smoothmc/chunks");
        this.chunkCache = new HashMap<>();
        
        if (!cacheDirectory.exists()) {
            cacheDirectory.mkdirs();
        }
        
        MinecraftForge.EVENT_BUS.register(this);
        SmoothMC.getLogger().info("ChunkCacheManager initialized");
    }
    
    @SubscribeEvent
    public void onChunkLoad(ChunkEvent.Load event) {
        if (event.world.isRemote) {
            Chunk chunk = event.chunk;
            String chunkKey = getChunkKey(chunk.xPosition, chunk.zPosition);
            
            ChunkData cachedData = loadChunkFromCache(chunkKey);
            if (cachedData != null) {
                SmoothMC.getLogger().debug("Loaded chunk from cache: " + chunkKey);
                
                // TODO: Apply cached block data to the chunk
                // 1. Iterate through cachedData.blockIds and cachedData.blockMetadata
                // 2. Use chunk.getBlockStorageArray() to get ExtendedBlockStorage arrays
                // 3. Populate each storage section with the cached block data
                // 4. Handle tile entities if cached
                // Example: chunk.setBlockState(new BlockPos(x, y, z), blockState);
            }
        }
    }
    
    @SubscribeEvent
    public void onChunkUnload(ChunkEvent.Unload event) {
        if (event.world.isRemote) {
            Chunk chunk = event.chunk;
            String chunkKey = getChunkKey(chunk.xPosition, chunk.zPosition);
            
            // TODO: Extract block data from the chunk before creating ChunkData
            // Currently only saves coordinates and timestamp, not actual block data
            ChunkData chunkData = new ChunkData(chunk.xPosition, chunk.zPosition);
            
            // TODO: Serialize chunk block data into ChunkData:
            // 1. Extract block IDs and metadata from chunk.getBlockStorageArray()
            // 2. Store them in ChunkData (add fields: blockIds[][][] and blockMetadata[][][])
            // 3. Optionally serialize tile entity data (chests, signs, etc.)
            // 4. Consider compression to reduce file size
            
            saveChunkToCache(chunkKey, chunkData);
            
            SmoothMC.getLogger().debug("Saved chunk to cache: " + chunkKey);
        }
    }
    
    private String getChunkKey(int x, int z) {
        return x + "_" + z;
    }
    
    private void saveChunkToCache(String key, ChunkData data) {
        chunkCache.put(key, data);
        
        File cacheFile = new File(cacheDirectory, key + ".json");
        try (FileWriter writer = new FileWriter(cacheFile)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            SmoothMC.getLogger().error("Failed to save chunk cache: " + key, e);
        }
    }
    
    private ChunkData loadChunkFromCache(String key) {
        if (chunkCache.containsKey(key)) {
            return chunkCache.get(key);
        }
        
        File cacheFile = new File(cacheDirectory, key + ".json");
        if (cacheFile.exists()) {
            try (FileReader reader = new FileReader(cacheFile)) {
                ChunkData data = gson.fromJson(reader, ChunkData.class);
                chunkCache.put(key, data);
                return data;
            } catch (IOException e) {
                SmoothMC.getLogger().error("Failed to load chunk cache: " + key, e);
            }
        }
        
        return null;
    }
    
    private static class ChunkData {
        private final int x;
        private final int z;
        private final long timestamp;
        
        // TODO: Add fields to store actual chunk block data:
        // private int[][][] blockIds;      // [x][y][z] block IDs (16x256x16)
        // private byte[][][] blockMetadata; // [x][y][z] block metadata (16x256x16)
        // private Map<String, TileEntityData> tileEntities; // Tile entity data (optional)
        
        // TODO: Add constructor parameter to accept and store chunk block data
        public ChunkData(int x, int z) {
            this.x = x;
            this.z = z;
            this.timestamp = System.currentTimeMillis();
            
            // TODO: Initialize block data arrays here
            // this.blockIds = new int[16][256][16];
            // this.blockMetadata = new byte[16][256][16];
        }
    }
}
