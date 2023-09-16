package orchard.controller;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class TextLabelHoverEnteredController implements EventHandler<MouseEvent>{

	private ImageView imageView;
	
	private final Image image = new Image(getClass().getResourceAsStream("/arrow.png"));

	@Override
	public void handle(MouseEvent event) {
		imageView.setImage(image);		
	}

	public TextLabelHoverEnteredController(ImageView imageView) {
		super();
		this.imageView = imageView;
	}
}
