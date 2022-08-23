package lab14;

import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {
    private int state;
    private int period;
    private double factor;

    public AcceleratingSawToothGenerator(int period, double factor) {
        state = 0;
        this.period = period;
        this.factor = factor;
    }

    @Override
    public double next() {
        state++;
        if (state % period == 0) {
            period *= factor;
            state = 0;
        }
        return normalize(state % period);
    }

    private double normalize(int state) {
        return (double) state / period * 2 - 1;
    }
}
