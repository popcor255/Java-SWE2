
public class changePainting{
    Painting painting = new Painting();
    String info = painting.getInfo();
    String artist = painting.getArtist();
    String createdAt = painting.getCreatedAt();
    String style = painting.getStyle();


    public boolean changePainting(Obj obj){

        if(obj != null){
            Constructable cons = new Constructable(obj, "Machine Learning", "K-MEANS", "FUSED");
            painting = new Painting(cons.name, cons.info, cons.artist, cons.createdAt, cons.style);
            return 1;
        }
        
        return 0;
    }

}