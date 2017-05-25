package beans;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

public class Paginateur {

	static int begin = 0;
	static int end = 10;
	static String pagenum = "1";
	static String pagination = "";
	static ArrayList<? extends Object> listem = null;

	public static String pagine(Long total, ArrayList<? extends Object> liste, HttpServletRequest request,
			String path2) {
		String path = path2;
		if (request.getParameter("begin") != null) {
			begin = Integer.parseInt(request.getParameter("begin"));
		}
		if (request.getParameter("end") != null) {
			end = Integer.parseInt(request.getParameter("end"));
		}
		if (total < 10) {
			listem = liste;

			pagination = "<li class=\"preview hidden\"><a href=\"" + path + "?action=afficherSousVendables&begin=0&end="
					+ total
					+ "&pagenum=1\" aria-label=\"Previous`\"><span aria-hidden=\"true\"><i class=\"fa fa-angle-left\" aria-hidden=\"true\"></i></span></a></li>";
			pagination = pagination + "<li><a class=\"active\" href=\"" + path
					+ "?action=afficherSousVendables&begin=0&end=" + total + "&pagenum=1\">1</a></li>";
			pagination = pagination + "<li class=\"next\"><a href=\"" + path
					+ "?action=afficherSousVendables&begin=0&end=" + total
					+ "&pagenum=1\"><span aria-hidden=\"true\"><i class=\"fa fa-angle-right\" aria-hidden=\"true\"></i></span></a></li>";

		} else {
			listem = liste;
			pagination = "<li class=\"preview hidden\"><a href=\"" + path
					+ "?action=afficherSousVendables&begin=1&end=10&pagenum=1\" aria-label=\"Previous`\"><span aria-hidden=\"true\"><i class=\"fa fa-angle-left\" aria-hidden=\"true\"></i></span></a></li>";
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
						pagination = pagination + "<li><a class=\"active\" href=\"" + path
								+ "?action=afficherSousVendables&begin=" + start + "&end=" + (start + count - 1)
								+ "&pagenum=" + page + "\">" + page + "</a></li>";

					} else {
						pagination = pagination + "<a href=\"" + path + "?action=afficherSousVendables&begin=" + start
								+ "&end=" + (start + count) + "&pagenum=" + page + "\">" + page + "</a></li>";
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
			pagination = pagination + "<li class=\"next\"><a href=\"" + path + "?action=afficherSousVendables&begin="
					+ finalStart + "&end=" + fin + "&pagenum=" + lastpage
					+ "\" aria-label=\"Next\"><span aria-hidden=\"true\"><i class=\"fa fa-angle-right\" aria-hidden=\"true\"></i></span></a></li>";
		}
		return pagination;
	}

	public ArrayList<? extends Object> getListe() {
		return listem;
	}

	public void setListe(ArrayList<? extends Object> liste) {
		Paginateur.listem = liste;
	}

	public static int getBegin() {
		return begin;
	}

	public static void setBegin(int begin) {
		Paginateur.begin = begin;
	}

	public static int getEnd() {
		return end;
	}

	public static void setEnd(int end) {
		Paginateur.end = end;
	}

}
