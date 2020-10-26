import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

final class AddressBookTest {

    @Test
    void canSetAddressToPerson(){
        Person person = new Person("Lili", "Coco", 23);
        AddressBook book = new AddressBook();
        book.setAddress(person, "Ici");
        assertThat(book.getAddress(person), is("Ici"));
    }

    @Test
    void DifferentLAstNameButSameNameAndAgeAreNotConfused(){
        Person person1 = new Person("Lili", "Cici", 23);
        Person person2 = new Person("Lili", "Coco", 23);
        AddressBook book = new AddressBook();
        book.setAddress(person1, "Ici");
        book.setAddress(person2, "La");
        assertThat(book.toString(),
                both(containsString("Ici"))
                .and(containsString("La")));


    }


    @Test
    void canRemovePerson() {
        Person person = new Person("Lili", "Coco", 23);
        AddressBook book = new AddressBook();
        book.setAddress(person, "Ici");
        book.removePerson(person);
        assertThat(book.getAddresses().containsKey(person), is(false));
    }

    @Test
    void behaveCorrectlyWhenEmptyBook() {
        AddressBook book = new AddressBook();
        assertThat(book.toString(), is(""));
    }

}