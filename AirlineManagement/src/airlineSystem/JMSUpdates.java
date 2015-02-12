package airlineSystem;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JMSUpdates {
	
	public void sendUpdate(){
		javax.jms.Connection connection=null;
		InitialContext jndi = null;
		ConnectionFactory conFactory = null;
		Destination destination = null;
		Session session = null;
		MessageProducer producer = null;


		try {

			Properties properties = new Properties();
			properties.put(Context.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			properties.put(Context.URL_PKG_PREFIXES, "org.jnp.interfaces");
			properties.put(Context.PROVIDER_URL, "localhost");


			jndi = new InitialContext(properties);
			conFactory = (ConnectionFactory)jndi.lookup("XAConnectionFactory");

			connection = conFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			try{
				destination = (Queue) jndi.lookup("queue1");
			}catch(NamingException NE1)
			{
				System.out.println("NamingException: "+NE1+ " : Continuing anyway...");
			}

			if(destination==null){
				destination = session.createTopic("queue1");
				jndi.bind("queue1", destination);
			}

			producer = session.createProducer(destination);
			connection.start();
			String message = "CONGRATUALTIONS YOU HAVE BEEN ADDED TO THE SYSTEM";
			TextMessage TM = session.createTextMessage(message);
			producer.send(TM);
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}
}
