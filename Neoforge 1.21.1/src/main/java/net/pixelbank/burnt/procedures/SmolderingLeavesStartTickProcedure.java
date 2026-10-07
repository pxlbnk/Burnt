package net.pixelbank.burnt.procedures;

import net.pixelbank.burnt.util.BurntTags;

import net.pixelbank.burnt.network.BurntModVariables;
import net.pixelbank.burnt.init.BurntModItems;
import net.pixelbank.burnt.init.BurntModBlocks;
import net.pixelbank.burnt.configuration.BurntBasicConfigConfiguration;
import net.pixelbank.burnt.BurntMod;
import net.neoforged.fml.ModList;

import net.pixelbank.burnt.util.BurntBlockUtil;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

public class SmolderingLeavesStartTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double fireSpeed = 0;
		if (!world.isClientSide()) {
			if (world.isEmptyBlock(BlockPos.containing(x + 1, y, z)) && world.isEmptyBlock(BlockPos.containing(x - 1, y, z)) && world.isEmptyBlock(BlockPos.containing(x, y + 1, z)) && world.isEmptyBlock(BlockPos.containing(x, y - 1, z))
					&& world.isEmptyBlock(BlockPos.containing(x, y, z + 1)) && world.isEmptyBlock(BlockPos.containing(x, y, z - 1))) {
				if (Math.random() <= 0.01) {
					world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
				}
			}
			if ((IsRainingProcedure.isGloballyRaining(world) || net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z))) {
				if ((net.pixelbank.burnt.util.BurntProtection.stopBurning(world, x, y, z) || BurntBasicConfigConfiguration.RAIN_EXTINGUISH.get() == true)) {
					BurntMod.queueServerWork((int) Mth.nextDouble(world.getRandom(), 0, 40), () -> {
						{
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockState _bs = BurntModBlocks.BURNT_LEAVES.defaultBlockState();
							BlockState _bso = world.getBlockState(_bp);
							_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
							if (_bs.hasProperty(BlockStateProperties.WATERLOGGED))
								_bs = _bs.setValue(BlockStateProperties.WATERLOGGED, false);
							world.setBlock(_bp, _bs, 3);
						}
					});
				}
			}
			if (BurntBasicConfigConfiguration.BURNT_ON.get() == true) {
				if ((double) BurntBasicConfigConfiguration.BURN_SPREAD.get() <= 100) {
					fireSpeed = (double) BurntBasicConfigConfiguration.BURN_SPREAD.get() / 300;
				} else {
					fireSpeed = 0.99;
				}
				if (BurntBlockUtil.hasAdjacentWater(world, x, y, z)) {
					{
						BlockPos _bp = BlockPos.containing(x, y, z);
						BlockState _bs = BurntModBlocks.BURNT_LEAVES.defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
						if (_bs.hasProperty(BlockStateProperties.WATERLOGGED))
							_bs = _bs.setValue(BlockStateProperties.WATERLOGGED, false);
						world.setBlock(_bp, _bs, 3);
					}
				} else {
					if (Math.random() <= fireSpeed) {
						if (!world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(((double) BurntBasicConfigConfiguration.PLAYER_DISTANCE.get()) / 2d), e -> true).isEmpty()) {
							if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SMOLDERING_OAK_LEAVES
									|| (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == BurntModBlocks.SMOLDERING_DARK_OAK_LEAVES) {
								if (Math.random() <= 0.005) {
									if (world instanceof ServerLevel _level) {
										ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(BurntModItems.BAKED_APPLE));
										entityToSpawn.setPickUpDelay(10);
										_level.addFreshEntity(entityToSpawn);
									}
								}
							}
							{
								BlockPos _bp = BlockPos.containing(x, y, z);
								BlockState _bs = (blockStateWithInt((BuiltInRegistries.BLOCK.getOrCreateTag(BurntTags.BLOCK_SMOLDERING_LEAVES).getRandomElement(world.getRandom())
										.orElseGet(() -> BuiltInRegistries.BLOCK.wrapAsHolder(Blocks.AIR)).value()).defaultBlockState(), "stage", 1));
								BlockState _bso = world.getBlockState(_bp);
								_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
								world.setBlock(_bp, _bs, 3);
							}
						}
					}
					if ((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.SNOW) {
						world.destroyBlock(BlockPos.containing(x, y + 1, z), false);
					}
				}
			} else {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = BurntModBlocks.BURNT_LEAVES.defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					_bs = net.pixelbank.burnt.util.BurntBlockUtil.copyProperties(_bso, _bs);
					if (_bs.hasProperty(BlockStateProperties.WATERLOGGED))
						_bs = _bs.setValue(BlockStateProperties.WATERLOGGED, false);
					world.setBlock(_bp, _bs, 3);
				}
			}
			if (ModList.get().isLoaded("cold_sweat")) {
				if (Math.random() <= 0.5) {
					if (!world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(4 / 2d), e -> true).isEmpty()) {
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"/temp modify @p addition world 0.1 false");
						BurntMod.queueServerWork((int) Mth.nextDouble(world.getRandom(), 200, 500), () -> {
							if (world instanceof ServerLevel _level)
								_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
										"/temp modify @p addition world -0.1 false");
						});
					}
				}
			}
		}
	}

	private static BlockState blockStateWithInt(BlockState blockState, String property, int newValue) {
		Property<?> prop = blockState.getBlock().getStateDefinition().getProperty(property);
		return prop instanceof IntegerProperty ip && prop.getPossibleValues().contains(newValue) ? blockState.setValue(ip, newValue) : blockState;
	}
}