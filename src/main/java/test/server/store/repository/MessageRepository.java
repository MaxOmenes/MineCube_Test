package test.server.store.repository;

import jakarta.persistence.EntityManager;
import test.server.store.DatabaseManager;
import test.server.store.entity.Message;

public class MessageRepository  {

    private static MessageRepository instance;

    private MessageRepository() {
    }

    /**
     * Get the singleton instance of MessageRepository.
     * Lazy initialization.
     * @return MessageRepository instance
     */
    public static MessageRepository getInstance() {
        if (instance == null) {
            instance = new MessageRepository();
        }
        return instance;
    }

    public void save(Message message){
        EntityManager em = DatabaseManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(message);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Failed to save message", e);
        } finally {
            em.close();
        }
    }
}
