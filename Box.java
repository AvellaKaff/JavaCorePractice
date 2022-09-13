package main.java.lesson3.fruits;

import java.util.ArrayList;

public class Box <T extends Fruit> extends ArrayList {

    private float boxWeight;

    boolean compare(Box <?> another) {
        boolean c;
        if (this.boxWeight == another.boxWeight){
            c = true;
        } else c = false;
        return c;
    }

    float getWeight(Fruit fruit){
        boxWeight = this.size() * fruit.getWheight();
        return boxWeight;
    }

    void pourOver (Box<T> box){
        this.addAll(box);
        box.clear();
    }
}
