import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class ToyApplication {

    public static void main(String...args) {
        System.out.println("Starting up the toy application!");
        Quarkus.run(args);
    }
}
