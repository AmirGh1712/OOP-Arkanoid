compile: bin
	javac -cp biuoop-1.4.jar:src -d bin src/*.java

run:
	java -cp biuoop-1.4.jar:resources:bin Ass7Game
    
bin:
	mkdir bin
    
jar:
	jar cfm ass7game.jar Mainfest.mf -C bin/ . -C resources/ .