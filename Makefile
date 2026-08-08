balls:
	javac -cp ./lib/gui.jar:./src/ -d out/ ./src/balls/TestBallsSimulator.java 

balls_exec:
	java -cp ./lib/gui.jar:./out/ balls.TestBallsSimulator

game_of_life:
	javac -cp ./lib/gui.jar:./src/ -d out/ ./src/gameoflife/TestGameOfLifeSimulator.java 

game_of_life_exec:
	java -cp ./lib/gui.jar:./out/ gameoflife.TestGameOfLifeSimulator

immigration_game:
	javac -cp ./lib/gui.jar:./src/ -d out/ ./src/immigrationgame/TestImmigrationGameSimulator.java

immigration_game_exec:
	java -cp ./lib/gui.jar:./out/ immigrationgame.TestImmigrationGameSimulator

schelling_model:
	javac -cp ./lib/gui.jar:./src/ -d out/ ./src/schelling/TestSchellingModelSimulator.java

schelling_model_exec:
	java -cp ./lib/gui.jar:./out/ schelling.TestSchellingModelSimulator
clean:
	rm -rf bin
