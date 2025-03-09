package com.springkafka.notificationservice.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BeanLister implements CommandLineRunner {

  private final ApplicationContext applicationContext;

  public BeanLister(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Override
  public void run(String... args) throws Exception {
    String[] beanNames = applicationContext.getBeanDefinitionNames();

    System.out.println("Beans registrados no contexto Spring:");
    for (String beanName : beanNames) {
      // System.out.println(beanName);
    }
  }
}
