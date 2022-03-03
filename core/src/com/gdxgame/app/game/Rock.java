package com.gdxgame.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Rock {
    private GameController gc;
    private Texture texture;
    private Vector2 position;
    private Vector2 lastDisplacement;
    private float angle;

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getLastDisplacement() {
        return lastDisplacement;
    }

    public float getAngle() {
        return angle;
    }

    public Rock() {
        this.texture = new Texture("asteroid.png");
        this.position = new Vector2(300, 300);
        this.lastDisplacement = new Vector2(0, 0);
        this.angle = 0.0f;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - 32, position.y - 32, 32, 32,
                64, 64, 1, 1, angle, 0, 0, 256, 256,
                false, false);
    }

    public void update(float dt) {
        angle += 20 * dt;
        if (angle > 360) angle = 0;

        position.x += MathUtils.cosDeg(160) * 100f * dt;
        position.y += MathUtils.sinDeg(160) * 100f * dt;
        lastDisplacement.set(MathUtils.cosDeg(160) * 100f * dt, MathUtils.sinDeg(160) * 100f * dt);

        checkBorders();
    }

    public void checkBorders() {
        if (position.x < -160) {
            position.x = ScreenManager.SCREEN_WIDTH;
        }
        if (position.x > ScreenManager.SCREEN_WIDTH) {
            position.x = -160f;
        }
        if (position.y < -160) {
            position.y = ScreenManager.SCREEN_HEIGHT;
        }
        if (position.y > ScreenManager.SCREEN_HEIGHT) {
            position.y = -160f;
        }
    }
}
