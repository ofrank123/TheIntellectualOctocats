JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Cactus.java \
	CactusHandler.java \
	Display.java \
	Entity.java \
	Player.java \
	Run.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
