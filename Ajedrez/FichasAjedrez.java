package Ajedrez;

public class FichasAjedrez {

    public FichasAjedrez () {
        Inicio();
    }
            
    public static void main(String[] args) {
        new FichasAjedrez();
    }
        
    // Valores iniciales validos, declaracion de objetos y llamada al 
    //metodo recursivo
    public void Inicio(){
        // falta implementar peticion de valores validos para casilla origen y 
        // destino, cambiar valores de casilla origen y destino para 
        // comprobar funcionamiento correcto.
        Casilla origen = new Casilla (5,0);
        Casilla destino = new Casilla (7,4);
        
    //    if(origen.existeCamino(destino)){            
            Tablero t=new Tablero();
            System.out.println(t);
            Camino c= new Camino();
            t.PonerVisitado(origen);
            c.insertarCasilla(origen);            
            alfil(origen,destino,t,c);          
        }
     /*   else{
            System.out.println("LOS CAMINOS DEL SEÑOR SON INESCRUTABLES");
        }*/
 //   }
    
    // metodo recursivo para los caminos minimos de un alfil entre una casilla
    // origen y una casilla destino
    public void alfil(Casilla origen,Casilla destino,Tablero t, Camino c){
        int i,j;
        Casilla aux;
        if(origen.equals(destino)){
            System.out.println("CAMINO "+ (c.getMostrados()+1)+ ": "+ c);
        }else{
            for(i=-2;i<=2;i++){
                for(j=-2;j<=2;j++){
                    aux=new Casilla(origen.getFila()+i,origen.getColumna()+j);
                    if(t.estaDentro(aux)){ // esta la casilla dentro del tablero
                        if(!t.EstaVisitado(aux)){ // esta la casilla no visitada
                          if(Math.abs(i)+Math.abs(j)==3){//pertenece a los movimientos posibles en diagonal
                                if(aux.distanciaM(destino)<origen.distanciaM(destino)){ // distancia menor desde la nueva casilla aux
                                    t.PonerVisitado(aux);
                                    c.insertarCasilla(aux);
                                    alfil(aux,destino,t,c);
                                    t.QuitarVisitado(aux);
                                    c.eliminarCasilla();
                                }
                          }
                        }
                    }
                }
            }
        }
    }
}
