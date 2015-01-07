package fluxedCrystals.proxy;

import fluxedCrystals.client.gui.GUIHandler;
import net.minecraft.world.World;

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
}
