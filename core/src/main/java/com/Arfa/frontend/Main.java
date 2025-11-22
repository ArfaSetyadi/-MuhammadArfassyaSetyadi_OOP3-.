package com.Arfa.frontend;

import com.Arfa.frontend.commands.Command;
import com.Arfa.frontend.commands.JetpackCommand;
import com.Arfa.frontend.commands.RestartCommand;
import com.Arfa.frontend.factories.ObstacleFactory;
import com.Arfa.frontend.obstacles.BaseObstacle;
import com.Arfa.frontend.obstacles.HomingMissile;
import com.Arfa.frontend.observers.ScoreUIObserver;
import com.Arfa.frontend.strategies.DifficultyStrategy;
import com.Arfa.frontend.strategies.MediumDifficultyStrategy;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends Game {

    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;

    private Player player;
    private Ground ground;
    private Background background;

    private GameManager gameManager;
    private ObstacleFactory obstacleFactory;

    private Command jetpackCommand;
    private Command restartCommand;

    private ScoreUIObserver scoreUIObserver;

    private OrthographicCamera camera;
    private float cameraOffset = 0.2f;

    private int screenWidth;
    private int screenHeight;

    private float obstacleSpawnTimer = 0f;
    private float lastObstacleSpawnX = 0f;

    private int lastLoggedScore = -1;

    @Override
    public void create() {

        shapeRenderer = new ShapeRenderer();
        spriteBatch = new SpriteBatch();

        gameManager = GameManager.getInstance();
        gameManager.setDifficultyStrategy(new MediumDifficultyStrategy());

        DifficultyStrategy ds = gameManager.getDifficultyStrategy();

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, screenWidth, screenHeight);

        player = new Player(new Vector2(100, screenHeight / 2f), ds);

        ground = new Ground();
        background = new Background();

        jetpackCommand = new JetpackCommand(player);
        restartCommand = new RestartCommand(player, gameManager);

        scoreUIObserver = new ScoreUIObserver();
        gameManager.addObserver(scoreUIObserver);

        obstacleFactory = new ObstacleFactory();
        obstacleFactory.setWeights(ds.getObstacleWeights());

        gameManager.startGame();
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();
        update(delta);
        renderGame();
    }

    private void update(float delta) {

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            jetpackCommand.execute();
        }

        if (player.isDead()) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                restartCommand.execute();
            }
            return;
        }

        player.update(delta, false);
        updateCamera();

        background.update(camera.position.x);
        ground.update(camera.position.x);

        player.checkBoundaries(ground, screenHeight);

        updateObstacles(delta);
        checkCollisions();

        int score = (int) player.getDistanceTraveled();
        if (score > gameManager.getScore()) {

            if (score != lastLoggedScore) {
                System.out.println("Distance: " + score + "m");
                lastLoggedScore = score;
            }

            gameManager.setScore(score);
        }
    }

    private void renderGame() {

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        spriteBatch.setProjectionMatrix(camera.combined);
        background.render(spriteBatch);

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        ground.renderShape(shapeRenderer);
        player.renderShape(shapeRenderer);

        shapeRenderer.setColor(Color.RED);
        for (BaseObstacle obstacle : obstacleFactory.getAllInUseObstacles()) {
            obstacle.render(shapeRenderer);
        }

        shapeRenderer.end();

        scoreUIObserver.render(gameManager.getScore());
    }

    private void updateCamera() {
        float cameraFocus = player.getPosition().x + screenWidth * cameraOffset;
        camera.position.x = cameraFocus;
        camera.update();
    }

    private void updateObstacles(float delta) {

        float spawnInterval = gameManager.getDifficultyStrategy().getSpawnInterval();
        obstacleSpawnTimer += delta;

        if (obstacleSpawnTimer >= spawnInterval) {
            spawnObstacle();
            obstacleSpawnTimer = 0f;
        }

        float cameraLeftEdge = camera.position.x - screenWidth / 2f;

        for (BaseObstacle obstacle : obstacleFactory.getAllInUseObstacles()) {

            if (obstacle instanceof HomingMissile missile) {
                missile.setTarget(player);
                missile.update(delta);
            }

            if (obstacle.isOffScreenCamera(cameraLeftEdge)) {
                obstacleFactory.releaseObstacle(obstacle);
            }
        }
    }

    private void spawnObstacle() {
        DifficultyStrategy ds = gameManager.getDifficultyStrategy();

        float cameraRightEdge = camera.position.x + screenWidth / 2f;
        float spawnAhead = cameraRightEdge + 300f;

        float spawnAfterLast = lastObstacleSpawnX + ds.getMinGap();
        float baseSpawnX = Math.max(spawnAhead, spawnAfterLast);

        int density = ds.getDensity();

        for (int i = 0; i < density; i++) {
            float spawnX = baseSpawnX + i * 250f;
            obstacleFactory.createRandomObstacle(ground.getTopY(), spawnX, player.getHeight());
            lastObstacleSpawnX = spawnX;
        }
    }

    private void checkCollisions() {

        Rectangle collider = player.getCollider();

        for (BaseObstacle obstacle : obstacleFactory.getAllInUseObstacles()) {

            if (obstacle.isColliding(collider)) {
                System.out.println("========================================");
                System.out.println("              GAME OVER");
                System.out.println("         Press SPACE to restart");
                System.out.println("========================================");

                player.die();
                return;
            }
        }
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        spriteBatch.dispose();
        obstacleFactory.releaseAllObstacles();
        scoreUIObserver.dispose();
        background.dispose();
    }
}
