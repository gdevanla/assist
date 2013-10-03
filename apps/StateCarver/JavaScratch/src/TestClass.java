import com.thoughtworks.xstream.XStream;

import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;

class Person {
    int age=10;
    String name = "MyPersonName";

    public int addAge(int newAge){
        age = newAge;
        return age;
    }
}

public class TestClass{

    public void testMethod() throws IOException, ClassNotFoundException {
        XStream xstream = new XStream();
        ObjectInputStream input = xstream.createObjectInputStream(new FileReader("/tmp/testPerson.xml"));
        Object o = input.readObject();
        System.out.println(o);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        TestClass c = new TestClass();
        c.testMethod();
    }

}
