package ac.compress;

import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.ColorCode;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModBlocks
{
	public static ModBlocks instance = new ModBlocks();

	private static final BlockData[] BLOCKS = //可被壓縮的方塊
	{
		new BlockData("dirt", Block.class),
		new BlockData("stone", Block.class),
		new BlockData("cobblestone", Block.class),
		new BlockData("cobbled_deepslate", Block.class),
		new BlockData("granite", Block.class),
		new BlockData("diorite", Block.class),
		new BlockData("andesite", Block.class),
		new BlockData("sand", ColoredFallingBlock.class),
		new BlockData("gravel", ColoredFallingBlock.class),
		new BlockData("netherrack", Block.class),
		new BlockData("blackstone", Block.class),
		new BlockData("soul_sand", SoulSandBlock.class),
		new BlockData("end_stone", Block.class)
	};

	protected final Map<String, Block[]> blocksMap = new LinkedHashMap<>(); //方塊名稱與它們對應的壓縮方塊們

	public void initialize()
	{
		for (BlockData blockData : BLOCKS) //走訪所有要被註冊的方塊們
		{
			Block[] blocks = new Block[Compress.LAYERS]; //每一個陣列元素 都是一個壓縮方塊

			for (int layer = 0; layer < Compress.LAYERS; layer++) //以層數作為名稱的流水號
			{
				blocks[layer] = createBlock(blockData); //註冊方塊

				Identifier id = Identifier.of(Compress.MOD_ID, blockData.name + '_' + (layer + 1)); //新方塊的ID
				Registry.register(Registries.BLOCK, id, blocks[layer]); //註冊方塊
				Registry.register(Registries.ITEM, id, new BlockItem(blocks[layer], new Item.Settings())); //註冊對應的物品
			}

			blocksMap.put(blockData.name, blocks); //方塊放入map裡
		}
	}

	private Block createBlock(BlockData blockData)
	{
		//建立一個新方塊 使用原本的屬性
		AbstractBlock.Settings settings = Registries.BLOCK.get(Identifier.of(Identifier.DEFAULT_NAMESPACE, blockData.name)).getSettings();

		if (blockData.type == Block.class) //普通的方塊
			return new Block(settings);

		if (blockData.type == SoulSandBlock.class) //普通的方塊
			return new SoulSandBlock(settings);

		if (blockData.type == ColoredFallingBlock.class) //重力方塊
			return new ColoredFallingBlock(new ColorCode(switch (blockData.name)
			{
				case "sand" -> 0xDBD3A0;
				case "gravel" -> 0xFF807C7B;
				default -> 0;
			}), settings);

		return Blocks.AIR; //都不是就回傳空氣
	}

	private record BlockData(String name, Class<? extends Block> type) {}
}