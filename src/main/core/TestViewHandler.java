package core;

public class TestViewHandler {
    private int state;
    private double time;
    public TestViewHandler(int s, double t) {
        state = s;
        time = t;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setTime(double time) {
        this.time = time;
    }
}
