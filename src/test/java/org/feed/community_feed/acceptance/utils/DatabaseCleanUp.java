package org.feed.community_feed.acceptance.utils;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Table;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author jiyoung
 */
@Profile("test")
@Component
@Slf4j
public class DatabaseCleanUp implements InitializingBean {

    @PersistenceContext
    private EntityManager em;
    private List<String> tableNames;
    private List<String> notGeneratedTableNames;

    @Override
    public void afterPropertiesSet() throws Exception {
        tableNames = em.getMetamodel().getEntities()
                .stream()
                .filter(entity -> entity.getJavaType().getAnnotation(Entity.class) != null)
                .map(entity -> entity.getJavaType().getAnnotation(Table.class).name())
                .toList();

        notGeneratedTableNames = List.of("community_user_relation", "community_like");
    }

    @Transactional
    public void execute() {
        em.flush();
        em.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
        for (String tableName : tableNames) {
            em.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
            if (!notGeneratedTableNames.contains(tableName)) {
                em.createNativeQuery("ALTER TABLE " + tableName + "ALTER COLUMN ID RESTART WITH 1").executeUpdate();
            }
        }
        em.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }
}
