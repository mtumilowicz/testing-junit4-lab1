package entities;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import org.apache.commons.collections4.SetUtils;

import java.math.BigDecimal;
import java.util.Set;
import java.util.function.Predicate;

import static java.util.Objects.nonNull;

/**
 * Created by mtumilowicz on 2018-11-08.
 */
public class PersonImmutable {
    private final int id;
    private final String name;
    private final String surname;
    private final int age;
    private final BigDecimal salary;
    private final ImmutableSet<String> hobbies;

    private PersonImmutable(int id, 
                    String name, 
                    String surname, 
                    int age, 
                    BigDecimal salary,
                    Set<String> hobbies) {
        this.id = id;

        Preconditions.checkArgument(nonNull(name));
        this.name = name;

        Preconditions.checkArgument(nonNull(surname));
        Preconditions.checkArgument(surname.matches("[a-zA-Z]+"));
        this.surname = surname;

        Preconditions.checkArgument(age >= 0);
        this.age = age;

        Preconditions.checkArgument(nonNull(salary));
        Preconditions.checkArgument(salary.compareTo(BigDecimal.ZERO) > 0);
        this.salary = salary;

        this.hobbies = ImmutableSet.copyOf(SetUtils.emptyIfNull(hobbies));
    }
    
    public static Predicate<PersonImmutable> olderThan(int age) {
        return person -> nonNull(person) && person.getAge() > age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public ImmutableSet<String> getHobbies() {
        return hobbies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonImmutable that = (PersonImmutable) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    class Builder {
        private int id;
        private String name;
        private String surname;
        private int age;
        private BigDecimal salary;
        private ImmutableSet<String> hobbies;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder salary(BigDecimal salary) {
            this.salary = salary;
            return this;
        }

        public Builder hobbies(Set<String> hobbies) {
            this.hobbies = ImmutableSet.copyOf(hobbies);
            return this;
        }

        public PersonImmutable build() {
            return new PersonImmutable(id, name, surname, age, salary, hobbies);
        }
    }
}
