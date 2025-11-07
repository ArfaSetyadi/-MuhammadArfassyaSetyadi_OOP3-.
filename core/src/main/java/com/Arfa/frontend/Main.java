package com.Arfa.frontend;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.Arfa.frontend.factories.ObstacleFactory;
import com.Arfa.frontend.obstacles.BaseObstacle;
import com.Arfa.frontend.obstacles.HomingMissile;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Main extends Game {

    private ShapeRenderer shapeRenderer;
    private Player player;
    private Ground ground;
    private GameManager gameManager;

    private ObstacleFactory obstacleFactory;
    private float obstacleSpawnTimer;
    private float lastObstacleSpawnX = 0f;

    private SpriteBatch batch;
    private BitmapFont font;
    private boolean gameOver;


    private static final float OBSTACLE_SPAWN_INTERVAL = 2.5f;
    private static final int OBSTACLE_DENSITY = 1;
    private static final float SPAWN_AHEAD_DISTANCE = 300f;
    private static final float MIN_OBSTACLE_GAP = 200f;

    private OrthographicCamera camera;
    private float cameraOffset = 0.2f;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(2f);

        gameManager = GameManager.getInstance();

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false);

        player = new Player(new Vector2(100, Gdx.graphics.getHeight() / 2f));
        ground = new Ground();

        obstacleFactory = new ObstacleFactory();
        obstacleSpawnTimer = 0f;

        gameManager.startGame();
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();
        update(delta);
        renderGame();
        renderHUD();
    }

    private void update(float delta) {
        boolean isFlying = Gdx.input.isKeyPressed(Input.Keys.SPACE);

        if (gameOver && isFlying) {
            resetGame();
            return;
        }


        player.update(delta, isFlying);
        player.checkBoundaries(ground, Gdx.graphics.getHeight());

        updateCamera();
        ground.update(camera.position.x);

        updateObstacles(delta);
        checkCollisions();

        gameManager.setScore((int) player.getDistanceTraveled());
    }

    private void renderGame() {
        ScreenUtils.clear(0.1f, 0.1f, 0.15f, 1);

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        ground.renderShape(shapeRenderer);
        player.renderShape(shapeRenderer);

        shapeRenderer.setColor(Color.RED);
        for (BaseObstacle obstacle : obstacleFactory.getAllInUseObstacles()) {
            obstacle.render(shapeRenderer);
        }

        shapeRenderer.end();
    }

    private void renderHUD() {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        font.draw(batch, "Score: " + gameManager.getScore(), camera.position.x - Gdx.graphics.getWidth()/2f + 40, Gdx.graphics.getHeight() - 40);
        if (gameOver) {
            font.draw(batch, "GAME OVER - Press SPACE to restart", camera.position.x - 200, camera.position.y + 20);
        }
        batch.end();
    }


    private void updateCamera() {
        float focus = player.getPosition().x + (cameraOffset * player.getWidth());
        camera.position.x = focus;
        camera.update();
    }

    private void updateObstacles(float delta) {
        obstacleSpawnTimer += delta;

        if (obstacleSpawnTimer >= OBSTACLE_SPAWN_INTERVAL) {
            spawnObstacle();
            obstacleSpawnTimer = 0f;
        }

        float cameraLeftEdge = camera.position.x - (Gdx.graphics.getWidth() / 2f);

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
        float cameraRightEdge = camera.position.x + (Gdx.graphics.getWidth() / 2f);
        float spawnAhead = cameraRightEdge + SPAWN_AHEAD_DISTANCE;
        float spawnAfterLast = lastObstacleSpawnX + MIN_OBSTACLE_GAP;
        float baseSpawnX = Math.max(spawnAhead, spawnAfterLast);

        for (int i = 0; i < OBSTACLE_DENSITY; i++) {
            float spawnX = baseSpawnX + (i * 250f);
            obstacleFactory.createRandomObstacle(ground.getTopY(), spawnX, player.getHeight());
            lastObstacleSpawnX = spawnX;
        }
    }

    private void checkCollisions() {
        Rectangle playerCollider = player.getCollider();

        for (BaseObstacle obstacle : obstacleFactory.getAllInUseObstacles()) {
            if (obstacle.isColliding(playerCollider)) {
                player.die();
                gameOver = true;
                return;
            }
        }
    }


    private void resetGame() {
        gameOver = false;
        player.reset();
        obstacleFactory.releaseAllObstacles();
        obstacleSpawnTimer = 0f;
        lastObstacleSpawnX = 0f;
        camera.position.x = 0;
        gameManager.setScore(0);
    }


    @Override
    public void dispose() {
        shapeRenderer.dispose();
        obstacleFactory.releaseAllObstacles();
        batch.dispose();
        font.dispose();
    }
}
