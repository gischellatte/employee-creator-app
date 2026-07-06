package employee.api.demo.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import employee.api.demo.dtos.UpdateEmployeeDto;
import employee.api.demo.entity.Employee;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMap = new ModelMapper();
        
        modelMap.getConfiguration()
        .setSkipNullEnabled(true).setPreferNestedProperties(false).setMatchingStrategy(MatchingStrategies.STRICT);

        modelMap.createTypeMap(UpdateEmployeeDto.class, Employee.class)
        .implicitMappings();

        return modelMap;
    }
    
}
