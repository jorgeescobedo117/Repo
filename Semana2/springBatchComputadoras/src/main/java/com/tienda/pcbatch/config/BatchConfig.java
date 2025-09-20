package com.tienda.pcbatch.config;

import com.tienda.pcbatch.model.Pedido;
import com.tienda.pcbatch.processor.PedidoProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<Pedido> reader() {
        return new FlatFileItemReaderBuilder<Pedido>()
                .name("pedidoItemReader")
                .resource(new ClassPathResource("pedidos.csv"))
                .delimited()
                .names("pedidoId","cliente","modeloPC","procesador","ram","almacenamiento","precio","estado")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Pedido>() {{
                    setTargetType(Pedido.class);
                }})
                .linesToSkip(1) // <- ignora la primera línea (headers)
                .build();
    }

    @Bean
    public PedidoProcessor processor() {
        return new PedidoProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Pedido> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Pedido>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO pedidos (pedido_id, cliente, modelo_pc, procesador, ram, almacenamiento, precio, estado) " +
                        "VALUES (:pedidoId, :cliente, :modeloPC, :procesador, :ram, :almacenamiento, :precio, :estado)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Pedido> writer) {
        return stepBuilderFactory.get("step1")
                .<Pedido, Pedido> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

    @Bean
    public Job importUserJob(Step step1) {
        return jobBuilderFactory.get("importPedidoJob")
                .flow(step1)
                .end()
                .build();
    }
}
