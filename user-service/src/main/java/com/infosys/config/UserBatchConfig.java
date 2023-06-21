package com.infosys.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.infosys.model.User;
import com.infosys.processor.UserProcessor;

@Configuration
@EnableBatchProcessing
public class UserBatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private UserProcessor processor;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
//	Method to read the file (csv) from local directory
	
	@Bean
	public FlatFileItemReader<User> reader(){
		FlatFileItemReader<User> reader=new FlatFileItemReader<>();
		reader.setResource(new FileSystemResource("C:\\Users\\amit.kumar442\\Desktop\\user.csv"));
		reader.setLineMapper(new DefaultLineMapper<>() {{
			setLineTokenizer(new DelimitedLineTokenizer() {{
				setNames(new String [] {"id","firstName","lastName","email"});
			}});
			setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
				setTargetType(User.class);
			}});
		}});
		return reader;
	}
	
//	Writer method
	@Bean
	public MongoItemWriter<User> writer(){
		MongoItemWriter<User> writer=new MongoItemWriter<>();
		writer.setTemplate(mongoTemplate);
		writer.setCollection("User");
		return writer;
	}
	
	@Bean
	public Step step() {
		return stepBuilderFactory.get("step1").<User, User>chunk(2)
				.reader(reader())
				.processor(processor)
				.writer(writer())
				.build();
	}
	
	@Bean
	public Job job() {
		return jobBuilderFactory.get("job")
				.incrementer(new RunIdIncrementer())
				.start(step())
				.build();
				
	}
}
