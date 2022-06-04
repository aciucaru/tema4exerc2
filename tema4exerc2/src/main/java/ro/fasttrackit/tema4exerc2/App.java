package ro.fasttrackit.tema4exerc2;

/*
Creeaza un obiect Person(String name,int age, String address).
Creeaza un obiect logicalSwitch care simuleaza un switch logic folosind Predicate. Astfel vom avea un Map care
are ca si cheie un Predicate<Person> si valoare Function<Person, String>.
Astfel vom putea procesa o persoana si se vor aplica toate entry-urile care se aplica persoanei.
Testati aplicatia.
 */

import java.util.List;
import java.util.ArrayList;

public class App 
{
    public static void main( String[] args )
    {
    	System.out.println("Person dispatcher");
    	System.out.println();
    	
    	// se creeaza si se populeaza o lista de obiecte Person
    	List<Person> persons = new ArrayList<Person>();
        PersonEventDipatcher dispatcher = new PersonEventDipatcher();
        persons.add(new Person("George Filip", 10, "Baia Mare"));
        persons.add(new Person("Andrei Balea", 30, "Cluj-Napoca"));
        persons.add(new Person("Vasile Baciut", 9, "Bucuresti"));
        persons.add(new Person("Valentina Caldararu", 25, "Deva"));
        persons.add(new Person("Marius Ciobanu", 28, "Bucuresti"));
        
        // pentru fiecare Person se aplica dipatch, adica un fel de switch logic
        for(Person person : persons)
        {
        	dispatcher.dipatch(person);
        }
    }
}
