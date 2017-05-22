package dao;

import java.util.ArrayList;

import beans.Invoice;

public interface InvoiceDao {

	public boolean addInvoice(Invoice facture);

	public boolean removeInvoice(Invoice facture);

	public Invoice findById(Long id);

	public ArrayList<Invoice> findAll(Long limit, Long offset);

}
