package ordonez.roger.bitacoraactivity;

/**
 * Created by roger on 22/03/2018.
 */

public class BitacoraItem {

    private String tasca;
    private String hora;
    private String diaMesAny;


    public BitacoraItem(String tasca, String hora, String diaMesAny) {
        this.tasca = tasca;
        this.hora = hora;
        this.diaMesAny = diaMesAny;
    }

    public String getTasca() {
        return tasca;
    }

    public void setTasca(String tasca) {
        this.tasca = tasca;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDiaMesAny() {
        return diaMesAny;
    }

    public void setDiaMesAny(String diaMesAny) {
        this.diaMesAny = diaMesAny;
    }
}
