package elevator;

import java.util.List;

interface Elevator_MODE {
	int executeCommands(List<Integer> path, int st_flr,
			String[] cmd_list);
}
