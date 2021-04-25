import java.util.*;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
		List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
		Collection<Person> persons = new ArrayList<>();
		for (int i = 0; i < 10_000_000; i++) {
			persons.add(new Person(
					names.get(new Random().nextInt(names.size())),
					families.get(new Random().nextInt(families.size())),
					new Random().nextInt(100),
					Sex.values()[new Random().nextInt(Sex.values().length)],
					Education.values()[new Random().nextInt(Education.values().length)])
			);
		}
		// Несовершеннолетние.
		long minorquantity = persons.stream()
				.filter(age -> age.getAge() < 18)
				.count();
		
		// Призывники.
		List<String> inductees = persons.stream()
				.filter(person -> 18 < person.getAge() && person.getAge() > 27 )
				.map(Person::getFamily)
				.collect(Collectors.toList());
		
		// Рабочие с высшим образованием.
		
		// Сортировка по женщинам.
		List<Person> womenWorker = persons.stream()
				.filter(sex -> sex.getSex().equals(Sex.WOMEN))
				.filter(age -> age.getAge() > 18 && age.getAge() < 60)
				.filter(education -> education.getEducation().equals(Education.HIGHER))
				.collect(Collectors.toList());
		
		// Сортировка по мужчинам.
		List<Person> worker = persons.stream()
				.filter(sex -> sex.getSex().equals(Sex.MAN))
				.filter(age -> age.getAge() > 18 && age.getAge() < 65)
				.filter(education -> education.getEducation().equals(Education.HIGHER))
				.collect(Collectors.toList());
		
		// Слияние рабочих.
		worker.addAll(womenWorker);
		
		// Сортировка.
		List<Person> workers = worker.stream()
				.sorted(Comparator.comparing(Person::getFamily))
				.collect(Collectors.toList());
		System.out.println(workers);
	}
}
