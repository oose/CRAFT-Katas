package de.oose.breakout;

import de.oose.breakout.assets.artifacts.*;
import de.oose.breakout.highscore.HighScore;
import de.oose.breakout.items.impl.Ball;
import de.oose.breakout.items.impl.Racket;
import de.oose.breakout.items.impl.Tile;
import de.oose.breakout.items.impl.Token;
import de.oose.breakout.statistics.StatisticTool;
import de.oose.breakout.util.ResourceLocator;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Breakout extends Application {

    private Pane canvas;

    public static void main(String[] args) {
         	launch(args);
    }

    Logger log = LogManager.getLogger(getClass());

    // game dimensions
    public final static int fieldWidth = 620;
    public final static int fieldHight = 700;
    private final int tileRows = 5;
    private final int tileCols = 20;
    private final int maxShots = 5;

    // items
    private List<Tile> tiles = null;
    private Ball ball = null;
    private LevelText levelTx = null;
    private NoOfBallsText noOfBallsText = null;
    private PointsText pointsText = null;
    private HighScoreText highScoreText = null;
    private MultiplyText multiplyText = null;
    private Token tokens[] = new Token[5];

    // images
    private Image backgroundImage;
    private Image racketImageNarrow;
    private Image racketImageWide;
    private Image ballImage;
    private Image greenTile;

    //FX stuff
    private Scene scene;
    private Racket racket;
    private Image racketImage;
    private Tile tile;

    private AudioClip gotToken = ResourceLocator.getSound("gottoken.wav");

    // counter
    private int noOfBalls = 3;
    private int multiply = 1;

    private boolean hold;

    // hall of fame
    List<HallOfFameText> hallOfFames = new ArrayList<HallOfFameText>();

    // switches
    private boolean run = true;
    private boolean showHallOfFame = false;
    private boolean shotActive = true;

    // speed constants
    private int gameSpeed = 8;
    private int shootSpeed = -2;
    private int tokenSpeed = 2;
    private int level = 0;

    public double bx = 7; //Step on x or velocity
    public double by = 3; //Step on y


    private void initImages(int player) {
        backgroundImage = ResourceLocator.getImage("Background1.gif");
        racketImage = ResourceLocator.getImage("Racket.gif");
        racketImageWide = ResourceLocator.getImage("RacketWide.gif");
        racketImageNarrow = ResourceLocator.getImage("RacketNarrow.gif");
        ballImage = ResourceLocator.getImage("ball.gif");
        greenTile = ResourceLocator.getImage("Tile_green.jpg");
    }

    private void nextLevel() {
        tiles = new Field(this, tileRows, tileCols).createField(level++).getTiles();
        racket = new Racket(this, racketImage);
        racket.moveRacketTo(fieldWidth / 2, fieldHight - 50);
        ball = new Ball(5, Color.CADETBLUE, this);

        ball.ballOff = false;
        double x = racket.getShape().getX();
        double y = racket.getShape().getY() - racket.getShape().getHeight() + 2;
        ball.relocate(x, y);
    }

    private void initItems() {
        levelTx = new LevelText(1);
        noOfBallsText = new NoOfBallsText(noOfBalls);
        pointsText = new PointsText();
        multiplyText = new MultiplyText(multiply);
        highScoreText = new HighScoreText(0);
        nextLevel();
    }

    private void drawTiles() {
        tiles.forEach(Tile::draw);
    }

    boolean goEast, goWest;

    @Override
    public void init() throws Exception {
        super.init();
        log.info("init");
        initImages(1);
        initItems();
        drawTiles();
    }

    @Override
    public void start(Stage stage) {
        log.info("start");

        canvas = new Pane();
        Scene scene = new Scene(canvas, fieldWidth, fieldHight, Color.ALICEBLUE);

        canvas.getChildren().add(ball);
        canvas.getChildren().add(racket);
        canvas.getChildren().addAll(tiles);
        canvas.getChildren().add(levelTx);
        canvas.getChildren().add(noOfBallsText);
        canvas.getChildren().add(pointsText);
        canvas.getChildren().add(multiplyText);
        canvas.getChildren().add(highScoreText);
        canvas.getChildren().add(new Token(Color.CYAN  , "H",  50));

        stage.setTitle("Breakout");
        stage.setScene(scene);
        stage.show();


        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case LEFT:
                        goWest = true;
                        break;
                    case RIGHT:
                        goEast = true;
                        break;
                    case SPACE:
                        ball.running = true;
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case LEFT:
                        goWest = false;
                        break;
                    case RIGHT:
                        goEast = false;
                        break;
                }
            }
        });


        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                //move the ball
                handleBallMove(canvas);
                handleMoveToken();
                drawTiles();
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int dx = 0, dy = 0;
                if (goEast) dx += 1;
                if (goWest) dx -= 1;

                moveRacketBy(dx *= 5, dy *= 5);
            }
        };
        timer.start();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        log.info("stop");
        printStatistics();
    }

    private void handleBallMove(Pane canvas) {
        if (ball != null) {
            if (ball.running) {
                ball.move(bx, by, racket);
                testCollisionBallWithTile();
                ball.hitBorder(canvas);
                ball.hitRacket(this, racket);
            } else {
                ball.freezeOnRacket(racket);
            }
        } else {
            nextBall();
        }
        testAndHandleBallOff();
    }

    private void nextBall() {
        if (noOfBalls > 0) {
            if (ball == null) {
                ball = new Ball(5, Color.CADETBLUE, this);
            }
            ball.ballOff = false;
            double x = racket.getShape().getX();
            double y = racket.getShape().getY() - racket.getShape().getHeight() + 2;
            ball.relocate(x, y);
            noOfBalls--;
            noOfBallsText.setNoOfBalls(noOfBalls);
            log.info("next Ball");
        }
    }

    private void moveRacketBy(int dx, int dy) {
        if (dx == 0 && dy == 0) return;

        final double cx = racket.getBoundsInLocal().getWidth() / 2;
        final double cy = racket.getBoundsInLocal().getHeight() / 2;

        double x = cx + racket.getLayoutX() + dx;
        double y = cy + racket.getLayoutY() + dy;

        racket.moveRacketTo(x, y);
    }

    private void handleMoveToken() {
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] != null) {
                tokens[i].move(tokenSpeed);
                if (tokens[i].isOutOfField()) {
                    tokens[i] = null;
                }
                testCollisionTokenWithRacket(i);
            }
        }
    }

    private void testCollisionTokenWithRacket(int i) {
        Token token = tokens[i];

        if (token != null && racket != null) {
            if (hits(token, racket)) {
                if (token.getCharacter().equals("L")) {
                    shotActive = true;
                } else if (token.getCharacter().equals("S")) {
                    gameSpeed = 11;
                } else if (token.getCharacter().equals("F")) {
                    gameSpeed = 6;
                } else if (token.getCharacter().equals("M")) {
                    multiply = 2;
                    multiplyText.setMultiplyer(multiply);
                } else if (token.getCharacter().equals("H")) {
                    hold = true;
                } else if (token.getCharacter().equals("W")) {
                    racket.setImage(racketImageWide);
                } else if (token.getCharacter().equals("N")) {
                    racket.setImage(racketImageNarrow);
                } else if (token.getCharacter().equals("T")) {
                    generateDoubleBall();
                } else if (token.getCharacter().equals("D")) {
                    // double laser
                }
                gotToken.play();
                pointsText.addPoints(token.getPoints());
                canvas.getChildren().remove(token);
                tokens[i] = null;
            }
        }
    }

    private void testCollisionBallWithTile() {
        if (tiles != null && ball != null) {
            for (Tile tile : tiles) {
                if (hits(ball, tile)) {
                    removeTile(tile);
                    changeDirectionY();
                    ball.running = !hold;
                    break;
                }
            }
        }
    }

    private void testAndHandleBallOff() {
        if (ball != null && ball.ballOff) {
            ball.running = false;
            isGameFinished();
            nextBall();
        }
    }

    private void isGameFinished() {
        if (noOfBalls == 0) {
            highScoreText.setPoints(pointsText.getPoints());
            tiles.clear();
            if (!showHallOfFame) {
                showHallOfFame = true;
                initHallOfFame();
                showHallOfFame();
            }
        }
    }

    private void showHallOfFame() {
        canvas.getChildren().addAll(hallOfFames);
    }

    private void generateDoubleBall() {
        // TODO Auto-generated method stub
    }

    private void initHallOfFame() {
        List<HighScore> hallOfFame = highScoreText.getHallOfFame();
        if (hallOfFame != null && hallOfFame.size() > 0) {
            hallOfFames.add(new HallOfFameText("Hall of Fame", 200, 200));
            int i = 1;
            for (HighScore highScore : hallOfFame) {
                hallOfFames.add(new HallOfFameText(highScore.formatForHallOfFame(), 200, 200 + i * 50));
                i++;
            }
        }
    }


    private boolean hits(Node a, Node b) {
        if (b.intersects(b.sceneToLocal(a.localToScene(a.getBoundsInLocal())))) {
            return true;
        }
        return false;
    }

    public void changeDirectionY() {
        by *= -1;
    }

    public void changeDirectionX() {
        bx *= -1;
    }

    private void removeTile(Tile tile) {
        if (tile.getToken() != null) {
            canvas.getChildren().add(tile.getToken());
            log.info("found token: " + tile.getToken());
            for (int i = 0; i < tokens.length; i++) {
                if (tokens[i] == null) {
                    tokens[i] = (tile.getToken());
                    break;
                }
            }
        }

        pointsText.addPoints(tile.points * multiply);
        tile.remove();
        tiles.remove(tile);
        log.info("hit Tile");
    }

    private void printStatistics() {
        StatisticTool st = new StatisticTool();
        System.out.println(st.getPrettyStatisticsString());
    }
}
