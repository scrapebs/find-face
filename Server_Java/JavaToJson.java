import java.util.List;

public class JavaToJson {

    private List<Integer> Coords;

    public JavaToJson() {
    }

    public JavaToJson(List<Integer> _Coords) {
        Coords = _Coords;
    }

    public List<Integer> getCoords() {
        return Coords;
    }

    public void setCoords(List<Integer> Coords) {
        this.Coords = Coords;
    }

    @Override
    public String toString() {
        return "User [ messages="
                + Coords + "]";
    }

    public static void AddFace(List<Integer> Coords) {
        Coords.addAll(Coords);
    }
}