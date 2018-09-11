package me.aikin.bicyclestore.user.api;

import org.hibernate.Session;
import org.hibernate.metamodel.internal.EntityTypeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.ArrayList;
import java.util.List;

@Service
@Profile("test")
public class TruncateDatabaseService {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void truncate() {
        List<String> tableNames = new ArrayList<>();
        Session session = entityManager.unwrap(Session.class);
        Metamodel metamodel = session.getEntityManagerFactory().getMetamodel();

        for (EntityType<?> classMetadata : metamodel.getEntities()) {
            EntityTypeImpl eti = (EntityTypeImpl) classMetadata;
            tableNames.add(eti.getName());
        }

        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = FALSE").executeUpdate();
        tableNames.forEach(tableName -> entityManager.createNativeQuery("TRUNCATE TABLE " + tableName.toUpperCase()).executeUpdate());
        entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS = TRUE").executeUpdate();
    }
}
