package Cuadrado_Magico;


public class backcm {
    private int dimension;
    private int [][] sol;
    
    public backcm (int dimension) {
        this.dimension=dimension;
        sol= new int [dimension][dimension]; 
        Iniciar();
    }
    
    public void Iniciar () {
        back(0,0,0); 
    }
    
    private void back (int etapa,int fila,int columna) {
        int i;
        
        if (etapa==dimension*dimension){
            if (esCuadradoMagico())
                System.out.println(toString());
        }
        else {
            for(i=1;i<=9;i++) {
                if (esPosible(i)) {
                    sol[fila][columna]=i;                    
                    if (columna==dimension-1)
                        back(etapa+1,fila+1,0);
                    else
                        back(etapa+1,fila,columna+1);
                    sol[fila][columna]=0;
                }
            }
        }
    }        
    
    private boolean esPosible(int valor){
        boolean encontrado=false;
        for (int i=0;i<sol.length  && !encontrado;i++) {
            for (int j=0;j<sol[i].length && !encontrado;j++) {
                if (sol[i][j]==valor)
                    encontrado=true;
            }
        }
        return !encontrado;
    }
    
    public boolean esCuadradoMagico() {
        boolean cuadradomagico=true;
        int sumafilas=0, sumacolumnas=0,dig1=0,dig2=0;
        int c1=0,c2=sol.length;
        for (int i=0;i<sol.length && cuadradomagico;i++) {
            sumafilas=0;sumacolumnas=0;
            dig1=0;
            dig2=0;
            for (int j=0;j<sol[i].length && cuadradomagico;j++) {
                sumafilas+=sol[i][j];
                
                sumacolumnas+=sol[j][i];
                
                if(j==i){
                	dig1+=sol[i][j];
                }
                for (int F=sol.length-1; F>=0; F--){
                    for (int M=0; j<M; M++){
                        if (sol.length-1-F == j) 
                            dig2=sol[F][M]; 
                    }
                }
            }
            if (sumafilas<15 || sumacolumnas<15||dig1<15||dig2<15)                
                cuadradomagico=false;            
        }
        return cuadradomagico;
    }
    
    // metodo que imprime como ha quedado el cuadrado magico
    public String toString () {
        String string="";
        for (int i=0;i<sol.length;i++) {
            for (int j=0;j<sol[i].length;j++) {
                string+=sol[i][j] + " ";
            }
            string+="\n";
        }               
        return string;
    } 
}
