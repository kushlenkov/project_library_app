package org.example.dao;

import org.example.models.Book;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private  final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getPersonByFullName(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    // Это для валидатора
    public Optional<Person> getPersonByFullName(String fullName) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE full_name=?",
                        new Object[]{fullName}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(full_name, year_birth) VALUES(?, ?)",
                person.getFullName(), person.getYearBirth());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET full_name=?, year_birth=? WHERE person_id=?",
                updatedPerson.getFullName(), updatedPerson.getYearBirth(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", id);
    }

    // Здесь Join не нужен. И так уже получили человека с помощью отдельного метода
    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }
}