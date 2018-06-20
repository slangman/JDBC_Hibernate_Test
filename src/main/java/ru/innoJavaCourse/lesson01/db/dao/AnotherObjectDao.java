package ru.innoJavaCourse.lesson01.db.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innoJavaCourse.lesson01.db.entities.AnotherObject;

import java.util.List;

@Repository
public class AnotherObjectDao {

    private static final Logger logger = Logger.getLogger("defaultLog");

    @Autowired
    private SessionFactory factory;

    public void addAndDeleteAnotherObjects(List<AnotherObject> anotherObjectList) {
        Session session = factory.openSession();
        for (AnotherObject anotherObject : anotherObjectList) {
            session.beginTransaction();
            session.save(anotherObject);
            session.getTransaction().commit();
            logger.info("Object successfully persisted.");
            session.beginTransaction();
            session.delete(anotherObject);
            session.getTransaction().commit();
            logger.info("Object successfully deleted.");
        }
        session.close();
    }

}
