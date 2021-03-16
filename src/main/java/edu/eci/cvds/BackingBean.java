package edu.eci.cvds;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;

import java.util.ArrayList;

@ManagedBean(name = "calculadoraBean")
@SessionScoped

public class BackingBean {
	private int media;
	private int moda;
	private int desviacionE;
	private int varianza;
	private ArrayList<Integer> valores;
		
	public BackingBean() {
		restart();
	}
	
	public int getMedia() {
		return media;
	}
	
	public int getModa() {
		return moda;
	}
	
	public int getDesviacionE() {
		return desviacionE;
	}
	
	public int getVarianza() {
		return varianza;
	}
	
	public void setMedia(int Media) {
		media=Media;
	}
	
	public void setModa(int Moda) {
		moda=Moda;
	}
	
	public void setDesviacionE(int DesviacionE) {
		desviacionE=DesviacionE;
	}
	
	public void setVarianza(int Varianza) {
		varianza=Varianza;
	}
	
	public void calculate(String lista) {
		valores = new ArrayList<Integer>();
        lista+=";";
        String valor="";
        for(int i=0;i<lista.length();i++){
            if(lista.charAt(i)!=';'){
                valor += lista.charAt(i)+"";
            }
            else {
                valores.add(Integer.parseInt(valor));
                valor="";
            }
        }
        //intentos.add(valores);
        media=calculateMean(valores);
        desviacionE=calculateStandardDeviation(valores);
        varianza=calculateVariance(valores);
        moda=calculateMode(valores);
	}
	
	public int calculateMean(ArrayList<Integer> valores) {
		int rta=0;
		for(int i=0; i<valores.size(); i++) {
			rta+=valores.get(i);
		}
		return rta/valores.size();
	}
	
	public int calculateStandardDeviation(ArrayList<Integer> valores) {
		int varianza=calculateVariance(valores);
		return (int) Math.pow(varianza, 0.5);
	}
	
	public int calculateVariance(ArrayList<Integer> valores) {
		int prom=calculateMean(valores);
		int sum=0;
		for(int i=0; i < valores.size(); i++) {
			sum+=Math.pow(valores.get(i)-prom, 2);
		}
		return sum/(valores.size()-1);
	}
	
	public int calculateMode(ArrayList<Integer> valores) {
		int temp=0;
		int repeticion=0;
		int moda=0;
		for(int i=0; i<valores.size(); i++) {
			int numero=valores.get(i);
			
			for(int j=0; j<valores.size(); j++) {
				if(valores.get(j)==numero) {
					temp+=1;
				}
			}
			
			if(repeticion<temp) {
				repeticion=temp;
				moda=numero;
			}
			temp=0;
		}
		return moda;
	}
	
	public void restart() {
		media=0;
		moda=0;
		desviacionE=0;
		varianza=0;
		valores=new ArrayList<>();
	}

}
