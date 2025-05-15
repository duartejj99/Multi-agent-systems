balls:
	javac -cp ./lib/gui.jar:./src/ -d out/ ./src/balls/TestBallsSimulator.java 

balls_exec:
	java -cp ./lib/gui.jar:./out/ balls.TestBallsSimulator
