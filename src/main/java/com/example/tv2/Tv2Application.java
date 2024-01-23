package com.example.tv2;

import com.example.tv2.core.events.EventEnvelope;
import com.example.tv2.core.events.EventMetadata;
import com.example.tv2.core.events.IEvent;
import com.example.tv2.core.events.OrderEvent;
import com.example.tv2.core.events.eventbus.IEventBus;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.UUID;

@SpringBootApplication
public class Tv2Application {

	public static void main(String[] args) {
		SpringApplication.run(Tv2Application.class, args);
	}

	@Bean
	CommandLineRunner run( IEventBus _eventBus
	){
		return args -> {


			var orderId = UUID.randomUUID();
			var phoneNumber = "09129821232";
			var correlationId = UUID.randomUUID().toString();

			var orderInitiatedEvent = new OrderEvent.OrderInitiated(
					orderId,
					phoneNumber,
					new EventMetadata(correlationId)
			);
			var envelope = new EventEnvelope<>(
					orderInitiatedEvent,
					new EventMetadata()
			);

			_eventBus.publish(envelope);




//			userService.saveRole(new Role(null , "ROLE_USER"));
			System.out.println("salam");

		} ;
	}


}
