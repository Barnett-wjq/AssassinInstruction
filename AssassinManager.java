import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.util.List;

public class AssassinManager{

    private AssassinNode first;
    private AssassinNode current;
    private AssassinNode nextOne;

    private AssassinNode firstKilled;
    private AssassinNode killed;

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
                    if(i == 0){
                        first = current;
                        AssassinNode c2 = first;
                        AssassinNode c3 = null;
                        while(c2.next != null){
                            c3 = c2;
                            c2 = c2.next;
                            c2.killer = c3.name;
                        }
                        c2.killer = first.name;
                        first.killer = c2.name;
                    }
                }
            }
        }else{
            throw new IllegalArgumentException();
        }
    }

    public void printKillRing(){
        AssassinNode current = first;
        while(current.next != null){
            System.out.println(current.name+" is stalking "+current.next.name);
            current = current.next;
        }
        System.out.println(current.name+" is stalking "+first.name);
    }

    public void printGraveyard(){
        AssassinNode killed = firstKilled;
        if(killed!=null){
            System.out.println(killed.name+" was killed by "+killed.killer);
            while(killed.next != null){
                killed = killed.next;
                System.out.println(killed.name+" was killed by "+killed.killer);
            }
        }
    }

    public boolean killRingContains(String name){
        AssassinNode current = first;
        boolean mark = false;
        while (current.next != null){
            if(name.toUpperCase().equals(current.name.toUpperCase())){
                mark = true;
                break;
            }
            current = current.next;
        }
        if(name.equals(current.name)) mark = true;
        return mark;
    }

    public boolean graveyardContains(String name){
        AssassinNode killed = firstKilled;
        boolean mark = false;
        if(killed != null){
            while (killed.next != null){
                if(name.toUpperCase().equals(killed.name.toUpperCase())){
                    mark = true;
                    break;
                }
                killed = killed.next;
            }
            if(name.equals(killed.name)) mark = true;
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
        AssassinNode current = first;
        String uName = name.toUpperCase();
        String uCurrentName = first.name.toUpperCase() ;
        if(uName.equals(uCurrentName)){
            killed = new AssassinNode(current.name);
            killed.killer = current.killer;
            first = current.next;
            if(firstKilled != null){
                isFirstKilled(firstKilled,killed);
            }else{
                firstKilled = killed;
            }
        }
        while (current.next != null){
            nextOne = current;
            current = current.next;
            uCurrentName = current.name.toUpperCase();
            if(uName.equals(uCurrentName)){
                killed = new AssassinNode(current.name);
                killed.killer = current.killer;
                if(firstKilled != null){
                    isFirstKilled(firstKilled,killed);
                }else{
                    firstKilled = killed;
                }
                nextOne.next = current.next;
            }
        }
        uCurrentName = current.name.toUpperCase();
        if(uName.equals(uCurrentName)){
            first.killer = nextOne.name;
        }

    }

    private void isFirstKilled(AssassinNode first,AssassinNode killed){
        if(first.next == null){
            first.next = killed;
        }else{
            isFirstKilled(first.next,killed);
        }
    }
}