package net.pixelbank.burnt.init;

// Screen registration moved to BurntModClient
// Kept for the ScreenAccessor interface used by the barrel GUI

public class BurntModScreens {

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}
