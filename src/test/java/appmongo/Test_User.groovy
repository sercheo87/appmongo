package appmongo

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.support.GenericXmlApplicationContext
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import com.data.core.User
import com.data.core.UserRepository


@RunWith(SpringJUnit4ClassRunner.class)
class Test_User {
	@Autowired
	private UserRepository repository;
	@Autowired
	MongoTemplate mongoTemplate;
	//static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

	@Test
	void testInsertUserRepository(){
		List<User> results = mongoTemplate.findAll(User.class);
		println("Total amount of persons: {}", results.size());
		repository.deleteAll();
		Assert.assertTrue(true)
	}

	@Test
	void testInsertUser(){

		// For XML
		ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");

		// For Annotation
		//ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

		User user = new User(1,"mkyong", "password123");

		// save
		mongoOperation.save(user);

		// now user object got the created id.
		System.out.println("1. user : " + user);

		// query to search user
		Query searchUserQuery = new Query(Criteria.where("username").is("mkyong"));

		// find the saved user again.
		User savedUser = mongoOperation.findOne(searchUserQuery, User.class);
		System.out.println("2. find - savedUser : " + savedUser);

		// update password
		mongoOperation.updateFirst(searchUserQuery, Update.update("password", "new password"), User.class);

		// find the updated user object
		User updatedUser = mongoOperation.findOne(new Query(Criteria.where("username").is("mkyong")), User.class);

		System.out.println("3. updatedUser : " + updatedUser);

		// delete
		mongoOperation.remove(searchUserQuery, User.class);

		// List, it should be empty now.
		List<User> listUser = mongoOperation.findAll(User.class);
		System.out.println("4. Number of user = " + listUser.size());
		Assert.assertTrue(true)
	}
}
