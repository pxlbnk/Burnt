package net.pixelbank.burnt.procedures;

import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class DoorTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		Direction dir = Direction.NORTH;
		if (!world.isClientSide()) {
			if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("half") instanceof EnumProperty _getep2 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep2).toString() : "")
					.equals("lower")) {
				if ((getBlockDirection(world, BlockPos.containing(x, y, z))) == Direction.NORTH) {
					if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hinge") instanceof EnumProperty _getep6 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep6).toString() : "")
							.equals("left")
							&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp8
									&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp8)) == false) {
						world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
						if (world instanceof ServerLevel _serverworld) {
							StructureTemplate template = _serverworld.getStructureManager().getOrCreate(ResourceLocation.fromNamespaceAndPath("burnt", "burnt_door_closed_left"));
							if (template != null) {
								template.placeInWorld(_serverworld, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z), new StructurePlaceSettings().setRotation(Rotation.CLOCKWISE_180).setMirror(Mirror.NONE).setIgnoreEntities(false),
										_serverworld.random, 3);
							}
						}
					} else if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hinge") instanceof EnumProperty _getep12
							? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep12).toString()
							: "").equals("right")
							&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp14
									&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp14)) == false) {
						world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
						if (world instanceof ServerLevel _serverworld) {
							StructureTemplate template = _serverworld.getStructureManager().getOrCreate(ResourceLocation.fromNamespaceAndPath("burnt", "burnt_door_closed_right"));
							if (template != null) {
								template.placeInWorld(_serverworld, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z), new StructurePlaceSettings().setRotation(Rotation.CLOCKWISE_180).setMirror(Mirror.NONE).setIgnoreEntities(false),
										_serverworld.random, 3);
							}
						}
					} else if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hinge") instanceof EnumProperty _getep18
							? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep18).toString()
							: "").equals("left")
							&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp20
									&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp20)) == true) {
						world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
						if (world instanceof ServerLevel _serverworld) {
							StructureTemplate template = _serverworld.getStructureManager().getOrCreate(ResourceLocation.fromNamespaceAndPath("burnt", "burnt_door_open_left"));
							if (template != null) {
								template.placeInWorld(_serverworld, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z), new StructurePlaceSettings().setRotation(Rotation.CLOCKWISE_180).setMirror(Mirror.NONE).setIgnoreEntities(false),
										_serverworld.random, 3);
							}
						}
					} else if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hinge") instanceof EnumProperty _getep24
							? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep24).toString()
							: "").equals("right")
							&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp26
									&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp26)) == true) {
						world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
						if (world instanceof ServerLevel _serverworld) {
							StructureTemplate template = _serverworld.getStructureManager().getOrCreate(ResourceLocation.fromNamespaceAndPath("burnt", "burnt_door_open_right"));
							if (template != null) {
								template.placeInWorld(_serverworld, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z), new StructurePlaceSettings().setRotation(Rotation.CLOCKWISE_180).setMirror(Mirror.NONE).setIgnoreEntities(false),
										_serverworld.random, 3);
							}
						}
					}
				} else if ((getBlockDirection(world, BlockPos.containing(x, y, z))) == Direction.EAST) {
					if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hinge") instanceof EnumProperty _getep32
							? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep32).toString()
							: "").equals("left")
							&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp34
									&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp34)) == false) {
						world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
						if (world instanceof ServerLevel _serverworld) {
							StructureTemplate template = _serverworld.getStructureManager().getOrCreate(ResourceLocation.fromNamespaceAndPath("burnt", "burnt_door_closed_left"));
							if (template != null) {
								template.placeInWorld(_serverworld, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z), new StructurePlaceSettings().setRotation(Rotation.COUNTERCLOCKWISE_90).setMirror(Mirror.NONE).setIgnoreEntities(false),
										_serverworld.random, 3);
							}
						}
					} else if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hinge") instanceof EnumProperty _getep38
							? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep38).toString()
							: "").equals("right")
							&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp40
									&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp40)) == false) {
						world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
						if (world instanceof ServerLevel _serverworld) {
							StructureTemplate template = _serverworld.getStructureManager().getOrCreate(ResourceLocation.fromNamespaceAndPath("burnt", "burnt_door_closed_right"));
							if (template != null) {
								template.placeInWorld(_serverworld, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z), new StructurePlaceSettings().setRotation(Rotation.COUNTERCLOCKWISE_90).setMirror(Mirror.NONE).setIgnoreEntities(false),
										_serverworld.random, 3);
							}
						}
					} else if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hinge") instanceof EnumProperty _getep44
							? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep44).toString()
							: "").equals("left")
							&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp46
									&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp46)) == true) {
						world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
						if (world instanceof ServerLevel _serverworld) {
							StructureTemplate template = _serverworld.getStructureManager().getOrCreate(ResourceLocation.fromNamespaceAndPath("burnt", "burnt_door_open_left"));
							if (template != null) {
								template.placeInWorld(_serverworld, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z), new StructurePlaceSettings().setRotation(Rotation.COUNTERCLOCKWISE_90).setMirror(Mirror.NONE).setIgnoreEntities(false),
										_serverworld.random, 3);
							}
						}
					} else if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hinge") instanceof EnumProperty _getep50
							? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep50).toString()
							: "").equals("right")
							&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp52
									&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp52)) == true) {
						world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
						if (world instanceof ServerLevel _serverworld) {
							StructureTemplate template = _serverworld.getStructureManager().getOrCreate(ResourceLocation.fromNamespaceAndPath("burnt", "burnt_door_open_right"));
							if (template != null) {
								template.placeInWorld(_serverworld, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z), new StructurePlaceSettings().setRotation(Rotation.COUNTERCLOCKWISE_90).setMirror(Mirror.NONE).setIgnoreEntities(false),
										_serverworld.random, 3);
							}
						}
					}
				} else if ((getBlockDirection(world, BlockPos.containing(x, y, z))) == Direction.SOUTH) {
					if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hinge") instanceof EnumProperty _getep58
							? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep58).toString()
							: "").equals("left")
							&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp60
									&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp60)) == false) {
						world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
						if (world instanceof ServerLevel _serverworld) {
							StructureTemplate template = _serverworld.getStructureManager().getOrCreate(ResourceLocation.fromNamespaceAndPath("burnt", "burnt_door_closed_left"));
							if (template != null) {
								template.placeInWorld(_serverworld, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z), new StructurePlaceSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setIgnoreEntities(false),
										_serverworld.random, 3);
							}
						}
					} else if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hinge") instanceof EnumProperty _getep64
							? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep64).toString()
							: "").equals("right")
							&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp66
									&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp66)) == false) {
						world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
						if (world instanceof ServerLevel _serverworld) {
							StructureTemplate template = _serverworld.getStructureManager().getOrCreate(ResourceLocation.fromNamespaceAndPath("burnt", "burnt_door_closed_right"));
							if (template != null) {
								template.placeInWorld(_serverworld, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z), new StructurePlaceSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setIgnoreEntities(false),
										_serverworld.random, 3);
							}
						}
					} else if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hinge") instanceof EnumProperty _getep70
							? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep70).toString()
							: "").equals("left")
							&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp72
									&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp72)) == true) {
						world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
						if (world instanceof ServerLevel _serverworld) {
							StructureTemplate template = _serverworld.getStructureManager().getOrCreate(ResourceLocation.fromNamespaceAndPath("burnt", "burnt_door_open_left"));
							if (template != null) {
								template.placeInWorld(_serverworld, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z), new StructurePlaceSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setIgnoreEntities(false),
										_serverworld.random, 3);
							}
						}
					} else if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hinge") instanceof EnumProperty _getep76
							? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep76).toString()
							: "").equals("right")
							&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp78
									&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp78)) == true) {
						world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
						if (world instanceof ServerLevel _serverworld) {
							StructureTemplate template = _serverworld.getStructureManager().getOrCreate(ResourceLocation.fromNamespaceAndPath("burnt", "burnt_door_open_right"));
							if (template != null) {
								template.placeInWorld(_serverworld, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z), new StructurePlaceSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setIgnoreEntities(false),
										_serverworld.random, 3);
							}
						}
					}
				} else if ((getBlockDirection(world, BlockPos.containing(x, y, z))) == Direction.WEST) {
					if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hinge") instanceof EnumProperty _getep84
							? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep84).toString()
							: "").equals("left")
							&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp86
									&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp86)) == false) {
						world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
						if (world instanceof ServerLevel _serverworld) {
							StructureTemplate template = _serverworld.getStructureManager().getOrCreate(ResourceLocation.fromNamespaceAndPath("burnt", "burnt_door_closed_left"));
							if (template != null) {
								template.placeInWorld(_serverworld, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z), new StructurePlaceSettings().setRotation(Rotation.CLOCKWISE_90).setMirror(Mirror.NONE).setIgnoreEntities(false),
										_serverworld.random, 3);
							}
						}
					} else if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hinge") instanceof EnumProperty _getep90
							? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep90).toString()
							: "").equals("right")
							&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp92
									&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp92)) == false) {
						world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
						if (world instanceof ServerLevel _serverworld) {
							StructureTemplate template = _serverworld.getStructureManager().getOrCreate(ResourceLocation.fromNamespaceAndPath("burnt", "burnt_door_closed_right"));
							if (template != null) {
								template.placeInWorld(_serverworld, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z), new StructurePlaceSettings().setRotation(Rotation.CLOCKWISE_90).setMirror(Mirror.NONE).setIgnoreEntities(false),
										_serverworld.random, 3);
							}
						}
					} else if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hinge") instanceof EnumProperty _getep96
							? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep96).toString()
							: "").equals("left")
							&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp98
									&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp98)) == true) {
						world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
						if (world instanceof ServerLevel _serverworld) {
							StructureTemplate template = _serverworld.getStructureManager().getOrCreate(ResourceLocation.fromNamespaceAndPath("burnt", "burnt_door_open_left"));
							if (template != null) {
								template.placeInWorld(_serverworld, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z), new StructurePlaceSettings().setRotation(Rotation.CLOCKWISE_90).setMirror(Mirror.NONE).setIgnoreEntities(false),
										_serverworld.random, 3);
							}
						}
					} else if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hinge") instanceof EnumProperty _getep102
							? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep102).toString()
							: "").equals("right")
							&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp104
									&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp104)) == true) {
						world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
						if (world instanceof ServerLevel _serverworld) {
							StructureTemplate template = _serverworld.getStructureManager().getOrCreate(ResourceLocation.fromNamespaceAndPath("burnt", "burnt_door_open_right"));
							if (template != null) {
								template.placeInWorld(_serverworld, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z), new StructurePlaceSettings().setRotation(Rotation.CLOCKWISE_90).setMirror(Mirror.NONE).setIgnoreEntities(false),
										_serverworld.random, 3);
							}
						}
					}
				}
			}
		}
	}

	private static Direction getBlockDirection(LevelAccessor world, BlockPos pos) {
		BlockState blockState = world.getBlockState(pos);
		Property<?> property = blockState.getBlock().getStateDefinition().getProperty("facing");
		if (property != null && blockState.getValue(property) instanceof Direction direction)
			return direction;
		else if (blockState.hasProperty(BlockStateProperties.AXIS))
			return Direction.fromAxisAndDirection(blockState.getValue(BlockStateProperties.AXIS), Direction.AxisDirection.POSITIVE);
		else if (blockState.hasProperty(BlockStateProperties.HORIZONTAL_AXIS))
			return Direction.fromAxisAndDirection(blockState.getValue(BlockStateProperties.HORIZONTAL_AXIS), Direction.AxisDirection.POSITIVE);
		return Direction.NORTH;
	}
}