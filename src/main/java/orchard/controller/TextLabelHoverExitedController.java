package orchard.controller;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class TextLabelHoverExitedController implements EventHandler<MouseEvent>{

	private ImageView imageView;

	@Override
	public void handle(MouseEvent event) {
		imageView.setImage(null);
	}

	public TextLabelHoverExitedController(ImageView imageView) {
		super();
		this.imageView = imageView;
	}
}
