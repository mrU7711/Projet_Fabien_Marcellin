import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine{
    private ArrayList<Displayable> renderList;
    private double cameraOffsetX = 0;
    private double cameraOffsetY = 0;


    public void setCameraPosition(double x, double y) {
        this.cameraOffsetX = x;
        this.cameraOffsetY = y;
    }



    public RenderEngine(JFrame jFrame) {
        renderList = new ArrayList<>();
    }

    public void addToRenderList(Displayable displayable){
        if (!renderList.contains(displayable)){
            renderList.add(displayable);
        }
    }

    public void addToRenderList(ArrayList<Displayable> displayable){
        if (!renderList.contains(displayable)){
            renderList.addAll(displayable);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Displayable renderObject : renderList) {
            Graphics2D g2d = (Graphics2D) g.create();
            // Décale les sprites en fonction de la caméra
            g2d.translate(-cameraOffsetX, -cameraOffsetY);
            renderObject.draw(g2d);
            g2d.dispose();
        }
    }

    @Override
    public void update(){
        this.repaint();
    }
}