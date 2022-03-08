package com.gdxgame.app.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.gdxgame.app.screen.ScreenManager;

public class GameController {
    private BackgroundGdx background;
    private BulletController bulletController;
    private RockController rockController;
    private Hero hero;
    private Vector2 tempVec;
    private ParticleController particleController;

    public ParticleController getParticleController() {
        return particleController;
    }

    public RockController getAsteroidController() {
        return rockController;
    }

    public BulletController getBulletController() {
        return bulletController;
    }

    public BackgroundGdx getBackground() {
        return background;
    }

    public Hero getHero() {
        return hero;
    }

    public GameController() {
        this.background = new BackgroundGdx(this);
        this.bulletController = new BulletController(this);
        this.rockController = new RockController(this);
        this.particleController = new ParticleController();
        this.hero = new Hero(this);
        this.tempVec = new Vector2();

        for (int i = 0; i < 3; i++) {
            rockController.setup(MathUtils.random(0, ScreenManager.SCREEN_WIDTH),
                    MathUtils.random(0, ScreenManager.SCREEN_HEIGHT),
                    MathUtils.random(-150, 150), MathUtils.random(-150, 150), 1.0f);
        }
    }

    public void update(float dt) {
        background.update(dt);
        bulletController.update(dt);
        rockController.update(dt);
        particleController.update(dt);
        hero.update(dt);
        checkCollisions();
    }

    public void checkCollisions() {
        for (int i = 0; i < rockController.getActiveList().size(); i++) {
            Rock a = rockController.getActiveList().get(i);
            if (hero.getHitArea().overlaps(a.getHitArea())) {
                float dst = a.getPosition().dst(hero.getPosition());
                float halfOverLen = (a.getHitArea().radius + hero.getHitArea().radius - dst) / 2.0f;
                tempVec.set(hero.getPosition()).sub(a.getPosition()).nor();
                hero.getPosition().mulAdd(tempVec, halfOverLen);
                a.getPosition().mulAdd(tempVec, -halfOverLen);

                float sumScl = hero.getHitArea().radius * 2 + a.getHitArea().radius;
                hero.getVelocity().mulAdd(tempVec, 200.0f * a.getHitArea().radius / sumScl);
                a.getLastDisplacement().mulAdd(tempVec, -200.0f * hero.getHitArea().radius / sumScl);

                if (a.takeDamage(2)) {
                    hero.addScore(a.getHpMax() * 50);
                }
                hero.takeDamage(2);
            }
        }

        for (int i = 0; i < bulletController.getActiveList().size(); i++) {
            Bullet b = bulletController.getActiveList().get(i);
            for (int j = 0; j < rockController.getActiveList().size(); j++) {
                Rock a = rockController.getActiveList().get(j);
                if (a.getHitArea().contains(b.getPosition())) {
                    particleController.setup(b.getPosition().x + MathUtils.random(-4, 4), b.getPosition().y + MathUtils.random(-4, 4),
                            b.getVelocity().x * -0.3f + MathUtils.random(-30, 30), b.getVelocity().y * -0.3f + MathUtils.random(-30, 30),
                            0.2f,
                            2.5f, 1.2f,
                            1.0f, 1.0f, 1.0f, 1.0f,
                            0.0f, 0.1f, 1.0f, 0.0f);

                    b.deactivate();
                    if (a.takeDamage(1)) {
                        hero.addScore(a.getHpMax() * 100);
                    }
                    break;
                }
            }
        }
    }
}
