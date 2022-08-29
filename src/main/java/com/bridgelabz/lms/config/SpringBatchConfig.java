package com.bridgelabz.lms.config;

import lombok.AllArgsConstructor;
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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import com.bridgelabz.lms.model.CandidateModel;
import com.bridgelabz.lms.repository.AdminRepository;
import com.bridgelabz.lms.repository.CandidateRepository;

@SuppressWarnings("ALL")
@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig {
	
	private JobBuilderFactory jobBuilderFactory;
	private StepBuilderFactory stepBuilderFactory;
	private CandidateRepository candidateRepository;
	private AdminRepository adminRepository;

	@Bean
	public FlatFileItemReader<CandidateModel> itemReader() {

		FlatFileItemReader<CandidateModel> flatFileItemReader = new FlatFileItemReader<>();
		flatFileItemReader.setResource(new FileSystemResource("src/main/resources/candidate.csv"));
		flatFileItemReader.setName("csvReader");
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapper());
		return flatFileItemReader;
	}

	@Bean
	public LineMapper<CandidateModel> lineMapper() {

		DefaultLineMapper<CandidateModel> defaultLineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames("id", "cicId", "fullName", "email", "mobileNum", "hiredDate", "degree", "aggrPer", "city", "state", "preferredJobLocation", "status", "passedOutYear", "creatorUser", "candidateStatus");

		BeanWrapperFieldSetMapper<CandidateModel> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(CandidateModel.class);

		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);

		return defaultLineMapper;
	}

	@Bean
	public CandidateProcessor processor(){
		return new CandidateProcessor();
	}

	@Bean
	public RepositoryItemWriter<CandidateModel> writer() {
		RepositoryItemWriter<CandidateModel> writer = new RepositoryItemWriter<>();
		writer.setRepository(candidateRepository);
		writer.setMethodName("save");
		return writer;
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("csv-step").<CandidateModel,CandidateModel>chunk(10)
				.reader(itemReader())
				.processor(processor())
				.writer(writer())
				.taskExecutor(taskExecutor())
				.build();
	}

	@Bean
	public Job runJob() {
		return jobBuilderFactory.get("importCandidates")
				.incrementer(new RunIdIncrementer())
				.start(step1())
				.build();
	}

	@Bean
	public TaskExecutor taskExecutor() {
		SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
		executor.setConcurrencyLimit(10);
		return executor;
	}
}
