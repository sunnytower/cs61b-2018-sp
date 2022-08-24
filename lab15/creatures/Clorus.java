package creatures;

import huglife.*;

import java.awt.*;
import java.util.Map;
import java.util.List;
public class Clorus extends Creature {
    private int r;
    private int g;
    private int b;
    private static final double moveEnergyLost = 0.03;
    private static final double stayEnergyLost = 0.01;
    public Clorus(double energy) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        this.energy = energy;
    }
    public Clorus() {
        this(1);
    }

    @Override
    public Color color() {
        return color(r, g, b);
    }

    @Override
    public void attack(Creature c) {
        energy += c.energy();
    }

    @Override
    public void move() {
        energy -= moveEnergyLost;
    }

    @Override
    public Clorus replicate() {
        energy /= 2;
        return new Clorus(energy);
    }

    @Override
    public void stay() {
        energy -= stayEnergyLost;
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        List<Direction> plip = getNeighborsOfType(neighbors, "plip");
        if (empties.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        } else if (!plip.isEmpty()) {
            Direction moveDir = HugLifeUtils.randomEntry(plip);
            return new Action(Action.ActionType.ATTACK, moveDir);
        } else if (energy >= 1) {
            Direction moveDir = HugLifeUtils.randomEntry(empties);
            return new Action(Action.ActionType.REPLICATE, moveDir);
        } else {
            Direction moveDir = HugLifeUtils.randomEntry(empties);
            return new Action(Action.ActionType.MOVE, moveDir);
        }
    }
}
