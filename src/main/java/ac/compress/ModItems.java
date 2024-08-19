package ac.compress;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItems
{
	static void initialize()
	{
		//壓縮方塊 item group
		Registry.register(Registries.ITEM_GROUP, Identifier.of(Compress.MOD_ID, "item_group"), FabricItemGroup.builder()
				.icon(() -> new ItemStack(ModBlocks.blocksMap.get("cobblestone")[0])) //以一層鵝卵石為icon
				.displayName(Text.translatable("itemGroup.compressedBlocks")) //名稱
				.entries((context, entries) ->
				{
					for (Block[] blocks : ModBlocks.blocksMap.values()) //所有的方塊陣列
						for (Block block : blocks) //陣列內的所有方塊
							entries.add(block); //加入
				}).build()); //註冊item group
	}
}