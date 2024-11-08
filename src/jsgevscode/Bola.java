package jsgevscode;

import java.awt.Color;
import br.com.davidbuzatto.jsge.core.engine.EngineFrame;

public class Bola {

    // bolinha
    double x, y, raio;
    double vx, vy;

    // forças da natureza da bolinha
    double atrito;
    double elasticidade;

    Color cor;

    void desenhar(EngineFrame e) {
        e.fillCircle(x, y, raio, cor);
    }
}
