package com.smoothmc.hit;

import com.smoothmc.SmoothMC;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class NoHitLagHandler {
    
    private final Minecraft mc;
    private boolean wasHurt = false;
    
    public NoHitLagHandler() {
        this.mc = Minecraft.getMinecraft();
        MinecraftForge.EVENT_BUS.register(this);
        SmoothMC.getLogger().info("NoHitLagHandler initialized");
    }
    
    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {
        if (event.entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entity;
            
            if (player == mc.thePlayer) {
                wasHurt = true;
                
                SmoothMC.getLogger().debug("Player hurt detected, applying lag reduction");
            }
        }
    }
    
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START && wasHurt) {
            if (mc.thePlayer != null) {
                mc.thePlayer.hurtTime = 0;
                mc.thePlayer.maxHurtTime = 0;
                
                // TODO: Additional hurt effects to cancel for complete lag removal:
                // 1. Cancel damage particles (ParticleManager)
                // 2. Cancel hurt sound (SoundHandler)
                // 3. Consider canceling screen shake (EntityRenderer)
                // Example: mc.effectRenderer.removeAllParticles();
            }
            
            wasHurt = false;
        }
    }
}
