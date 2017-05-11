package beans;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

public class Paginateur {

	int begin = 0;
	int end = 10;
	String pagenum = "1";
	String pagination = "";
	ArrayList<Salable> liste = null;

	public String pagine(Integer total, ArrayList<Salable> liste, HttpServletRequest request) {
		begin = Integer.parseInt(request.getParameter("begin"));
		end = Integer.parseInt(request.getParameter("end"));
		if (total < 10) {
			this.liste = liste;

			pagination = "<a href=\"ServletPropose?action=afficherSousVendables&begin=1&end=" + total
					+ "&pagenum=1\">&laquo;</a>\n";
			pagination = pagination
					+ "<a class=\"active\" href=\"ServletPropose?action=afficherSousVendables&begin=0&end=" + total
					+ "&pagenum=1\">1</a>\n";
			pagination = pagination + " <a href=\"ServletPropose?action=afficherSousVendables&begin=0&end=" + total
					+ "&pagenum=1\">&raquo;</a>\n";

		} else {
			this.liste = liste;
			pagination = "<a href=\"ServletPropose?action=afficherSousVendables&begin=1&end=10&pagenum=1\">&laquo;</a>\n";
			int i = 0;
			int count = 0;
			int start = 0;
			int fin = 10;
			Integer page = 1;
			int finalStart = 0;
			int lastpage = 1;
			pagenum = request.getParameter("pagenum");
			while (i <= total) {
				count = count + 1;

				if (count == 10 || i == total - 1) {
					if (page.toString().equals(pagenum)) {
						pagination = pagination
								+ "<a class=\"active\" href=\"ServletPropose?action=afficherSousVendables&begin="
								+ start + "&end=" + (start + count - 1) + "&pagenum=" + page + "\">" + page + "</a>\n";

					} else {
						pagination = pagination + "<a href=\"ServletPropose?action=afficherSousVendables&begin=" + start
								+ "&end=" + (start + count) + "&pagenum=" + page + "\">" + page + "</a>\n";
					}
					fin = start + count - 1;
					finalStart = start;
					lastpage = page;
					page++;
					start = start + 10;
					count = 0;
				}
				i++;
			}
			pagination = pagination + " <a href=\"ServletPropose?action=afficherSousVendables&begin=" + finalStart
					+ "&end=" + fin + "&pagenum=" + lastpage + "\">&raquo;</a>\n";
		}
		return pagination;
	}

	public ArrayList<Salable> getListe() {
		return liste;
	}

	public void setListe(ArrayList<Salable> liste) {
		this.liste = liste;
	}

}
