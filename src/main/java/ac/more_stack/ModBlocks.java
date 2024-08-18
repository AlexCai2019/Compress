package ac.more_stack;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class ModBlocks
{
	static final Map<String, Block[]> blocksMap = new HashMap<>(); //方塊名稱與它們對應的壓縮方塊們

	static void initialize()
	{
		for (String blockName : MoreStack.BLOCKS) //走訪所有要被註冊的方塊們
		{
			Block[] blocks = new Block[MoreStack.LAYERS]; //每一個元素 都是一個壓縮方塊

			for (int layer = 0; layer < MoreStack.LAYERS; layer++) //以層數作為名稱的流水號
			{
				Identifier id = Identifier.of(MoreStack.MOD_ID, blockName + '_' + (layer + 1)); //方塊ID
				blocks[layer] = new Block(Registries.BLOCK.get(Identifier.of(Identifier.DEFAULT_NAMESPACE, blockName)).getSettings()); //方塊
				Registry.register(Registries.BLOCK, id, blocks[layer]); //註冊方塊
				Registry.register(Registries.ITEM, id, new BlockItem(blocks[layer], new Item.Settings())); //註冊對應的物品
			}

			blocksMap.put(blockName, blocks); //放入方塊
		}
	}
}