package p3l_8980.com.atmaauto.Controller;

public class Position {
    private String id;
    private String name;

    public Position(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Position){
            Position c = (Position ) obj;
            if(c.getName().equals(name) && c.getId()==id ) return true;
        }

        return false;
    }
}
