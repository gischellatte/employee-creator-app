package employee.api.demo.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import employee.api.demo.dtos.UpdateEmployeeDto;
import employee.api.demo.entity.Employee;
import employee.api.demo.employmenthistory.entity.EmploymentHistory;
import employee.api.demo.employmenthistory.dtos.UpdateEmploymentHistoryDto;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMap = new ModelMapper();
        
        modelMap.getConfiguration()
        .setSkipNullEnabled(true).setPreferNestedProperties(false).setMatchingStrategy(MatchingStrategies.STRICT);

        modelMap.createTypeMap(UpdateEmployeeDto.class, Employee.class)
        .implicitMappings();

        modelMap.createTypeMap(UpdateEmploymentHistoryDto.class, EmploymentHistory.class).implicitMappings();

        return modelMap;
    }
    
}
