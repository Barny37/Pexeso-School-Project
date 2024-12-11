package sk.tuke.kpi.kp.pexeso;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;
import sk.tuke.kpi.kp.pexeso.service.*;


@SpringBootApplication
@Configuration
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "sk.tuke.kpi.kp.pexeso.server.*"))
public class SpringClient {
    public static void main(String[] args){
       // SpringApplication.run(SpringClient.class);
        new SpringApplicationBuilder(SpringClient.class).web(WebApplicationType.NONE).run(args);
    }
    @Bean
    public CommandLineRunner runner(GameUI gameUI){
        return s -> {
            gameUI.playingGame();
        };
    }
    @Bean
    public GameUI gameUI(){
        return new GameUI(new GameField());
    }
    @Bean
    public ScoreService scoreService(){
        return new ScoreServiceRestClient();
    }

    @Bean
    public CommentService commentService() {return new CommentServiceRestClient(); }

    @Bean
    public RatingService ratingService() {return new RatingServiceRestClient();}

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
