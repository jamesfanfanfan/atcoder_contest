package TestAbstract;

public class Dog extends Animal implements bark{
//    @Override
//    public void eat() {
//        super.eat();
//    }


    @Override
    public void sound() {
        System.out.println("ddd");
    }

    public static void main(String[] args) {
        Student s = new Student("aba");
        
    }
}
