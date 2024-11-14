package jsgevscode;

import java.awt.Color;
import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.math.MathUtils;

public class Bola {

    // bolinha
    double x, y, raio;
    double vx, vy;

    // forças da natureza da bolinha
    double atrito;
    double elasticidade;

    boolean isBeingDragged;

    double offsetX, offsetY;

    double xAnterior, yAnterior;

    Color cor;

    void atualizar(double delta, EngineFrame e) {

        double xMouse = e.getMouseX();
        double yMouse = e.getMouseY();

        if (e.isMouseButtonPressed(e.MOUSE_BUTTON_LEFT)) {

            double cat1 = xMouse - x;
            double cat2 = yMouse - y;

            double distance = Math.sqrt((cat1 * cat1) + (cat2 * cat2));

            if (distance <= raio) {
                isBeingDragged = true;
            }

            offsetX = cat1;
            offsetY = cat2;

        }

        if (e.isMouseButtonReleased(e.MOUSE_BUTTON_LEFT)) {
            isBeingDragged = false;
        }

        if (isBeingDragged) {
            // bolinha sendo arrastada
            x = xMouse - offsetX;
            y = yMouse - offsetY;

            vx = (x - xAnterior) / delta;
            vy = (y - yAnterior) / delta;

            xAnterior = x;
            yAnterior = y;

        } else {
            // se não estiver sendo arrastada, atualiza a posição (está em movimento)
            x += vx * delta;
            y += vy * delta;

            if (x + raio >= e.getScreenWidth()) {
                x = e.getScreenWidth() - raio;
                vx = -vx * elasticidade;
            } else if (x - raio <= 0) {
                x = raio;
                vx = -vx * elasticidade;
            }

            if (y + raio >= e.getScreenHeight()) {
                y = e.getScreenHeight() - raio;
                vy = -vy * elasticidade;
            } else if (y - raio <= 0) {
                y = raio;
                vy = -vy * elasticidade;
            }
            vx *= atrito;
            vy = (vy * atrito) + Main.GRAVIDADE;
        }
    }

    void desenhar(EngineFrame e) {
        e.fillCircle(x, y, raio, cor);
    }
}
