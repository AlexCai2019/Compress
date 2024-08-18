import java.io.FileWriter;
import java.io.IOException;

public class Generate
{
	private static final String[] blocks =
	{
		"netherrack",
		"cobblestone",
		"cobbled_deepslate"
	};
	private static final int LAYERS = 9;

	public static void main(String[] args) throws IOException
	{
		for (String block : blocks)
		{
			for (int layer = 1; layer <= LAYERS; layer++)
			{
				blockState(block, layer);
				lang(block, layer);
				blockModel(block, layer);
				itemModel(block, layer);
				lootTable(block, layer);
			}

			decompress(block, 0);
			for (int layer = 1; layer < LAYERS; layer++)
			{
				decompress(block, layer);
				compress(block, layer);
			}
			compress(block, LAYERS);
		}
	}

	private static void blockState(String block, int layer) throws IOException
	{
		FileWriter writer = new FileWriter(STR."assets/more_stack/blockstates/\{block}_\{layer}.json");
		writer.write(STR."""
		{
			"variants":
			{
				"": { "model": "more_stack:block/\{block}_\{layer}" }
			}
		}""");
		writer.close();
	}

	private static void lang(String block, int layer) throws IOException
	{
		FileWriter en = new FileWriter("assets/more_stack/lang/en_us.json", true);
		FileWriter zh = new FileWriter("assets/more_stack/lang/zh_tw.json", true);

		en.write(STR."\"block.more_stack.\{block}_\{layer}\": \"Compressed \{block.substring(0, 1).toUpperCase()}\{block.substring(1)} \{layer}\",\n");
		zh.write(STR."\"block.more_stack.\{block}_\{layer}\": \"\\u58d3\\u7e2e\\u7684 \{layer}\",\n");

		en.close();
		zh.close();
	}

	private static void blockModel(String block, int layer) throws IOException
	{
		FileWriter writer = new FileWriter(STR."assets/more_stack/models/block/\{block}_\{layer}.json");
		writer.write(STR."""
		{
			"parent": "block/cube_all",
			"textures":
			{
				"all": "more_stack:block/\{block}_\{layer}"
			}
		}""");
		writer.close();
	}

	private static void itemModel(String block, int layer) throws IOException
	{
		FileWriter writer = new FileWriter(STR."assets/more_stack/models/item/\{block}_\{layer}.json");
		writer.write(STR."""
		{
			"parent": "more_stack:block/\{block}_\{layer}"
		}""");
		writer.close();
	}

	private static void lootTable(String block, int layer) throws IOException
	{
		FileWriter writer = new FileWriter(STR."data/more_stack/loot_table/blocks/\{block}_\{layer}.json");
		writer.write(STR."""
		{
			"type": "minecraft:block",
			"pools":
			[
				{
					"rolls": 1,
					"entries":
					[
						{
							"type": "minecraft:item",
							"name": "more_stack:\{block}_\{layer}"
						}
					],
					"conditions":
					[
						{
							"condition": "minecraft:survives_explosion"
						}
					]
				}
			]
		}""");
		writer.close();
	}

	private static void decompress(String block, int layer) throws IOException
	{
		FileWriter writer = new FileWriter(STR."data/more_stack/recipe/\{block}_\{layer}_from_decompress.json");
		writer.write(STR."""
		{
			"type": "minecraft:crafting_shapeless",
			"category": "building",
			"group": "\{block}_\{layer}",
			"ingredients":
			[
				{
					"item": "more_stack:\{block}_\{layer + 1}"
				}
			],
			"result":
			{
				"id": "more_stack:\{block}_\{layer}",
				"count": 9
			}
		}""");
		writer.close();
	}

	private static void compress(String block, int layer) throws IOException
	{
		char key = (char)(block.charAt(0) - ' ');
		FileWriter writer = new FileWriter(STR."data/more_stack/recipe/\{block}_\{layer}_from_compress.json");
		writer.write(STR."""
		{
			"type": "minecraft:crafting_shaped",
			"category": "building",
			"group": "\{block}_\{layer}",
			"pattern":
			[
				"\{key}\{key}\{key}",
				"\{key}\{key}\{key}",
				"\{key}\{key}\{key}"
			],
			"key":
			{
				"\{key}":
				{
					"item": "more_stack:\{block}_\{layer - 1}"
				}
			},
			"result":
			{
				"id": "more_stack:\{block}_\{layer}"
			}
		}""");
		writer.close();
	}
}