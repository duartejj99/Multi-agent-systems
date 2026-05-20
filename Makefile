balls:
	javac -cp ./lib/gui.jar:./src/ -d out/ ./src/balls/TestBallsSimulator.java 

balls_exec:
	java -cp ./lib/gui.jar:./out/ balls.TestBallsSimulator

game_of_life:
	javac -cp ./lib/gui.jar:./src/ -d out/ ./src/gameoflife/TestGameOfLifeSimulator.java 

game_of_life_exec:
	java -cp ./lib/gui.jar:./out/ gameoflife.TestGameOfLifeSimulator

clean:
	rm -rf bin
