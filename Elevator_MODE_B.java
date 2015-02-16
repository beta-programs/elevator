package elevator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * MODE B: Acknowledge commands in sequence, Transporting more people at a time
 */
public class Elevator_MODE_B implements Elevator_MODE {
	private static Elevator_MODE_B instance = null;

	private Elevator_MODE_B() {
	}

	public static Elevator_MODE_B getInstance() {
		if (instance == null) {
			instance = new Elevator_MODE_B();
		}
		return instance;
	}

	private enum DIRECTION {
		UP, DOWN
	}

	@Override
	public int executeCommands(List<Integer> path, int st_flr, String[] cmd_list) {
		int tmp_st_flr, tmp_end_flr;
		int dist = 0;
		DIRECTION cur_dir = null, dir;
		String[] split;

		List<Integer> floorList = new ArrayList<Integer>();
		// add the starting floor
		path.add(st_flr);

		for (String cmd : cmd_list) {

			split = cmd.split("-");
			tmp_st_flr = Integer.parseInt(split[0]);
			tmp_end_flr = Integer.parseInt(split[1]);
			dir = getDirection(tmp_st_flr, tmp_end_flr);

			if (cur_dir == null || cur_dir == dir) {
				// add the floor
				addFloors(floorList, tmp_st_flr, tmp_end_flr);
				cur_dir = dir;
				continue;
			}

			// collected all floors - time to move elevator
			if (cur_dir == DIRECTION.DOWN) {
				// desc order
				Collections.sort(floorList, Collections.reverseOrder());
			} else {
				// asc order
				Collections.sort(floorList);
			}
			// move
			dist += travel(path, st_flr, floorList);
			if (!floorList.isEmpty()) {
				st_flr = floorList.get(floorList.size() - 1);
			}
			floorList.clear();

			// add the floor
			addFloors(floorList, tmp_st_flr, tmp_end_flr);
			cur_dir = dir;
		}

		// collected all floors - time to move elevator
		if (cur_dir == DIRECTION.DOWN) {
			// desc order
			Collections.sort(floorList, Collections.reverseOrder());
		} else {
			// asc order
			Collections.sort(floorList);
		}
		// move
		dist += travel(path, st_flr, floorList);

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
	 * @return int
	 */
	private int move(List<Integer> path, int st_flr, int end_flr) {
		if (end_flr == st_flr)
			return 0;
		path.add(end_flr);
		return Math.abs(end_flr - st_flr);
	}

	/**
	 * Returns the moving direction of elevator
	 * 
	 * @param st_flr starting floor / request from
	 * @param end_flr ending floor / request to
	 * @return distance travelled
	 */
	private DIRECTION getDirection(int st_flr, int end_flr) {
		return (end_flr - st_flr) > 0 ? DIRECTION.UP : DIRECTION.DOWN;
	}

}
