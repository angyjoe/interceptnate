# InterceptNate

## Introduction
InterceptNate is a Hibernate session and transaction manager that doesn't use any third party library (e.g., [Spring Transaction Management](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/transaction.html)). InterceptNate promises over 50% reduction in the size of the [DAOs](http://en.wikipedia.org/wiki/Data_access_object) used in a Hibernate-powered application.

## Theory
Hibernate session and transaction management is traditionally done as follows:

```java
Session session = TraditionalInterceptor.getSessionFactory().openSession();
Transaction transaction = null;
try {
	transaction = session.beginTransaction();
	// session.get, session.save, session.delete, session.update, session.createQuery or session.createSQLQuery
	transaction.commit();
} catch (HibernateException e) {
	if (transaction != null)
		transaction.rollback();
	e.printStackTrace();
} finally {
	session.close();
}
```

With InterceptNate, it's sufficient to only write this:

```java
return CallbackInterceptor.inTransaction(new TransactionCallback() {
	@Override
	public T doInTransaction() {
		// return session.get, session.save, session.delete, session.update, session.createQuery or session.createSQLQuery
	}
});
```

## Ingredients
InterceptNate uses the following software components:

1. JDK 1.8.0

2. Hibernate 4.3.1

3. P6Spy (optional for displaying Hibernate SQL parameter values)

## Deployment
Refer to the [main sample application](./samples/mainsample).

## Licence
Copyright &copy; 2013 **[Sari Haj Hussein](http://sarihh.info)**.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

[http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

## Code Disclaimer
The author of this software code has used his best efforts in preparing the code. These efforts include the development, research, testing, and optimization of the theories and programs to determine their effectiveness. This software code is not designed or intended for use in the design, construction, operation or maintenance of any nuclear facility. Author disclaims any express or implied warranty of fitness for such uses. The author makes no warranty of any kind, expressed or implied, with regard to this software code or to the documentation accompanying it. In no event shall the author be liable for any direct, indirect, incidental, special, exemplary, or consequential damages (including but not limited to, procurement of substitute goods or services; loss of use, data, or profits; or business interruption whatsoever) arising out of, the furnishing, performance, or use of this software code, even if advised of the possibilities of such damages.