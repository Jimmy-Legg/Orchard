package orchard.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import orchard.model.Player;
import orchard.model.enums.FruitType;
import orchard.model.enums.OrchardColor;

public class GameOverPlayerPanel extends HBox {

	private static final Background BACKGROUNG = new Background(
			new BackgroundFill(OrchardColor.BEIGE.color(), new CornerRadii(10), Insets.EMPTY));
	
	private static final String RAVEN_IMAGE = "/raven.png";
	private static final String MEDAIL_URL = "/medail";
	private static final String MEDAIL_IMAGE_FORMAT = ".png";
	private static final Integer IMAGE_SIZE = 70;

	private ImageView medailImageView;
	private Label playerNameLabel;
	
	private ImageView appleImageView;
	private ImageView pearImageView;
	private ImageView plumImageView;
	private ImageView cherryImageView;
	private ImageView ravenImageView;

	private Label appleScoreLabel;
	private Label pearScoreLabel;
	private Label plumScoreLabel;
	private Label cherryScoreLabel;
	private Label ravenScoreLabel;

	public GameOverPlayerPanel(Player player, Integer rank) {
		super();

		try {
			this.medailImageView = new ImageView(
					new Image(this.getClass().getResourceAsStream(MEDAIL_URL + rank + MEDAIL_IMAGE_FORMAT),
							IMAGE_SIZE, IMAGE_SIZE, false, false));
		} catch (Exception e) {
			this.medailImageView = new ImageView(
					new Image(this.getClass().getResourceAsStream(MEDAIL_URL + "1" + MEDAIL_IMAGE_FORMAT), IMAGE_SIZE,
							IMAGE_SIZE, false, false));
			throw new IllegalArgumentException("rank must be between 0 and 2 (2 include)");
		}

		this.appleImageView = new ImageView(
				new Image(FruitType.APPLE.getImageURL(), IMAGE_SIZE, IMAGE_SIZE, false, false));
		this.pearImageView = new ImageView(
				new Image(FruitType.PEAR.getImageURL(), IMAGE_SIZE, IMAGE_SIZE, false, false));
		this.plumImageView = new ImageView(
				new Image(FruitType.PLUM.getImageURL(), IMAGE_SIZE, IMAGE_SIZE, false, false));
		this.cherryImageView = new ImageView(
				new Image(FruitType.CHERRY.getImageURL(), IMAGE_SIZE, IMAGE_SIZE, false, false));

		this.playerNameLabel = new Label(player.getPlayerData().getName());
		this.playerNameLabel.setPrefWidth(150);
		
		this.appleScoreLabel = new Label(player.getAppleAmount().asString().get());
		this.pearScoreLabel = new Label(player.getPearAmount().asString().get());
		this.plumScoreLabel = new Label(player.getPlumAmount().asString().get());
		this.cherryScoreLabel = new Label(player.getCherryAmount().asString().get());
		this.ravenScoreLabel = new Label(player.getRavenPieceAmount().toString());

		this.ravenImageView = new ImageView(new Image(RAVEN_IMAGE, IMAGE_SIZE, IMAGE_SIZE, false, false));

		this.getChildren().addAll(medailImageView, playerNameLabel, appleImageView, appleScoreLabel, pearImageView, pearScoreLabel,
				plumImageView, plumScoreLabel, cherryImageView, cherryScoreLabel, ravenImageView, ravenScoreLabel);

		this.setBackground(BACKGROUNG);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(20));
		this.setSpacing(20);
	}

}
