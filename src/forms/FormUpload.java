package forms;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import beans.Fichier;

public final class FormUpload {
	private static final String CHAMP_FICHIER = "fichier";
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	String webAppPath;

	public FormUpload(String Path) {
		this.webAppPath=Path;
	}

	public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	
	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}	
	public Fichier writeFile(HttpServletRequest request) {
		Date aujourdhui = new Date();
		DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
		Fichier fichier = new Fichier();
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						String name = new File(item.getName()).getName();
						fichier.setNom("assets\\img\\accountpicture"+ File.separator+ mediumDateFormat.format(aujourdhui).hashCode() + name);
						fichier.setCourtNom(name);
						item.write(new File(webAppPath+"assets\\img\\accountpicture"+ File.separator+ mediumDateFormat.format(aujourdhui).hashCode() + name));
					}
				}

				// File uploaded successfully
				request.setAttribute("message", "File Uploaded Successfully");
			} catch (Exception ex) {
				request.setAttribute("message", "File Upload Failed due to " + ex);
				setErreur(CHAMP_FICHIER, "Echec du téléchargment du à :"+ ex);
			}

		} else {
			request.setAttribute("message", "Sorry this Servlet only handles file upload request");
			setErreur(CHAMP_FICHIER, "Désolé mais ce formulaire est destiné à luploading de fichiers");

		}
		if (erreurs.isEmpty()) {
			resultat = "Succès de l'envoi du fichier.";
		} else {
			resultat = "Échec de l'envoi du fichier.";
		}
		return fichier;
	}
}