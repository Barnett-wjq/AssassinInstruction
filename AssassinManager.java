import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.util.List;

public class AssassinManager{

    private AssassinNode node;

    //Contructors
    public AssassinManager(){
        super();
    }
    public AssassinManager(List<String> names){
        if (names != null && names.size() != 0){
            for(int i = 0;i<names.size();i++){
                node = new AssassinNode(names.get(i));
            }
        }else{
            throw new IllegalArgumentException("");
        }
    }

    public void printKillRing(){

    }

    public void printGraveyard(){

    }

    public boolean killRingContains(String name){

        return false;
    }

    public boolean graveyardContains(String name){
        return false;
    }

    public boolean isGameOver(){
        return false;
    }

    public String winner(){
        return "";
    }

    public void kill(String name){

    }
}