package elevator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * <pre>
 * 
 * TEST INPUT: 
 * 
 * 10:8-1
 * 9:1-5,1-6,1-5
 * 2:4-1,4-2,6-8
 * 3:7-9,3-7,5-8,7-11,11-1
 * 7:11-6,10-5,6-8,7-4,12-7,8-9
 * 6:1-8,6-8
 * 
 * TEST OUTPUT: 
 * 
 * *********** MODE A *************
 * 10 8 1 (9)
 * 9 1 5 1 6 1 5 (30)
 * 2 4 1 4 2 6 8 (16)
 * 3 7 9 3 7 5 8 7 11 1 (36)
 * 7 11 6 10 5 6 8 7 4 12 7 8 9 (40)
 * 6 1 8 6 8 (16)
 * 
 * *********** MODE B **************
 * 10 8 1 (9)
 * 9 1 5 6 (13)
 * 2 4 2 1 6 8 (12)
 * 3 5 7 8 9 11 1 (18)
 * 7 11 10 6 5 6 8 12 7 4 8 9 (30)
 * 6 1 6 8 (12)
 * 
 * </pre>
 */

public class Elevator {

	public static void main(String[] args) {

		// long st = new java.util.Date().getTime();
		// System.out.println("Start: " + new java.util.Date());
		String file_name = null;
		String mode = null;
		if (args.length < 2) {
			System.out.println("usage: java Elevator <file_name> <mode[A|B]>");
			System.exit(1);
		} else {
			file_name = args[0];
			mode = args[1];
		}

		if (mode == null
				|| !(mode.equalsIgnoreCase("A") || mode.equalsIgnoreCase("B"))) {
			System.out.println("Error! Invalid MODE(" + mode
					+ "). Valid modes: 'A', 'B'");
			System.exit(1);
		}
		File file = new File(file_name);
		if (!file.exists()) {
			System.out.println("Error! File Doesn't exist. File_name: "
					+ file_name);
			System.exit(1);
		}

		BufferedReader bf = null;
		try {
			Elevator_Context elev;
			if (mode.equalsIgnoreCase("A")) {
				elev = new Elevator_Context(Elevator_MODE_A.getInstance());
				System.out.println("*********** Mode A ***********");
			} else { // default
				elev = new Elevator_Context(Elevator_MODE_B.getInstance());
				System.out.println("*********** Mode B ***********");
			}

			bf = new BufferedReader(new FileReader(file));
			String line;
			int st_floor, col_idx;
			String[] floor_cmds;
			StringBuilder outputStr = new StringBuilder(256);
			List<Integer> path;
			int tot_dist;
			while ((line = bf.readLine()) != null) {

				/* Parse Input */
				col_idx = line.indexOf(":");
				if (col_idx <= 0)
					continue;
				st_floor = Integer.parseInt(line.substring(0, col_idx));

				floor_cmds = line.substring(col_idx + 1).split(",");

				/* Execute commands */
				elev.executeCommands(st_floor, floor_cmds);
				path = elev.getPath();
				tot_dist = elev.getDistance();

				/* Print output */
				for (Integer flr : path) {
					outputStr.append(flr).append(" ");
				}
				outputStr.append("(").append(tot_dist).append(")");
				System.out.println(outputStr.toString());

				// reset
				elev.flushPath();
				outputStr.setLength(0);
			} // end of file
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bf != null)
					bf.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		// System.out.println("Start: " + new java.util.Date(st));
		// System.out.println("End: " + new java.util.Date());
	}
}
