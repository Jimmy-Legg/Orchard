package orchard.application;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import orchard.scene.SceneController;

/**
 * Orchard Game V6 | Java FX
 */

public class App extends Application {

	private static final String TITLE = "Orchard";
	private static final String ICON_URL = "/iconOrchard.png";
	
	private static final Integer MIN_WIDTH = 1200;
	private static final Integer MIN_HEIGHT = 800;
	
	@Override
	public void start(Stage stage) {

		stage.setTitle(TITLE);

		SceneController sceneController = new SceneController(stage);
		sceneController.showMenu();

		stage.setMinWidth(MIN_WIDTH);
		stage.setMinHeight(MIN_HEIGHT);

		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		stage.setWidth(bounds.getWidth());
		stage.setHeight(bounds.getHeight());

		Image icon = new Image(getClass().getResourceAsStream(ICON_URL));
		stage.getIcons().add(icon);
		
		stage.setMaximized(true);
		stage.show();

	}

	public static void main(String[] args) {
		launch();
	}

}