package net.pixelbank.burnt.procedures;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class CookedMushroomBlockstatesProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
        for (int i = 0; i < 64; i++) {
            boolean north = (i & 32) == 0;
            boolean south = (i & 16) == 0;
            boolean east = (i & 8) == 0;
            boolean west = (i & 4) == 0;
            boolean up = (i & 2) == 0;
            boolean down = (i & 1) == 0;

            if ((blockstate.getBlock().getStateDefinition().getProperty("north") instanceof BooleanProperty _getbpN && blockstate.getValue(_getbpN)) == north
                    && (blockstate.getBlock().getStateDefinition().getProperty("south") instanceof BooleanProperty _getbpS && blockstate.getValue(_getbpS)) == south
                    && (blockstate.getBlock().getStateDefinition().getProperty("east") instanceof BooleanProperty _getbpE && blockstate.getValue(_getbpE)) == east
                    && (blockstate.getBlock().getStateDefinition().getProperty("west") instanceof BooleanProperty _getbpW && blockstate.getValue(_getbpW)) == west
                    && (blockstate.getBlock().getStateDefinition().getProperty("up") instanceof BooleanProperty _getbpU && blockstate.getValue(_getbpU)) == up
                    && (blockstate.getBlock().getStateDefinition().getProperty("down") instanceof BooleanProperty _getbpD && blockstate.getValue(_getbpD)) == down) {
                {
                    int _value = i + 1;
                    BlockPos _pos = BlockPos.containing(x, y, z);
                    BlockState _bs = world.getBlockState(_pos);
                    if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
                        world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
                }
                return; // Exit loop once a match is found
            }
        }
    }
}
