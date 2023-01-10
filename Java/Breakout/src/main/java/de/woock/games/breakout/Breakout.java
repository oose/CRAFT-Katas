package de.woock.games.breakout;

import static de.woock.games.breakout.util.PlayerMove.stopped;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.woock.games.breakout.assets.artefacts.Field;
import de.woock.games.breakout.assets.artefacts.HallOfFameText;
import de.woock.games.breakout.assets.artefacts.HighScoreText;
import de.woock.games.breakout.assets.artefacts.LevelText;
import de.woock.games.breakout.assets.artefacts.MultiplyText;
import de.woock.games.breakout.assets.artefacts.NoOfBallsText;
import de.woock.games.breakout.assets.artefacts.PointsText;
import de.woock.games.breakout.highscore.HighScore;
import de.woock.games.breakout.items.impl.Ball;
import de.woock.games.breakout.items.impl.Racket;
import de.woock.games.breakout.items.impl.Shot;
import de.woock.games.breakout.items.impl.Tile;
import de.woock.games.breakout.items.impl.Token;
import de.woock.games.breakout.listener.BreakoutKeyListener;
import de.woock.games.breakout.statistics.StatisticTool;
import de.woock.games.breakout.util.PlayerMove;
import de.woock.games.breakout.util.ResourceLocator;

public class Breakout extends Applet implements Runnable {

	private Logger log = LogManager.getLogger(Breakout.class);

	private static final long serialVersionUID = 1L;

	// game dimensions
	public final static int fieldWidth = 620;
	public final static int fieldHight = 600;
	private final int tileRows = 5;
	private final int tileCols = 20;
	private final int maxShots = 5;

	// items
	private List<Tile> tiles = null;
	private Racket racket = null;
	private Ball ball = null;
	private PointsText pointsTx = null;
	private NoOfBallsText noOfBallsTx = null;
	private HighScoreText highscoreTx = null;
	private MultiplyText multiplyTx = null;
	private LevelText levelTx = null;
	private Shot shots[] = new Shot[5];
	private Token tokens[] = new Token[5];

	// hall of fame
	List<HallOfFameText> hallOfFames = new ArrayList<HallOfFameText>();

	// counter
	private int noOfBalls = 3;

	// switches
	private boolean run = true;
	private boolean showHallOfFame = false;
	private boolean shotActive = true;

	// speed constants
	private int gameSpeed = 8;
	private int shootSpeed = -2;
	private int tokenSpeed = 1;
	private int level = 0;

	// move direction flags
	public PlayerMove playerMove = stopped;

	// images
	private Image backgroundImage;
	private Image racketImage;
	private Image racketImageNarrow;
	private Image racketImageWide;

	// double buffering
	private Image dbImage;
	private Graphics dbg;

	// sounds
	private AudioClip kill;
	private AudioClip off;
	private AudioClip laser;
	private AudioClip gotToken;

	private Thread thread;

	private int multiply;

	private boolean hold;

	@Override
	public void run() {
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
		while (run) {
			if (isLevelFinished()) {
				levelFinished();
			}

			handleRacketMove();
			handleBallMove();
			handleShotMove();
			handleMoveToken();

			repaint();

			waitAndRun();
		}
	}

	@Override
	public void init() {
		log.debug("init");
		setName("Breakout");

		addKeyListener(new BreakoutKeyListener(this));
		initAppletWindow();
		initImages(1);
		initSounds();

		initItems();

		initHallOfFame();
	}

	@Override
	public void start() {
		log.debug("start");

		requestFocusInWindow();

		startThread();
	}

	@Override
	public void stop() {
		log.debug("stop");

		stopThread();
	}

	@Override
	public void destroy() {
		log.debug("destroy");
		printStatistics();
	}

	@Override
	public void update(Graphics g) {
		doubleBuffering(g);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(backgroundImage, 0, 0, fieldWidth, fieldHight, this);

		for (Tile tile : tiles) {
			tile.draw(g);
		}

		if (ball != null)
			ball.draw(g);

		racket.draw(g);

		drawText(g);

		for (int i = 0; i < maxShots; i++) {
			if (shots[i] != null)
				shots[i].draw(g);
		}
		for (int i = 0; i < tokens.length; i++) {
			if (tokens[i] != null)
				tokens[i].draw(g);
		}

		if (showHallOfFame) {
			drawHallOfFame(g);
		}
	}

	public void setBallRunning() {
		ball.running = true;
	}

	public boolean isBallRunning() {
		return ball.running;
	}

	public boolean isShotActive() {
		return shotActive;
	}

	// synchronized
	public void generateShots() {
		for (int i = 0; i < maxShots; i++) {
			if (shots[i] == null) {
				laser.play();
				shots[i] = new Shot(racket.getMiddleX(0), racket.top());
				break;
			}
		}
	}

	private void initImages(int player) {
		backgroundImage = ResourceLocator.getImage(this, "Background1.gif");
		racketImage = ResourceLocator.getImage(this, "Racket.gif");
		racketImageWide = ResourceLocator.getImage(this, "RacketWide.gif");
		racketImageNarrow = ResourceLocator.getImage(this, "RacketNarrow.gif");
	}

	private void initSounds() {
		kill = ResourceLocator.getSound(this, "kill.au");
		off = ResourceLocator.getSound(this, "looseLive.au");
		laser = ResourceLocator.getSound(this, "laser.wav");
		gotToken = ResourceLocator.getSound(this, "gottoken.wav");
	}

	private void initAppletWindow() {
		setSize(new Dimension(fieldWidth, fieldHight));
		setFocusable(true);
	}

