package com.infosys.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.infosys.model.User;
import com.infosys.processor.UserProcessor;
import com.infosys.repository.UserRepository;

@Configuration
@EnableBatchProcessing
public class UserBatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private UserRepository repository;

//	@Autowired
//	private UserProcessor processor;

	@Autowired
//	private MongoTemplate mongoTemplate;

//	Method to read the file (csv) from local directory

	@Value("${file-path}")
	private String fileLocation;

	@Bean
	public FlatFileItemReader<User> reader() {
		FlatFileItemReader<User> itemReader = new FlatFileItemReader<>();
		itemReader.setResource(new FileSystemResource(fileLocation));
		/*
		 * reader.setLineMapper(new DefaultLineMapper<User>() { { setLineTokenizer(new
		 * DelimitedLineTokenizer() { { setNames(new String[] { "userId", "firstName",
		 * "lastName", "email" }); } }); setFieldSetMapper(new
		 * BeanWrapperFieldSetMapper<User>() { { setTargetType(User.class); } }); } });
		 * return reader;
		 */
		itemReader.setName("csvReader");
		itemReader.setLinesToSkip(1);
		itemReader.setLineMapper(lineMapper());
		return itemReader;
	}
	
    private LineMapper<User> lineMapper() {
        DefaultLineMapper<User> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("userId","firstName","lastName","email");

        BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(User.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;

    }
    
    @Bean
    public UserProcessor processor() {
    	return new UserProcessor();
    }
    
    
//	Writer method
//	@Bean
//	public MongoItemWriter<User> writer() {
//		MongoItemWriter<User> writer = new MongoItemWriter<>();
//		writer.setTemplate(mongoTemplate);
//		writer.setCollection("User");
//		return writer;
//	}
    
    @Bean
    public RepositoryItemWriter<User> writer() {
        RepositoryItemWriter<User> writer = new RepositoryItemWriter<>();
        writer.setRepository(repository);
        writer.setMethodName("save");
        return writer;
    }

//	@Bean
//	public Step step() {
//		return stepBuilderFactory.get("step1").<User, User>chunk(2).reader(reader()).processor(processor)
//				.writer(writer()).build();
//	}
    
    @Bean
    public Step step1() {
        return stepBuilderFactory.get("csv-step").<User, User>chunk(5)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .taskExecutor(taskExecutor())
                .build();
    }
    
    @Bean
    public Job runJob() {
        return jobBuilderFactory.get("importUser")
                .flow(step1()).end().build();

    }
    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(5);
        return asyncTaskExecutor;
    }

//	@Bean
//	public Job job() {
//		return jobBuilderFactory.get("job").incrementer(new RunIdIncrementer()).start(step()).build();
//	}
}
