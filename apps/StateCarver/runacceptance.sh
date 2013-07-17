
assist_dependencies="/Users/gdevanla/.m2/repository/com/thoughtworks/xstream/xstream/1.4.4/xstream-1.4.4.jar:\
/Users/gdevanla/.m2/repository/commons-configuration/commons-configuration/1.6/commons-configuration-1.6.jar:\
/Users/gdevanla/.m2/repository/commons-lang/commons-lang/2.6/commons-lang-2.6.jar:\
/Users/gdevanla/.m2/repository/commons-logging/commons-logging/1.1.1/commons-logging-1.1.1.jar:\
/Users/gdevanla/.m2/repository/commons-collections/commons-collections/3.2.1/commons-collections-3.2.1.jar:\
/Users/gdevanla/.m2/repository/commons-io/commons-io/2.4/commons-io-2.4.jar"

assist_app="/Users/gdevanla/Dropbox/private/se_research/myprojects/assist/apps/StateCarver/assist/target/classes"

app_classpath="/tmp/sootOutput"
command_with_arguments="com.ser.statecarver.testartifacts.TestPojo"

cp=$assist_dependencies:$assist_app:$app_classpath
echo $cp
java -cp $cp $command_with_arguments
