package sk.tuke.kpi.kp.pexeso.server.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import sk.tuke.kpi.kp.pexeso.*;
import sk.tuke.kpi.kp.pexeso.entity.Comment;
import sk.tuke.kpi.kp.pexeso.entity.Rating;
import sk.tuke.kpi.kp.pexeso.entity.Score;
import sk.tuke.kpi.kp.pexeso.service.CommentService;
import sk.tuke.kpi.kp.pexeso.service.RatingService;
import sk.tuke.kpi.kp.pexeso.service.ScoreService;

import java.sql.Timestamp;

@Controller
@RequestMapping("/pexeso")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class PexesoController {
    @Autowired
    private UserController userController;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private RatingService ratingService;

    public GameField gameField = new GameField();
    public Player playerI = new Player(gameField);
    private int openCards =0;

    @RequestMapping("/menu")
    public String menu(@RequestParam (required = false) String nameP,@RequestParam (required = false) Integer rows,@RequestParam (required = false) Integer columns){
        if ( nameP!=null || rows!=null || columns!=null ){
            if ((rows >= 4 && rows <= 8) && (columns >= 4 && columns <= 8)) {
                if ((rows * columns) % 2 == 0) {
                    gameField.setRows(rows);
                    gameField.setColums(columns);
                    playerI = new Player(gameField);
                    playerI.setName(nameP);
                    gameField.setUpGame();
                    gameField.setGameState(GameState.PLAYING);
                    return "pexeso";
                }
            }
        }
        return "pexeso";
    }

    @RequestMapping
    public String pexeso(@RequestParam (required = false) Integer row,@RequestParam (required = false) Integer column, Model model){

            if (gameField.getItemFromField(0, 0) == null) {
                gameField.setUpGame();
                playerI = new Player(gameField);
            }
            if (openCards == 2) {
                gameField.gameLogic();
                playerI.countScore();
                openCards = 0;

            }
            if (gameField.getGameState()!=GameState.SETINGUP) {
                if (row != null || column != null) {
                    if (gameField.getItemFromField(column, row).getCardState() == CardState.CLOSED) {
                        gameField.getItemFromField(column, row).changeTurnedState();
                        playerI.countMove();
                        openCards++;
                    }
                    model.addAttribute("player", playerI.getPlayer());
                    model.addAttribute("scores", scoreService.getTopScore());
                    model.addAttribute("comments", commentService.getTopComments());
                    model.addAttribute("ratings", ratingService.getTopRatings());

                }
            }
            if (gameField.isWon()==GameState.WON && userController.isLogged()){
                scoreService.addScore(new Score(userController.getLoginPlayer().getLogins(), gameField.getNumOfCards(), playerI.getMoves(), playerI.getScore() ));
            }else if (gameField.isWon()==GameState.WON && !userController.isLogged()){
                gameField.setGameState(GameState.SETINGUP);
            }

        return "pexeso";
    }
    @RequestMapping("/new")
    public String newGame(){
        gameField=new GameField();
        gameField.setUpGame();
        playerI = new Player(gameField);
        return "pexeso";
    }

    public String getHtmlField(){
        StringBuilder sb = new StringBuilder();
        sb.append("<table>\n");
        for(int row=0;row<gameField.getRows();row++){
            sb.append("<tr>\n");
                for (int column = 0; column < gameField.getColums(); column++) {
                    var card = gameField.getItemFromField(column,row);
                    sb.append("<td>\n");
                    sb.append("<a href='/pexeso?row=" + row + "&column=" + column + "'>\n");
                    sb.append("<img src='/images.pexeso/" + getImageName(card)+ ".png'>");
                    sb.append("</a>");
                    sb.append("</td>\n");
                }
            sb.append("</tr>\n");
        }
        sb.append("</table>\n");
        return sb.toString();
    }


    private String getImageName(Card card){
        if (card.getCardState()==CardState.FOUNDED){
                return card.getImage();
        }else if (card.getCardState() == CardState.OPENED){
            return card.getImage();
        }else{
           return card.getDefaultImage();
        }
    }

    public boolean isSetingUp(){
        return gameField.getGameState() ==GameState.SETINGUP;
    }
    public boolean isPlaying(){
        return gameField.getGameState() ==GameState.PLAYING;
    }
    public boolean isWon(){
        return gameField.getGameState() ==GameState.WON;
    }
    @RequestMapping("/comment")
    public String addComment(@RequestParam String comment){
        Comment commentP=new Comment(userController.getLoginPlayer().getLogins(),comment,new Timestamp(System.currentTimeMillis()));
        commentService.addComments(commentP);
        return "redirect:/pexeso";
    }

    @RequestMapping("/rating")
    public String addRating(@RequestParam float rating){
        Rating ratingP=new Rating(userController.getLoginPlayer().getLogins(),rating,new Timestamp(System.currentTimeMillis()));
        if (rating>0&&rating<=5){
            ratingService.addRatings(ratingP);
        }
        return "redirect:/pexeso";
    }
}
