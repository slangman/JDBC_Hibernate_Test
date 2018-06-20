package ru.innoJavaCourse.lesson01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innoJavaCourse.lesson01.db.dao.AnotherObjectDao;
import ru.innoJavaCourse.lesson01.db.dao.SomeObjectDao;
import ru.innoJavaCourse.lesson01.db.entities.AnotherObject;
import ru.innoJavaCourse.lesson01.db.pojo.SomeObject;
import ru.innoJavaCourse.lesson01.util.RandomWordGenerator;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {
    @Autowired
    private SomeObjectDao someObjectDao;

    @Autowired
    private AnotherObjectDao anotherObjectDao;

    /**
     * Checks the time of adding and deleting from database a number of objects using JDBC.
     * Number can be changed in loop declaration.
     * @param model
     * @return
     */
    @RequestMapping(value = "/addAndDeleteViaJDBC", method = RequestMethod.GET)
    public String addAndDeleteViaJDBC(Model model) {
        List<SomeObject> listOfObjects = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            SomeObject someObject = new SomeObject(RandomWordGenerator.getRandomWord(3, 3),
                    RandomWordGenerator.getRandomWord(3, 10));
            listOfObjects.add(someObject);
        }
        long startTime = System.currentTimeMillis();
        someObjectDao.addAndDeleteSomeObjects(listOfObjects);
        long endTime = System.currentTimeMillis() - startTime;
        model.addAttribute("timeInMiliseconds", endTime);
        return "result";
    }

    /**
     * Checks the time of adding and deleting from database a number of objects using Hibernate.
     * Number can be changed in loop declaration.
     * @param model
     * @return
     */
    @RequestMapping(value = "/addViaHibernate", method = RequestMethod.GET)
    public String addViaHibernate(Model model) {
        List<AnotherObject> listOfObjects = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            AnotherObject anotherObject = new AnotherObject(RandomWordGenerator.getRandomWord(3, 3),
                    RandomWordGenerator.getRandomWord(3, 10));
            listOfObjects.add(anotherObject);
        }
        long startTime = System.currentTimeMillis();
        anotherObjectDao.addAndDeleteAnotherObjects(listOfObjects);
        long endTime = System.currentTimeMillis() - startTime;
        model.addAttribute("timeInMiliseconds", endTime);
        return "result";
    }
}
