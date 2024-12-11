package sk.tuke.kpi.kp.pexeso.server;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sk.tuke.kpi.kp.pexeso.service.*;

@SpringBootApplication
@Configuration
@EntityScan(basePackages = "sk.tuke.kpi.kp.pexeso.entity")
public class GameStudioServer {
    public static void main(String[] args){
        SpringApplication.run(GameStudioServer.class);
    }

    @Bean
    public PlayersService playersService(){return new PlayersServiceJPA();}

    @Bean
    public ScoreService scoreService(){
        return new ScoreServiceJPA();
    }

    @Bean
    public CommentService commentService() {return new CommentServiceJPA();}

    @Bean
    public RatingService ratingService() {return new RatingServiceJPA();}

}
