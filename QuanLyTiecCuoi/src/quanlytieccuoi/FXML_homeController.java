/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi;

import quanlytieccuoi.*;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.sql.Insert;
import quanlytieccuoi.models.Admin;
import quanlytieccuoi.models.BanquetHall;
import quanlytieccuoi.models.FoodDrinkService;
import quanlytieccuoi.models.MenuGroup;


/**
 * FXML Controller class
 *
 * @author MyPC
 */
public class FXML_homeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ScrollPane scrPane;
    @FXML
    private FlowPane flowPane;
    @FXML
    private Menu menu;
    @FXML
    private Menu banquetHall;
    @FXML
    private Button imageOrders;
    @FXML
    private ImageView imageHome;
    @FXML 
    private Button btnSignIn;
    @FXML
    private Button btnSignUpCustomer;
    @FXML
    private Label lbTen;
    @FXML
    private MenuItem itemExit;
    @FXML
    private TextField search;
    @FXML
    private MediaView mv;
    private MediaPlayer mp;
    private Media me;
    @FXML
    private StackPane p;
    @FXML
    private AnchorPane b;
    @FXML
    private Button btnStatistical;
    @FXML
    private Button btnSignOut;
    @FXML
    private Label inserthall;
    @FXML
    private Label insertdata;
    
    
    SessionFactory factory;
    UtilsDao utils = new UtilsDao();
    Stage stage;
    EventHandler<ActionEvent> event = eventLoad();
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.factory = HibernateUtil.getSessionFactory();
        scrPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        
        
        // load các menuitem
        loadHome();
        loadMenu();
        loadBanquetHall();
        scrPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        imageOrders.setVisible(false);
        btnSignOut.setVisible(false);
        btnStatistical.setVisible(false);
        //menuhome.setOnShowing(e -> { utils.alertInformationHandler("dfdf", "dfsdfd");});
        //menuhome.setOnAction(null);
        
        imageOrders.setOnAction(e ->{
            utils.changeSceneHandlers("FXML_choosecustomer.fxml", e);
            
        });
        
        btnSignIn.setOnAction(e->{
            utils.changeSceneHandler("FXML_login.fxml", e);
        });
        btnSignOut.setOnAction(e -> {
            utils.changeSceneHandler("FXML_home.fxml", e);
        });
        imageHome.setOnMouseClicked(e->{
            utils.changeSceneHandler("FXML_home.fxml", e);
        });
        
        
        inserthall.setOnMouseClicked(e->{
            utils.changeSceneHandler("FXML_banquethall.fxml", e);
        });
        insertdata.setOnMouseClicked(e->{
            utils.changeSceneHandler("FXML_insertdata.fxml", e);
        });
        btnStatistical.setOnAction(ov ->{
           utils.changeSceneHandlers("FXML_monthchart.fxml", ov);
        });
        videoPlay();
        
    } 
    
      
    // xử lý sự kiện trên menu item
    public EventHandler<ActionEvent> eventLoad() {
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                MenuItem mItem = (MenuItem) event.getSource();
                String side = mItem.getText();
                if ("food".equalsIgnoreCase(side)) {
                    flowPane.getChildren().clear();
                    
                    loadFoodDrinkServiceToApp("Food");
  
                }
                if ("Drink".equalsIgnoreCase(side)) {
                    flowPane.getChildren().clear();
                    
                    loadFoodDrinkServiceToApp("Drink");
                }
                if ("Service".equalsIgnoreCase(side)) {
                    
                   flowPane.getChildren().clear();
                    loadFoodDrinkServiceToApp("Service");
                }
                if ("Starlight Ballroom".equalsIgnoreCase(side)) {
                    flowPane.getChildren().clear();
                    loadBanquetHall("Starlight Ballroom");
                }
                if ("Paradise Restaurant".equalsIgnoreCase(side)) {
                    flowPane.getChildren().clear();
                    loadBanquetHall("Paradise Restaurant");
                }
                if ("Classic Wedding Hall".equalsIgnoreCase(side)) {
                    flowPane.getChildren().clear();
                    loadBanquetHall("Classic Wedding Hall");
                }if ("Royal Wedding Hall".equalsIgnoreCase(side)) {
                    flowPane.getChildren().clear();
                    loadBanquetHall("Royal Wedding Hall");
                }
                if ("Diamond Wedding Hall".equalsIgnoreCase(side)) {
                    flowPane.getChildren().clear();
                    loadBanquetHall("Diamond Wedding Hall");
                }                
            }
        };
    }
    
    public void setHome(boolean a){
        if(a){
            btnStatistical.setVisible(true);
            
        }
        btnSignIn.setDisable(true);
        btnSignOut.setVisible(true);
        imageOrders.setVisible(true);
        
    }
    
    public void loadFoodDrinkServiceToApp(String p){
        List<FoodDrinkService> l = utils.getFoodDrinkService();
        menuSmall();
        String path = new File("src/quanlytieccuoi/Images/Foodvideo.mp4").getAbsolutePath();

        Media me2 = new Media(new File(path).toURI().toString());
        MediaPlayer mp2 = new MediaPlayer(me2);
        MediaView mv2 = new MediaView(mp2);
        
        mp2.setAutoPlay(true);
        mp2.setCycleCount(MediaPlayer.INDEFINITE);   
        
        HBox hb = new HBox();
        Label lb = new Label("WEDDING KINGDOM");
        Line l1 = new Line(-100, 0, 230.5, 0);
        l1.setStrokeWidth(2);
        l1.setStroke(Color.web("f8b4a8"));
        l1.setTranslateX(-10);
        Line l2 = new Line(-100, 0, 230.5, 0);
        l2.setStrokeWidth(2);
        l2.setStroke(Color.web("f8b4a8"));
        l2.setTranslateX(10);
        
        hb.setTranslateX(50);
        hb.setStyle("-fx-alignment: CENTER;");
        hb.setPrefWidth(IMG_WIDTH);
        hb.getChildren().addAll(l1, lb, l2);
        flowPane.getChildren().addAll(mv2,hb);
        
        for(FoodDrinkService f : l){
            if(f.getIDType().getmName().equalsIgnoreCase(p)){
                VBox v = new VBox(3);
               
                String image = "quanlytieccuoi/Images/" + f.getImage();
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(237);
                imageView.setFitHeight(202);
                imageView.setId("hoverImg");

                Text txt = new Text(f.getName());
                txt.setWrappingWidth(237);
                txt.setFont(new Font("Arial", 22));
                txt.setStyle("-fx-text-alignment: CENTER;");

                Text price = new Text(String.format("Price: %s VNĐ", f.getPrice()));
                price.setWrappingWidth(237);
                price.setFill(Color.RED);
                price.setFont(new Font("Arial", 18));
                
                price.setStyle("-fx-font-style: italic;");
                price.setStyle("-fx-text-alignment: CENTER;");
                
                v.setTranslateX(17);
                v.getChildren().addAll(imageView, txt, price);

                flowPane.getChildren().addAll(v);
                
            }
                
        }   
       contact();
    }
    
    public void loadBanquetHall(String p){
        List<BanquetHall> l = utils.getBanquetHall();
        menuSmall();
        String path = new File("src/quanlytieccuoi/Images/Claris.mp4").getAbsolutePath();

        Media me2 = new Media(new File(path).toURI().toString());
        MediaPlayer mp2 = new MediaPlayer(me2);
        MediaView mv2 = new MediaView(mp2);
        
        mp2.setAutoPlay(true);
        mp2.setCycleCount(MediaPlayer.INDEFINITE);   
     
        flowPane.getChildren().addAll(mv2);
        for(BanquetHall f : l){
            if(f.getName().equalsIgnoreCase(p)){
                VBox v = new VBox(3);
                VBox v1 = new VBox(3);
                HBox h = new HBox();
                HBox hname = new HBox();
                HBox hAcreage = new HBox();
                HBox hMaxpeople = new HBox();
                HBox hPrice = new HBox();
                HBox hStatus = new HBox();
                
                String image = "quanlytieccuoi/Images/" + f.getImage();
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(600);
                imageView.setFitHeight(400);
                imageView.setId("hoverImg");
                
                Rectangle rec = new Rectangle(10, 40,Color.valueOf("#333333"));
                rec.setTranslateX(22);
             
                Text txt = new Text(f.getName());
                txt.setWrappingWidth(300);
                txt.setFont(new Font("ProximaNW01-Reg", 28));
                txt.setFill(Color.RED);
                txt.setTranslateX(26);
                txt.setTranslateY(7);
                
                Text txtAcrege = new Text(String.format("Acreage: %s m2", f.getAcreage()));
                txtAcrege.setWrappingWidth(237);
                txtAcrege.setFont(new Font("ProximaNW01-Reg", 18));
                txtAcrege.setTranslateX(22);
                txtAcrege.setTranslateY(20);
         
                Text txtMaxPeople = new Text(String.format("Max people: %s people", f.getMaxPeople()));
                txtMaxPeople.setWrappingWidth(237);
                txtMaxPeople.setFont(new Font("ProximaNW01-Reg", 18));
                txtMaxPeople.setTranslateX(22);
                txtMaxPeople.setTranslateY(20);
                
                Text price = new Text(String.format("Price: %s VNĐ", f.getPrice()));
                price.setWrappingWidth(237);
                price.setFont(new Font("ProximaNW01-Reg", 18));
                price.setTranslateX(22);
                price.setTranslateY(20);
                
                Text txtStatus = new Text(String.format("%s", f.getStatus()));
                txtStatus.setWrappingWidth(550);
                txtStatus.setFont(new Font("ProximaNW01-Reg", 18));
                txtStatus.setTranslateX(22);
                txtStatus.setTranslateY(40);
                
                hname.getChildren().addAll(rec,txt);
                v1.getChildren().addAll(hname,hAcreage, hMaxpeople, hPrice, hStatus);
                hAcreage.getChildren().addAll(txtAcrege);
                hMaxpeople.getChildren().addAll(txtMaxPeople);
                hPrice.getChildren().addAll(price);
                hStatus.getChildren().addAll(txtStatus);
                h.getChildren().addAll(imageView,v1);
                h.setPrefWidth(1240);
                h.setPadding(new Insets(10, 0, 10, 0));
                v.getChildren().addAll(h);
                flowPane.getChildren().add(v);
            }      
        } 
        contact();
    }
    
    public void loadHome(){
            
            VBox v = new VBox(2);
            VBox v2 = new VBox(2);

            HBox h = new HBox(3);
            
            HBox v1 = new HBox(2);
            
            Text t = new Text("we are...");
            t.setFont(Font.font("Didot LT Std",40));
            
            Text t1 = new Text("…destination wedding videographers, husband & "
                    + "wife team, and animal lovers Sarah & Rick Pendergraft."
                    + " We create timeless\n"
                    + "wedding videos by capturing the experience of marriage in"
                    + " an authentic way. Inspired by real moments, emotions and people,\n"
                    + "our films are genuine, artistic, and personal. We "
                    + "frequently shoot weddings across the U.S. and worldwide,"
                    + " and call Tulsa home.\n");
            t1.setFont(Font.font("Verdana"));
            
            Text t3 = new Text("who are you?");
            t3.setFont(Font.font("Didot LT Std",38));
            
            Text t4 = new Text("You can’t wait to get married. You adore your fiancé. "
                    + "Family is everything. You’re fun loving, and not afraid "
                    + "to be yourself in front\n"
                    + "\t\t\t\tof the camera.  Feelings are meant to be shared,"
                    + " and the world is meant to be explored.\n"
                    + "Whether your wedding is luxury or rustic, a platinum bash"
                    + " or destination elopement, in a ballroom or on the beach… "
                    + "what matters\n"
                    + "\t\t\t\tmost are the people present, and the experience "
                    + "you will hold dear for a lifetime.");
            t4.setFont(Font.font("Verdana"));
            
            for (int j = 1 ; j < 5 ; j++){
                   
                String image = "quanlytieccuoi/Images/w" + j + ".png";
                ImageView imageView1 = new ImageView(image);
                imageView1.setFitWidth(254);
                imageView1.setFitHeight(154);
                
                imageView1.setTranslateX(60);
                imageView1.setTranslateY(30);
                
                imageView1.setId("im");
               
                h.getChildren().addAll(imageView1);
                
            }
            Text t5 = new Text("CONTACT US");
            t5.setFont(Font.font("ProximaNW01-Reg",25));
            
            ImageView ct1 = new ImageView("quanlytieccuoi/Images/c1.png");
            ImageView ct2 = new ImageView("quanlytieccuoi/Images/c2.png");
            ImageView ct3 = new ImageView("quanlytieccuoi/Images/c3.png");
            ImageView ct4 = new ImageView("quanlytieccuoi/Images/c4.png");
            
            ct1.setId("imgContact");
            ct2.setId("imgContact");
            ct3.setId("imgContact");
            ct4.setId("imgContact");
            
            h.setSpacing(30);
            v.setStyle("-fx-alignment: CENTER;");
            v.setTranslateX(120);
            
            v1.getChildren().addAll(ct1,ct2,ct3,ct4);
            v1.setPrefWidth(IMG_WIDTH);
            v1.setPrefHeight(IMG_HEIGHT);
            startAnimation(v1);
            
            v2.getChildren().add(t5);
            v2.setTranslateX(530);
            v2.setTranslateY(45);
            
            v1.setId("vboxContact");
            v.getChildren().addAll(t,t1,t3,t4);
           
            flowPane.getChildren().addAll(v,h,v2,v1);
            contact();
            
            
    }
    
    public void loadMenu(){
        List<MenuGroup> mArr = utils.getMenuGroup();
        for(MenuGroup m : mArr){
            MenuItem menuItem = new MenuItem(m.getmName());
            menuItem.setOnAction(event);
            menu.getItems().add(menuItem);
            
        }
        

        itemExit.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        itemExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

    }
    
    public void loadBanquetHall(){
        List<BanquetHall> mArr = utils.getBanquetHall();
        for(BanquetHall m : mArr){
            MenuItem menuItem = new MenuItem(m.getName());
            menuItem.setOnAction(event);
            banquetHall.getItems().add(menuItem);
        }
        
    }
    
    
    public void videoPlay(){
        
        String path = new File("src/quanlytieccuoi/Images/Destination Wedding.mp4").getAbsolutePath();
        
        me = new Media(new File(path).toURI().toString());
        mp = new MediaPlayer(me);
        mv.setMediaPlayer(mp);
       
        mp.setAutoPlay(true);
        mp.setCycleCount(MediaPlayer.INDEFINITE);   
    }

     //slide image    
    private final int NUM_OF_IMGS = 4;
    private final int SLIDE_FREQ = 4;
    private final double IMG_WIDTH = 1106;
    private final double IMG_HEIGHT = 270;
    TranslateTransition trans;
    
    public void startAnimation(final HBox hbox){
        EventHandler<ActionEvent> slideAction = (ActionEvent t) -> {
            trans = new TranslateTransition(Duration.seconds(1.5), hbox);
            trans.setByX(-IMG_WIDTH - 98);
            trans.setInterpolator(Interpolator.EASE_BOTH);
            trans.play();
        };
        EventHandler<ActionEvent> resetAction = (ActionEvent t) -> {
            trans = new TranslateTransition(Duration.seconds(1), hbox);
            trans.setByX((NUM_OF_IMGS - 1) * (IMG_WIDTH + 98));
            trans.setInterpolator(Interpolator.EASE_BOTH);
            trans.play();
        };
       
        List<KeyFrame> keyFrames = new ArrayList<>();
        for (int i = 1; i <= NUM_OF_IMGS; i++) {
            if (i == NUM_OF_IMGS) {
                keyFrames.add(new KeyFrame(Duration.seconds(i * SLIDE_FREQ), resetAction));
            } else {
                keyFrames.add(new KeyFrame(Duration.seconds(i * SLIDE_FREQ), slideAction));
                
            }
        }
        Timeline anim = new Timeline(keyFrames.toArray(new KeyFrame[NUM_OF_IMGS]));
 
        anim.setCycleCount(Timeline.INDEFINITE);
        anim.playFromStart();
    }
    
    public void menuSmall(){
        VBox v1 = new VBox();
        MenuBar menuBar = new MenuBar();
        Menu introduceMenu = new Menu("INTRODUCE");
        Menu menuMenu = new Menu("MENU");
        Menu banMenu = new Menu("BANQUETHALL");
        Label insHall = new Label("INSERT HALL");
        insHall.setOnMouseClicked(e->{
            utils.changeSceneHandler("FXML_banquethall.fxml", e);
        });
        Menu hallMenu = new Menu("", insHall);
        hallMenu.setId("menu-item");
        
        Label insData = new Label("INSERT DATA");
        Menu dataMenu = new Menu("" ,insData);
        insData.setOnMouseClicked(e->{
            utils.changeSceneHandler("FXML_insertdata.fxml", e);
        });
        dataMenu.setId("menu-item");
        
        Menu helpMenu = new Menu("HELP");

        // Tạo các MenuItem
        MenuItem foodItem = new MenuItem("Food");
        foodItem.setOnAction(event);
        foodItem.setId("menu-itemA");
        MenuItem drinkItem = new MenuItem("Drink");
        drinkItem.setOnAction(event);
        drinkItem.setId("menu-itemA");
        MenuItem serviceItem = new MenuItem("Service");
        serviceItem.setOnAction(event);
        serviceItem.setId("menu-itemA");

        MenuItem haItem = new MenuItem("Starlight Ballroom");
        haItem.setOnAction(event);
        haItem.setId("menu-item");
        MenuItem hbItem = new MenuItem("Paradise Restaurant");
        hbItem.setOnAction(event);
        hbItem.setId("menu-item");
        MenuItem hcItem = new MenuItem("Classic Wedding Hall");
        hcItem.setOnAction(event);
        hcItem.setId("menu-item");
        MenuItem hdItem = new MenuItem("Royal Wedding Hall");
        hdItem.setOnAction(event);
        hdItem.setId("menu-item");
        MenuItem heItem = new MenuItem("Diamond Wedding Hall");
        heItem.setOnAction(event);
        heItem.setId("menu-item");
        
        MenuItem itemExit = new MenuItem("Exit");
        itemExit.setId("menu-itemB");
        
        itemExit.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        itemExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        // Thêm các MenuItem vào Menu.
        menuMenu.getItems().addAll(foodItem, drinkItem, serviceItem);
        banMenu.getItems().addAll(haItem,hbItem,hcItem,hdItem,heItem);
        helpMenu.getItems().add(itemExit);

        // Thêm các Menu vào MenuBar
        menuBar.getMenus().addAll(introduceMenu, menuMenu,banMenu,hallMenu,dataMenu, helpMenu);
        
        menuBar.setId("menuFood");
        
        
        menuBar.setPrefWidth(1230);
        menuBar.setPrefHeight(49);
        
        v1.getChildren().add(menuBar);
        flowPane.getChildren().add(v1);
    }
    
    private void contact(){
        HBox hbox = new HBox();
        VBox v1 = new VBox();
        VBox v2 = new VBox();
        VBox v3 = new VBox();
        
        HBox hfb = new HBox();
        HBox hins = new HBox();
        HBox htw = new HBox();
        //rectangle
        Rectangle rec = new Rectangle(10, 10);
        rec.setStroke(Color.valueOf("ffcccc"));
        rec.setFill(Color.TRANSPARENT);
        rec.setTranslateY(12);
        
        Rectangle rec1 = new Rectangle(10, 10);
        rec1.setStroke(Color.valueOf("ffcccc"));
        rec1.setFill(Color.TRANSPARENT);
        rec1.setTranslateY(12);
        
        Rectangle rec2 = new Rectangle(10, 10);
        rec2.setStroke(Color.valueOf("ffcccc"));
        rec2.setFill(Color.TRANSPARENT);
        rec2.setTranslateY(12);
        //vbox 1
        Label lb1 = new Label("LET'S BE FRIENDS");
        lb1.setFont(new Font("ProximaNW01-Reg",25));
        lb1.setStyle("-fx-text-fill: #FFFFFF;");
        lb1.setTranslateX(-10);
        lb1.setTranslateY(-20);
        
        Hyperlink fb = new Hyperlink("FACEBOOK");
        fb.setFont(new Font("ProximaNW01-Reg",18));
        fb.setStyle("-fx-text-fill: #FFFFFF;"); 
        fb.setFocusTraversable(false);
        fb.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                try {
                    UtilsDao.goLink("https://www.facebook.com");
                } catch (URISyntaxException ex) {
                   System.out.println("ERROR: " + ex.getMessage());
                }
            }
        });
        Hyperlink ins = new Hyperlink("INSTAGRAM");
        ins.setFont(new Font("ProximaNW01-Reg",18));
        
        ins.setStyle("-fx-text-fill: #FFFFFF;");
        ins.setFocusTraversable(false);
        ins.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                try {
                    UtilsDao.goLink("https://www.instagram.com");
                } catch (URISyntaxException ex) {
                    System.out.println("ERROR: " + ex.getMessage());
                }
            }
        });
        
        Hyperlink tw = new Hyperlink("TWITTER");
        tw.setFont(new Font("ProximaNW01-Reg",18));
        tw.setStyle("-fx-text-fill: #FFFFFF;");
        tw.setFocusTraversable(false);
        tw.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                try {
                    UtilsDao.goLink("https://twitter.com/?lang=vi");
                } catch (URISyntaxException ex) {
                   System.out.println("ERROR: " + ex.getMessage());
                }
            }
        });
        
        //vbox 2
        Label lb2 = new Label("CONTACT US");
        lb2.setFont(new Font("ProximaNW01-Reg",25));
        lb2.setStyle("-fx-text-fill: #FFFFFF;");
        lb2.setTranslateY(-20);
        
        Label lb5 = new Label("080.789.789");
        lb5.setFont(new Font("ProximaNW01-Reg",18));
        lb5.setStyle("-fx-text-fill: #FFFFFF;");
        lb5.setTranslateY(5);
        Hyperlink email = new Hyperlink("wedding.kingdom@gmail.com");
        email.setFont(new Font("ProximaNW01-Reg", 18));
        email.setStyle("-fx-text-fill: #FFFFFF;");
        email.setTranslateX(-5);
        email.setTranslateY(5);
        email.setStyle("-fx-text-fill: #FFFFFF;");
        email.setFocusTraversable(false);
        email.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    UtilsDao.goLink("https://www.google.com/intl/vi/gmail/about/#");
                } catch (URISyntaxException ex) {
                   System.out.println("ERROR: " + ex.getMessage());
                }
            }
        });
        //vbox 3
        ImageView ct1 = new ImageView("quanlytieccuoi/Images/iconapp.png");
        ct1.setFitWidth(100);
        ct1.setFitHeight(100);
        ct1.setTranslateY(-60);
        ct1.setTranslateX(260);
        ct1.setCursor(Cursor.HAND);
        ct1.setOnMouseClicked(e -> {
            utils.changeSceneHandler("FXML_home.fxml", e);
        });
        Label lb3 = new Label("PRODUCTION");
        lb3.setFont(new Font("ProximaNW01-Reg",16));
        lb3.setStyle("-fx-text-fill: #FFFFFF;");
        lb3.setTranslateX(260);
        lb3.setTranslateY(-58);
        Line l1 = new Line(0, 0, 30, 0);
        l1.setStrokeWidth(2);
        l1.setStroke(Color.web("#ffffff"));
        l1.setTranslateX(290);
        l1.setTranslateY(-45);
        Label lb4 = new Label("© 2020 WEDDING KINGDOM");
        lb4.setFont(new Font("ProximaNW01-Reg",18));
        lb4.setStyle("-fx-text-fill: #FFFFFF;");
        lb4.setTranslateX(195);
        lb4.setTranslateY(-30);
        
        hfb.getChildren().addAll(rec,fb);
        hins.getChildren().addAll(rec1,ins);
        htw.getChildren().addAll(rec2,tw);
        v1.getChildren().addAll(lb1,hfb,hins,htw);
        v2.getChildren().addAll(lb2,lb5,email);
        
        v3.getChildren().addAll(ct1,lb3,l1,lb4);
        hbox.getChildren().addAll(v1, v2, v3);
        hbox.setId("imgContact");
        hbox.setPrefSize(1240, 100); 
        flowPane.getChildren().addAll(hbox);
       
    }
    
}
