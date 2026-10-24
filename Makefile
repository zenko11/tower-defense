JFLAGS = -d classes -sourcepath src

.PHONY: all tests runtests clean towerdefense_a_random towerdefense_b_random towerdefense_a_interactive towerdefense_b_interactive

all: docs classes jar runtests 

classes:
	@mkdir -p classes
	javac $(JFLAGS) $$(find src -name "*.java")


docs:
	@mkdir -p docs
	javadoc -d docs -sourcepath src -subpackages game


jar: classes
	@mkdir -p jar
	jar cfe jar/towerdefense_b_random.jar game.Towerdefense_b_random -C classes .
	jar cfe jar/towerdefense_a_interactive.jar game.Towerdefense_a_interactive -C classes .
	jar cfe jar/towerdefense_b_interactive.jar game.Towerdefense_b_interactive -C classes .
	jar cfe jar/towerdefense_a_random.jar game.Towerdefense_a_random -C classes .

tests: classes
	@mkdir -p classes/tests
	javac -classpath junit-console.jar:classes $$(find tests -name "*.java") -d classes/tests 

runtests: tests
	java -jar junit-console.jar -classpath classes/tests:classes -scan-classpath


towerdefense_a_random: jar
	java -jar jar/towerdefense_a_random.jar 10 10 5 

towerdefense_b_random: jar
	java -jar jar/towerdefense_b_random.jar 10 10 5 

towerdefense_a_interactive: jar
	java -jar jar/towerdefense_a_interactive.jar 10 10 20

towerdefense_b_interactive: jar
	java -jar jar/towerdefense_b_interactive.jar 10 10 4 20

clean:
	rm -rf classes
	rm -rf docs
	rm -rf jar

