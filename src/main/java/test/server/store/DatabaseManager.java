package test.server.store;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DatabaseManager {
    private static EntityManagerFactory entityManagerFactory;

    public static void initialize() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("MinecubePU");
        }
    }

    public static EntityManager getEntityManager() {
        if (entityManagerFactory == null) {
            initialize();
        }
        return entityManagerFactory.createEntityManager();
    }

    public static void shutdown() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
