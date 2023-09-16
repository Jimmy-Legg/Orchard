package orchard.scene;

import javafx.application.Platform;
import javafx.scene.Scene;
import orchard.controller.PlayTextLabelClickedActionController;
import orchard.controller.TextLabelHoverEnteredController;
import orchard.controller.TextLabelHoverExitedController;
import orchard.gui.MenuRoot;

public class MenuScene extends Scene{
	
	public MenuScene(SceneController sceneController) {
		super(new MenuRoot());
		
		MenuRoot root = (MenuRoot) this.getRoot();
		
		TextLabelHoverEnteredController playTextLabelHoverEnteredController =  new TextLabelHoverEnteredController(root.getImageView());
		TextLabelHoverExitedController playTextLabelHoverExitedController =  new TextLabelHoverExitedController(root.getImageView());
		root.getPlayTextLbl().setOnMouseEntered(playTextLabelHoverEnteredController);
		root.getPlayTextLbl().setOnMouseExited(playTextLabelHoverExitedController);
		
		TextLabelHoverEnteredController quitTextLabelHoverEnteredController =  new TextLabelHoverEnteredController(root.getImageView2());
		TextLabelHoverExitedController quitTextLabelHoverExitedController =  new TextLabelHoverExitedController(root.getImageView2());
		root.getQuitTextLbl().setOnMouseEntered(quitTextLabelHoverEnteredController);
		root.getQuitTextLbl().setOnMouseExited(quitTextLabelHoverExitedController);
		
		PlayTextLabelClickedActionController playTextLabelClickedActionController =  new PlayTextLabelClickedActionController(sceneController);
		root.getPlayTextLbl().setOnMouseClicked(playTextLabelClickedActionController);
		
		root.getQuitTextLbl().setOnMouseClicked(event -> Platform.exit());
	}
	
}
