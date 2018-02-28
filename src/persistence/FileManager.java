package persistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import controller.ConstantList;
import model.Bill;
import model.Store;

public class FileManager {

	public static void saveStores(String path, Store store) {
		Element root = new Element(ConstantList.STORE_LIST);
		Document doc = new Document(root);
		Element storeElem = new Element(ConstantList.STORE);
		Element id = new Element(ConstantList.ID).setText(String.valueOf(store.getId()));
		Element name = new Element(ConstantList.NAME).setText(store.getName());
		Element password = new Element(ConstantList.PASSWORD_XML).setText(store.getPassword());
		Element cellphone = new Element(ConstantList.CELLPHONE_XML).setText(store.getcellphone());
		Element photo = new Element(ConstantList.PHOTO).setText(store.getPhoto());
		storeElem.addContent(id);
		storeElem.addContent(name);
		storeElem.addContent(password);
		storeElem.addContent(cellphone);
		storeElem.addContent(photo);
		doc.getRootElement().addContent(storeElem);
		try {
			FileWriter fileWriter = new FileWriter(path);
			XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
			xmlOutputter.setFormat(Format.getCompactFormat());
			xmlOutputter.output(doc, fileWriter);
			fileWriter.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public static Store loadStore(String fileName) {
		Store store = null;
		SAXBuilder builder = new SAXBuilder();
		try {
			Document document = (Document) builder.build(new File(fileName));
			Element rootNode = (Element) ((org.jdom2.Document) document).getRootElement();
			List<Element> userFileList = ((org.jdom2.Element) rootNode).getChildren(ConstantList.STORE);
			Element matchElement = userFileList.get(0);
			int id = Integer.parseInt(matchElement.getChildTextTrim(ConstantList.ID));
			String name = matchElement.getChildTextTrim(ConstantList.NAME);
			String password = matchElement.getChildTextTrim(ConstantList.PASSWORD_XML);
			String cellphone = matchElement.getChildTextTrim(ConstantList.CELLPHONE_XML);
			String photo = matchElement.getChildTextTrim(ConstantList.PHOTO);
			store = new Store(id, name, password, cellphone, photo);
		} catch (IOException io) {
			System.err.println(io.getMessage());
		} catch (JDOMException jdomex) {
			System.err.println(jdomex.getMessage());
		}
		return store;
	}

	public static void saveBills(String path, ArrayList<Bill> list) {
		Element root = new Element(ConstantList.BILL_LIST);
		Document doc = new Document(root);
		for (Bill bill : list) {
			Element billElem = new Element(ConstantList.BILL);
			Element id = new Element(ConstantList.ID).setText(String.valueOf(bill.getId()));
			Element date = new Element(ConstantList.DATE).setText(bill.getStDate());
			Element saleType = new Element(ConstantList.SALE_TYPE_XML).setText(bill.getSaleType().toString());
			Element price = new Element(ConstantList.PRICE).setText(String.valueOf(bill.getPrice()));
			billElem.addContent(id);
			billElem.addContent(date);
			billElem.addContent(saleType);
			billElem.addContent(price);
			doc.getRootElement().addContent(billElem);
		}
		try {
			FileWriter fileWriter = new FileWriter(path);
			XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
			xmlOutputter.setFormat(Format.getCompactFormat());
			xmlOutputter.output(doc, fileWriter);
			fileWriter.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public static ArrayList<Bill> loadBills(String fileName) throws ParseException {
		ArrayList<Bill> bills = new ArrayList<Bill>();
		Bill bill = null;
		SAXBuilder builder = new SAXBuilder();
		try {
			Document document = (Document) builder.build(new File(fileName));
			Element rootNode = (Element) ((org.jdom2.Document) document).getRootElement();
			List<Element> billFileList = ((org.jdom2.Element) rootNode).getChildren(ConstantList.BILL);
			for (Element matchElement : billFileList) {
				int id = Integer.parseInt(matchElement.getChildTextTrim(ConstantList.ID));
				String date = matchElement.getChildTextTrim(ConstantList.DATE);
				String saleType = matchElement.getChildTextTrim(ConstantList.SALE_TYPE_XML);
				int price = Integer.parseInt(matchElement.getChildTextTrim(ConstantList.PRICE));
				bill = Store.createBill(id, date, saleType, price);
				bills.add(bill);
			}
		} catch (IOException io) {
			System.err.println(io.getMessage());
		} catch (JDOMException jdomex) {
			System.err.println(jdomex.getMessage());
		}
		return bills;
	}
}