package com.linkus.room.repository;

import com.linkus.room.entity.Room;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoomRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Room room) {
        em.persist(room);
    }

    public Room findById(Long roomId) {
        return em.find(Room.class, roomId);
    }

    public List<Room> findAll() {
        return em.createQuery("select r from Room r", Room.class)
                .getResultList();
    }

    public Room findByName(String roomName) {
        return em.createQuery("select r from Room r where r.roomName = :roomName", Room.class)
                .setParameter("roomName", roomName)
                .getSingleResult();
    }


}
