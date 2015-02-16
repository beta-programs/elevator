#elevator
Elevator problem with modes

Summary:
------------------------------------------------------------------------------------------
A Building with 12 floors and 1 elevator. based on the set of input commands and mode enlist the route followed by the elevator and the total distance travelled. The distance travelled is the difference between start and end floor.

Usage:
------------------------------------------------------------------------------------------
<p>
command: <em><b>Elevator {file_name} {mode[A|B]}</b></em><br/>
<br/>
<em><b>{file_name}:</b></em> The input file with complete path<br/>
<em><b>{mode[A|B]}:</b></em> Modes to run the elevator in eg. <b>'A', 'B'</b><br/>
<br/>
The input file provides the starting floor and the list of commands (requests for elevator floors (from & to).
Each line of input is independent event with first number indicating the elevators starting floor followed by the floor requests. <em><b>[{start_floor}-{end_floor}]</b></em> indicates a request/command for the elevator from <em><b>{start_floor}</b></em> to <em><b>{end_floor}</b></em>.
</p>

<em><b>INPUT FORMAT</b></em><br/>
<em><b>{start_floor}:{start_floor}-{end_floor}[,{start_floor}-{end_floor}]</b></em><br/>
<em><b>{start_floor}:{start_floor}-{end_floor}[,{start_floor}-{end_floor}]</b></em><br/>
<em><b>...</b></em><br/>
<br/>
<em><b> OUTPUT FORMAT</b></em><br/>
<br/>
********************* <em><b>{mode[A|B]}</b></em> ********************* <br/>
<em><b>{floor}[ {floor}] \({distance_travelled}\)</b></em><br/>
<em><b>{floor}[ {floor}] \({distance_travelled}\)</b></em><br/>
<em><b>...</b></em><br/>

Modes:
------------------------------------------------------------------------------------------
<p>
<b>MODE A:</b><br/>
Allows transporting one person at a time. Elevator takes request and handles it.
</p>
<p>
<b>MODE B:</b><br/>
Acknowledges commands in sequence, transporting more people at a time. If consecutive request to travel in same direction are received, the elevator can handle them all in one trip.
</p>

Sample:
------------------------------------------------------------------------------------------
<b>INPUT: <em>Elevator elevator_test.txt A</em></b><br/>
<br/>
10:8-1<br/>
9:1-5,1-6,1-5<br/>
2:4-1,4-2,6-8<br/>
3:7-9,3-7,5-8,7-11,11-1<br/>
7:11-6,10-5,6-8,7-4,12-7,8-9<br/>
6:1-8,6-8<br/>
4:5-9,7-8,7-5,8-4,10-3<br/>
<br/>
<b>OUTPUT:</b><br/>
<br/>
*********** MODE A ************* <br/>
10 8 1 (9)<br/>
9 1 5 1 6 1 5 (30)<br/>
2 4 1 4 2 6 8 (16)<br/>
3 7 9 3 7 5 8 7 11 1 (36)<br/>
7 11 6 10 5 6 8 7 4 12 7 8 9 (40)<br/>
6 1 8 6 8 (16)<br/>
4 5 9 7 8 7 5 8 4 10 3 (31)<br/>
<br/>
<b>INPUT: <em>Elevator elevator_test.txt B</em></b><br/>
<br/>
<b>OUTPUT:</b><br/>
<br/>
*********** MODE B ************** <br/>
10 8 1 (9)<br/>
9 1 5 6 (13)<br/>
2 4 2 1 6 8 (12)<br/>
3 5 7 8 9 11 1 (18)<br/>
7 11 10 6 5 6 8 12 7 4 8 9 (30)<br/>
6 1 6 8 (12)<br/>
4 5 7 8 9 10 8 7 5 4 3 (13)<br/>
