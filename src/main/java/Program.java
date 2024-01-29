import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;

public class Program {
    public static void main(String[] args) {
        // Создание фабрики сессий
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // Создание и добавление нового объекта в БД
        createCourse(sessionFactory);


        // Получение объекта БД с id=3
        readCourse(sessionFactory, 3);

        // Изменение объекста БД с id=2
        updateCourse(sessionFactory, 2, "Изменённое название", 10.1);

        // Удаление объекта БД с id=1
        deleteCourse(sessionFactory, 1);


    }

    public static void createCourse(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Course course = Course.create("Новый курс", 4.5);
            session.save(course);
            session.getTransaction().commit();
            System.out.println("Object course save successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void readCourse(SessionFactory factory, int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Course course = session.get(Course.class, id);
            session.getTransaction().commit();
            System.out.println("Retrieved student object: " + course);
            System.out.println("Object student retrieved successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void updateCourse(SessionFactory factory, int id, String title, double duration) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Course course = session.get(Course.class, id);
            course.setTitle(title);
            course.setDuration(duration);
            session.update(course);
            session.getTransaction().commit();
            System.out.println("Updated course object: " + course);
            System.out.println("Object course update successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void deleteCourse(SessionFactory factory, int id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Course course = session.get(Course.class, id);
            session.delete(course);
            session.getTransaction().commit();
            System.out.println("Deleted course object: " + course);
            System.out.println("Object course delete successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
