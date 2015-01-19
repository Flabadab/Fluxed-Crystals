package fluxedCrystals.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import fluxedCrystals.client.gui.GUIHandler;

public class CommonProxy {

	public void initGuis() {
		new GUIHandler();
	}

	public void initRenderers() {

	}

	public World getClientWorld() {
		return null;
	}

	public void renderTrans() {

	}
	
	  public EntityPlayer getClientPlayer() {
	    return null;
	  }
}
