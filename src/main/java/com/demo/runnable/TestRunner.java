package com.demo.runnable;

import com.demo.model.TestObject;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

// Mit @RequestScope ist die Instanz weg, sobald das Servlet beendet ist.
// @Dependent sorgt dafür, dass die Instanz solange gehalten wird, wie sie benötigt wird und erstellt bei jedem Aufruf eine neue Instanz
@Dependent
public class TestRunner implements Runnable{


    private TestObject testObject;


    @Inject
    public TestRunner(TestObject testObject) {
        this.testObject = testObject;
    }

    @PostConstruct
    public void init(){
        System.out.println("PostConstruct called");
    }

    @Override
    public void run() {
        System.out.println("run called");
        System.out.println(testObject.getUuid());
        System.out.println(testObject.getNumber1());
        System.out.println(testObject.getNumber2());

        for (int i = 0; i < 100; i++) {
            System.out.println("i = " + i);
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public TestObject getTestObject() {
        return testObject;
    }

    public void setNumber1(int number1) {
        this.testObject.setNumber1(number1);
    }

    public void setNumber2(int number2) {
        this.testObject.setNumber2(number2);
    }
}