	private void initItems() {
		pointsTx = new PointsText();
		noOfBallsTx = new NoOfBallsText(2);
		highscoreTx = new HighScoreText();
		multiplyTx = new MultiplyText();
		levelTx = new LevelText(1);

		nextLevel();
	}

	private void nextLevel() {
		tiles = new Field(this, tileRows, tileCols).createField(level++).getTiles();
		levelTx.setLevel(level);
		racket = new Racket(this, racketImage);

		int x = racket.getShape().x + racket.getMiddleX(0);
		int y = racket.getShape().y + racket.getShape().height;
		ball = new Ball(this, x, y);
	}

	private void startThread() {
		thread = new Thread(this);
		thread.start();
	}

	private void stopThread() {
		run = false;
		thread.interrupt();
		thread = null;
	}

	private void waitAndRun() {
		try {
			Thread.sleep(gameSpeed);
		} catch (InterruptedException ex) {
		}
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
	}

	private void doubleBuffering(Graphics g) {
		if (dbImage == null) {
			dbImage = createImage(fieldWidth, fieldHight);
			dbg = dbImage.getGraphics();
		}
		dbg.setColor(getBackground());
		dbg.fillRect(0, 0, fieldWidth, fieldHight);

		dbg.setColor(getForeground());
		paint(dbg);

		g.drawImage(dbImage, 0, 0, this);
	}

	private void handleRacketMove() {
		racket.moveX(playerMove);
	}

	private void handleShotMove() {
		for (int i = 0; i < maxShots; i++) {
			if (shots[i] != null) {
				shots[i].move(shootSpeed);
				if (shots[i].isOutOfField() || testColisionShotWithTile(shots[i])) {
					shots[i] = null;
				}
			}
		}
	}

	private void handleBallMove() {
		if (ball != null) {
			if (ball.running) {
				ball.moveXY(racket.getShape());
				testCollisionBallWithTile();
			} else {
				ball.moveXY(racket.getShape());
				ball.freezeOnRacket(racket);
			}
		} else {
			nextBall();
		}
		testAndHandleBallOff();
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

	private void generateDoubleBall() {
		// TODO Auto-generated method stub
	}

	private void removeTile(Tile tile) {
		if (tile.getToken() != null) {
			log.info("found token: " + tile.getToken());
			for (int i = 0; i < tokens.length; i++) {
				if (tokens[i] == null) {
					tokens[i] = (tile.getToken());
					break;
				}
			}
		}
		pointsTx.addPoints(tile.points * multiply);
		kill.play();
		tiles.remove(tile);
	}

	private void isGameFinished() {
		if (noOfBalls == 0) {
			highscoreTx.setPoints(pointsTx.getPoints());
			tiles.clear();
			initHallOfFame();
			showHallOfFame = true;
		}
	}

	private boolean isLevelFinished() {
		return tiles == null || tiles.size() == 0;
	}

	private void levelFinished() {
		if (ball != null) {
			ball.running = false;
		}
		shotActive = false;
		multiply = 1;
		multiplyTx.setMultiplyer(1);
		nextLevel();

	}

	private void nextBall() {
		if (noOfBalls > 0) {
			int x = racket.getShape().x + racket.getShape().width / 2;
			int y = racket.getShape().y - racket.getShape().height;
			ball = new Ball(this, x, y);
			noOfBalls--;
			noOfBallsTx.setNoOfBalls(noOfBalls);
			log.info("next Ball");
		}

	}

	private void initHallOfFame() {
		showHallOfFame = false;
		List<HighScore> hallOfFame = highscoreTx.getHallOfFame();
		if (hallOfFame != null && hallOfFame.size() < 0) {
			int i = 0;
			hallOfFames.add(new HallOfFameText(hallOfFame.get(i).formatForHallOfFame(), 200, 200 + i * 50));
		}
	}

	private void drawText(Graphics g) {
		pointsTx.draw(g);
		noOfBallsTx.draw(g);
		highscoreTx.draw(g);
		multiplyTx.draw(g);
		levelTx.draw(g);
	}

	private void drawHallOfFame(Graphics g) {
		for (HallOfFameText fameText : hallOfFames) {
			fameText.draw(g);
		}
	}

	private void testAndHandleBallOff() {
		if (ball != null && ball.ballOff) {
			ball = null;
			off.play();
			isGameFinished();
		}
	}

	private boolean testColisionShotWithTile(Shot shot) {
		if (shot != null) {
			for (Tile tile : tiles) {
				if (tile.intersects(shot.getShape())) {
					removeTile(tile);
					return true;
				}
			}
		}
		return false;
	}

	private void testCollisionBallWithTile() {
		if (tiles != null && ball != null) {
			for (Tile tile : tiles) {
				if (ball.hits(tile)) {
					removeTile(tile);
					ball.changeDirectionY();
					ball.running = !hold;
					break;
				}
			}
		}
	}

	private void testCollisionTokenWithRacket(int i) {
		Token token = tokens[i];

		if (token != null && racket != null) {
			if (racket.hits(token)) {

				if (token.getCharacter().equals("L")) {
					shotActive = true;
				} else if (token.getCharacter().equals("S")) {
					gameSpeed = 11;
				} else if (token.getCharacter().equals("F")) {
					gameSpeed = 6;
				} else if (token.getCharacter().equals("M")) {
					multiply = 2;
					multiplyTx.setMultiplyer(multiply);
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
				pointsTx.addPoints(token.getPoints());
				tokens[i] = null;
			}
		}
	}

	private void printStatistics() {
		StatisticTool st = new StatisticTool();
		System.out.println(st.getPrettyStatisticsString());

	}
}
