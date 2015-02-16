package elevator;

import java.util.ArrayList;
import java.util.List;

/**
 * MODE A: Transporting 1 person at a time
 */
public class Elevator_MODE_A implements Elevator_MODE {
	private static Elevator_MODE_A instance = null;

	private Elevator_MODE_A() {
	}

	public static Elevator_MODE_A getInstance() {
		if (instance == null) {
			instance = new Elevator_MODE_A();
		}
		return instance;
	}

	@Override
	public int executeCommands(List<Integer> path, int st_flr, String[] cmd_list) {
		int tmp_st_flr, tmp_end_flr;
		int dist = 0;
		List<Integer> floorList = new ArrayList<Integer>();
		// add the starting floor
		path.add(st_flr);

		for (String cmd : cmd_list) {

			String[] split = cmd.split("-");
			tmp_st_flr = Integer.parseInt(split[0]);
			tmp_end_flr = Integer.parseInt(split[1]);

			// collect floors
			addFloors(floorList, tmp_st_flr, tmp_end_flr);

			// move
			dist += travel(path, st_flr, floorList);
			if (!floorList.isEmpty()) {
				st_flr = floorList.get(floorList.size() - 1);
			}
			floorList.clear();
		}
		return dist;
	}

	private void addFloors(List<Integer> stops, int st_flr, int end_flr) {
		stops.add(st_flr);
		stops.add(end_flr);
	}

	private int travel(List<Integer> path, int st_flr, List<Integer> stops) {
		int tmp_st_flr = st_flr;
		int dist = 0;
		for (Integer stop : stops) {
			dist += move(path, tmp_st_flr, stop);
			tmp_st_flr = stop;
		}
		return dist;
	}

	/**
	 * Returns the distance covered by the elevator
	 * 
	 * @param st_flr starting floor / request from
	 * @param end_flr ending floor / request to
	 * @return distance travelled
	 */
	private int move(List<Integer> path, int st_flr, int end_flr) {
		if (end_flr == st_flr)
			return 0;
		path.add(end_flr);
		return Math.abs(end_flr - st_flr);
	}
}
