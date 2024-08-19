package ac.compress;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModBlocks
{
	public static ModBlocks instance = new ModBlocks();

	private static final String[] BLOCKS = //可被壓縮的方塊
	{
		"dirt",
		"stone",
		"cobblestone",
		"cobbled_deepslate",
		"granite",
		"diorite",
		"andesite",
		"netherrack",
		"blackstone",
		"end_stone"
	};

	protected final Map<String, Block[]> blocksMap = new LinkedHashMap<>(); //方塊名稱與它們對應的壓縮方塊們

	public void initialize()
	{
		for (String blockName : BLOCKS) //走訪所有要被註冊的方塊們
		{
			Block[] blocks = new Block[Compress.LAYERS]; //每一個陣列元素 都是一個壓縮方塊

			for (int layer = 0; layer < Compress.LAYERS; layer++) //以層數作為名稱的流水號
			{
				blocks[layer] = new Block(Registries.BLOCK.get(Identifier.of(Identifier.DEFAULT_NAMESPACE, blockName)).getSettings()); //建立一個新方塊 使用原本的屬性

				Identifier id = Identifier.of(Compress.MOD_ID, blockName + '_' + (layer + 1)); //新方塊的ID
				Registry.register(Registries.BLOCK, id, blocks[layer]); //註冊方塊
				Registry.register(Registries.ITEM, id, new BlockItem(blocks[layer], new Item.Settings())); //註冊對應的物品
			}

			blocksMap.put(blockName, blocks); //方塊放入map裡
		}
	}
}