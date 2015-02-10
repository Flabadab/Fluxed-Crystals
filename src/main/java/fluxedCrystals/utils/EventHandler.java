package fluxedCrystals.utils;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.world.BlockEvent;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;

public class EventHandler {
	public boolean resetRender;
	public float trans = 0;
	public boolean descending;

	public EventHandler() {
	}
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void render(RenderLivingEvent.Pre event) {
		String s = EnumChatFormatting.getTextWithoutFormattingCodes(event.entity.getCommandSenderName());
		if (s.equals("Jaredlll08") || s.equals("esriel123")) {
			if (new Random().nextInt(2) == 0) {
				if (!descending) {
					trans++;
				} else {
					trans--;
				}
				if (trans > 100) {
					descending = true;
					trans = 100f;
				}
				if (trans < 0) {
					descending = false;
					trans = 0;
				}
			}
			GL11.glColor4f(1.0F, 1.0F, 1.0F, trans/100);
			GL11.glEnable(3042);
			GL11.glBlendFunc(770, 771);
			resetRender = true;
		}

	}

	@SubscribeEvent
	public void entityColorRender(RenderLivingEvent.Post event) {
		if (this.resetRender) {
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(3042);
			resetRender = false;
		}
	}

}
