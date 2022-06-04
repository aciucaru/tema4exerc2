package ro.fasttrackit.tema4exerc2;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

public record Person(String name, int age, String address)
{
	// constructorul canonic poate fi personalizat la nevoie, (de ex. pentru protectie fata de argumente ilegale)
	public Person(String name, int age, String address)
	{
		if(name != null)
			this.name = name;
		else
			this.name = "n/a";
		
		if(age > 0)
			this.age = age;
		else
			this.age = 1;
		
		if(address != null)
			this.address = address;
		else
			this.address = "n/a";
	}
	
	// se suprascrie metoda toString() ca sa se poata afisa mai usor fiecare persoana
	@Override
	public String toString()
	{
		return this.name + ", " + this.age + ", " + this.address;
	}
	
	@Override
	public boolean equals(Object otherObj)
	{
		boolean areEqual = false;
		
		if(otherObj != null)
		{
			if(otherObj instanceof Person)
			{
				Person otherPerson = (Person) otherObj;
				areEqual = this.address.equals(otherPerson.address) &&
							this.age == otherPerson.age &&
							this.name.equals(otherPerson.name);
			}
		}
		
		return areEqual;
	}
	
    @Override
    public int hashCode()
    {
        return Objects.hash(name, age, address);
    }
}
