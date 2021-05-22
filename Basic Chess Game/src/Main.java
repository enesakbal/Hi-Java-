import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class Main {
    public static LinkedList<Piece> ps = new LinkedList<>();
    public static Piece selectedPiece = null;


    public static void main(String[] args) throws IOException {




        JFrame frame = new JFrame("Chess");
        frame.setBounds(10,10,512,512);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        BufferedImage all= ImageIO.read(new File("C:\\Users\\Tom Bombadil\\Desktop\\chess.png"));
        Image imgs[]=new Image[12];
        int ind=0;
        for(int y=0;y<400;y+=200){
            for(int x=0;x<1200;x+=200){
                imgs[ind]=all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                ind++;
            }
        }

        {
            Piece brook = new Piece(0, 0, false, "rook", ps);
            Piece bkinght = new Piece(1, 0, false, "knight", ps);
            Piece bbishop = new Piece(2, 0, false, "bishop", ps);
            Piece bqueen = new Piece(3, 0, false, "queen", ps);
            Piece bking = new Piece(4, 0, false, "king", ps);
            Piece bbishop2 = new Piece(5, 0, false, "bishop", ps);
            Piece bkight2 = new Piece(6, 0, false, "knight", ps);
            Piece brook2 = new Piece(7, 0, false, "rook", ps);
            Piece bpawn1 = new Piece(1, 1, false, "pawn", ps);
            Piece bpawn2 = new Piece(2, 1, false, "pawn", ps);
            Piece bpawn3 = new Piece(3, 1, false, "pawn", ps);
            Piece bpawn4 = new Piece(4, 1, false, "pawn", ps);
            Piece bpawn5 = new Piece(5, 1, false, "pawn", ps);
            Piece bpawn6 = new Piece(6, 1, false, "pawn", ps);
            Piece bpawn7 = new Piece(7, 1, false, "pawn", ps);
            Piece bpawn8 = new Piece(0, 1, false, "pawn", ps);

            Piece wrook = new Piece(0, 7, true, "rook", ps);
            Piece wkinght = new Piece(1, 7, true, "knight", ps);
            Piece wbishop = new Piece(2, 7, true, "bishop", ps);
            Piece wqueen = new Piece(3, 7, true, "queen", ps);
            Piece wking = new Piece(4, 7, true, "king", ps);
            Piece wbishop2 = new Piece(5, 7, true, "bishop", ps);
            Piece wkight2 = new Piece(6, 7, true, "knight", ps);
            Piece wrook2 = new Piece(7, 7, true, "rook", ps);
            Piece wpawn1 = new Piece(1, 6, true, "pawn", ps);
            Piece wpawn2 = new Piece(2, 6, true, "pawn", ps);
            Piece wpawn3 = new Piece(3, 6, true, "pawn", ps);
            Piece wpawn4 = new Piece(4, 6, true, "pawn", ps);
            Piece wpawn5 = new Piece(5, 6, true, "pawn", ps);
            Piece wpawn6 = new Piece(6, 6, true, "pawn", ps);
            Piece wpawn7 = new Piece(7, 6, true, "pawn", ps);
            Piece wpawn8 = new Piece(0, 6, true, "pawn", ps);
        }




        JPanel panel = new JPanel(){
            @Override
            public void paint(Graphics g) {
                boolean white = true;
                for (int i = 0 ; i < 8 ; i++){

                    for (int j = 0 ; j < 8 ; j++){

                        if (white){
                            g.setColor(Color.white.darker());
                        }
                        else
                            g.setColor(Color.darkGray.brighter());

                        g.fillRect(i*64, j*64, 64, 64);

                        white =! white;
                    }
                    white =! white;
                }
                for(Piece p: ps){
                    int ind=0;
                    if(p.name.equalsIgnoreCase("king")){
                        ind=0;
                    }
                    if(p.name.equalsIgnoreCase("queen")){
                        ind=1;
                    }
                    if(p.name.equalsIgnoreCase("bishop")){
                        ind=2;
                    }
                    if(p.name.equalsIgnoreCase("knight")){
                        ind=3;
                    }
                    if(p.name.equalsIgnoreCase("rook")){
                        ind=4;
                    }
                    if(p.name.equalsIgnoreCase("pawn")){
                        ind=5;
                    }
                    if(!p.isWhite){
                        ind+=6;
                    }
                    g.drawImage(imgs[ind], p.x, p.y, this);
                }

            }
        };



        frame.add(panel);
        frame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(selectedPiece != null){

                    selectedPiece.x = e.getX()-32;
                    selectedPiece.y = e.getY()-32;
                    frame.repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (getPiece(e.getX(), e.getY()) != null)
                    selectedPiece = getPiece(e.getX(), e.getY());


            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (selectedPiece != null){
                    selectedPiece.move(e.getX()/64, e.getY()/64);
                    frame.repaint();
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {


            }
        });



        frame.setVisible(true);
    }
    public static Piece getPiece(int x , int y){
        int xp = x/64;
        int yp = y/64;

        for (Piece piece : ps){
            if (xp == piece.xp && yp == piece.yp){
                return piece;
            }

        }

        return null;


    }
}
