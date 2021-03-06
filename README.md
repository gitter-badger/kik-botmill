[![Build Status](https://travis-ci.org/BotMill/kik-botmill.svg?branch=master)](https://travis-ci.org/BotMill/kik-botmill)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/co.aurasphere.botmill/kik-botmill/badge.svg?style=blue)](https://maven-badges.herokuapp.com/maven-central/co.aurasphere.botmill/kik-botmill)
[![Javadocs](http://www.javadoc.io/badge/co.aurasphere.botmill/kik-botmill.svg)](http://www.javadoc.io/doc/co.aurasphere.botmill/kik-botmill)

# Kik-BotMill - Tools to Mill Kik Bots

Inspired by our first ever Bot Framework [FB-BotMill](https://github.com/BotMill/fb-botmill)

Kik-BotMill is designed to ease the process of developing, designing and running bots that exist inside Kik Messenger. 

It provides a semantic Java API that can be imported on your Java EE Project to send and receive messages from Kik so that developers can focus on developing the actual application instead of dealing with Kik API endpoints.

**<h3>Getting Started</h3>**

x.x.x - indicates version.  

	<dependency>
	  <groupId>co.aurasphere.botmill</groupId>
	  <artifactId>kik-botmill</artifactId>
	  <version>x.x.x</version>
	</dependency>
	
Gradle
    
    compile 'co.aurasphere.botmill:kik-botmill:x.x.x'

Grovvy

    @Grapes( 
        @Grab(group='co.aurasphere.botmill', module='kik-botmill', version='x.x.x') 
    )
    
Other ways to import, visit Maven central repo [site](http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22kik-botmill%22) 

Once you've imported the API. You need to register the KikBotMillServlet. To do that, create a Servlet project in your IDE and add this to your web.xml:

```xml

 <servlet>
	  <servlet-name>myKikBot</servlet-name>
	  <servlet-class>co.aurasphere.botmill.kik.KikBotMillServlet</servlet-class>
	  <init-param>
		  <param-name>bot-definition-class</param-name>
		  <param-value>com.sample.kik.demo.KikBotEntryPoint</param-value>
	  </init-param>
	  <load-on-startup>0</load-on-startup>
  </servlet>
  <servlet-mapping>
	  <servlet-name>myKikBot</servlet-name>
	  <url-pattern>/myKikBot</url-pattern>
  </servlet-mapping>
  
```

Your KikBotEntryPoint should extends KikBotMillEntry. You need to override the kikBotEntry and define your domains and behaviours.

```java

public class KikBotEntryPoint extends KikBotMillEntry {
	/**
	 * Entry point is the main method that will be called only once.
	 * This is where we define our configuration and responses.
	 */
	@Override
	protected void kikBotEntry() {
		
		//	setup
		KikBotMillContext.getInstance().setup("<USERNAME>", "<APIKEY>");
		
		//	configuration.
		ConfigurationBuilder.getInstance().setWebhook("<webhook>/myKikBot")
			.setManuallySendReadReceipts(false)
			.setReceiveDeliveryReceipts(false)
			.setReceiveIsTyping(true)
			.setReceiveReadReceipts(false)
			.setStaticKeyboard(
				KeyboardBuilder.getInstance().setType(KeyboardType.SUGGESTED)
				.addResponse(MessageFactory.createResponse("Make me a ChatBot!", ResponseType.TEXT))
				.addResponse(MessageFactory.createResponse("What are ChatBots?", ResponseType.TEXT))
				.addResponse(MessageFactory.createResponse("Milling Tools!", ResponseType.TEXT))
			.buildKeyboard())
		.buildConfiguration();
			
		//	Domain > collection of responses
		KikBotMillContext.getInstance().registerDomain(new SampleDomain());
		
	}
}

```
	
Your domain holds all the actions of your Bot.  
In the following example, the action will catch either a "hello" or "HELLO" response from the user and respond back a message "Hey <user>! How can I help you today?".  

```java

public class SampleDomain extends AbstractDomain {

	@Override
	public void buildDomain() {
	
		ActionFrameBuilder.getInstance()
			.setEvent(EventFactory .textMessagePattern("(?i:hello)"))
			.addReply(new TextMessageReply() {
				@Override
				public TextMessage processReply(Message message) {
					return TextMessageBuilder.getInstance()
							.setBody("Hey " + ((IncomingMessage) message).getFrom() + "! How can I help you today?")
							.build();
				}
			}).buildToContext();
		
	}
}

```

<div>
<div style="text-align:centered; width:100%;padding:5px; border:1px solid gray;">
<img src="https://dl.dropboxusercontent.com/u/1737239/botmill/kik_sample.png" height="345" width="210" />&nbsp;
</div>
</div>

Alternatively, if you're not using any XML file to initialize your context, you can always use the following methods.

```java
// Call this upon initialization of your app (should only be called once)
KikBotMillLoader.getLoader().loadEntryPoint(new KikBotEntryPoint());

//	Call this on your callback url post handler (req = HttpRequest, Resp = HttpResponse).
KikBotMillLoader.getLoader().postHandler(req, resp); 
```


**On Spark Java**

```java
import static spark.Spark.*;
			
public class KikBot {
    public static void main(String[] args) {
		// called once.
    	KikBotMillLoader.getLoader().loadEntryPoint(new KikBotEntryPoint());
    	 
    	//	register post (use this as webhook url on the config entrypoint);
    	post("/webhook", (request, response) -> {
	    	KikBotMillLoader.getLoader().postHandler(req, resp); 
		});
    }
}
```
	
**On Spring Boot**

```java

@SpringBootApplication
public class KikBotConfiguration {

	public static void main(String[] args) {
	    //	call the loader inside the Hell
	    SpringApplication.run(KikBotConfiguration.class, args); 
	    
	    //	and load Entry Point.
	    KikBotMillLoader.getLoader().loadEntryPoint(new KikBotEntryPoint());
		
	}

}
```

```java	

@Controller
public class RestfulSourceController {

    @Autowired
    Response response;
    
	@Autowired
    Request request;
    
    @RequestMapping(value="/webhoolurl", method=RequestMethod.POST, produces="application/json")
    @ResponseBody
    public void post() {
        return KikBotMillLoader.getLoader().postHandler(request, response); 
    }

}

```

**<h3>What's currently supported</h3>**

Kik-BotMill supports this Kik Messenger Platform components:

- Authentication
- Configuration
- Sending and Receiving Messages
	- Text
	- Link
	- Picture
	- Video
	- Start Chatting
	- Scan Data
	- Sticker
	- Is Typing
	- Delivery Receipt
	- Read Receipt
	- Friend Picker
- Keyboards
- Attributions
- Broadcasting
- User Profiles
- Kik Codes  


<sub>Copyright (c) 2017 BotMill.io</sub>
