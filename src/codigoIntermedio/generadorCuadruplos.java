package codigoIntermedio;

import java.awt.FontMetrics;
import java.util.ArrayList;

import javax.swing.JTextPane;

import Entidades.Cuadruplos;
import main.Identificador;
import main.Token;

public class generadorCuadruplos{

	private ArrayList<Identificador> tSymb;
	private ArrayList<String> cuads;
	private ArrayList<ArrayList<Cuadruplos>> objs;
	private ArbolExpresion tree;
	private String output = "";
	private JTextPane txtpane;
	private boolean bl = false;

	public generadorCuadruplos(){ }
	public generadorCuadruplos(ArrayList<Identificador> t,JTextPane view){
		tSymb = t;
		txtpane = view;
		cuads = new ArrayList<>();
		objs = new ArrayList<>();
		//arbol = new ArbolExpresion();
	}

	public ArrayList<Identificador> retTable(){
		return tSymb;
	}
	public String retOutput(){
		return output;
	}
	public String delimiliter(){
		FontMetrics fm = txtpane.getFontMetrics(txtpane.getFont());
		int charwidth = fm.charWidth('w');
		int total_width = txtpane.getWidth();
		int num = total_width/charwidth;
		String doubleline = "";
		for (int i = 0; i < num-3; i++) {
			doubleline += "=";
		}
		return doubleline += "\n";
	}
	public ArrayList<Cuadruplos> retC(){
		return tree.retList();
	}
	public ArrayList<String> retStr(){
		return cuads;
	}
	public ArrayList<ArrayList<Cuadruplos>> retObjs(){
		return objs;
	}
	public void genarateCuadruples(){
		output = "";
		int i = 1;
		String men="",aux = "";
		int space = 11;
		String delimiter = delimiliter();
		for( Identificador ide: tSymb){
			//System.out.println(ide.getNombre());
			if( !ide.getTipo().equals("-") && ide.getExp() != null){
				output += delimiter;
				output += String.format("%25s %n","Cadruple #"+i);
				tree = new ArbolExpresion(ide,tSymb);
				ArrayList<Token> expression = ide.getExp();
				for(Token tok: expression){
					System.out.println(tok.getToken());
					tree.add(tok);
					men += " "+tok.getToken();
				}
					output += aux += String.format("%10s %s %s %n", ide.getNombre(),"=",men);

					output += aux += String.format("%"+space+"s "+"%"+space+"s "+"%"+space+"s "+
							"%"+space+"s %n", "Operador","Operand1","Operand2","Resultado");

				String val = tree.solve();
				ide.setValor(val);
				output += aux += tree.result;
				i++;
				men = "";
				cuads.add(aux);
				objs.add(tree.retList());
				aux = "";
			}
		}
		output += delimiter;
	}
} 