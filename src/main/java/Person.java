import java.util.Objects;
import java.util.OptionalInt;

public class Person {
    private final String name;
    private final String surname;
    private Integer age;
    private String address;

    protected Person(PersonBuilder builder) {
        this.name = builder.getName();
        this.surname = builder.getSurname();
        this.age = builder.getAge();
        this.address = builder.getAddress();
    }

    public void setAddress(String address) { this.address = address; }

    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getAddress() { return address; }

    /**
     * Возвращает возраст объекта с присутствующим значением, если он был установлен.
     * иначе OptionalInt.empty()
     */
    public OptionalInt getAge() { return age != null ? OptionalInt.of(age) : OptionalInt.empty(); }

    public boolean hasAge() { return age != null; }
    public boolean hasAddress() { return address != null; }

    /**
     * Увеличивет возраст объекта на одну едину, если он был установлен.
     * иначе выбрасывает исключение IllegalStateException
     */
    public void happyBirthday() {
        if (hasAge()) age++;
        else throw new IllegalStateException("Невозможно увеличить возраст: значение не задано.");
    }

    /**
     * Создаёт билдера для объекта-ребёнка.
     * Унаследует фамилию и, если есть, адрес.
     * Имя и возраст должны быть установлены отдельно.
     */
    PersonBuilder newChildBuilder() {
        PersonBuilder childBuilder = new PersonBuilder()
                .setSurname(surname);
        if (hasAddress()) childBuilder.setAddress(address);

        return childBuilder;
    }


    @Override
    public String toString() {
        return new StringBuilder("[")
                .append("name=").append(name)
                .append(", surname=").append(surname)
                .append(hasAge() ? ", age=" + age : "null,")
                .append(hasAddress() ? ", address=" + address : "null,")
                .append("]")
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(surname, person.surname) &&
                Objects.equals(age, person.age) &&
                Objects.equals(address, person.address);
    }



}
