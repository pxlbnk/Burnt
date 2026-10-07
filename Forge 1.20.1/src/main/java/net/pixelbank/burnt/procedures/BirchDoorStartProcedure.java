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

public class BirchDoorStartProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		if (!world.isClientSide()) {
			if ((getBlockDirection(world, BlockPos.containing(x, y, z))) == Direction.NORTH) {
				if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hinge") instanceof EnumProperty _getep4 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep4).toString() : "")
						.equalsIgnoreCase("left")
						&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp6
								&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp6)) == false) {
					world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
					if (world instanceof ServerLevel _serverworld) {
						StructureTemplate template = _serverworld.getStructureManager().getOrCreate(new ResourceLocation("burnt", "birch_closed_left"));
						if (template != null) {
							template.placeInWorld(_serverworld, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z), new StructurePlaceSettings().setRotation(Rotation.CLOCKWISE_180).setMirror(Mirror.NONE).setIgnoreEntities(false),
									_serverworld.random, 3);
						}
					}
				} else if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hinge") instanceof EnumProperty _getep10
						? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep10).toString()
						: "").equalsIgnoreCase("right")
						&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp12
								&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp12)) == false) {
					world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
					if (world instanceof ServerLevel _serverworld) {
						StructureTemplate template = _serverworld.getStructureManager().getOrCreate(new ResourceLocation("burnt", "birch_closed_right"));
						if (template != null) {
							template.placeInWorld(_serverworld, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z), new StructurePlaceSettings().setRotation(Rotation.CLOCKWISE_180).setMirror(Mirror.NONE).setIgnoreEntities(false),
									_serverworld.random, 3);
						}
					}
				} else if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hinge") instanceof EnumProperty _getep16
						? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep16).toString()
						: "").equalsIgnoreCase("left")
						&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp18
								&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp18)) == true) {
					world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
					if (world instanceof ServerLevel _serverworld) {
						StructureTemplate template = _serverworld.getStructureManager().getOrCreate(new ResourceLocation("burnt", "birch_open_left"));
						if (template != null) {
							template.placeInWorld(_serverworld, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z), new StructurePlaceSettings().setRotation(Rotation.CLOCKWISE_180).setMirror(Mirror.NONE).setIgnoreEntities(false),
									_serverworld.random, 3);
						}
					}
				} else if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hinge") instanceof EnumProperty _getep22
						? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep22).toString()
						: "").equalsIgnoreCase("right")
						&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp24
								&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp24)) == true) {
					world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
					if (world instanceof ServerLevel _serverworld) {
						StructureTemplate template = _serverworld.getStructureManager().getOrCreate(new ResourceLocation("burnt", "birch_open_right"));
						if (template != null) {
							template.placeInWorld(_serverworld, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z), new StructurePlaceSettings().setRotation(Rotation.CLOCKWISE_180).setMirror(Mirror.NONE).setIgnoreEntities(false),
									_serverworld.random, 3);
						}
					}
				}
			} else if ((getBlockDirection(world, BlockPos.containing(x, y, z))) == Direction.EAST) {
				if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hinge") instanceof EnumProperty _getep30 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep30).toString() : "")
						.equalsIgnoreCase("left")
						&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp32
								&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp32)) == false) {
					world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
					if (world instanceof ServerLevel _serverworld) {
						StructureTemplate template = _serverworld.getStructureManager().getOrCreate(new ResourceLocation("burnt", "birch_closed_left"));
						if (template != null) {
							template.placeInWorld(_serverworld, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z), new StructurePlaceSettings().setRotation(Rotation.COUNTERCLOCKWISE_90).setMirror(Mirror.NONE).setIgnoreEntities(false),
									_serverworld.random, 3);
						}
					}
				} else if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hinge") instanceof EnumProperty _getep36
						? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep36).toString()
						: "").equalsIgnoreCase("right")
						&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp38
								&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp38)) == false) {
					world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
					if (world instanceof ServerLevel _serverworld) {
						StructureTemplate template = _serverworld.getStructureManager().getOrCreate(new ResourceLocation("burnt", "birch_closed_right"));
						if (template != null) {
							template.placeInWorld(_serverworld, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z), new StructurePlaceSettings().setRotation(Rotation.COUNTERCLOCKWISE_90).setMirror(Mirror.NONE).setIgnoreEntities(false),
									_serverworld.random, 3);
						}
					}
				} else if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hinge") instanceof EnumProperty _getep42
						? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep42).toString()
						: "").equalsIgnoreCase("left")
						&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp44
								&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp44)) == true) {
					world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
					if (world instanceof ServerLevel _serverworld) {
						StructureTemplate template = _serverworld.getStructureManager().getOrCreate(new ResourceLocation("burnt", "birch_open_left"));
						if (template != null) {
							template.placeInWorld(_serverworld, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z), new StructurePlaceSettings().setRotation(Rotation.COUNTERCLOCKWISE_90).setMirror(Mirror.NONE).setIgnoreEntities(false),
									_serverworld.random, 3);
						}
					}
				} else if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hinge") instanceof EnumProperty _getep48
						? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep48).toString()
						: "").equalsIgnoreCase("right")
						&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp50
								&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp50)) == true) {
					world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
					if (world instanceof ServerLevel _serverworld) {
						StructureTemplate template = _serverworld.getStructureManager().getOrCreate(new ResourceLocation("burnt", "birch_open_right"));
						if (template != null) {
							template.placeInWorld(_serverworld, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z), new StructurePlaceSettings().setRotation(Rotation.COUNTERCLOCKWISE_90).setMirror(Mirror.NONE).setIgnoreEntities(false),
									_serverworld.random, 3);
						}
					}
				}
			} else if ((getBlockDirection(world, BlockPos.containing(x, y, z))) == Direction.SOUTH) {
				if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hinge") instanceof EnumProperty _getep56 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep56).toString() : "")
						.equalsIgnoreCase("left")
						&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp58
								&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp58)) == false) {
					world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
					if (world instanceof ServerLevel _serverworld) {
						StructureTemplate template = _serverworld.getStructureManager().getOrCreate(new ResourceLocation("burnt", "birch_closed_left"));
						if (template != null) {
							template.placeInWorld(_serverworld, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z), new StructurePlaceSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setIgnoreEntities(false), _serverworld.random,
									3);
						}
					}
				} else if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hinge") instanceof EnumProperty _getep62
						? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep62).toString()
						: "").equalsIgnoreCase("right")
						&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp64
								&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp64)) == false) {
					world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
					if (world instanceof ServerLevel _serverworld) {
						StructureTemplate template = _serverworld.getStructureManager().getOrCreate(new ResourceLocation("burnt", "birch_closed_right"));
						if (template != null) {
							template.placeInWorld(_serverworld, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z), new StructurePlaceSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setIgnoreEntities(false), _serverworld.random,
									3);
						}
					}
				} else if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hinge") instanceof EnumProperty _getep68
						? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep68).toString()
						: "").equalsIgnoreCase("left")
						&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp70
								&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp70)) == true) {
					world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
					if (world instanceof ServerLevel _serverworld) {
						StructureTemplate template = _serverworld.getStructureManager().getOrCreate(new ResourceLocation("burnt", "birch_open_left"));
						if (template != null) {
							template.placeInWorld(_serverworld, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z), new StructurePlaceSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setIgnoreEntities(false), _serverworld.random,
									3);
						}
					}
				} else if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hinge") instanceof EnumProperty _getep74
						? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep74).toString()
						: "").equalsIgnoreCase("right")
						&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp76
								&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp76)) == true) {
					world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
					if (world instanceof ServerLevel _serverworld) {
						StructureTemplate template = _serverworld.getStructureManager().getOrCreate(new ResourceLocation("burnt", "birch_open_right"));
						if (template != null) {
							template.placeInWorld(_serverworld, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z), new StructurePlaceSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setIgnoreEntities(false), _serverworld.random,
									3);
						}
					}
				}
			} else if ((getBlockDirection(world, BlockPos.containing(x, y, z))) == Direction.WEST) {
				if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hinge") instanceof EnumProperty _getep82 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep82).toString() : "")
						.equalsIgnoreCase("left")
						&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp84
								&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp84)) == false) {
					world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
					if (world instanceof ServerLevel _serverworld) {
						StructureTemplate template = _serverworld.getStructureManager().getOrCreate(new ResourceLocation("burnt", "birch_closed_left"));
						if (template != null) {
							template.placeInWorld(_serverworld, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z), new StructurePlaceSettings().setRotation(Rotation.CLOCKWISE_90).setMirror(Mirror.NONE).setIgnoreEntities(false),
									_serverworld.random, 3);
						}
					}
				} else if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hinge") instanceof EnumProperty _getep88
						? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep88).toString()
						: "").equalsIgnoreCase("right")
						&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp90
								&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp90)) == false) {
					world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
					if (world instanceof ServerLevel _serverworld) {
						StructureTemplate template = _serverworld.getStructureManager().getOrCreate(new ResourceLocation("burnt", "birch_closed_right"));
						if (template != null) {
							template.placeInWorld(_serverworld, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z), new StructurePlaceSettings().setRotation(Rotation.CLOCKWISE_90).setMirror(Mirror.NONE).setIgnoreEntities(false),
									_serverworld.random, 3);
						}
					}
				} else if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hinge") instanceof EnumProperty _getep94
						? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep94).toString()
						: "").equalsIgnoreCase("left")
						&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp96
								&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp96)) == true) {
					world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
					if (world instanceof ServerLevel _serverworld) {
						StructureTemplate template = _serverworld.getStructureManager().getOrCreate(new ResourceLocation("burnt", "birch_open_left"));
						if (template != null) {
							template.placeInWorld(_serverworld, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z), new StructurePlaceSettings().setRotation(Rotation.CLOCKWISE_90).setMirror(Mirror.NONE).setIgnoreEntities(false),
									_serverworld.random, 3);
						}
					}
				} else if (((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("hinge") instanceof EnumProperty _getep100
						? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getep100).toString()
						: "").equalsIgnoreCase("right")
						&& ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp102
								&& (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp102)) == true) {
					world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
					if (world instanceof ServerLevel _serverworld) {
						StructureTemplate template = _serverworld.getStructureManager().getOrCreate(new ResourceLocation("burnt", "birch_open_right"));
						if (template != null) {
							template.placeInWorld(_serverworld, BlockPos.containing(x, y, z), BlockPos.containing(x, y, z), new StructurePlaceSettings().setRotation(Rotation.CLOCKWISE_90).setMirror(Mirror.NONE).setIgnoreEntities(false),
									_serverworld.random, 3);
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