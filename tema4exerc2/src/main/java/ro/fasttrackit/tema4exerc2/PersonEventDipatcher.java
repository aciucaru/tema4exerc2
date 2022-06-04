package ro.fasttrackit.tema4exerc2;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;

public class PersonEventDipatcher
{
	// 'logicalSwitch' este un Map ce are ca si chei un predicat si ca valori o functie.
	// Predicate este o interfata functionala ce testeaza o conditie.
	// In functie de rezultatul testului dintr-un obiect Predicate, se va rula sau nu functia (Function) asociata
	// acelui predicat, din Map-ul de mai jos. Rularea conditionata a logicii din obiectul Function asociat
	// cheii se face cu ajutorul stream-urilor aplicate acelui Map (Map-ul in sine nu face nimic).
	// Interfata Function<T, R> are o metoda apply() ce primeste argument de tip T si returneaza raspuns de tip R
    private final Map< Predicate<Person>, Function<Person, String> > logicalSwitch = new HashMap<>();

    public PersonEventDipatcher()
    {
    	// aceasta metoda genereaza perechi de predicat si functie asociata predicatului;
    	// functia asociata unui predicat (test) va fi apelata doar daca testul respectiv returneaza adevarat
    	// predicatele si functiile s-au scris separat, ca sa fie mai usor de inteles cea ce se scrie
    	
    	// primele doua predicate (teste) sunt pentru nume:
    	// daca numele este sub 'L', atunci persoana este programata mai repede la examene/teste, adica in urmatoare saptamana
    	// daca numele este peste 'L', atunci persoana este programata la examene/teste peste doua saptamani
    	Predicate<Person> nameFirstLetterTest1 = person -> person.name().substring(0, 1).compareToIgnoreCase("L") <= 0;
    	Function<Person, String> function1 = person -> "Name is bellow L, I will start exams next week";
        logicalSwitch.put(nameFirstLetterTest1, function1);
        
    	Predicate<Person> nameFirstLetterTest2 = person -> person.name().substring(0, 1).compareToIgnoreCase("L") > 0;
    	Function<Person, String> function2 = person -> "Name is above L, I will start exams after 2 weeks";
        logicalSwitch.put(nameFirstLetterTest2, function2);
        
        
        // urmatoarele doua predicate (teste) sunt pentru varsta:
        // daca persoana are sub 19 ani, inseamna ca este la scoala si da teste
        // daca persoana are peste 19 ani, inseamna ca este la facultate si da examene
    	Predicate<Person> ageTest1 = person -> person.age() < 19;
    	Function<Person, String> function3 = person -> "I have school tests";
        logicalSwitch.put(ageTest1, function3);
        
    	Predicate<Person> ageTest2 = person -> person.age() >= 19;
    	Function<Person, String> function4 = person -> "I have university exams";
        logicalSwitch.put(ageTest2, function4);
        
        
        // urmatoarele doua predicate (teste) sunt pentru adresa:
        // daca persoana locuieste in Bucurest, inseamna ca este din capitala
        // altfel, inseamna ca este din provincie
    	Predicate<Person> addressTest1 = person -> person.address().equalsIgnoreCase("Bucuresti");
    	Function<Person, String> function5 = person -> "I live in the capital";
        logicalSwitch.put(addressTest1, function5);
        
    	Predicate<Person> addressTest2 = person -> !person.address().equalsIgnoreCase("Bucuresti");
    	Function<Person, String> function6 = person -> "I live in the province";
        logicalSwitch.put(addressTest2, function6);
    }
    
    //< Entry<  Predicate<Person>, Function<Person, String>  > > 

    public void dipatch(Person person)
    {
    	if(person != null)
    	{
    		String message = person.toString() + ":\n";
    		
    		// mai intai se transforma Map-ul intr-un Set cu metoda entrySet()
    		// metoda entrySet() transforma un Map<K,V> intr-un Set<Entry<K,V>>
            String dispatchResult = logicalSwitch.entrySet().stream()
            		// se plica filtru pe elementele Set-ului obtinut (adica 'entry');
            		// functia de filtrare se gaseste chiar in fiecare 'entry', ea este chiar cheia Map-ului
            		// cheia se obtine cu getKey()
	                .filter(entry -> entry.getKey().test(person))
	                // in acest punct au ajuns doar elementele care au trecut de filtrare
	                // aceste elemente se convertesc din Entry<K,V> in String, pt. a putea fi afisate
	                // convertirea se face cu map() si metoda apply() a valorii ce se gaseste in fiecare 'entry'
	                .map(entry -> "   " + entry.getValue().apply(person))
	                .collect(joining(lineSeparator()));
            
            // String-ul obtinut din 'dispatchResult' se adauga la cel original si se printeaza
            message = message.concat(dispatchResult + "\n");
            System.out.println(message);
    	}
    }
}
