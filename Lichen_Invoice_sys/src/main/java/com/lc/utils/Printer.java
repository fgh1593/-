package com.lc.utils;

import java.awt.Color;

import java.awt.Font;

import java.awt.Graphics;

import java.awt.Graphics2D;

import java.awt.print.PageFormat;

import java.awt.print.Paper;

import java.awt.print.Printable;

import java.awt.print.PrinterException;

import java.awt.print.PrinterJob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.attribute.Attribute;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.lc.pojo.Invoice;

@Component
public class Printer implements Printable {

	private int pageSize=1;// 列印的總頁數

	private final double paperW = 216;// 列印的紙張寬度

	private final double paperH = 396;// 列印的紙張高度
	
	private Invoice invoice;
	
	private PrinterJob prnJob = PrinterJob.getPrinterJob();
	

//實現java.awt.print.Printable介面的列印方法

//pageIndex:列印的當前頁，此參數是系統自動維護的，不需要手動維護，系統會自動遞增

	

	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

		if (pageIndex >= pageSize)

			//退出列印

			return Printable.NO_SUCH_PAGE;

		else {

			Graphics2D g2 = (Graphics2D) graphics;

			g2.setColor(Color.BLACK);

			Paper p = new Paper();

			//此處的paperW和paperH是從目標列印機的進紙規格中獲取的，實際針式列印機的可列印區域是有限的，
			//距紙張的上下左右1inch(英寸)的中間的距形框為實際可列印區域，超出範圍的內容將不會列印出來(沒有設置偏移的情況)
			//也可以手動指定，從是如果手動指定的寬高和目標列印機的進紙規格相差較大，將會默認以A4紙為列印模版

			p.setImageableArea(0, 0, paperW, paperH);// 設置可列印區域

			p.setSize(paperW, paperH);// 設置紙張的大小
			

			pageFormat.setPaper(p);

			drawCurrentPageText(g2);// 調用列印內容的方法

			return PAGE_EXISTS;

		}

	}

	// 列印內容

	private void drawCurrentPageText(Graphics2D g2) {

		Font font = null;

		//設置列印的字體

		font = new Font("Microsoft JhengHei", Font.PLAIN, 11);

		g2.setFont(font);// 設置字體

		//此處列印一句話，列印開始位置是(200,200),表示從pf.getPaper()中座標為(200,200)開始列印

		//此處200的單位是1/72(inch)，inch:英寸，所以這裏的長度，在測量後需要進行轉換
		
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String time = df.format(invoice.getTime());

		g2.drawString("利成塑膠廠", 20, 65);
		g2.drawString("台南市安南區怡安路二段500號", 20, 80);
		g2.drawString("統一編號:19709855 TEL:06-2475328", 20, 95);
		g2.drawString(time, 50, 110);
		g2.drawString(invoice.getInfo().getInvoiceTitle(), 50, 125);
		g2.drawString("統一編號:", 50, 140);
		g2.drawString(invoice.getInfo().getTxIDNumber(),100, 140);
		
		
		g2.drawString("品名_______________數量__________金額",20, 175);
		g2.drawString(invoice.getInvoiceItem().getName(),20, 190);
		g2.drawString("1",125, 190);
		g2.drawString(invoice.getTaxexclude().toString(),180, 190);
		g2.drawString(invoice.getTaxexclude().toString(),60, 320);
		g2.drawString(invoice.getTax().toString(),60, 333);
		g2.drawString(invoice.getTaxinclude().toString(),60, 346);
		

	}

	//連接列印機，彈出列印對話框

	public boolean starPrint(Invoice invoice) throws InterruptedException {

		try {
			
			this.invoice=invoice;
			
			
			PrintService[] ps = prnJob.lookupPrintServices();
		    PrintService printer = null;
		    for(int i=0; i<ps.length; i++) {
		    	System.out.println(ps[i].getName());
		      if(ps[i].getName().equals("WP-103s Ver.3.10")) {
		        printer = ps[i];
		        break;
		      }
		    }
		    
			PageFormat pageFormat = new PageFormat();
			pageFormat.setOrientation(PageFormat.PORTRAIT);
			prnJob.setPrintable(this);
			prnJob.setPrintService(printer);
			
			
			PrintServiceAttributeSet attributes = printer.getAttributes();
			for (Attribute a : attributes.toArray()) {
	            String name = a.getName();
	            String value = attributes.get(a.getClass()).toString();
	            System.out.println(name + " : " + value);
	         
	        }
			
			//消除預設邊界
			HashPrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();
			attr.add(new MediaPrintableArea(0f, 0f, (float)paperW/72f,(float)paperH/72f, MediaPrintableArea.INCH));
			//啟動列印工作
			prnJob.print(attr);
			
			
			
			return true;
			
		} catch (PrinterException ex) {
			ex.printStackTrace();
			System.err.println("列印錯誤：" + ex.toString());
			prnJob.cancel();
			return false;
		}

	}
	
	



}