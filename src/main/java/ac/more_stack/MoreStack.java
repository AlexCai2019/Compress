package ac.more_stack;

import net.fabricmc.api.ModInitializer;

public class MoreStack implements ModInitializer
{
	public static final String MOD_ID = "more_stack";

	static final int LAYERS = 9; //壓縮的最高層數

	static final String[] BLOCKS = //可被壓縮的方塊
	{
		"netherrack",
		"cobblestone"
	};

	@Override
	public void onInitialize()
	{
		ModBlocks.initialize();
		ModItems.initialize();
	}
}