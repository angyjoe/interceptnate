package info.sarihh.interceptnatesample.daos;

import info.sarihh.interceptnate.CallbackInterceptor;
import info.sarihh.interceptnate.TransactionCallback;
import info.sarihh.interceptnatesample.beans.Student;

import java.util.Date;
import java.util.List;

/**
 * @author Sari Haj Hussein
 */
public class CallbackStudentDAO {

	/** add a Student to the database */
	public Integer addStudent(final Student student) {
		return CallbackInterceptor.inTransaction(new TransactionCallback() {
			@Override
			public Integer doInTransaction() {
				return (Integer) CallbackInterceptor.getSession().save(student);
			}
		});
	}

	/** delete the Student identified by studentID from the database */
	public Student deleteStudent(final Integer studentID) {

		return CallbackInterceptor.inTransaction(new TransactionCallback() {
			@Override
			public Student doInTransaction() {
				Student student = (Student) CallbackInterceptor.getSession()
						.get(Student.class, studentID);
				CallbackInterceptor.getSession().delete(student);
				return student;
			}
		});
	}

	/** update the birth date of the Student identified by studentID */
	public Student updateStudent(final Integer studentID, final Date birthDate) {
		return CallbackInterceptor.inTransaction(new TransactionCallback() {
			@Override
			public Student doInTransaction() {
				Student student = (Student) CallbackInterceptor.getSession()
						.get(Student.class, studentID);
				student.setBirthDate(birthDate);
				CallbackInterceptor.getSession().update(student);
				return student;
			}
		});
	}

	/** retrieve the Student identified by studentID from the database */
	public Student getStudent(final Integer studentID) {

		return CallbackInterceptor.inTransaction(new TransactionCallback() {
			@Override
			public Student doInTransaction() {
				return (Student) CallbackInterceptor.getSession().get(
						Student.class, studentID);
			}
		});
	}

	/** list the Students stored in the database */
	public List<Student> listStudents() {
		return CallbackInterceptor.inTransaction(new TransactionCallback() {
			@Override
			public List<Student> doInTransaction() {
				return (List<Student>) CallbackInterceptor.getSession()
						.createQuery("FROM Student").list();
			}
		});
	}
}
