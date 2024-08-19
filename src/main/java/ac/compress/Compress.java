package ac.compress;

import net.fabricmc.api.ModInitializer;

public class Compress implements ModInitializer
{
	public static final String MOD_ID = "compress";

	static final int LAYERS = 9; //壓縮的最高層數

	@Override
	public void onInitialize()
	{
		ModBlocks.instance.initialize();
		ModItems.instance.initialize();
	}
}