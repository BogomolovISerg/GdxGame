package com.gdxgame.app.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.gdxgame.app.game.helpers.Poolable;
import com.gdxgame.app.screen.utils.Assets;

public class Bonus implements Poolable {
    public BonusType type;
    private GameController gc;
    private Vector2 position;
    private boolean active;
    private TextureRegion texture;
    private Circle hitArea;

    public Bonus(GameController gameController, BonusType type, float x, float y){
        this.gc = gameController;
        this.position = new Vector2();
        this.type = type;
        this.active = false;
        this.hitArea = new Circle(0, 0, 0);
        this.position = new Vector2(x, y);
    }

    public void activate() {
        active = true;
        if(type == BonusType.Bullets)
            this.texture = Assets.getInstance().getAtlas().findRegion("bullets");
        else if(type == BonusType.MedicalKit)
            this.texture = Assets.getInstance().getAtlas().findRegion("medickit");
        else if(type == BonusType.Currency)
            this.texture = Assets.getInstance().getAtlas().findRegion("many");
    }

    public Circle getHitArea() {
        return hitArea;
    }

    private void deactivate() {
        active = false;
    }

    public Vector2 getPosition() {
        return position;
    }

    @Override
    public boolean isActive() {
            return active;
    }
}
