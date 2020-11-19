import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;

// konsep yang digunakan pada aplikasi ini adalah membaca data pada file daftarBelanjaan yang merupakan daftar pesanan mama
// yang mana daftar tersebut akan dibelikan ke market. pada market tersebut terdapat daftarHarga yang berisi barang dan hrga barang tersebut
// jadi pada aplikasi ini, program membaca data pada 2 file.


public class GoToASCII extends JFrame implements ActionListener{

	ArrayList<String> data = new ArrayList<String>();
	static ArrayList<String> getData(String input) {
		ArrayList<String> list = new ArrayList<String>();
	    BufferedReader read;
	    
	    try {
	    	read = new BufferedReader(new FileReader(input));
	    	String line = read.readLine();
	    	while(line!=null) {
	    		list.add(line);
	    		line = read.readLine();
	    	}
	    	read.close();
	    }catch(IOException e) {
	    	e.printStackTrace();
	    }
	    return list;
	}
	
	static int harga (ArrayList<String> belanjaan) {
		ArrayList<String> daftarHarga = getData("src/daftarHarga.txt");
		int sum =0;
		
		for(int i=0; i< belanjaan.size();i++) {
			for (int j=0; j< daftarHarga.size();j++) {
				String[] hg = daftarHarga.get(j).split(" ");
				if(belanjaan.get(i).compareTo(hg[0])==0) {
					sum+=Integer.parseInt(hg[1]);
					break;
				}
			}
		}
		return sum;
	}
	
	private JPanel contentPane;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GoToASCII frame = new GoToASCII();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public String getTextData(ArrayList<String> string){
		String sum = "";
		for(int i=0;i<string.size();i++)
			sum+=string.get(i)+"\n";
		return sum;
	}
	
	public GoToASCII() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNisa = new JLabel("DAFTAR BELANJAAN MAMA");
		panel.add(lblNisa);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.EAST);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new MigLayout("", "[grow][][][][grow][][grow][][][][grow]", "[][][grow]"));
		
		JLabel lblBelanjaanMamaNisa = new JLabel("Daftar Harga");
		panel_3.add(lblBelanjaanMamaNisa, "flowx,cell 0 1 5 1");
		
		data = getData("src/daftarHarga.txt");
		
		JLabel lblStruk = new JLabel("Belanjaan Mama");
		panel_3.add(lblStruk, "cell 6 1");
		
		JScrollPane scrollPane = new JScrollPane();
		panel_3.add(scrollPane, "cell 0 2,grow");
		JTextPane textPane_1 = new JTextPane();
		scrollPane.setViewportView(textPane_1);
		textPane_1.setText(getTextData(data));
		data = getData("src/daftarBelanjaan.txt");
		
		JButton btnBeli = new JButton("Beli");
		btnBeli.addActionListener(this);
		panel_3.add(btnBeli, "cell 5 2");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_3.add(scrollPane_1, "cell 6 2,grow");
		scrollPane_1.setViewportView(textPane);
		textPane.setText(getTextData(data));
	}
	
	JTextPane textPane = new JTextPane();
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String total = getTextData(data);
		total += "==============\ntotal: "+harga(data);
		textPane.setText(total);
	}

}
