
STATECARVER_BASE=/Users/gdevanla/Dropbox/private/se_research/myprojects/assist/apps/StateCarver

CLASSPATH=/Users/gdevanla/fsf/soot/soot-2.5.0/lib/sootclasses-2.5.0-myversion.jar:/Users/gdevanla/fsf/soot/polyglot-1.3.5/lib/polyglot.jar:/Users/gdevanla/fsf/soot/jasmin-2.5.0/lib/jasminclasses-2.5.0.jar:/Users/gdevanla/fsf/soot/polyglot-1.3.5/lib/java_cup.jar

SOOT_CLASSPATH=.:$STATECARVER_BASE/src/test/java:/System/Library/Frameworks/JavaVM.framework/Classes/classes.jar:/Users/gdevanla/.m2/repository/junit/junit/3.8.2/junit-3.8.2.jar:$STATECARVER_BASE/TestArtifacts/src/main/java:/Users/gdevanla/.m2/repository/com/thoughtworks/xstream/xstream/1.4.4/xstream-1.4.4.jar:$STATECARVER_BASE/assist/src/main/java

echo $SOOT_CLASSPATH
java -cp $CLASSPATH  soot.Main -f c -cp $SOOT_CLASSPATH  TestSimpleReturn103
