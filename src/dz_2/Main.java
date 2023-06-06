package dz_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    /*
    Допустим, в программе есть класс Кот. У кота есть кличка и аппетит (сколько единиц еды он потребляет за приём
пищи). Эти поля мы заполняем с помощью конструктора. Также есть метод eat(), который заставляет
кота покушать, но пока что он пустой, так как неизвестно, откуда кот должен брать еду:

public class Cat {
    private String name;
    private int appetite;
    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }
    public void eat() { }
}
Чтобы можно было хранить еду, создадим класс Тарелка с полем food (еда измеряется в целых
числах и не важно, что это за единицы измерения). При создании тарелки мы можем указать
начальное значение food. В процессе работы с помощью метода info() можно вывести в консоль
информацию о тарелке.

public class Plate {
    private int food;
    public Plate(int food) {
        this.food = food;
    }
    public void info() {
        System.out.println("plate: " + food);
    }
}
Если в методе main() создать объекты этих классов, то можно узнать информацию о тарелке и
вызвать пустой метод eat() у кота. Эти объекты пока никак не могут взаимодействовать между собой.
Можно добавить в классы геттеры и сеттеры и получить код вроде.

public class MainClass {
    public static void main(String[] args) {
        Cat cat = new Cat("Barsik", 5);
        Plate plate = new Plate(100);
        plate.info();
        cat.eat();
        plate.setFood(plate.getFood() - cat.getAppetite());
    }
}
ЗАДАНИЕ

Сделать так, чтобы в тарелке с едой не могло получиться отрицательного количества еды (например, в миске 10 еды, а кот пытается покушать 15-20).
Каждому коту нужно добавить поле сытость (когда создаем котов, они голодны). Если коту удалось покушать (хватило еды), сытость = true.
Считаем, что если коту мало еды в тарелке, то он её просто не трогает, то есть не может быть наполовину сыт (это сделано для упрощения логики программы).
Создать массив котов и тарелку с едой, попросить всех котов покушать из этой тарелки и потом вывести информацию о сытости котов в консоль.
Добавить в тарелку метод, с помощью которого можно было бы добавлять еду в тарелку.
     */

    public static void main(String[] args) {

        int casCounter = numGen();//генерация случайного количества котов
        System.out.println("Сгенерировано " + casCounter + " котиков");
        Cat[] cats = catGenerate(casCounter);
        System.out.println();

        Plate plate = new Plate(30);
        Plate plate1 = plate;
        System.out.println("В тарелке имеется еды: ");
        plate.info();
        System.out.println();

        System.out.println("Котики поели");
        Plate newPlate = information(cats, plate);
        System.out.println("После того как котики поели, осталось еды: ");
        newPlate.info();
        System.out.println();

        int eat = eatAdd(cats, plate1);
        int f = 0;
        while (eat - f > 0){
                System.out.println("Некоторые котики остались голодными, добавьте еды, не менее " + eat);
                System.out.print("> ");
                f = addedEat();
        }
        System.out.println("Спасибо, все котики сытые");

    }


    protected static int addedEat(){
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        return number;
    }

    //Метод кормит котов
    protected static Plate information (Cat[] cats, Plate plate){
        Cat[] newCats = cats;
        Plate newPlate = plate;

        for (int i = 0; i < newCats.length; i++){
            if(newPlate.getFood() >= newCats[i].getAppetite()) {
                newPlate.setFood(newPlate.getFood() - newCats[i].getAppetite());
                if (newCats[i].getAppetite() != 0){
                newCats[i].setAppetite(newCats[i].getAppetite() - newCats[i].getAppetite());
                newCats[i].setSatiety(true);
                } else newCats[i].setSatiety(false);
            }
        }

        for (Cat c : newCats){
            System.out.println(c);
        }
        System.out.println();
        return newPlate;
    }

    //Метод генерирует случайное количество котов
    protected static int numGen(){
        Random random = new Random();
        int num = random.nextInt(0, 10);
        return num;
    }

    //Метод выбирает случайное имя кота
    protected static String catName(){

        List<String> catNameList = new ArrayList<>();
        catNameList.add("Barsik");
        catNameList.add("Murzik");
        catNameList.add("Vaska");
        catNameList.add("Ryjick");
        catNameList.add("Tom");
        catNameList.add("Pushistick");
        catNameList.add("Tolstyack");
        catNameList.add("Chernish");
        catNameList.add("Garfield");
        catNameList.add("Leopold");

        Random random = new Random();
        int num = random.nextInt(0, catNameList.size());
        String nameCat = catNameList.get(num);

        return nameCat;
    }

    //Метод генерирует аппетит кота
   protected static int appetitGen(){
        Random random = new Random();
        int num = random.nextInt(0, 20);
        return num;
    }

    //Метод генерирует массив котов
    protected static Cat[] catGenerate (int cat){
        Cat[] cats = new Cat[cat];
        for (int i = 0; i < cat; i++){
            cats[i] = new Cat(catName(), appetitGen(), false);
        }
        for (Cat c : cats)
            System.out.println(c);
        return cats;
    }

    //Метод информирует о количестве оставшейся еды
    protected static int eatAdd (Cat[] cats, Plate plate){
        Cat[] newCats = cats;
        Plate newPlate = plate;
        int s = 0;
        for (int i = 0; i < newCats.length; i++){
            if(newPlate.getFood() >= newCats[i].getAppetite()) {
                newPlate.setFood(newPlate.getFood() - newCats[i].getAppetite());
                newCats[i].setAppetite(newCats[i].getAppetite() - newCats[i].getAppetite());
            } else s = s + newCats[i].getAppetite();
        }
        System.out.println("Нехватило " + s + " еды");
        return s;
    }
}