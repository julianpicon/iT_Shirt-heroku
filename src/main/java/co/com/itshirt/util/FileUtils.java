package co.com.itshirt.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

/**
 * Utilidades manejo de archivos.
 * @author ja.picon
 *
 */
public abstract class FileUtils {
	
	public final static String RUTA_ESTAMPAS = "/resources/estampas/";
	public final static String RUTA_CAMISETAS = "/resources/camisetas/";
	
	/**
	 * Guardado de la imagen de la estampa.
	 * @param file
	 * @param idUsuario
	 * @param idEstampa
	 * @param request
	 */
	public static final void guardarArchivoEstampa(final MultipartFile file, Long idUsuario, Long idEstampa, HttpServletRequest request){
		if (!file.isEmpty()) {
            try {
                String uploadsDir = RUTA_ESTAMPAS + idUsuario;
                String realPathtoUploads =  request.getServletContext().getRealPath(uploadsDir);
                if(! new File(realPathtoUploads).exists())
                {
                    new File(realPathtoUploads).mkdir();
                }
                String filePath = realPathtoUploads + "/"+ idEstampa + "." + obtenerExtension(file);
                File dest = new File(filePath);
                file.transferTo(dest);
            } catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Guardado de la imagen del estilo de camiseta.
	 */
	public static final void guardarArchivoCamiseta(final MultipartFile file, Long idEstilo, HttpServletRequest request){
		if (!file.isEmpty()) {
            try {
                String uploadsDir = RUTA_CAMISETAS + idEstilo;
                String realPathtoUploads =  request.getServletContext().getRealPath(uploadsDir);
                String filePath = realPathtoUploads + "." + obtenerExtension(file);
                File dest = new File(filePath);
                file.transferTo(dest);
            } catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Obtiene la extensión de un archivo multipart.
	 */
	public static final String obtenerExtension(MultipartFile file) {
		 String[] fileFrags = file.getOriginalFilename().split("\\.");
         String extension = fileFrags[fileFrags.length-1];
         return extension;
	}

}
