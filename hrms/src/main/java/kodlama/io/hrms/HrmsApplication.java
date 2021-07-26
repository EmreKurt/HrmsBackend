package kodlama.io.hrms;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import kodlama.io.hrms.business.abstracts.*;
import kodlama.io.hrms.business.concretes.*;
import kodlama.io.hrms.core.adapters.mernis.MernisServiceAdapter;
import kodlama.io.hrms.core.adapters.mernis.UserCheckService;

@SpringBootApplication
@EnableSwagger2
public class HrmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrmsApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}
	
	 @Bean
	 public UserCheckService checkService(){
	     return new MernisServiceAdapter();
	 }
	    
	 @Bean
	 public MailService mailService(){
	     return new MailManager();
	 }
	
	
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("kodlama.io.hrms"))                                      
          .build();                                           
    }
}
