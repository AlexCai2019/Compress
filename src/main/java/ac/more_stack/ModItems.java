package ac.more_stack;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItems
{
	public static void initialize()
	{
		ItemGroup moreStackItemGroup = FabricItemGroup.builder()
				.icon(() -> new ItemStack(ModBlocks.blocksMap.get("netherrack")[0]))
				.displayName(Text.translatable("itemGroup.moreStack"))
				.entries((context, entries) ->
				{
					for (Block[] blocks : ModBlocks.blocksMap.values())
						for (Block block : blocks)
							entries.add(block);
				})
				.build(); //成立item group, 以一層地獄石為icon

		Registry.register(Registries.ITEM_GROUP, Identifier.of(MoreStack.MOD_ID, "item_group"), moreStackItemGroup);
	}
}