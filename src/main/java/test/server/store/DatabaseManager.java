package test.server.store;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DatabaseManager {
    private static EntityManagerFactory entityManagerFactory;

    private DatabaseManager() {
    }

    private static void initialize() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("MinecubePU");
        }
    }

    /**
     * Get a new EntityManager instance.
     * Lazy initialization of EntityManagerFactory.
     * @return EntityManager instance
     */
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
