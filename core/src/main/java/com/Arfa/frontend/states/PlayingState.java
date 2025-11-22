package com.Arfa.frontend.states;

import com.Arfa.frontend.*;
import com.Arfa.frontend.commands.JetpackCommand;
import com.Arfa.frontend.commands.RestartCommand;
import com.Arfa.frontend.factories.ObstacleFactory;
import com.Arfa.frontend.obstacles.BaseObstacle;
import com.Arfa.frontend.observers.ScoreUIObserver;
import com.Arfa.frontend.strategies.DifficultyStrategy;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class PlayingState implements GameState {

    private final GameStateManager gsm;

    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;

    private Player player;
    private Ground ground;
    private Background background;
    private GameManager gameManager;

    private ObstacleFactory obstacleFactory;
    private float obstacleSpawnTimer = 0f;
    private float lastObstacleSpawnX = 0f;

    private JetpackCommand jetpackCommand;
    private RestartCommand restartCommand;

    private ScoreUIObserver scoreUIObserver;

    private float screenWidth;
    private float screenHeight;

    public PlayingState(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    private void init() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        gameManager = GameManager.getInstance();
        DifficultyStrategy ds = gameManager.getDifficultyStrategy();

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

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
    public void update(float delta) {

        // Jetpack input
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            jetpackCommand.execute();
        }

        if (player.isDead()) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                gsm.set(new PlayingState(gsm));  // restart state
            }
            return;
        }

        // Main player update
        player.update(delta, false);

        background.update(player.getPosition().x);
        ground.update(player.getPosition().x);

        player.checkBoundaries(ground, screenHeight);

        updateObstacles(delta);
        checkCollisions();

        // Score update
        gameManager.setScore((int) player.getDistanceTraveled());
    }

    private void updateObstacles(float delta) {
        obstacleSpawnTimer += delta;

        float spawnInterval = gameManager.getDifficultyStrategy().getSpawnInterval();
        if (obstacleSpawnTimer >= spawnInterval) {
            spawnObstacle();
            obstacleSpawnTimer = 0f;
        }

        float cameraLeft = player.getPosition().x - screenWidth * 0.5f;

        for (BaseObstacle obstacle : obstacleFactory.getAllInUseObstacles()) {
            obstacle.update(delta, player);

            if (obstacle.isOffScreenCamera(cameraLeft)) {
                obstacleFactory.releaseObstacle(obstacle);
            }
        }
    }

    private void spawnObstacle() {
        float spawnX = player.getPosition().x + screenWidth;

        float minGap = gameManager.getDifficultyStrategy().getMinGap();
        spawnX = Math.max(spawnX, lastObstacleSpawnX + minGap);

        int density = gameManager.getDifficultyStrategy().getDensity();

        for (int i = 0; i < density; i++) {
            float offset = i * 250f;
            obstacleFactory.createRandomObstacle(ground.getTopY(), spawnX + offset, player.getHeight());
        }

        lastObstacleSpawnX = spawnX;
    }

    private void checkCollisions() {
        Rectangle collider = player.getCollider();

        for (BaseObstacle obs : obstacleFactory.getAllInUseObstacles()) {
            if (obs.isColliding(collider)) {
                player.die();
                gsm.set(new GameOverState(gsm));
                return;
            }
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        batch.setProjectionMatrix(batch.getProjectionMatrix());

        batch.begin();
        background.render(batch);
        batch.end();

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

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
        background.dispose();
        scoreUIObserver.dispose();
        obstacleFactory.releaseAllObstacles();
    }
}
