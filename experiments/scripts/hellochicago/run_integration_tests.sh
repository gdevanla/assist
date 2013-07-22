assist_dependencies="/Users/gdevanla/.m2/repository/com/thoughtworks/xstream/xstream/1.4.4/xstream-1.4.4.jar:\
/Users/gdevanla/.m2/repository/commons-configuration/commons-configuration/1.6/commons-configuration-1.6.jar:\
/Users/gdevanla/.m2/repository/commons-lang/commons-lang/2.6/commons-lang-2.6.jar:\
/Users/gdevanla/.m2/repository/commons-logging/commons-logging/1.1.1/commons-logging-1.1.1.jar:\
/Users/gdevanla/.m2/repository/commons-collections/commons-collections/3.2.1/commons-collections-3.2.1.jar:\
/Users/gdevanla/.m2/repository/commons-io/commons-io/2.4/commons-io-2.4.jar:\
/Users/gdevanla/.m2/repository/soot/soot/2.5.0-myversion/soot-2.5.0-myversion.jar:\
/Users/gdevanla/.m2/repository/soot/soot/2.5.0-myversion/soot-2.5.0-myversion.jar:\
/Users/gdevanla/.m2/repository/com/google/guava/guava/14.0.1/guava-14.0.1.jar"


assist_app=/Users/gdevanla/Dropbox/private/se_research/myprojects/assist/apps/StateCarver/assist/target/classes

other_dependencies=/Users/gdevanla/.m2/repository/junit/junit/3.8.1/junit-3.8.1.jar

app_classpath=/Users/gdevanla/Dropbox/private/se_research/myprojects/assist/apps/StateCarver/HelloChicago/target/classes/

#these will be the new tests
app_intg_test_classpath=/Users/gdevanla/Dropbox/private/se_research/myprojects/assist/experiments/results/hellochicago/integration_tests

command_with_arguments="junit.textui.TestRunner "

cp=$assist_dependencies:$assist_app:$app_classpath:$other_dependencies:$app_intg_test_classpath
for i in `find $app_intg_test_classpath -name '*Test.class' | sed -e "s/.*\///g" -e "s/.class//g"`
do
java -cp $cp $command_with_arguments $i
done
