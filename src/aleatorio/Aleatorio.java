package aleatorio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.Ref;

import javax.sound.midi.Soundbank;

public class Aleatorio {
	String[] codes = { "p1", "p2", "p3" };
	String[] descricion = { "parafusos", "cravos ", "tachas" };
	int[] prices = { 3, 4, 5 };
	String ruta = "C:\\Users\\David\\Desktop\\aleatorio.txt";
	File fichero = new File(ruta);
	RandomAccessFile raf;

	public void escritura() {

		try {
			if (!fichero.exists())
				fichero.createNewFile();

			raf = new RandomAccessFile(fichero, "rw");

			int i = 0;
			while (i < codes.length) {
				raf.writeChars(String.format("%-3s", codes[i]).replace(" ", "*"));
				raf.writeChars(String.format("%-10s", descricion[i]).replace(" ", "*"));
				raf.writeInt(prices[i]);
				i++;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void lectura() {
		try {
			raf = new RandomAccessFile(fichero, "r");
			raf.seek(posicion(2));

			String cod ="";
			String des ="";
			int pre;
			
			for (int i = 0; i < 3; i++) {
				cod += String.valueOf(raf.readChar()).replace("*", "");
			}
			for (int i = 0; i < 10; i++) {
				des += String.valueOf(raf.readChar()).replace("*", "");
			}
			pre = raf.readInt();

			System.out.println(cod + "\n" + des + "\n" + pre);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				raf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public long posicion(int campo) {
		long pos = (campo - 1) * 30;
		return pos;
	}

	public static void main(String[] args) {
		Aleatorio obj = new Aleatorio();
		obj.escritura();
		obj.lectura();
	}

}
