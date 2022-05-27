package ua.rusyn.hibernate_mvc.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.rusyn.hibernate_mvc.model.Person;

import java.util.List;

@Component
public class PersonDAO {
    private final SessionFactory sessionFactory;
    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Transactional()
    public List<Person> findAll(){
        Session session = sessionFactory.getCurrentSession();
        List<Person> personList = session.createQuery("select p from Person p",Person.class)
                .getResultList();
        return personList;
    }
    @Transactional
    public void save(Person person){
        Session session = sessionFactory.getCurrentSession();
        session.save(person);

    }
    @Transactional
    public Person find(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class,id);
    }
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class,id));
    }
    @Transactional
    public void update(int id,Person updatedPerson){
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        person.setName(updatedPerson.getName());
        person.setAge(updatedPerson.getAge());
    }
}
