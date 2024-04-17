package org.example.services;

import org.example.models.Book;
import org.example.models.Person;
import org.example.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    // For PersonValidator
    public Optional<Person> findByFullName(String fullName) {
        return peopleRepository.findByFullName(fullName).stream().findAny();
    }

    @Transactional
    public void save(Person savePerson) {
        peopleRepository.save(savePerson);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public List<Book> getBooksByPersonId(int id) {
        Optional<Person> booksOwner = peopleRepository.findById(id);

        if(booksOwner.isPresent()) {
            Hibernate.initialize(booksOwner.get().getBooks());
            // Мы внизу итерируемся по книгам, поэтому они точно будут загружены, но на всякий случай
            // не мешает всегда вызывать Hibernate.initialize()
            // (на случай, например, если код в дальнейшем поменяется и итерация по книгам удалится)

            // Проверка просроченности книг
            booksOwner.get().getBooks().forEach(book -> {
                long diffInMillisecond  = Math.abs(book.getTakenAt().getTime() - new Date().getTime());
                if (diffInMillisecond > 864_000_000L)
                    book.setExpired(true);
            });

            return booksOwner.get().getBooks();
        } else {
            return Collections.emptyList();
        }
    }
}