package uiController;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import apiConnector.ApiConnector;
import classes.Competition;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;



public class Controller {

    @FXML
    private TextField areaCountryCode;

    @FXML
    private TextField areaId;

    @FXML
    private TextField areaName;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnPr;

    @FXML
    private TextField compId;

    @FXML
    private TextField compName;


    private int i=0;

    private final List<Competition> competitions = setComp();


    @FXML
    public void initialize()  {
       
        compId.setText(this.competitions.get(0).id + "");
        compName.setText(this.competitions.get(0).name + "");
        areaId.setText(this.competitions.get(0).area.id + "");
        areaName.setText(this.competitions.get(0).area.name + "");

        areaCountryCode.setText(this.competitions.get(0).area.countryCode + "");



    }


    @FXML
   void next(ActionEvent event)  {

            compId.setText(competitions.get(this.i + 1).id + "");
            compName.setText(competitions.get(this.i + 1).name + "");
            areaId.setText(competitions.get(this.i + 1).area.id + "");
            areaName.setText(competitions.get(this.i + 1).area.name + "");
            areaCountryCode.setText(this.competitions.get(this.i + 1).area.countryCode+ "");

            this.i++;
        
    }



    @FXML
    void previous(ActionEvent event)  {
        
            
            btnNext.setDisable(false);
            compId.setText(this.competitions.get(this.i-1).id + "");
            compName.setText(this.competitions.get(this.i-1).name + "");
            areaId.setText(this.competitions.get(this.i-1).area.id + "");
            areaName.setText(this.competitions.get(this.i-1).area.name + "");
            areaCountryCode.setText(this.competitions.get(this.i-1).area.countryCode+ "");
            this.i--;

        
    }


    public List<Competition> setComp() {
        ApiConnector ui=new ApiConnector();
        String body=ui.request("competitions");
        Gson gson =new Gson();
        JsonObject jsonObject;
        jsonObject =gson.fromJson(body,JsonObject.class);
        Type competitionListType=new TypeToken<ArrayList<Competition>>(){}.getType();
        List<Competition> competitions =gson.fromJson((jsonObject.get("competitions")),competitionListType);
        return competitions;

    }

    }

