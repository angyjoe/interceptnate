package info.sarihh.interceptnate.samples.mainsample.daos;

import info.sarihh.interceptnate.TraditionalInterceptor;
import info.sarihh.interceptnate.samples.mainsample.beans.Student;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Sari Haj Hussein
 */
public class TraditionalStudentDAO {

	/** add a Student to the database */
	public Integer addStudent(Student student) {
		Session session = TraditionalInterceptor.getSessionFactory()
				.openSession();
		Transaction transaction = null;
		Integer studentID = null;
		try {
			transaction = session.beginTransaction();
			studentID = (Integer) session.save(student);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return studentID;
	}

	/** delete the Student identified by studentID from the database */
	public Student deleteStudent(Integer studentID) {
		Session session = TraditionalInterceptor.getSessionFactory()
				.openSession();
		Transaction transaction = null;
		Student student = null;
		try {
			transaction = session.beginTransaction();
			student = (Student) session.get(Student.class, studentID);
			session.delete(student);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return student;
	}

	/** retrieve the Student identified by studentID from the database */
	public Student getStudent(Integer studentID) {
		Session session = TraditionalInterceptor.getSessionFactory()
				.openSession();
		Transaction transaction = null;
		Student student = null;
		try {
			transaction = session.beginTransaction();
			student = (Student) session.get(Student.class, studentID);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return student;
	}

	/** list the Students stored in the database */
	public List<Student> listStudents() {
		Session session = TraditionalInterceptor.getSessionFactory()
				.openSession();
		Transaction transaction = null;
		List<Student> students = new ArrayList<Student>();
		try {
			transaction = session.beginTransaction();
			students = (List<Student>) session.createQuery("FROM Student")
					.list();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return students;
	}
}
