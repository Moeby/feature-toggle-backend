package com.moeby.featuretoggle.service.dao.impl;


import com.moeby.featuretoggle.service.dao.IFeatureToggleDAO;
import com.moeby.featuretoggle.service.model.FeatureToggle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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

@Transactional
public class FeatureToggleDAO implements IFeatureToggleDAO {

    private static final Logger logger = Logger.getLogger(PersonDAO.class.getName());

    @PersistenceContext
    private EntityManager em;
    private final SessionFactory sessionFactory;

    @Autowired
    public FeatureToggleDAO(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public FeatureToggle add(FeatureToggle featureToggle) {
        try (Session session = this.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(featureToggle);
            transaction.commit();
        } catch (final DataAccessException e) {
            logger.severe("Could not add feature toggle" );
        }
        return featureToggle;
    }

    @Override
    public FeatureToggle update(FeatureToggle featureToggle) {
        try (Session session = this.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(featureToggle);
            transaction.commit();
        } catch (final DataAccessException e) {
            logger.severe(String.format("Could not update feature toggle: %s", featureToggle.getFeatureId()));
        }
        return featureToggle;
    }

    @Override
    public List<FeatureToggle> findAll() {

        try (Session session = this.sessionFactory.openSession()) {
            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            final CriteriaQuery<FeatureToggle> featureToggleQuery = criteriaBuilder.createQuery(FeatureToggle.class);
            Root<FeatureToggle> featureToggleRoot = featureToggleQuery.from(FeatureToggle.class);
            final CriteriaQuery<FeatureToggle> featureToggleCriteriaQuery = featureToggleQuery
                    .select(featureToggleRoot);
            return session.createQuery(featureToggleCriteriaQuery).getResultList();
        } catch (final DataAccessException e) {
            logger.severe(String.format("Could not find feature toggles: %s", e.getMessage()));
            return new ArrayList<>();
        }
    }

    @Override
    public List<FeatureToggle> findByTechnicalNames(List<String> technicalNames) {

        try (Session session = this.sessionFactory.openSession()) {
            final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            final CriteriaQuery<FeatureToggle> featureToggleQuery = criteriaBuilder.createQuery(FeatureToggle.class);
            Root<FeatureToggle> featureToggleRoot = featureToggleQuery.from(FeatureToggle.class);
            final CriteriaQuery<FeatureToggle> featureToggleCriteriaQuery = featureToggleQuery
                    .select(featureToggleRoot)
                    .where(featureToggleRoot.get("technical_name")
                    .in(technicalNames));
            return session.createQuery(featureToggleCriteriaQuery).getResultList();
        } catch (final DataAccessException e) {
            technicalNames.forEach(id -> logger.severe(String.format("Could not find feature toggle with technical name: %s", id)));
            logger.severe(e.getMessage());
            return new ArrayList<>();
        }
    }

}
