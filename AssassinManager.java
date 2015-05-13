import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.util.List;

public class AssassinManager{

    private AssassinNode first;
    private AssassinNode current;
    private AssassinNode nextOne;

    private AssassinNode firstKilled;
    private AssassinNode killed;
    private AssassinNode nextKilled;

    //Contructors
    public AssassinManager(){
        super();
    }
    public AssassinManager(List<String> names){
        if (names != null && names.size() != 0){
            for(int i = names.size()-1;i>=0;i--){
                if(i == names.size()-1){
                    current = new AssassinNode(names.get(i));
                    nextOne = current;
                }else{
                    current = new AssassinNode(names.get(i),nextOne);
                    nextOne = current;
                    if(i == 0) first = current;
                }
            }
        }else{
            throw new IllegalArgumentException();
        }
    }

    public void printKillRing(){
        current = first;
        while(current.next != null){
            System.out.println(current.name+" is stalking "+current.next.name);
            current = current.next;
        }
        System.out.println(current.name+" is stalking "+first.name);
    }

    public void printGraveyard(){
        killed = firstKilled;
        while(killed.next != null){
            System.out.println(killed.name+" was killed by "+killed.killer);
            killed = killed.next;
        }
    }

    public boolean killRingContains(String name){
        current = first;
        boolean mark = false;
        while (current.next != null){
            if(name.equals(current.name)){
                mark = true;
                break;
            }
            current = current.next;
        }
        return mark;
    }

    public boolean graveyardContains(String name){
        killed = firstKilled;
        boolean mark = false;
        while (killed.next != null){
            if(name.equals(killed.name)){
                mark = true;
                break;
            }
            killed = killed.next;
        }
        return mark;
    }

    public boolean isGameOver(){
        boolean mark = false;
        if (first.next == null){
            mark = true;
        }
        return mark;
    }

    public String winner(){
        return first.name;
    }

    public void kill(String name){
        current = first;
        String uName = name.toUpperCase();
        String uCurrentName = first.name.toUpperCase() ;
        AssassinNode prev = null;
        while(current.next != null){
            uCurrentName = current.name.toUpperCase();
            if(uName.equals(uCurrentName)){
                killed = new AssassinNode(current.name);
                if(firstKilled == null){
                    firstKilled = killed;
                }else{
                    firstKilled.next = killed;
                }
                break;
            }
            current = current.next;
        }
        if(firstKilled == null){
            firstKilled = killed;
        }

        while(current.next != null){
            if(killed.name.equals(current.name)){
                if(prev == current){
                    first = current.next;
                }else{
                    prev.next = current.next.next;
                }
            }
            prev = current;
            current = current.next;
        }

    }
}