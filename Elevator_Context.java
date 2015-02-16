package elevator;

import java.util.ArrayList;
import java.util.List;

class Elevator_Context {
    private final List<Integer> path;
    private int distance;
    private final Elevator_MODE elevator_mode;

    public Elevator_Context(Elevator_MODE mode) {
        this.elevator_mode = mode;
        this.path = new ArrayList<Integer>();
    }

    public void executeCommands(int st_flr, String[] cmd_list) {
        this.distance = this.elevator_mode.executeCommands(this.path, st_flr,
                cmd_list);
    }

    public void flushPath() {
        this.path.clear();
    }

    public List<Integer> getPath() {
        return path;
    }

    public int getDistance() {
        return distance;
    }
}