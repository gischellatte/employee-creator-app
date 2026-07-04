package employee.api.demo.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import employee.api.demo.dtos.UpdateEmployeeDto;
import employee.api.demo.entity.Employee;

@Configuration //model mapper needs a pom.xml extensiom
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMap = new ModelMapper();
         
        //setSkipNullEnabled(true) if DTO (source) has a null field, ModelMapper will not touch that field in that entity
        //setPreferNestedProperties(false) means  the mapping works without any exception.
        //setMatchingStrategy() means the method must be an exact match
        modelMap.getConfiguration()
        .setSkipNullEnabled(true).setPreferNestedProperties(false).setMatchingStrategy(MatchingStrategies.STRICT);

        modelMap.createTypeMap(UpdateEmployeeDto.class, Employee.class)
        .implicitMappings();

        return modelMap;
    }
    
}
