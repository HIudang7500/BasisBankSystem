package chieuthu2;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Giaodien implements ActionListener {
	Frame f = new Frame("ATM");
	Label l1 = new Label("so tai khoan");
	TextField tf1 = new TextField(20);
	Button tao = new Button("tao");
	Label l2 = new Label(" tai khoan");
	TextField tf2 = new TextField(25);
	Button nhap = new Button("Nhap");
	Label l3 = new Label("Nap tien");
	TextField tf3 = new TextField(25);
	Button nap = new Button("Nap");
	Label l4 = new Label("Rut tien");
	TextField tf4 = new TextField(25);
	Button rut = new Button("Rut");
	Label l5 = new Label("Chuyen khoan");
	TextField tf5 = new TextField(10);
	Label l6 = new Label("So tien");
	TextField tf6 = new TextField(10);
	Button chuyen = new Button("Chuyen");
	
	Label thongtin = new Label("Thong tin tai khoan");
	TextField tf7 = new TextField(20);
	
	
	Button hienthi = new Button("Hien thi danh sach tai khoan");
	TextArea ta1 = new TextArea(10, 40);
	
	Button luu = new Button("Luu File");
	Button dong = new Button("Dong");
	
	ArrayList<ATM> atm = new ArrayList<ATM>() ;//Tạo chuối đối tượng ATM
	int id = 0;
	
	public Giaodien() {
		
		Transfer();  // thực hiện lệnh nhập data từ file 
		f.setLayout(new FlowLayout());
		f.add(l1); f.add(tf1);  f.add(tao);
		f.add(l2); f.add(tf2); f.add(nhap);
		f.add(l3); f.add(tf3); f.add(nap);
		f.add(l4); f.add(tf4);  f.add(rut);
		f.add(l5); f.add(tf5);  f.add(l6);  f.add(tf6);  f.add(chuyen);
		 f.add(thongtin); f.add(tf7);
		 f.add(hienthi);  f.add(ta1);
		 f.add(luu); f.add(dong);
		 nhap.addActionListener(this);
		 tao.addActionListener(this);
		 nap.addActionListener(this);
		 rut.addActionListener(this);
		 chuyen.addActionListener(this);
		 hienthi.addActionListener(this);
		 luu.addActionListener(this);
		 dong.addActionListener(this);
		 
		 f.setSize(430,550);
	    f.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource() == dong) {
			System.exit(0);
		}
		if(ae.getSource() == tao) {
			tf7.setText("");
			int i =0;
			String loi = new String("Tai khoan da ton tai");
			while(i < atm.size()) {
				if ((atm.get(i)).laySTK().equals(tf1.getText())) { // atm[i]
					 tf7.setText(loi);
					 tf1.setText("");
					 break;
				}
				i++;
			}
			String s = new String(tf7.getText());
			if (!(s.equals(loi))) {
				ATM tao = new ATM(tf1.getText(), 0); //tài khoản mới tạo với s là tên tk lấy từ textfield 1, số dư 0
				atm.add(tao); // them tài khoản mới tạo vào atm
				tf7.setText("Tạo tài khoản \""+ tf1.getText() + "\" thành công");
				tf1.setText("");
			}
		}		
		
		if(ae.getSource() == nhap) {
			int i = 0;
			int trung = 0;
			String loi = new String("Tai khoan khong ton tai");
			while(i < atm.size()) { // so sánh phần tử atm 
				if (((atm.get(i)).laySTK()).equals(tf2.getText())) {
					id = i ;
					trung = 1;
					tf7.setText("OK");
					break;
				}
				i++;
			}
			
			if(trung == 0) {
				tf7.setText(loi);
				tf2.setText("");
			}
			
		}
		if(ae.getSource() == nap  ) {
			(atm.get(id)).napTien(Double.parseDouble(tf3.getText()));
			tf7.setText("So tai khoan: "+ (atm.get(id)).laySTK() + ", so du: " + (atm.get(id)).laySoDu());
		}
		if(ae.getSource() == rut ) {
			
			boolean b = (atm.get(id)).rutTien(Double.parseDouble(tf4.getText()));
			if(b) tf7.setText("So tai khoan: "+ (atm.get(id)).laySTK() + ", so du: " + (atm.get(id)).laySoDu());
			else tf7.setText("Khong du tien");
		}
		
		if(ae.getSource() == chuyen) {
			int i = 0;
			String loi = new String("Tai khoan chuyen khong ton tai");
			int loi1 = 0;
			while(i < atm.size()) {
				if (((atm.get(i)).laySTK()).equals(tf5.getText())) {
					boolean b = (atm.get(id)).chuyenTien((atm.get(i)), Double.parseDouble(tf6.getText()));
					if(b) tf7.setText("So tai khoan: "+ (atm.get(id)).laySTK() + ", so du: " + (atm.get(id)).laySoDu() + ", chuyen tien thanh cong");
					else  tf7.setText("Khong du tien");
					loi1 =1;
					break;
				}
				i++;
			}
			if(loi1 == 0) {
				tf7.setText(loi);
			}
			
		}
		if(ae.getSource() == hienthi) {
			//thực hiện lệnh nhập data từ file
			ta1.setText("");
			int i = 0 ;
			while(i < atm.size()) {
				ta1.append(i+1 + ". Tài khoản: "+ (atm.get(i)).laySTK() + ", số dư: " + (atm.get(i)).laySoDu() +"\n");
				i++;
			}
		}
		if(ae.getSource() == luu) { 
			try {
				ta1.setText("Lưu File thành công");
				int i = 0;
				FileOutputStream fos = new FileOutputStream("ATM.txt");
				DataOutputStream dos = new DataOutputStream(fos);
				while (i < atm.size()) {
					dos.writeBytes((atm.get(i).laySTK())+" "+(atm.get(i).laySoDu()) + "\n");
//					ta1.append((atm.get(i).laySTK())+" "+(atm.get(i).laySoDu()));
					i++;
				}
				dos.close();
				fos.close();
			}
			catch(IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	public void Transfer() { // cchuyển dữ liệu từ file vào list	atm 
		String s;	
		try {
			FileInputStream f1 = new FileInputStream("ATM.txt");
			DataInputStream df1 = new DataInputStream(f1); 
			int i=0;
			while ((s= df1.readLine()) != null) {
				String[] part = s.split(" "); // đoạn này tách cái xxx yyy ra từ chỗ dấu cách thành part 0 part 1
				ATM taikhoannhap = new ATM(part[0], Double.parseDouble(part[1]));
				atm.add(taikhoannhap); // thêm dữ liệu mới vào chuỗi atm đã tạo ở dòng 38
				i++;
			}
			df1.close();
			f1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
