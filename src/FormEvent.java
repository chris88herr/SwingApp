import java.util.EventObject;

public class FormEvent extends EventObject {

    private String name;
    private String occupation;
    private int ageCat;


    private String taxId;
    private boolean citizen;

    public String getEmpCat() {
        return empCat;
    }

    private String empCat;

    public FormEvent(Object source){
        super(source);

    }

    public FormEvent(Object source, String name, String occupation,
                     int ageCat, String empCat, String taxId, boolean citizen){
        super(source);
        this.name = name;
        this.occupation = occupation;
        this.ageCat = ageCat;
        this.empCat = empCat;
        this.taxId = taxId;
        this.citizen = citizen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public int getAgeCat() {
        return ageCat;
    }

    public String getTaxId() {
        return taxId;
    }

    public boolean isCitizen() {
        return citizen;
    }

}
