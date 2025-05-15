public class PersonBuilder {
    private String name;
    private String surname;
    private Integer age;
    private String address;

    public PersonBuilder() {}

    protected String getName() { return name; }
    protected String getSurname() { return surname; }
    protected Integer getAge() { return age; }
    protected String getAddress() { return address; }


    public PersonBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public PersonBuilder setAge(int age) {
        this.age = age;
        checkAge(age);
        return this;
    }

    public PersonBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    /**
     * Создаёт объект {@link Person} после валидации обязательных полей.
     *
     * @return новый экземпляр {@link Person}
     * @throws IllegalStateException если имя или фамилия не заданы
     * @throws IllegalArgumentException если возраст задан некорректно (вне диапазона 0–100)
     */
    public Person build() {
        checkRequired(name, "Имя");
        checkRequired(surname, "Фамилия");

        return new Person(this);
    }

    /**
     * Проверяет, что возраст находится в допустимом диапазоне.
     *
     * @param age возраст, может быть null
     * @throws IllegalArgumentException если возраст < 0 или > 100
     */
    private static void checkAge(Integer age) {
        if (age != null && (age < 0 || age > 100)) {
            throw new IllegalArgumentException("Возраст вне допустимого диапазона: 0–100");
        }
    }

    /**
     * Проверяет, что строковое значение не null и не пустое.
     *
     * @param value     значение поля
     * @param fieldName имя поля (для сообщения об ошибке)
     * @throws IllegalStateException если поле не задано или пустое
     */
    private static void checkRequired(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Не заполнено обязательное поле: " + fieldName);
        }
    }


}


