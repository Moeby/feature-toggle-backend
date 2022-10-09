package com.moeby.featuretoggle.service.dao.impl;

import com.moeby.featuretoggle.service.common.util.UUIDUtil;
import com.moeby.featuretoggle.service.dao.IPersonDAO;
import com.moeby.featuretoggle.service.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Transactional(rollbackFor = Exception.class)
public class PersonDAO implements IPersonDAO {

    private static final Logger logger = Logger.getLogger(PersonDAO.class.getName());

    @PersistenceContext
    private EntityManager em;
    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Person> findByIds(List<String> ids) {

        try (Session session = this.sessionFactory.openSession()) {
            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            final CriteriaQuery<Person> personQuery = criteriaBuilder.createQuery(Person.class);
            Root<Person> personRoot = personQuery.from(Person.class);
            final CriteriaQuery<Person> personCriteriaQuery = personQuery
                    .select(personRoot)
                    .where(personRoot.get("personId")
                            .in(UUIDUtil.from(ids)));
            return session.createQuery(personCriteriaQuery).getResultList();

        } catch (final DataAccessException e) {
            ids.forEach(id -> logger.severe(String.format("Could not find person with id: %s", id)));
            logger.severe(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Person> findAll() {

        try (Session session = this.sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
            criteria.from(Person.class);
            return session.createQuery(criteria).getResultList();
        } catch (final DataAccessException e) {
            logger.severe(String.format("Could not load people: %s", e.getMessage()));
            return new ArrayList<>();
        }
    }
}
