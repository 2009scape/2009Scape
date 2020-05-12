package org.crandor.game.node.item;

import org.crandor.tools.RandomFunction;

public class WeightedChanceItem {
    int id,minimum_amount,maximum_amount;
    public int weight;

    public WeightedChanceItem(int id, int minimum_amount, int maximum_amount, int weight){
        this.id = id;
        this.minimum_amount = minimum_amount;
        this.maximum_amount = maximum_amount;
        this.weight = weight;
    }

    public WeightedChanceItem(int id, int amount, int weight){
        this(id,amount,amount,weight);
    }

    public Item getItem(){
        return new Item(this.id, RandomFunction.random(this.minimum_amount, this.maximum_amount));
    }
}
